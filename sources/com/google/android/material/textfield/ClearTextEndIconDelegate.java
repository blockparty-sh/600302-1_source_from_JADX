package com.google.android.material.textfield;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import androidx.appcompat.content.res.AppCompatResources;
import com.google.android.material.C1435R;
import com.google.android.material.textfield.TextInputLayout.OnEditTextAttachedListener;

class ClearTextEndIconDelegate extends EndIconDelegate {
    /* access modifiers changed from: private */
    public final TextWatcher clearTextEndIconTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            ClearTextEndIconDelegate.this.textInputLayout.setEndIconVisible(editable.length() > 0);
        }
    };
    private final OnEditTextAttachedListener clearTextOnEditTextAttachedListener = new OnEditTextAttachedListener() {
        public void onEditTextAttached(EditText editText) {
            ClearTextEndIconDelegate.this.textInputLayout.setEndIconVisible(!TextUtils.isEmpty(editText.getText()));
            editText.removeTextChangedListener(ClearTextEndIconDelegate.this.clearTextEndIconTextWatcher);
            editText.addTextChangedListener(ClearTextEndIconDelegate.this.clearTextEndIconTextWatcher);
        }
    };

    ClearTextEndIconDelegate(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    /* access modifiers changed from: 0000 */
    public void initialize() {
        this.textInputLayout.setEndIconDrawable(AppCompatResources.getDrawable(this.context, C1435R.C1437drawable.mtrl_ic_cancel));
        this.textInputLayout.setEndIconContentDescription(this.textInputLayout.getResources().getText(C1435R.string.clear_text_end_icon_content_description));
        this.textInputLayout.setEndIconOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ClearTextEndIconDelegate.this.textInputLayout.getEditText().setText(null);
            }
        });
        this.textInputLayout.addOnEditTextAttachedListener(this.clearTextOnEditTextAttachedListener);
    }
}
