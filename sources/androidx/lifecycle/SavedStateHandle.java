package androidx.lifecycle;

import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.savedstate.SavedStateRegistry.SavedStateProvider;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class SavedStateHandle {
    private static final Class[] ACCEPTABLE_CLASSES;
    private static final String KEYS = "keys";
    private static final String VALUES = "values";
    private final Map<String, SavingStateLiveData<?>> mLiveDatas;
    final Map<String, Object> mRegular;
    private final SavedStateProvider mSavedStateProvider;

    static class SavingStateLiveData<T> extends MutableLiveData<T> {
        private SavedStateHandle mHandle;
        private String mKey;

        SavingStateLiveData(SavedStateHandle savedStateHandle, String str, T t) {
            super(t);
            this.mKey = str;
            this.mHandle = savedStateHandle;
        }

        SavingStateLiveData(SavedStateHandle savedStateHandle, String str) {
            this.mKey = str;
            this.mHandle = savedStateHandle;
        }

        public void setValue(T t) {
            SavedStateHandle savedStateHandle = this.mHandle;
            if (savedStateHandle != null) {
                savedStateHandle.mRegular.put(this.mKey, t);
            }
            super.setValue(t);
        }

        /* access modifiers changed from: 0000 */
        public void detach() {
            this.mHandle = null;
        }
    }

    public SavedStateHandle(@NonNull Map<String, Object> map) {
        this.mLiveDatas = new HashMap();
        this.mSavedStateProvider = new SavedStateProvider() {
            @NonNull
            public Bundle saveState() {
                Set<String> keySet = SavedStateHandle.this.mRegular.keySet();
                ArrayList arrayList = new ArrayList(keySet.size());
                ArrayList arrayList2 = new ArrayList(arrayList.size());
                for (String str : keySet) {
                    arrayList.add(str);
                    arrayList2.add(SavedStateHandle.this.mRegular.get(str));
                }
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(SavedStateHandle.KEYS, arrayList);
                bundle.putParcelableArrayList(SavedStateHandle.VALUES, arrayList2);
                return bundle;
            }
        };
        this.mRegular = new HashMap(map);
    }

    public SavedStateHandle() {
        this.mLiveDatas = new HashMap();
        this.mSavedStateProvider = new SavedStateProvider() {
            @NonNull
            public Bundle saveState() {
                Set<String> keySet = SavedStateHandle.this.mRegular.keySet();
                ArrayList arrayList = new ArrayList(keySet.size());
                ArrayList arrayList2 = new ArrayList(arrayList.size());
                for (String str : keySet) {
                    arrayList.add(str);
                    arrayList2.add(SavedStateHandle.this.mRegular.get(str));
                }
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(SavedStateHandle.KEYS, arrayList);
                bundle.putParcelableArrayList(SavedStateHandle.VALUES, arrayList2);
                return bundle;
            }
        };
        this.mRegular = new HashMap();
    }

    static SavedStateHandle createHandle(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
        if (bundle == null && bundle2 == null) {
            return new SavedStateHandle();
        }
        HashMap hashMap = new HashMap();
        if (bundle2 != null) {
            for (String str : bundle2.keySet()) {
                hashMap.put(str, bundle2.get(str));
            }
        }
        if (bundle == null) {
            return new SavedStateHandle(hashMap);
        }
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(KEYS);
        ArrayList parcelableArrayList2 = bundle.getParcelableArrayList(VALUES);
        if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
            throw new IllegalStateException("Invalid bundle passed as restored state");
        }
        for (int i = 0; i < parcelableArrayList.size(); i++) {
            hashMap.put((String) parcelableArrayList.get(i), parcelableArrayList2.get(i));
        }
        return new SavedStateHandle(hashMap);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public SavedStateProvider savedStateProvider() {
        return this.mSavedStateProvider;
    }

    @MainThread
    public boolean contains(@NonNull String str) {
        return this.mRegular.containsKey(str);
    }

    @MainThread
    @NonNull
    public <T> MutableLiveData<T> getLiveData(@Nullable String str) {
        SavingStateLiveData savingStateLiveData;
        MutableLiveData<T> mutableLiveData = (MutableLiveData) this.mLiveDatas.get(str);
        if (mutableLiveData != null) {
            return mutableLiveData;
        }
        if (this.mRegular.containsKey(str)) {
            savingStateLiveData = new SavingStateLiveData(this, str, this.mRegular.get(str));
        } else {
            savingStateLiveData = new SavingStateLiveData(this, str);
        }
        this.mLiveDatas.put(str, savingStateLiveData);
        return savingStateLiveData;
    }

    @MainThread
    @NonNull
    public Set<String> keys() {
        return Collections.unmodifiableSet(this.mRegular.keySet());
    }

    @MainThread
    @Nullable
    public <T> T get(@NonNull String str) {
        return this.mRegular.get(str);
    }

    @MainThread
    public <T> void set(@NonNull String str, @Nullable T t) {
        validateValue(t);
        MutableLiveData mutableLiveData = (MutableLiveData) this.mLiveDatas.get(str);
        if (mutableLiveData != null) {
            mutableLiveData.setValue(t);
        } else {
            this.mRegular.put(str, t);
        }
    }

    private static void validateValue(Object obj) {
        Class[] clsArr = ACCEPTABLE_CLASSES;
        int length = clsArr.length;
        int i = 0;
        while (i < length) {
            if (!clsArr[i].isInstance(obj)) {
                i++;
            } else {
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't put value with type ");
        sb.append(obj.getClass());
        sb.append(" into saved state");
        throw new IllegalArgumentException(sb.toString());
    }

    @MainThread
    @Nullable
    public <T> T remove(@NonNull String str) {
        T remove = this.mRegular.remove(str);
        SavingStateLiveData savingStateLiveData = (SavingStateLiveData) this.mLiveDatas.remove(str);
        if (savingStateLiveData != null) {
            savingStateLiveData.detach();
        }
        return remove;
    }

    static {
        Class[] clsArr = new Class[31];
        clsArr[0] = Boolean.TYPE;
        clsArr[1] = boolean[].class;
        clsArr[2] = Double.TYPE;
        clsArr[3] = double[].class;
        clsArr[4] = Integer.TYPE;
        clsArr[5] = int[].class;
        clsArr[6] = Long.TYPE;
        clsArr[7] = long[].class;
        clsArr[8] = Long.TYPE;
        clsArr[9] = long[].class;
        clsArr[10] = String.class;
        clsArr[11] = String[].class;
        clsArr[12] = Binder.class;
        clsArr[13] = Bundle.class;
        clsArr[14] = Byte.TYPE;
        clsArr[15] = byte[].class;
        clsArr[16] = Character.TYPE;
        clsArr[17] = char[].class;
        clsArr[18] = CharSequence.class;
        clsArr[19] = CharSequence[].class;
        clsArr[20] = ArrayList.class;
        clsArr[21] = Float.TYPE;
        clsArr[22] = float[].class;
        clsArr[23] = Parcelable.class;
        clsArr[24] = Parcelable[].class;
        clsArr[25] = Serializable.class;
        clsArr[26] = Short.TYPE;
        clsArr[27] = short[].class;
        clsArr[28] = SparseArray.class;
        clsArr[29] = VERSION.SDK_INT >= 21 ? Size.class : Integer.TYPE;
        clsArr[30] = VERSION.SDK_INT >= 21 ? SizeF.class : Integer.TYPE;
        ACCEPTABLE_CLASSES = clsArr;
    }
}
