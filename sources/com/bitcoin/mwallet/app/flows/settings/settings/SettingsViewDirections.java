package com.bitcoin.mwallet.app.flows.settings.settings;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsViewDirections;", "", "()V", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SettingsViewDirections.kt */
public final class SettingsViewDirections {
    public static final Companion Companion = new Companion(null);

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsViewDirections$Companion;", "", "()V", "actionSettingsViewToContactsView", "Landroidx/navigation/NavDirections;", "actionSettingsViewToCurrenciesView", "actionSettingsViewToLanguageView", "actionSettingsViewToNetworkFeeView", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SettingsViewDirections.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final NavDirections actionSettingsViewToContactsView() {
            return new ActionOnlyNavDirections(C1018R.C1021id.action_settingsView_to_contactsView);
        }

        @NotNull
        public final NavDirections actionSettingsViewToNetworkFeeView() {
            return new ActionOnlyNavDirections(C1018R.C1021id.action_settingsView_to_networkFeeView);
        }

        @NotNull
        public final NavDirections actionSettingsViewToCurrenciesView() {
            return new ActionOnlyNavDirections(C1018R.C1021id.action_settingsView_to_currenciesView);
        }

        @NotNull
        public final NavDirections actionSettingsViewToLanguageView() {
            return new ActionOnlyNavDirections(C1018R.C1021id.action_settingsView_to_languageView);
        }
    }

    private SettingsViewDirections() {
    }
}
