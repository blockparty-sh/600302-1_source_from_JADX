package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import androidx.preference.Preference.BaseSavedState;

public class SeekBarPreference extends Preference {
    private static final String TAG = "SeekBarPreference";
    boolean mAdjustable;
    private int mMax;
    int mMin;
    SeekBar mSeekBar;
    private OnSeekBarChangeListener mSeekBarChangeListener;
    private int mSeekBarIncrement;
    private OnKeyListener mSeekBarKeyListener;
    int mSeekBarValue;
    private TextView mSeekBarValueTextView;
    private boolean mShowSeekBarValue;
    boolean mTrackingTouch;
    boolean mUpdatesContinuously;

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int mMax;
        int mMin;
        int mSeekBarValue;

        SavedState(Parcel parcel) {
            super(parcel);
            this.mSeekBarValue = parcel.readInt();
            this.mMin = parcel.readInt();
            this.mMax = parcel.readInt();
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mSeekBarValue);
            parcel.writeInt(this.mMin);
            parcel.writeInt(this.mMax);
        }
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mSeekBarChangeListener = new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (!z || (!SeekBarPreference.this.mUpdatesContinuously && SeekBarPreference.this.mTrackingTouch)) {
                    SeekBarPreference seekBarPreference = SeekBarPreference.this;
                    seekBarPreference.updateLabelValue(i + seekBarPreference.mMin);
                    return;
                }
                SeekBarPreference.this.syncValueInternal(seekBar);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                SeekBarPreference.this.mTrackingTouch = true;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                SeekBarPreference.this.mTrackingTouch = false;
                if (seekBar.getProgress() + SeekBarPreference.this.mMin != SeekBarPreference.this.mSeekBarValue) {
                    SeekBarPreference.this.syncValueInternal(seekBar);
                }
            }
        };
        this.mSeekBarKeyListener = new OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 0) {
                    return false;
                }
                if ((!SeekBarPreference.this.mAdjustable && (i == 21 || i == 22)) || i == 23 || i == 66) {
                    return false;
                }
                if (SeekBarPreference.this.mSeekBar != null) {
                    return SeekBarPreference.this.mSeekBar.onKeyDown(i, keyEvent);
                }
                Log.e(SeekBarPreference.TAG, "SeekBar view is null and hence cannot be adjusted.");
                return false;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0544R.styleable.SeekBarPreference, i, i2);
        this.mMin = obtainStyledAttributes.getInt(C0544R.styleable.SeekBarPreference_min, 0);
        setMax(obtainStyledAttributes.getInt(C0544R.styleable.SeekBarPreference_android_max, 100));
        setSeekBarIncrement(obtainStyledAttributes.getInt(C0544R.styleable.SeekBarPreference_seekBarIncrement, 0));
        this.mAdjustable = obtainStyledAttributes.getBoolean(C0544R.styleable.SeekBarPreference_adjustable, true);
        this.mShowSeekBarValue = obtainStyledAttributes.getBoolean(C0544R.styleable.SeekBarPreference_showSeekBarValue, false);
        this.mUpdatesContinuously = obtainStyledAttributes.getBoolean(C0544R.styleable.SeekBarPreference_updatesContinuously, false);
        obtainStyledAttributes.recycle();
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0544R.attr.seekBarPreferenceStyle);
    }

    public SeekBarPreference(Context context) {
        this(context, null);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.itemView.setOnKeyListener(this.mSeekBarKeyListener);
        this.mSeekBar = (SeekBar) preferenceViewHolder.findViewById(C0544R.C0547id.seekbar);
        this.mSeekBarValueTextView = (TextView) preferenceViewHolder.findViewById(C0544R.C0547id.seekbar_value);
        if (this.mShowSeekBarValue) {
            this.mSeekBarValueTextView.setVisibility(0);
        } else {
            this.mSeekBarValueTextView.setVisibility(8);
            this.mSeekBarValueTextView = null;
        }
        SeekBar seekBar = this.mSeekBar;
        if (seekBar == null) {
            Log.e(TAG, "SeekBar view is null in onBindViewHolder.");
            return;
        }
        seekBar.setOnSeekBarChangeListener(this.mSeekBarChangeListener);
        this.mSeekBar.setMax(this.mMax - this.mMin);
        int i = this.mSeekBarIncrement;
        if (i != 0) {
            this.mSeekBar.setKeyProgressIncrement(i);
        } else {
            this.mSeekBarIncrement = this.mSeekBar.getKeyProgressIncrement();
        }
        this.mSeekBar.setProgress(this.mSeekBarValue - this.mMin);
        updateLabelValue(this.mSeekBarValue);
        this.mSeekBar.setEnabled(isEnabled());
    }

    /* access modifiers changed from: protected */
    public void onSetInitialValue(Object obj) {
        if (obj == null) {
            obj = Integer.valueOf(0);
        }
        setValue(getPersistedInt(((Integer) obj).intValue()));
    }

    /* access modifiers changed from: protected */
    public Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInt(i, 0));
    }

    public int getMin() {
        return this.mMin;
    }

    public void setMin(int i) {
        int i2 = this.mMax;
        if (i > i2) {
            i = i2;
        }
        if (i != this.mMin) {
            this.mMin = i;
            notifyChanged();
        }
    }

    public final int getSeekBarIncrement() {
        return this.mSeekBarIncrement;
    }

    public final void setSeekBarIncrement(int i) {
        if (i != this.mSeekBarIncrement) {
            this.mSeekBarIncrement = Math.min(this.mMax - this.mMin, Math.abs(i));
            notifyChanged();
        }
    }

    public int getMax() {
        return this.mMax;
    }

    public final void setMax(int i) {
        int i2 = this.mMin;
        if (i < i2) {
            i = i2;
        }
        if (i != this.mMax) {
            this.mMax = i;
            notifyChanged();
        }
    }

    public boolean isAdjustable() {
        return this.mAdjustable;
    }

    public void setAdjustable(boolean z) {
        this.mAdjustable = z;
    }

    public boolean getUpdatesContinuously() {
        return this.mUpdatesContinuously;
    }

    public void setUpdatesContinuously(boolean z) {
        this.mUpdatesContinuously = z;
    }

    public boolean getShowSeekBarValue() {
        return this.mShowSeekBarValue;
    }

    public void setShowSeekBarValue(boolean z) {
        this.mShowSeekBarValue = z;
        notifyChanged();
    }

    private void setValueInternal(int i, boolean z) {
        int i2 = this.mMin;
        if (i < i2) {
            i = i2;
        }
        int i3 = this.mMax;
        if (i > i3) {
            i = i3;
        }
        if (i != this.mSeekBarValue) {
            this.mSeekBarValue = i;
            updateLabelValue(this.mSeekBarValue);
            persistInt(i);
            if (z) {
                notifyChanged();
            }
        }
    }

    public int getValue() {
        return this.mSeekBarValue;
    }

    public void setValue(int i) {
        setValueInternal(i, true);
    }

    /* access modifiers changed from: 0000 */
    public void syncValueInternal(SeekBar seekBar) {
        int progress = this.mMin + seekBar.getProgress();
        if (progress == this.mSeekBarValue) {
            return;
        }
        if (callChangeListener(Integer.valueOf(progress))) {
            setValueInternal(progress, false);
            return;
        }
        seekBar.setProgress(this.mSeekBarValue - this.mMin);
        updateLabelValue(this.mSeekBarValue);
    }

    /* access modifiers changed from: 0000 */
    public void updateLabelValue(int i) {
        TextView textView = this.mSeekBarValueTextView;
        if (textView != null) {
            textView.setText(String.valueOf(i));
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (isPersistent()) {
            return onSaveInstanceState;
        }
        SavedState savedState = new SavedState(onSaveInstanceState);
        savedState.mSeekBarValue = this.mSeekBarValue;
        savedState.mMin = this.mMin;
        savedState.mMax = this.mMax;
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!parcelable.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mSeekBarValue = savedState.mSeekBarValue;
        this.mMin = savedState.mMin;
        this.mMax = savedState.mMax;
        notifyChanged();
    }
}
