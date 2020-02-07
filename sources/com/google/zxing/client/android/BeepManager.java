package com.google.zxing.client.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Vibrator;
import android.util.Log;
import java.io.IOException;

public final class BeepManager {
    private static final float BEEP_VOLUME = 0.1f;
    /* access modifiers changed from: private */
    public static final String TAG = "BeepManager";
    private static final long VIBRATE_DURATION = 200;
    private boolean beepEnabled = true;
    private final Context context;
    private boolean vibrateEnabled = false;

    public BeepManager(Activity activity) {
        activity.setVolumeControlStream(3);
        this.context = activity.getApplicationContext();
    }

    public boolean isBeepEnabled() {
        return this.beepEnabled;
    }

    public void setBeepEnabled(boolean z) {
        this.beepEnabled = z;
    }

    public boolean isVibrateEnabled() {
        return this.vibrateEnabled;
    }

    public void setVibrateEnabled(boolean z) {
        this.vibrateEnabled = z;
    }

    @SuppressLint({"MissingPermission"})
    public synchronized void playBeepSoundAndVibrate() {
        if (this.beepEnabled) {
            playBeepSound();
        }
        if (this.vibrateEnabled) {
            Vibrator vibrator = (Vibrator) this.context.getSystemService("vibrator");
            if (vibrator != null) {
                vibrator.vibrate(VIBRATE_DURATION);
            }
        }
    }

    public MediaPlayer playBeepSound() {
        AssetFileDescriptor openRawResourceFd;
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(3);
        mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });
        mediaPlayer.setOnErrorListener(new OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                String access$000 = BeepManager.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to beep ");
                sb.append(i);
                sb.append(", ");
                sb.append(i2);
                Log.w(access$000, sb.toString());
                mediaPlayer.stop();
                mediaPlayer.release();
                return true;
            }
        });
        try {
            openRawResourceFd = this.context.getResources().openRawResourceFd(C2220R.C2223raw.zxing_beep);
            mediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
            openRawResourceFd.close();
            mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
            mediaPlayer.prepare();
            mediaPlayer.start();
            return mediaPlayer;
        } catch (IOException e) {
            Log.w(TAG, e);
            mediaPlayer.release();
            return null;
        } catch (Throwable th) {
            openRawResourceFd.close();
            throw th;
        }
    }
}
