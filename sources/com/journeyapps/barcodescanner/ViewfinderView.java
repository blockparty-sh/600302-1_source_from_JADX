package com.journeyapps.barcodescanner;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.C2220R;
import com.journeyapps.barcodescanner.CameraPreview.StateListener;
import java.util.ArrayList;
import java.util.List;

public class ViewfinderView extends View {
    protected static final long ANIMATION_DELAY = 80;
    protected static final int CURRENT_POINT_OPACITY = 160;
    protected static final int MAX_RESULT_POINTS = 20;
    protected static final int POINT_SIZE = 6;
    protected static final int[] SCANNER_ALPHA = {0, 64, 128, 192, 255, 192, 128, 64};
    protected static final String TAG = "ViewfinderView";
    protected CameraPreview cameraPreview;
    protected Rect framingRect;
    protected final int laserColor;
    protected List<ResultPoint> lastPossibleResultPoints;
    protected final int maskColor;
    protected final Paint paint = new Paint(1);
    protected List<ResultPoint> possibleResultPoints;
    protected Rect previewFramingRect;
    protected Bitmap resultBitmap;
    protected final int resultColor;
    protected final int resultPointColor;
    protected int scannerAlpha;

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = getResources();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C2220R.styleable.zxing_finder);
        this.maskColor = obtainStyledAttributes.getColor(C2220R.styleable.zxing_finder_zxing_viewfinder_mask, resources.getColor(C2220R.C2221color.zxing_viewfinder_mask));
        this.resultColor = obtainStyledAttributes.getColor(C2220R.styleable.zxing_finder_zxing_result_view, resources.getColor(C2220R.C2221color.zxing_result_view));
        this.laserColor = obtainStyledAttributes.getColor(C2220R.styleable.zxing_finder_zxing_viewfinder_laser, resources.getColor(C2220R.C2221color.zxing_viewfinder_laser));
        this.resultPointColor = obtainStyledAttributes.getColor(C2220R.styleable.zxing_finder_zxing_possible_result_points, resources.getColor(C2220R.C2221color.zxing_possible_result_points));
        obtainStyledAttributes.recycle();
        this.scannerAlpha = 0;
        this.possibleResultPoints = new ArrayList(20);
        this.lastPossibleResultPoints = new ArrayList(20);
    }

    public void setCameraPreview(CameraPreview cameraPreview2) {
        this.cameraPreview = cameraPreview2;
        cameraPreview2.addStateListener(new StateListener() {
            public void cameraClosed() {
            }

            public void cameraError(Exception exc) {
            }

            public void previewStarted() {
            }

            public void previewStopped() {
            }

            public void previewSized() {
                ViewfinderView.this.refreshSizes();
                ViewfinderView.this.invalidate();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void refreshSizes() {
        CameraPreview cameraPreview2 = this.cameraPreview;
        if (cameraPreview2 != null) {
            Rect framingRect2 = cameraPreview2.getFramingRect();
            Rect previewFramingRect2 = this.cameraPreview.getPreviewFramingRect();
            if (!(framingRect2 == null || previewFramingRect2 == null)) {
                this.framingRect = framingRect2;
                this.previewFramingRect = previewFramingRect2;
            }
        }
    }

    public void onDraw(Canvas canvas) {
        refreshSizes();
        Rect rect = this.framingRect;
        if (rect != null) {
            Rect rect2 = this.previewFramingRect;
            if (rect2 != null) {
                int width = canvas.getWidth();
                int height = canvas.getHeight();
                this.paint.setColor(this.resultBitmap != null ? this.resultColor : this.maskColor);
                float f = (float) width;
                canvas.drawRect(0.0f, 0.0f, f, (float) rect.top, this.paint);
                canvas.drawRect(0.0f, (float) rect.top, (float) rect.left, (float) (rect.bottom + 1), this.paint);
                float f2 = f;
                canvas.drawRect((float) (rect.right + 1), (float) rect.top, f2, (float) (rect.bottom + 1), this.paint);
                canvas.drawRect(0.0f, (float) (rect.bottom + 1), f2, (float) height, this.paint);
                if (this.resultBitmap != null) {
                    this.paint.setAlpha(160);
                    canvas.drawBitmap(this.resultBitmap, null, rect, this.paint);
                    return;
                }
                this.paint.setColor(this.laserColor);
                this.paint.setAlpha(SCANNER_ALPHA[this.scannerAlpha]);
                this.scannerAlpha = (this.scannerAlpha + 1) % SCANNER_ALPHA.length;
                int height2 = (rect.height() / 2) + rect.top;
                canvas.drawRect((float) (rect.left + 2), (float) (height2 - 1), (float) (rect.right - 1), (float) (height2 + 2), this.paint);
                float width2 = ((float) rect.width()) / ((float) rect2.width());
                float height3 = ((float) rect.height()) / ((float) rect2.height());
                int i = rect.left;
                int i2 = rect.top;
                if (!this.lastPossibleResultPoints.isEmpty()) {
                    this.paint.setAlpha(80);
                    this.paint.setColor(this.resultPointColor);
                    for (ResultPoint resultPoint : this.lastPossibleResultPoints) {
                        canvas.drawCircle((float) (((int) (resultPoint.getX() * width2)) + i), (float) (((int) (resultPoint.getY() * height3)) + i2), 3.0f, this.paint);
                    }
                    this.lastPossibleResultPoints.clear();
                }
                if (!this.possibleResultPoints.isEmpty()) {
                    this.paint.setAlpha(160);
                    this.paint.setColor(this.resultPointColor);
                    for (ResultPoint resultPoint2 : this.possibleResultPoints) {
                        canvas.drawCircle((float) (((int) (resultPoint2.getX() * width2)) + i), (float) (((int) (resultPoint2.getY() * height3)) + i2), 6.0f, this.paint);
                    }
                    List<ResultPoint> list = this.possibleResultPoints;
                    this.possibleResultPoints = this.lastPossibleResultPoints;
                    this.lastPossibleResultPoints = list;
                    this.possibleResultPoints.clear();
                }
                postInvalidateDelayed(ANIMATION_DELAY, rect.left - 6, rect.top - 6, rect.right + 6, rect.bottom + 6);
            }
        }
    }

    public void drawViewfinder() {
        Bitmap bitmap = this.resultBitmap;
        this.resultBitmap = null;
        if (bitmap != null) {
            bitmap.recycle();
        }
        invalidate();
    }

    public void drawResultBitmap(Bitmap bitmap) {
        this.resultBitmap = bitmap;
        invalidate();
    }

    public void addPossibleResultPoint(ResultPoint resultPoint) {
        if (this.possibleResultPoints.size() < 20) {
            this.possibleResultPoints.add(resultPoint);
        }
    }
}
