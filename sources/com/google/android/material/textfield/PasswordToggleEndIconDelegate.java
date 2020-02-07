package com.google.android.material.textfield;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import androidx.appcompat.content.res.AppCompatResources;
import com.google.android.material.C1435R;
import com.google.android.material.textfield.TextInputLayout.OnEditTextAttachedListener;
import com.google.android.material.textfield.TextInputLayout.OnEndIconChangedListener;

class PasswordToggleEndIconDelegate extends EndIconDelegate {
    private final OnEditTextAttachedListener onEditTextAttachedListener = new OnEditTextAttachedListener() {
        public void onEditTextAttached(EditText editText) {
            PasswordToggleEndIconDelegate.this.textInputLayout.setEndIconVisible(true);
            PasswordToggleEndIconDelegate.this.endIconView.setChecked(true ^ PasswordToggleEndIconDelegate.this.hasPasswordTransformation());
            editText.removeTextChangedListener(PasswordToggleEndIconDelegate.this.textWatcher);
            editText.addTextChangedListener(PasswordToggleEndIconDelegate.this.textWatcher);
        }
    };
    private final OnEndIconChangedListener onEndIconChangedListener = new OnEndIconChangedListener() {
        public void onEndIconChanged(int i) {
            EditText editText = PasswordToggleEndIconDelegate.this.textInputLayout.getEditText();
            if (editText != null && i == 1) {
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    };
    /* access modifiers changed from: private */
    public final TextWatcher textWatcher = new TextWatcher() {
        public void afterTextChanged(Editable editable) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            PasswordToggleEndIconDelegate.this.endIconView.setChecked(!PasswordToggleEndIconDelegate.this.hasPasswordTransformation());
        }
    };

    PasswordToggleEndIconDelegate(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    /* access modifiers changed from: 0000 */
    public void initialize() {
        this.textInputLayout.setEndIconDrawable(AppCompatResources.getDrawable(this.context, C1435R.C1437drawable.design_password_eye));
        this.textInputLayout.setEndIconContentDescription(this.textInputLayout.getResources().getText(C1435R.string.password_toggle_content_description));
        this.textInputLayout.setEndIconOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                EditText editText = PasswordToggleEndIconDelegate.this.textInputLayout.getEditText();
                if (editText != null) {
                    int selectionEnd = editText.getSelectionEnd();
                    if (PasswordToggleEndIconDelegate.this.hasPasswordTransformation()) {
                        editText.setTransformationMethod(null);
                    } else {
                        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    editText.setSelection(selectionEnd);
                }
            }
        });
        this.textInputLayout.addOnEditTextAttachedListener(this.onEditTextAttachedListener);
        this.textInputLayout.addOnEndIconChangedListener(this.onEndIconChangedListener);
    }

    /* access modifiers changed from: private */
    public boolean hasPasswordTransformation() {
        EditText editText = this.textInputLayout.getEditText();
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }
}
