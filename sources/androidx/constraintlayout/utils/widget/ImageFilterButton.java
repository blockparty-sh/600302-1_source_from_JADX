package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.C0215R;

public class ImageFilterButton extends AppCompatImageButton {
    private float mCrossfade = 0.0f;
    private ImageMatrix mImageMatrix = new ImageMatrix();
    LayerDrawable mLayer;
    Drawable[] mLayers;
    private boolean mOverlay = true;
    private Path mPath;
    RectF mRect;
    /* access modifiers changed from: private */
    public float mRound = Float.NaN;
    /* access modifiers changed from: private */
    public float mRoundPercent = 0.0f;
    ViewOutlineProvider mViewOutlineProvider;

    public ImageFilterButton(Context context) {
        super(context);
        init(context, null);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        setPadding(0, 0, 0, 0);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0215R.styleable.ImageFilterView);
            int indexCount = obtainStyledAttributes.getIndexCount();
            Drawable drawable = obtainStyledAttributes.getDrawable(C0215R.styleable.ImageFilterView_altSrc);
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == C0215R.styleable.ImageFilterView_crossfade) {
                    this.mCrossfade = obtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == C0215R.styleable.ImageFilterView_warmth) {
                    setWarmth(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == C0215R.styleable.ImageFilterView_saturation) {
                    setSaturation(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == C0215R.styleable.ImageFilterView_contrast) {
                    setContrast(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == C0215R.styleable.ImageFilterView_round) {
                    setRound(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == C0215R.styleable.ImageFilterView_roundPercent) {
                    setRoundPercent(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == C0215R.styleable.ImageFilterView_overlay) {
                    setOverlay(obtainStyledAttributes.getBoolean(index, this.mOverlay));
                }
            }
            obtainStyledAttributes.recycle();
            if (drawable != null) {
                this.mLayers = new Drawable[2];
                this.mLayers[0] = getDrawable();
                Drawable[] drawableArr = this.mLayers;
                drawableArr[1] = drawable;
                this.mLayer = new LayerDrawable(drawableArr);
                this.mLayer.getDrawable(1).setAlpha((int) (this.mCrossfade * 255.0f));
                super.setImageDrawable(this.mLayer);
            }
        }
    }

    private void setOverlay(boolean z) {
        this.mOverlay = z;
    }

    public void setSaturation(float f) {
        ImageMatrix imageMatrix = this.mImageMatrix;
        imageMatrix.mSaturation = f;
        imageMatrix.updateMatrix(this);
    }

    public float getSaturation() {
        return this.mImageMatrix.mSaturation;
    }

    public void setContrast(float f) {
        ImageMatrix imageMatrix = this.mImageMatrix;
        imageMatrix.mContrast = f;
        imageMatrix.updateMatrix(this);
    }

    public float getContrast() {
        return this.mImageMatrix.mContrast;
    }

    public void setWarmth(float f) {
        ImageMatrix imageMatrix = this.mImageMatrix;
        imageMatrix.mWarmth = f;
        imageMatrix.updateMatrix(this);
    }

    public float getWarmth() {
        return this.mImageMatrix.mWarmth;
    }

    public void setCrossfade(float f) {
        this.mCrossfade = f;
        if (this.mLayers != null) {
            if (!this.mOverlay) {
                this.mLayer.getDrawable(0).setAlpha((int) ((1.0f - this.mCrossfade) * 255.0f));
            }
            this.mLayer.getDrawable(1).setAlpha((int) (this.mCrossfade * 255.0f));
            super.setImageDrawable(this.mLayer);
        }
    }

    public float getCrossfade() {
        return this.mCrossfade;
    }

    public void setBrightness(float f) {
        ImageMatrix imageMatrix = this.mImageMatrix;
        imageMatrix.mBrightness = f;
        imageMatrix.updateMatrix(this);
    }

    public void setRoundPercent(float f) {
        boolean z = this.mRoundPercent != f;
        this.mRoundPercent = f;
        if (this.mRoundPercent != 0.0f) {
            if (this.mPath == null) {
                this.mPath = new Path();
            }
            if (this.mRect == null) {
                this.mRect = new RectF();
            }
            if (VERSION.SDK_INT >= 21 && this.mViewOutlineProvider == null) {
                this.mViewOutlineProvider = new ViewOutlineProvider() {
                    public void getOutline(View view, Outline outline) {
                        int width = ImageFilterButton.this.getWidth();
                        int height = ImageFilterButton.this.getHeight();
                        outline.setRoundRect(0, 0, width, height, (((float) Math.min(width, height)) * ImageFilterButton.this.mRoundPercent) / 2.0f);
                    }
                };
                setOutlineProvider(this.mViewOutlineProvider);
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float min = (((float) Math.min(width, height)) * this.mRoundPercent) / 2.0f;
            this.mRect.set(0.0f, 0.0f, (float) width, (float) height);
            this.mPath.reset();
            this.mPath.addRoundRect(this.mRect, min, min, Direction.CW);
        } else if (VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (z && VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setRound(float f) {
        if (Float.isNaN(f)) {
            this.mRound = f;
            float f2 = this.mRoundPercent;
            this.mRoundPercent = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.mRound != f;
        this.mRound = f;
        if (this.mRound != 0.0f) {
            if (this.mPath == null) {
                this.mPath = new Path();
            }
            if (this.mRect == null) {
                this.mRect = new RectF();
            }
            if (VERSION.SDK_INT >= 21 && this.mViewOutlineProvider == null) {
                this.mViewOutlineProvider = new ViewOutlineProvider() {
                    public void getOutline(View view, Outline outline) {
                        outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), ImageFilterButton.this.mRound);
                    }
                };
                setOutlineProvider(this.mViewOutlineProvider);
                setClipToOutline(true);
            }
            this.mRect.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.mPath.reset();
            Path path = this.mPath;
            RectF rectF = this.mRect;
            float f3 = this.mRound;
            path.addRoundRect(rectF, f3, f3, Direction.CW);
        } else if (VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (z && VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public float getRoundPercent() {
        return this.mRoundPercent;
    }

    public float getRound() {
        return this.mRound;
    }

    public void draw(Canvas canvas) {
        boolean z;
        if (VERSION.SDK_INT >= 21 || this.mRound == 0.0f) {
            z = false;
        } else {
            z = true;
            canvas.save();
            canvas.clipPath(this.mPath);
        }
        super.draw(canvas);
        if (z) {
            canvas.restore();
        }
    }
}
