package com.bitcoin.mwallet.app.flows.settings.settings;

import androidx.navigation.NavDirections;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.RouterBase;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004J\u0006\u0010\t\u001a\u00020\u0004J\u0006\u0010\n\u001a\u00020\u0004¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/settings/SettingsRouter;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "()V", "toAboutInfo", "", "toContacts", "toCurrencySelect", "toLanguageSelect", "toNetworkFee", "toPrevious", "toSettings", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SettingsRouter.kt */
public final class SettingsRouter extends RouterBase {
    public final void toAboutInfo() {
    }

    public final void toSettings() {
    }

    public final void toContacts() {
        NavDirections actionSettingsViewToContactsView = SettingsViewDirections.Companion.actionSettingsViewToContactsView();
        actionSettingsViewToContactsView.getArguments();
        getNavController().navigate(actionSettingsViewToContactsView);
    }

    public final void toNetworkFee() {
        getNavController().navigate((int) C1018R.C1021id.action_settingsView_to_networkFeeView);
    }

    public final void toCurrencySelect() {
        getNavController().navigate((int) C1018R.C1021id.action_settingsView_to_currenciesView);
    }

    public final void toLanguageSelect() {
        getNavController().navigate((int) C1018R.C1021id.action_settingsView_to_languageView);
    }

    public final void toPrevious() {
        getNavController().popBackStack();
    }
}
