package com.leanplum.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import com.leanplum.utils.BitmapUtil;
import com.leanplum.utils.SizeUtil;

public class BackgroundImageView extends AppCompatImageView {
    private Matrix emptyMatrix = new Matrix();
    private boolean fullscreen;
    private boolean loadedBitmap;
    private Paint paint = new Paint();

    public BackgroundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public BackgroundImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public BackgroundImageView(Context context, boolean z) {
        super(context);
        init();
        this.fullscreen = z;
    }

    private void init() {
        this.paint.setColor(-16711936);
        this.paint.setStrokeWidth(2.0f);
        this.paint.setStyle(Style.FILL_AND_STROKE);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.fullscreen) {
            if (this.loadedBitmap) {
                this.loadedBitmap = false;
                return;
            }
            Bitmap loadBitmapFromView = loadBitmapFromView(this);
            canvas.drawColor(0, Mode.CLEAR);
            canvas.drawBitmap(BitmapUtil.getRoundedCornerBitmap(loadBitmapFromView, SizeUtil.dp20), this.emptyMatrix, this.paint);
        }
    }

    public Bitmap loadBitmapFromView(View view) {
        if (view.getMeasuredHeight() <= 0) {
            view.measure(-2, -2);
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        this.loadedBitmap = true;
        view.draw(canvas);
        return createBitmap;
    }
}
