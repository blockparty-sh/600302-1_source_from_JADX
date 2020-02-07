package com.github.curioustechizen.ago;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.widget.TextView;
import java.lang.ref.WeakReference;

public class RelativeTimeTextView extends TextView {
    private static final long INITIAL_UPDATE_INTERVAL = 60000;
    private boolean isUpdateTaskRunning = false;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    private String mPrefix;
    private long mReferenceTime;
    private String mSuffix;
    private UpdateTimeRunnable mUpdateTimeTask;

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        /* access modifiers changed from: private */
        public long referenceTime;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeLong(this.referenceTime);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.referenceTime = parcel.readLong();
        }
    }

    private static class UpdateTimeRunnable implements Runnable {
        private long mRefTime;
        private final WeakReference<RelativeTimeTextView> weakRefRttv;

        UpdateTimeRunnable(RelativeTimeTextView relativeTimeTextView, long j) {
            this.mRefTime = j;
            this.weakRefRttv = new WeakReference<>(relativeTimeTextView);
        }

        /* access modifiers changed from: 0000 */
        public boolean isDetached() {
            return this.weakRefRttv.get() == null;
        }

        /* access modifiers changed from: 0000 */
        public void detach() {
            this.weakRefRttv.clear();
        }

        public void run() {
            RelativeTimeTextView relativeTimeTextView = (RelativeTimeTextView) this.weakRefRttv.get();
            if (relativeTimeTextView != null) {
                long abs = Math.abs(System.currentTimeMillis() - this.mRefTime);
                long j = RelativeTimeTextView.INITIAL_UPDATE_INTERVAL;
                if (abs > 604800000) {
                    j = 604800000;
                } else if (abs > 86400000) {
                    j = 86400000;
                } else if (abs > 3600000) {
                    j = 3600000;
                }
                relativeTimeTextView.updateTextDisplay();
                relativeTimeTextView.mHandler.postDelayed(this, j);
            }
        }
    }

    public RelativeTimeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public RelativeTimeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C1369R.styleable.RelativeTimeTextView, 0, 0);
        try {
            String string = obtainStyledAttributes.getString(C1369R.styleable.RelativeTimeTextView_reference_time);
            this.mPrefix = obtainStyledAttributes.getString(C1369R.styleable.RelativeTimeTextView_relative_time_prefix);
            this.mSuffix = obtainStyledAttributes.getString(C1369R.styleable.RelativeTimeTextView_relative_time_suffix);
            String str = "";
            this.mPrefix = this.mPrefix == null ? str : this.mPrefix;
            if (this.mSuffix != null) {
                str = this.mSuffix;
            }
            this.mSuffix = str;
            try {
                this.mReferenceTime = Long.valueOf(string).longValue();
            } catch (NumberFormatException unused) {
                this.mReferenceTime = -1;
            }
            setReferenceTime(this.mReferenceTime);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    @Deprecated
    public String getPrefix() {
        return this.mPrefix;
    }

    @Deprecated
    public void setPrefix(String str) {
        this.mPrefix = str;
        updateTextDisplay();
    }

    @Deprecated
    public String getSuffix() {
        return this.mSuffix;
    }

    @Deprecated
    public void setSuffix(String str) {
        this.mSuffix = str;
        updateTextDisplay();
    }

    public void setReferenceTime(long j) {
        this.mReferenceTime = j;
        stopTaskForPeriodicallyUpdatingRelativeTime();
        initUpdateTimeTask();
        startTaskForPeriodicallyUpdatingRelativeTime();
        updateTextDisplay();
    }

    /* access modifiers changed from: private */
    public void updateTextDisplay() {
        if (this.mReferenceTime != -1) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mPrefix);
            sb.append(getRelativeTimeDisplayString(this.mReferenceTime, System.currentTimeMillis()));
            sb.append(this.mSuffix);
            setText(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public CharSequence getRelativeTimeDisplayString(long j, long j2) {
        long j3 = j2 - j;
        if (j3 < 0 || j3 > INITIAL_UPDATE_INTERVAL) {
            return DateUtils.getRelativeTimeSpanString(this.mReferenceTime, j2, INITIAL_UPDATE_INTERVAL, 262144);
        }
        return getResources().getString(C1369R.string.just_now);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        startTaskForPeriodicallyUpdatingRelativeTime();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopTaskForPeriodicallyUpdatingRelativeTime();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 8 || i == 4) {
            stopTaskForPeriodicallyUpdatingRelativeTime();
        } else {
            startTaskForPeriodicallyUpdatingRelativeTime();
        }
    }

    private void startTaskForPeriodicallyUpdatingRelativeTime() {
        if (this.mUpdateTimeTask.isDetached()) {
            initUpdateTimeTask();
        }
        this.mHandler.post(this.mUpdateTimeTask);
        this.isUpdateTaskRunning = true;
    }

    private void initUpdateTimeTask() {
        this.mUpdateTimeTask = new UpdateTimeRunnable(this, this.mReferenceTime);
    }

    private void stopTaskForPeriodicallyUpdatingRelativeTime() {
        if (this.isUpdateTaskRunning) {
            this.mUpdateTimeTask.detach();
            this.mHandler.removeCallbacks(this.mUpdateTimeTask);
            this.isUpdateTaskRunning = false;
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.referenceTime = this.mReferenceTime;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.mReferenceTime = savedState.referenceTime;
        super.onRestoreInstanceState(savedState.getSuperState());
    }
}
