package com.guardanis.applock.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.guardanis.applock.C2245R;
import com.guardanis.applock.views.LockCreationViewController;
import com.guardanis.applock.views.LockCreationViewController.Delegate;

public class LockCreationActivity extends AppCompatActivity implements Delegate {
    protected LockCreationViewController viewController;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2245R.layout.applock__activity_lock_creation);
        this.viewController = new LockCreationViewController(this, findViewById(C2245R.C2248id.pin__container));
        this.viewController.setDelegate(this);
        this.viewController.setupRootFlow();
    }

    public void onLockCreated() {
        setResult(-1);
        finish();
    }
}
