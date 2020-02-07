package com.facebook.stetho.inspector.elements.android;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.AttributeAccumulator;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;

final class TextViewDescriptor extends AbstractChainedDescriptor<TextView> {
    private static final String TEXT_ATTRIBUTE_NAME = "text";
    private final Map<TextView, ElementContext> mElementToContextMap = Collections.synchronizedMap(new IdentityHashMap());

    private final class ElementContext implements TextWatcher {
        private TextView mElement;

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        private ElementContext() {
        }

        public void hook(TextView textView) {
            this.mElement = (TextView) Util.throwIfNull(textView);
            this.mElement.addTextChangedListener(this);
        }

        public void unhook() {
            TextView textView = this.mElement;
            if (textView != null) {
                textView.removeTextChangedListener(this);
                this.mElement = null;
            }
        }

        public void afterTextChanged(Editable editable) {
            String str = "text";
            if (editable.length() == 0) {
                TextViewDescriptor.this.getHost().onAttributeRemoved(this.mElement, str);
            } else {
                TextViewDescriptor.this.getHost().onAttributeModified(this.mElement, str, editable.toString());
            }
        }
    }

    TextViewDescriptor() {
    }

    /* access modifiers changed from: protected */
    public void onHook(TextView textView) {
        ElementContext elementContext = new ElementContext();
        elementContext.hook(textView);
        this.mElementToContextMap.put(textView, elementContext);
    }

    /* access modifiers changed from: protected */
    public void onUnhook(TextView textView) {
        ((ElementContext) this.mElementToContextMap.remove(textView)).unhook();
    }

    /* access modifiers changed from: protected */
    public void onGetAttributes(TextView textView, AttributeAccumulator attributeAccumulator) {
        CharSequence text = textView.getText();
        if (text != null && text.length() != 0) {
            attributeAccumulator.store("text", text.toString());
        }
    }
}
