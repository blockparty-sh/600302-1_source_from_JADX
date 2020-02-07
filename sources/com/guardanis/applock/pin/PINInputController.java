package com.guardanis.applock.pin;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import java.lang.ref.WeakReference;

public class PINInputController implements OnEditorActionListener {
    private WeakReference<InputEventListener> eventListener = new WeakReference<>(null);
    private WeakReference<PINInputView> inputView;

    public interface InputEventListener {
        void onInputEntered(String str);
    }

    public PINInputController(PINInputView pINInputView) {
        this.inputView = new WeakReference<>(pINInputView);
        pINInputView.setOnEditorActionListener(this);
    }

    public PINInputController ensureKeyboardVisible() {
        final PINInputView pINInputView = (PINInputView) this.inputView.get();
        if (pINInputView == null) {
            return this;
        }
        pINInputView.postDelayed(new Runnable() {
            public void run() {
                pINInputView.ensureKeyboardVisible();
            }
        }, 300);
        return this;
    }

    public PINInputController setPasswordCharactersEnabled(boolean z) {
        ((PINInputView) this.inputView.get()).setPasswordCharactersEnabled(z);
        return this;
    }

    public PINInputController setInputNumbersCount(int i) {
        ((PINInputView) this.inputView.get()).setInputViewsCount(i);
        return this;
    }

    public PINInputController setInputEventListener(InputEventListener inputEventListener) {
        this.eventListener = new WeakReference<>(inputEventListener);
        return this;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        PINInputView pINInputView = (PINInputView) this.inputView.get();
        if (pINInputView == null || !isSoftKeyboardFinishedAction(textView, i, keyEvent)) {
            return false;
        }
        InputEventListener inputEventListener = (InputEventListener) this.eventListener.get();
        if (inputEventListener != null) {
            inputEventListener.onInputEntered(pINInputView.getText().toString());
        }
        pINInputView.reset();
        return true;
    }

    private boolean isSoftKeyboardFinishedAction(TextView textView, int i, KeyEvent keyEvent) {
        boolean z = false;
        if (keyEvent != null && keyEvent.getAction() != 0) {
            return false;
        }
        if (i == 0 && keyEvent.getKeyCode() == 66) {
            return true;
        }
        if (i == 6 || i == 2 || i == 4) {
            z = true;
        }
        return z;
    }

    public boolean matchesRequiredPINLength(String str) {
        PINInputView pINInputView = (PINInputView) this.inputView.get();
        if (pINInputView == null) {
            return false;
        }
        return pINInputView.matchesRequiredPINLength(str);
    }
}
