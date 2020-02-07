package com.bitcoin.mwallet.app.flows.sendv2.review;

import android.view.View;
import androidx.biometric.BiometricPrompt.AuthenticationCallback;
import androidx.biometric.BiometricPrompt.AuthenticationResult;
import com.bitcoin.mwallet.C1018R;
import com.ncorti.slidetoact.SlideToActView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, mo37405d2 = {"com/bitcoin/mwallet/app/flows/sendv2/review/ReviewView$showBiometricPrompt$biometricPrompt$1", "Landroidx/biometric/BiometricPrompt$AuthenticationCallback;", "onAuthenticationError", "", "errorCode", "", "errString", "", "onAuthenticationSucceeded", "result", "Landroidx/biometric/BiometricPrompt$AuthenticationResult;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReviewView.kt */
public final class ReviewView$showBiometricPrompt$biometricPrompt$1 extends AuthenticationCallback {
    final /* synthetic */ ReviewView this$0;

    ReviewView$showBiometricPrompt$biometricPrompt$1(ReviewView reviewView) {
        this.this$0 = reviewView;
    }

    public void onAuthenticationError(int i, @NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "errString");
        super.onAuthenticationError(i, charSequence);
        View view = this.this$0.getView();
        if (view != null) {
            SlideToActView slideToActView = (SlideToActView) view.findViewById(C1018R.C1021id.sendButton);
            if (slideToActView != null) {
                slideToActView.resetSlider();
            }
        }
    }

    public void onAuthenticationSucceeded(@NotNull AuthenticationResult authenticationResult) {
        Intrinsics.checkParameterIsNotNull(authenticationResult, "result");
        super.onAuthenticationSucceeded(authenticationResult);
        ((ReviewPresenter) this.this$0.getPresenter()).onSend();
    }
}
