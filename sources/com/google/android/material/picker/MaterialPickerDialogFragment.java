package com.google.android.material.picker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.C1435R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.resources.MaterialAttributes;
import java.text.SimpleDateFormat;
import java.util.Locale;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class MaterialPickerDialogFragment<S> extends DialogFragment {
    private static final String CALENDAR_BOUNDS_KEY = "CALENDAR_BOUNDS_KEY";
    @VisibleForTesting
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final Object CANCEL_BUTTON_TAG = "CANCEL_BUTTON_TAG";
    @VisibleForTesting
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final Object CONFIRM_BUTTON_TAG = "CONFIRM_BUTTON_TAG";
    public static final CalendarBounds DEFAULT_BOUNDS = CalendarBounds.create(DEFAULT_START, DEFAULT_END);
    public static final Month DEFAULT_END = Month.create(2100, 11);
    public static final Month DEFAULT_START = Month.create(1900, 0);
    private static final String GRID_SELECTOR_KEY = "GRID_SELECTOR_KEY";
    private static final String THEME_RES_ID_KEY = "THEME_RES_ID";
    private static final String TITLE_TEXT_RES_ID_KEY = "TITLE_TEXT_RES_ID_KEY";
    private CalendarBounds calendarBounds;
    private GridSelector<S> gridSelector;
    private TextView header;
    /* access modifiers changed from: private */
    public MaterialCalendar<S> materialCalendar;
    /* access modifiers changed from: private */
    public S selection;
    private SimpleDateFormat simpleDateFormat;
    @AttrRes
    private int themeResId;
    @StringRes
    private int titleTextResId;

    /* access modifiers changed from: protected */
    public abstract GridSelector<S> createGridSelector();

    /* access modifiers changed from: protected */
    public abstract int getDefaultThemeAttr();

    /* access modifiers changed from: protected */
    public abstract String getHeaderText(@Nullable S s);

    protected static void addArgsToBundle(Bundle bundle, int i, CalendarBounds calendarBounds2, @StringRes int i2) {
        bundle.putInt(THEME_RES_ID_KEY, i);
        bundle.putParcelable(CALENDAR_BOUNDS_KEY, calendarBounds2);
        bundle.putInt(TITLE_TEXT_RES_ID_KEY, i2);
    }

    @StyleRes
    private static int getThemeResource(Context context, int i, int i2) {
        return i2 != 0 ? i2 : MaterialAttributes.resolveOrThrow(context, i, MaterialPickerDialogFragment.class.getCanonicalName());
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(THEME_RES_ID_KEY, this.themeResId);
        bundle.putParcelable(GRID_SELECTOR_KEY, this.gridSelector);
        bundle.putParcelable(CALENDAR_BOUNDS_KEY, this.calendarBounds);
        bundle.putInt(TITLE_TEXT_RES_ID_KEY, this.titleTextResId);
    }

    public final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.simpleDateFormat = new SimpleDateFormat(getResources().getString(C1435R.string.mtrl_picker_date_format), Locale.getDefault());
        if (bundle == null) {
            bundle = getArguments();
        }
        this.themeResId = getThemeResource(getContext(), getDefaultThemeAttr(), bundle.getInt(THEME_RES_ID_KEY));
        this.gridSelector = (GridSelector) bundle.getParcelable(GRID_SELECTOR_KEY);
        this.calendarBounds = (CalendarBounds) bundle.getParcelable(CALENDAR_BOUNDS_KEY);
        this.titleTextResId = bundle.getInt(TITLE_TEXT_RES_ID_KEY);
        if (this.gridSelector == null) {
            this.gridSelector = createGridSelector();
        }
        this.materialCalendar = MaterialCalendar.newInstance(this.gridSelector, this.themeResId, this.calendarBounds);
    }

    public final Dialog onCreateDialog(@Nullable Bundle bundle) {
        return new Dialog(requireContext(), this.themeResId);
    }

    @NonNull
    public final View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(C1435R.layout.mtrl_picker_dialog, viewGroup);
        this.header = (TextView) inflate.findViewById(C1435R.C1438id.mtrl_picker_header_text);
        ((TextView) inflate.findViewById(C1435R.C1438id.mtrl_picker_title_text)).setText(this.titleTextResId);
        MaterialButton materialButton = (MaterialButton) inflate.findViewById(C1435R.C1438id.confirm_button);
        materialButton.setTag(CONFIRM_BUTTON_TAG);
        materialButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MaterialPickerDialogFragment materialPickerDialogFragment = MaterialPickerDialogFragment.this;
                materialPickerDialogFragment.selection = materialPickerDialogFragment.materialCalendar.getSelection();
                MaterialPickerDialogFragment.this.dismiss();
            }
        });
        MaterialButton materialButton2 = (MaterialButton) inflate.findViewById(C1435R.C1438id.cancel_button);
        materialButton2.setTag(CANCEL_BUTTON_TAG);
        materialButton2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MaterialPickerDialogFragment.this.selection = null;
                MaterialPickerDialogFragment.this.dismiss();
            }
        });
        return inflate;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.replace(C1435R.C1438id.mtrl_calendar_frame, this.materialCalendar);
        beginTransaction.commit();
    }

    public void onStart() {
        super.onStart();
        updateHeader(this.materialCalendar.getSelection());
        this.materialCalendar.addOnSelectionChangedListener(new OnSelectionChangedListener<S>() {
            public void onSelectionChanged(S s) {
                MaterialPickerDialogFragment.this.updateHeader(s);
            }
        });
    }

    public void onStop() {
        this.materialCalendar.clearOnSelectionChangedListeners();
        super.onStop();
    }

    public final void onDismiss(@NonNull DialogInterface dialogInterface) {
        ViewGroup viewGroup = (ViewGroup) getView();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    @Nullable
    public final S getSelection() {
        return this.selection;
    }

    public final void setSimpleDateFormat(SimpleDateFormat simpleDateFormat2) {
        this.simpleDateFormat = simpleDateFormat2;
    }

    public final SimpleDateFormat getSimpleDateFormat() {
        return this.simpleDateFormat;
    }

    @Nullable
    public final MaterialCalendar<? extends S> getMaterialCalendar() {
        return this.materialCalendar;
    }

    /* access modifiers changed from: private */
    public void updateHeader(S s) {
        this.header.setText(getHeaderText(s));
    }
}
