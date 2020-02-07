package com.guardanis.applock.pin;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView.OnEditorActionListener;
import com.guardanis.applock.C2245R;
import com.guardanis.applock.pin.PINItemAnimator.ItemAnimationDirection;
import java.util.WeakHashMap;

public class PINInputView extends LinearLayout implements TextWatcher {
    private WeakHashMap<PINItemView, PINItemAnimator> animators = new WeakHashMap<>();
    private EditText editText;
    private int inputViewsCount = 10;
    private Paint itemBackgroundPaint;
    private Paint itemTextPaint;
    private String lastText = "";
    private String passwordCharacter;
    private boolean passwordCharactersEnabled = true;
    private PINItemView[] pinItemViews;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public PINInputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        setWillNotDraw(false);
        setupEditText();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C2245R.styleable.PINInputView);
        this.itemTextPaint = new Paint();
        this.itemTextPaint.setColor(obtainStyledAttributes.getColor(C2245R.styleable.PINInputView_pinTextColor, getResources().getColor(C2245R.C2246color.applock__item_text)));
        this.itemBackgroundPaint = new Paint();
        this.itemBackgroundPaint.setColor(obtainStyledAttributes.getColor(C2245R.styleable.PINInputView_pinBackgroundColor, getResources().getColor(C2245R.C2246color.applock__item_background)));
        this.inputViewsCount = getResources().getInteger(C2245R.integer.applock__input_pin_item_count);
        this.passwordCharacter = getResources().getString(C2245R.string.applock__password_char);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void setupEditText() {
        removeAllViews();
        this.editText = inflateFakeEditText();
        this.editText.addTextChangedListener(this);
        addView(this.editText);
    }

    /* access modifiers changed from: protected */
    public EditText inflateFakeEditText() {
        return (EditText) LayoutInflater.from(getContext()).inflate(C2245R.layout.applock__fake_edit_text, this, false);
    }

    public PINInputView setInputViewsCount(int i) {
        this.inputViewsCount = i;
        return reset();
    }

    public PINInputView reset() {
        String str = "";
        this.lastText = str;
        this.editText.setText(str);
        if (this.pinItemViews != null) {
            animateLastOut();
        }
        return this;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return super.onTouchEvent(motionEvent);
        }
        ensureKeyboardVisible();
        return true;
    }

    public void ensureKeyboardVisible() {
        this.editText.requestFocus();
        ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this.editText, 0);
    }

    public void onDraw(Canvas canvas) {
        PINItemView[] pINItemViewArr = this.pinItemViews;
        if (pINItemViewArr == null || pINItemViewArr.length != this.inputViewsCount) {
            setupItemViews(canvas);
        }
        String obj = this.editText.getText().toString();
        int i = 0;
        while (i < this.pinItemViews.length) {
            String str = i < obj.length() ? this.passwordCharactersEnabled ? this.passwordCharacter : obj.substring(i, i + 1) : "";
            this.pinItemViews[i].draw(canvas, str);
            i++;
        }
    }

    private void setupItemViews(Canvas canvas) {
        this.pinItemViews = new PINItemView[this.inputViewsCount];
        int width = canvas.getWidth() / this.inputViewsCount;
        int min = Math.min(width / 2, canvas.getHeight() / 2);
        int[] iArr = {(int) (((float) min) * Float.parseFloat(getResources().getString(C2245R.string.applock__empty_item_min_size_percent))), min};
        this.itemTextPaint.setTextSize((float) ((int) (((double) min) * 0.85d)));
        for (int i = 0; i < this.pinItemViews.length; i++) {
            this.pinItemViews[i] = new PINItemView(getPositionInCanvas(canvas, i, width), iArr, this.itemTextPaint, this.itemBackgroundPaint);
        }
    }

    private float[] getPositionInCanvas(Canvas canvas, int i, int i2) {
        return new float[]{(float) ((i * i2) + (i2 / 2)), (float) (canvas.getHeight() / 2)};
    }

    public void afterTextChanged(Editable editable) {
        if (editable != null && this.lastText != null) {
            String obj = editable.toString();
            if (this.inputViewsCount < obj.length()) {
                this.editText.setText(this.lastText);
                EditText editText2 = this.editText;
                editText2.setSelection(editText2.getText().length());
            } else if (obj.length() < this.lastText.length()) {
                animateLastOut();
                this.lastText = obj;
            } else if (this.lastText.length() < obj.length()) {
                animateLastIn();
                this.lastText = obj;
            }
        }
    }

    private void animateLastOut() {
        int length = this.editText.getText().toString().length();
        for (int length2 = this.pinItemViews.length - 1; length <= length2; length2--) {
            if (!this.pinItemViews[length2].isAnimatedOut()) {
                animate(this.pinItemViews[length2], ItemAnimationDirection.OUT);
            }
        }
    }

    private void animateLastIn() {
        animate(this.pinItemViews[this.editText.getText().toString().length() - 1], ItemAnimationDirection.IN);
    }

    private void animate(PINItemView pINItemView, ItemAnimationDirection itemAnimationDirection) {
        cancelPreviousAnimation(pINItemView);
        pINItemView.setAnimationDirection(itemAnimationDirection);
        PINItemAnimator pINItemAnimator = new PINItemAnimator(this, pINItemView, itemAnimationDirection);
        this.animators.put(pINItemView, pINItemAnimator);
        pINItemAnimator.start();
    }

    private void cancelPreviousAnimation(PINItemView pINItemView) {
        try {
            ((PINItemAnimator) this.animators.get(pINItemView)).cancel();
            this.animators.put(pINItemView, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getText() {
        return this.editText.getText().toString();
    }

    public void setOnEditorActionListener(OnEditorActionListener onEditorActionListener) {
        this.editText.setOnEditorActionListener(onEditorActionListener);
    }

    public void setPasswordCharactersEnabled(boolean z) {
        this.passwordCharactersEnabled = z;
    }

    public void setPasswordCharacter(String str) {
        this.passwordCharacter = str;
    }

    public boolean matchesRequiredPINLength(String str) {
        return str.length() == this.inputViewsCount;
    }
}
