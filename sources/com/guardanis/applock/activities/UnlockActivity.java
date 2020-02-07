package com.guardanis.applock.activities;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.guardanis.applock.C2245R;
import com.guardanis.applock.views.UnlockViewController;
import com.guardanis.applock.views.UnlockViewController.Delegate;

public class UnlockActivity extends AppCompatActivity implements Delegate {
    public static final String INTENT_ALLOW_UNLOCKED_EXIT = "pin__allow_activity_exit";
    protected UnlockViewController viewController;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2245R.layout.applock__activity_unlock);
        this.viewController = new UnlockViewController(this, findViewById(C2245R.C2248id.pin__container));
        this.viewController.setAutoAuthorizationEnabled(false);
        this.viewController.setDelegate(this);
        this.viewController.setupRootFlow();
        this.viewController.setAutoAuthorizationEnabled(true);
    }

    public void onUnlockSuccessful() {
        setResult(-1);
        finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (keyEvent.getRepeatCount() == 0) {
            handleBackPressed();
        }
        return true;
    }

    public void onBackPressed() {
        handleBackPressed();
    }

    /* access modifiers changed from: protected */
    public void handleBackPressed() {
        if (!getIntent().getBooleanExtra(INTENT_ALLOW_UNLOCKED_EXIT, false)) {
            Toast.makeText(this, getString(C2245R.string.applock__toast_unlock_required), 1).show();
            return;
        }
        setResult(0);
        finish();
    }
}
