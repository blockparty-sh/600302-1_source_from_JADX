package com.guardanis.applock.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.guardanis.applock.AppLock;

public class LockableAppCompatActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        AppLock.onActivityResumed(this);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 9371 && i2 == 0) {
            handleUnlockActivityReturnFailure();
        }
    }

    /* access modifiers changed from: protected */
    public void handleUnlockActivityReturnFailure() {
        setResult(0);
        finish();
    }
}
