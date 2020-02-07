package com.leanplum.messagetemplates;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.recyclerview.widget.ItemTouchHelper.Callback;
import com.leanplum.ActionArgs;
import com.leanplum.ActionContext;

public class CenterPopupOptions extends BaseMessageOptions {
    private int height;
    private int width;

    public /* bridge */ /* synthetic */ void accept() {
        super.accept();
    }

    public /* bridge */ /* synthetic */ int getAcceptButtonBackgroundColor() {
        return super.getAcceptButtonBackgroundColor();
    }

    public /* bridge */ /* synthetic */ String getAcceptButtonText() {
        return super.getAcceptButtonText();
    }

    public /* bridge */ /* synthetic */ int getAcceptButtonTextColor() {
        return super.getAcceptButtonTextColor();
    }

    public /* bridge */ /* synthetic */ int getBackgroundColor() {
        return super.getBackgroundColor();
    }

    public /* bridge */ /* synthetic */ Bitmap getBackgroundImage() {
        return super.getBackgroundImage();
    }

    public /* bridge */ /* synthetic */ Bitmap getBackgroundImageRounded(int i) {
        return super.getBackgroundImageRounded(i);
    }

    public /* bridge */ /* synthetic */ int getMessageColor() {
        return super.getMessageColor();
    }

    public /* bridge */ /* synthetic */ String getMessageText() {
        return super.getMessageText();
    }

    public /* bridge */ /* synthetic */ String getTitle() {
        return super.getTitle();
    }

    public /* bridge */ /* synthetic */ int getTitleColor() {
        return super.getTitleColor();
    }

    public CenterPopupOptions(ActionContext actionContext) {
        super(actionContext);
        setWidth(actionContext.numberNamed("Layout.Width").intValue());
        setHeight(actionContext.numberNamed("Layout.Height").intValue());
    }

    public int getWidth() {
        return this.width;
    }

    private void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    private void setHeight(int i) {
        this.height = i;
    }

    public static ActionArgs toArgs(Context context) {
        return BaseMessageOptions.toArgs(context).with("Layout.Width", Integer.valueOf(300)).with("Layout.Height", Integer.valueOf(Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
    }
}
