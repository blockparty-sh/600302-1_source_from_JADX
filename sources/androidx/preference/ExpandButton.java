package androidx.preference;

import android.content.Context;
import android.text.TextUtils;
import androidx.room.RoomDatabase;
import java.util.ArrayList;
import java.util.List;

final class ExpandButton extends Preference {
    private long mId;

    ExpandButton(Context context, List<Preference> list, long j) {
        super(context);
        initLayout();
        setSummary(list);
        this.mId = j + 1000000;
    }

    private void initLayout() {
        setLayoutResource(C0544R.layout.expand_button);
        setIcon(C0544R.C0546drawable.ic_arrow_down_24dp);
        setTitle(C0544R.string.expand_button_title);
        setOrder(RoomDatabase.MAX_BIND_PARAMETER_CNT);
    }

    private void setSummary(List<Preference> list) {
        ArrayList arrayList = new ArrayList();
        CharSequence charSequence = null;
        for (Preference preference : list) {
            CharSequence title = preference.getTitle();
            boolean z = preference instanceof PreferenceGroup;
            if (z && !TextUtils.isEmpty(title)) {
                arrayList.add((PreferenceGroup) preference);
            }
            if (arrayList.contains(preference.getParent())) {
                if (z) {
                    arrayList.add((PreferenceGroup) preference);
                }
            } else if (!TextUtils.isEmpty(title)) {
                if (charSequence == null) {
                    charSequence = title;
                } else {
                    charSequence = getContext().getString(C0544R.string.summary_collapsed_preference_list, new Object[]{charSequence, title});
                }
            }
        }
        setSummary(charSequence);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.setDividerAllowedAbove(false);
    }

    /* access modifiers changed from: 0000 */
    public long getId() {
        return this.mId;
    }
}
