package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.res.TypedArrayUtils;

public class SwitchPreferenceCompat extends TwoStatePreference {
    private final Listener mListener;
    private CharSequence mSwitchOff;
    private CharSequence mSwitchOn;

    private class Listener implements OnCheckedChangeListener {
        Listener() {
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!SwitchPreferenceCompat.this.callChangeListener(Boolean.valueOf(z))) {
                compoundButton.setChecked(!z);
            } else {
                SwitchPreferenceCompat.this.setChecked(z);
            }
        }
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mListener = new Listener();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0544R.styleable.SwitchPreferenceCompat, i, i2);
        setSummaryOn((CharSequence) TypedArrayUtils.getString(obtainStyledAttributes, C0544R.styleable.SwitchPreferenceCompat_summaryOn, C0544R.styleable.SwitchPreferenceCompat_android_summaryOn));
        setSummaryOff((CharSequence) TypedArrayUtils.getString(obtainStyledAttributes, C0544R.styleable.SwitchPreferenceCompat_summaryOff, C0544R.styleable.SwitchPreferenceCompat_android_summaryOff));
        setSwitchTextOn((CharSequence) TypedArrayUtils.getString(obtainStyledAttributes, C0544R.styleable.SwitchPreferenceCompat_switchTextOn, C0544R.styleable.SwitchPreferenceCompat_android_switchTextOn));
        setSwitchTextOff((CharSequence) TypedArrayUtils.getString(obtainStyledAttributes, C0544R.styleable.SwitchPreferenceCompat_switchTextOff, C0544R.styleable.SwitchPreferenceCompat_android_switchTextOff));
        setDisableDependentsState(TypedArrayUtils.getBoolean(obtainStyledAttributes, C0544R.styleable.SwitchPreferenceCompat_disableDependentsState, C0544R.styleable.SwitchPreferenceCompat_android_disableDependentsState, false));
        obtainStyledAttributes.recycle();
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0544R.attr.switchPreferenceCompatStyle);
    }

    public SwitchPreferenceCompat(Context context) {
        this(context, null);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        syncSwitchView(preferenceViewHolder.findViewById(C0544R.C0547id.switchWidget));
        syncSummaryView(preferenceViewHolder);
    }

    public void setSwitchTextOn(CharSequence charSequence) {
        this.mSwitchOn = charSequence;
        notifyChanged();
    }

    public void setSwitchTextOff(CharSequence charSequence) {
        this.mSwitchOff = charSequence;
        notifyChanged();
    }

    public CharSequence getSwitchTextOn() {
        return this.mSwitchOn;
    }

    public void setSwitchTextOn(int i) {
        setSwitchTextOn((CharSequence) getContext().getString(i));
    }

    public CharSequence getSwitchTextOff() {
        return this.mSwitchOff;
    }

    public void setSwitchTextOff(int i) {
        setSwitchTextOff((CharSequence) getContext().getString(i));
    }

    /* access modifiers changed from: protected */
    @RestrictTo({Scope.LIBRARY})
    public void performClick(View view) {
        super.performClick(view);
        syncViewIfAccessibilityEnabled(view);
    }

    private void syncViewIfAccessibilityEnabled(View view) {
        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            syncSwitchView(view.findViewById(C0544R.C0547id.switchWidget));
            syncSummaryView(view.findViewById(16908304));
        }
    }

    private void syncSwitchView(View view) {
        boolean z = view instanceof SwitchCompat;
        if (z) {
            ((SwitchCompat) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.mChecked);
        }
        if (z) {
            SwitchCompat switchCompat = (SwitchCompat) view;
            switchCompat.setTextOn(this.mSwitchOn);
            switchCompat.setTextOff(this.mSwitchOff);
            switchCompat.setOnCheckedChangeListener(this.mListener);
        }
    }
}
