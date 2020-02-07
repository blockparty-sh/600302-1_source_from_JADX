package androidx.constraintlayout.solver.state;

import androidx.constraintlayout.solver.state.State.Constraint;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor.C0202Type;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

public class ConstraintReference implements Reference {
    private Object key;
    Object mBaselineToBaseline = null;
    Object mBottomToBottom = null;
    Object mBottomToTop = null;
    private ConstraintWidget mConstraintWidget;
    Object mEndToEnd = null;
    Object mEndToStart = null;
    float mHorizontalBias = 0.5f;
    int mHorizontalChainStyle = 0;
    Dimension mHorizontalDimension = Dimension.Fixed(Dimension.WRAP_DIMENSION);
    Constraint mLast = null;
    Object mLeftToLeft = null;
    Object mLeftToRight = null;
    int mMarginBottom = 0;
    int mMarginEnd = 0;
    int mMarginLeft = 0;
    int mMarginRight = 0;
    int mMarginStart = 0;
    int mMarginTop = 0;
    Object mRightToLeft = null;
    Object mRightToRight = null;
    Object mStartToEnd = null;
    Object mStartToStart = null;
    final State mState;
    Object mTopToBottom = null;
    Object mTopToTop = null;
    float mVerticalBias = 0.5f;
    int mVerticalChainStyle = 0;
    Dimension mVerticalDimension = Dimension.Fixed(Dimension.WRAP_DIMENSION);
    private Object mView;

    public interface ConstraintReferenceFactory {
        ConstraintReference create(State state);
    }

    class IncorrectConstraintException extends Exception {
        private final ArrayList<String> mErrors;

        public IncorrectConstraintException(ArrayList<String> arrayList) {
            this.mErrors = arrayList;
        }

        public ArrayList<String> getErrors() {
            return this.mErrors;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("IncorrectConstraintException: ");
            sb.append(this.mErrors.toString());
            return sb.toString();
        }
    }

    public void setKey(Object obj) {
        this.key = obj;
    }

    public Object getKey() {
        return this.key;
    }

    public void setView(Object obj) {
        this.mView = obj;
        ConstraintWidget constraintWidget = this.mConstraintWidget;
        if (constraintWidget != null) {
            constraintWidget.setCompanionWidget(this.mView);
        }
    }

    public Object getView() {
        return this.mView;
    }

    public void setConstraintWidget(ConstraintWidget constraintWidget) {
        if (constraintWidget != null) {
            this.mConstraintWidget = constraintWidget;
            this.mConstraintWidget.setCompanionWidget(this.mView);
        }
    }

    public ConstraintWidget getConstraintWidget() {
        if (this.mConstraintWidget == null) {
            this.mConstraintWidget = createConstraintWidget();
            this.mConstraintWidget.setCompanionWidget(this.mView);
        }
        return this.mConstraintWidget;
    }

    public ConstraintWidget createConstraintWidget() {
        return new ConstraintWidget(getWidth().getValue(), getHeight().getValue());
    }

    public void validate() throws IncorrectConstraintException {
        ArrayList arrayList = new ArrayList();
        if (!(this.mLeftToLeft == null || this.mLeftToRight == null)) {
            arrayList.add("LeftToLeft and LeftToRight both defined");
        }
        if (!(this.mRightToLeft == null || this.mRightToRight == null)) {
            arrayList.add("RightToLeft and RightToRight both defined");
        }
        if (!(this.mStartToStart == null || this.mStartToEnd == null)) {
            arrayList.add("StartToStart and StartToEnd both defined");
        }
        if (!(this.mEndToStart == null || this.mEndToEnd == null)) {
            arrayList.add("EndToStart and EndToEnd both defined");
        }
        if (!((this.mLeftToLeft == null && this.mLeftToRight == null && this.mRightToLeft == null && this.mRightToRight == null) || (this.mStartToStart == null && this.mStartToEnd == null && this.mEndToStart == null && this.mEndToEnd == null))) {
            arrayList.add("Both left/right and start/end constraints defined");
        }
        if (arrayList.size() > 0) {
            throw new IncorrectConstraintException(arrayList);
        }
    }

    private Object get(Object obj) {
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof ConstraintReference)) {
            obj = this.mState.reference(obj);
        }
        return obj;
    }

    public ConstraintReference(State state) {
        this.mState = state;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public int getVerticalChainStyle(int i) {
        return this.mVerticalChainStyle;
    }

    public ConstraintReference clearVertical() {
        top().clear();
        baseline().clear();
        bottom().clear();
        return this;
    }

    public ConstraintReference clearHorizontal() {
        start().clear();
        end().clear();
        left().clear();
        right().clear();
        return this;
    }

    public ConstraintReference left() {
        if (this.mLeftToLeft != null) {
            this.mLast = Constraint.LEFT_TO_LEFT;
        } else {
            this.mLast = Constraint.LEFT_TO_RIGHT;
        }
        return this;
    }

    public ConstraintReference right() {
        if (this.mRightToLeft != null) {
            this.mLast = Constraint.RIGHT_TO_LEFT;
        } else {
            this.mLast = Constraint.RIGHT_TO_RIGHT;
        }
        return this;
    }

    public ConstraintReference start() {
        if (this.mStartToStart != null) {
            this.mLast = Constraint.START_TO_START;
        } else {
            this.mLast = Constraint.START_TO_END;
        }
        return this;
    }

    public ConstraintReference end() {
        if (this.mEndToStart != null) {
            this.mLast = Constraint.END_TO_START;
        } else {
            this.mLast = Constraint.END_TO_END;
        }
        return this;
    }

    public ConstraintReference top() {
        if (this.mTopToTop != null) {
            this.mLast = Constraint.TOP_TO_TOP;
        } else {
            this.mLast = Constraint.TOP_TO_BOTTOM;
        }
        return this;
    }

    public ConstraintReference bottom() {
        if (this.mBottomToTop != null) {
            this.mLast = Constraint.BOTTOM_TO_TOP;
        } else {
            this.mLast = Constraint.BOTTOM_TO_BOTTOM;
        }
        return this;
    }

    public ConstraintReference baseline() {
        this.mLast = Constraint.BASELINE_TO_BASELINE;
        return this;
    }

    private void dereference() {
        this.mLeftToLeft = get(this.mLeftToLeft);
        this.mLeftToRight = get(this.mLeftToRight);
        this.mRightToLeft = get(this.mRightToLeft);
        this.mRightToRight = get(this.mRightToRight);
        this.mStartToStart = get(this.mStartToStart);
        this.mStartToEnd = get(this.mStartToEnd);
        this.mEndToStart = get(this.mEndToStart);
        this.mEndToEnd = get(this.mEndToEnd);
        this.mTopToTop = get(this.mTopToTop);
        this.mTopToBottom = get(this.mTopToBottom);
        this.mBottomToTop = get(this.mBottomToTop);
        this.mBottomToBottom = get(this.mBottomToBottom);
        this.mBaselineToBaseline = get(this.mBaselineToBaseline);
    }

    public ConstraintReference leftToLeft(Object obj) {
        this.mLast = Constraint.LEFT_TO_LEFT;
        this.mLeftToLeft = obj;
        return this;
    }

    public ConstraintReference leftToRight(Object obj) {
        this.mLast = Constraint.LEFT_TO_RIGHT;
        this.mLeftToRight = obj;
        return this;
    }

    public ConstraintReference rightToLeft(Object obj) {
        this.mLast = Constraint.RIGHT_TO_LEFT;
        this.mRightToLeft = obj;
        return this;
    }

    public ConstraintReference rightToRight(Object obj) {
        this.mLast = Constraint.RIGHT_TO_RIGHT;
        this.mRightToRight = obj;
        return this;
    }

    public ConstraintReference startToStart(Object obj) {
        this.mLast = Constraint.START_TO_START;
        this.mStartToStart = obj;
        return this;
    }

    public ConstraintReference startToEnd(Object obj) {
        this.mLast = Constraint.START_TO_END;
        this.mStartToEnd = obj;
        return this;
    }

    public ConstraintReference endToStart(Object obj) {
        this.mLast = Constraint.END_TO_START;
        this.mEndToStart = obj;
        return this;
    }

    public ConstraintReference endToEnd(Object obj) {
        this.mLast = Constraint.END_TO_END;
        this.mEndToEnd = obj;
        return this;
    }

    public ConstraintReference topToTop(Object obj) {
        this.mLast = Constraint.TOP_TO_TOP;
        this.mTopToTop = obj;
        return this;
    }

    public ConstraintReference topToBottom(Object obj) {
        this.mLast = Constraint.TOP_TO_BOTTOM;
        this.mTopToBottom = obj;
        return this;
    }

    public ConstraintReference bottomToTop(Object obj) {
        this.mLast = Constraint.BOTTOM_TO_TOP;
        this.mBottomToTop = obj;
        return this;
    }

    public ConstraintReference bottomToBottom(Object obj) {
        this.mLast = Constraint.BOTTOM_TO_BOTTOM;
        this.mBottomToBottom = obj;
        return this;
    }

    public ConstraintReference baselineToBaseline(Object obj) {
        this.mLast = Constraint.BASELINE_TO_BASELINE;
        this.mBaselineToBaseline = obj;
        return this;
    }

    public ConstraintReference centerHorizontally(Object obj) {
        Object obj2 = get(obj);
        this.mStartToStart = obj2;
        this.mEndToEnd = obj2;
        this.mLast = Constraint.CENTER_HORIZONTALLY;
        this.mHorizontalBias = 0.5f;
        return this;
    }

    public ConstraintReference centerVertically(Object obj) {
        Object obj2 = get(obj);
        this.mTopToTop = obj2;
        this.mBottomToBottom = obj2;
        this.mLast = Constraint.CENTER_VERTICALLY;
        this.mVerticalBias = 0.5f;
        return this;
    }

    public ConstraintReference width(Dimension dimension) {
        return setWidth(dimension);
    }

    public ConstraintReference height(Dimension dimension) {
        return setHeight(dimension);
    }

    public Dimension getWidth() {
        return this.mHorizontalDimension;
    }

    public ConstraintReference setWidth(Dimension dimension) {
        this.mHorizontalDimension = dimension;
        return this;
    }

    public Dimension getHeight() {
        return this.mVerticalDimension;
    }

    public ConstraintReference setHeight(Dimension dimension) {
        this.mVerticalDimension = dimension;
        return this;
    }

    public ConstraintReference margin(Object obj) {
        return margin(this.mState.convertDimension(obj));
    }

    public ConstraintReference margin(int i) {
        if (this.mLast != null) {
            switch (this.mLast) {
                case LEFT_TO_LEFT:
                case LEFT_TO_RIGHT:
                    this.mMarginLeft = i;
                    break;
                case RIGHT_TO_LEFT:
                case RIGHT_TO_RIGHT:
                    this.mMarginRight = i;
                    break;
                case START_TO_START:
                case START_TO_END:
                    this.mMarginStart = i;
                    break;
                case END_TO_START:
                case END_TO_END:
                    this.mMarginEnd = i;
                    break;
                case TOP_TO_TOP:
                case TOP_TO_BOTTOM:
                    this.mMarginTop = i;
                    break;
                case BOTTOM_TO_TOP:
                case BOTTOM_TO_BOTTOM:
                    this.mMarginBottom = i;
                    break;
            }
        } else {
            this.mMarginLeft = i;
            this.mMarginRight = i;
            this.mMarginStart = i;
            this.mMarginEnd = i;
            this.mMarginTop = i;
            this.mMarginBottom = i;
        }
        return this;
    }

    public ConstraintReference horizontalBias(float f) {
        this.mHorizontalBias = f;
        return this;
    }

    public ConstraintReference verticalBias(float f) {
        this.mVerticalBias = f;
        return this;
    }

    public ConstraintReference bias(float f) {
        if (this.mLast == null) {
            return this;
        }
        switch (this.mLast) {
            case LEFT_TO_LEFT:
            case LEFT_TO_RIGHT:
            case RIGHT_TO_LEFT:
            case RIGHT_TO_RIGHT:
            case START_TO_START:
            case START_TO_END:
            case END_TO_START:
            case END_TO_END:
            case CENTER_HORIZONTALLY:
                this.mHorizontalBias = f;
                break;
            case TOP_TO_TOP:
            case TOP_TO_BOTTOM:
            case BOTTOM_TO_TOP:
            case BOTTOM_TO_BOTTOM:
            case CENTER_VERTICALLY:
                this.mVerticalBias = f;
                break;
        }
        return this;
    }

    public ConstraintReference clear() {
        if (this.mLast != null) {
            switch (this.mLast) {
                case LEFT_TO_LEFT:
                case LEFT_TO_RIGHT:
                    this.mLeftToLeft = null;
                    this.mLeftToRight = null;
                    this.mMarginLeft = 0;
                    break;
                case RIGHT_TO_LEFT:
                case RIGHT_TO_RIGHT:
                    this.mRightToLeft = null;
                    this.mRightToRight = null;
                    this.mMarginRight = 0;
                    break;
                case START_TO_START:
                case START_TO_END:
                    this.mStartToStart = null;
                    this.mStartToEnd = null;
                    this.mMarginStart = 0;
                    break;
                case END_TO_START:
                case END_TO_END:
                    this.mEndToStart = null;
                    this.mEndToEnd = null;
                    this.mMarginEnd = 0;
                    break;
                case TOP_TO_TOP:
                case TOP_TO_BOTTOM:
                    this.mTopToTop = null;
                    this.mTopToBottom = null;
                    this.mMarginTop = 0;
                    break;
                case BOTTOM_TO_TOP:
                case BOTTOM_TO_BOTTOM:
                    this.mBottomToTop = null;
                    this.mBottomToBottom = null;
                    this.mMarginBottom = 0;
                    break;
                case BASELINE_TO_BASELINE:
                    this.mBaselineToBaseline = null;
                    break;
            }
        } else {
            this.mLeftToLeft = null;
            this.mLeftToRight = null;
            this.mMarginLeft = 0;
            this.mRightToLeft = null;
            this.mRightToRight = null;
            this.mMarginRight = 0;
            this.mStartToStart = null;
            this.mStartToEnd = null;
            this.mMarginStart = 0;
            this.mEndToStart = null;
            this.mEndToEnd = null;
            this.mMarginEnd = 0;
            this.mTopToTop = null;
            this.mTopToBottom = null;
            this.mMarginTop = 0;
            this.mBottomToTop = null;
            this.mBottomToBottom = null;
            this.mMarginBottom = 0;
            this.mBaselineToBaseline = null;
            this.mHorizontalBias = 0.5f;
            this.mVerticalBias = 0.5f;
        }
        return this;
    }

    private ConstraintWidget getTarget(Object obj) {
        if (obj instanceof Reference) {
            return ((Reference) obj).getConstraintWidget();
        }
        return null;
    }

    private void applyConnection(ConstraintWidget constraintWidget, Object obj, Constraint constraint) {
        ConstraintWidget target = getTarget(obj);
        if (target != null) {
            int i = C01951.f33x7d4bfe12[constraint.ordinal()];
            switch (constraint) {
                case LEFT_TO_LEFT:
                    constraintWidget.getAnchor(C0202Type.LEFT).connect(target.getAnchor(C0202Type.LEFT), this.mMarginLeft);
                    break;
                case LEFT_TO_RIGHT:
                    constraintWidget.getAnchor(C0202Type.LEFT).connect(target.getAnchor(C0202Type.RIGHT), this.mMarginLeft);
                    break;
                case RIGHT_TO_LEFT:
                    constraintWidget.getAnchor(C0202Type.RIGHT).connect(target.getAnchor(C0202Type.LEFT), this.mMarginRight);
                    break;
                case RIGHT_TO_RIGHT:
                    constraintWidget.getAnchor(C0202Type.RIGHT).connect(target.getAnchor(C0202Type.RIGHT), this.mMarginRight);
                    break;
                case START_TO_START:
                    constraintWidget.getAnchor(C0202Type.LEFT).connect(target.getAnchor(C0202Type.LEFT), this.mMarginStart);
                    break;
                case START_TO_END:
                    constraintWidget.getAnchor(C0202Type.LEFT).connect(target.getAnchor(C0202Type.RIGHT), this.mMarginStart);
                    break;
                case END_TO_START:
                    constraintWidget.getAnchor(C0202Type.RIGHT).connect(target.getAnchor(C0202Type.LEFT), this.mMarginEnd);
                    break;
                case END_TO_END:
                    constraintWidget.getAnchor(C0202Type.RIGHT).connect(target.getAnchor(C0202Type.RIGHT), this.mMarginEnd);
                    break;
                case TOP_TO_TOP:
                    constraintWidget.getAnchor(C0202Type.TOP).connect(target.getAnchor(C0202Type.TOP), this.mMarginTop);
                    break;
                case TOP_TO_BOTTOM:
                    constraintWidget.getAnchor(C0202Type.TOP).connect(target.getAnchor(C0202Type.BOTTOM), this.mMarginTop);
                    break;
                case BOTTOM_TO_TOP:
                    constraintWidget.getAnchor(C0202Type.BOTTOM).connect(target.getAnchor(C0202Type.TOP), this.mMarginBottom);
                    break;
                case BOTTOM_TO_BOTTOM:
                    constraintWidget.getAnchor(C0202Type.BOTTOM).connect(target.getAnchor(C0202Type.BOTTOM), this.mMarginBottom);
                    break;
                case BASELINE_TO_BASELINE:
                    constraintWidget.immediateConnect(C0202Type.BASELINE, target, C0202Type.BASELINE, 0, 0);
                    break;
            }
        }
    }

    public void apply() {
        ConstraintWidget constraintWidget = this.mConstraintWidget;
        if (constraintWidget != null) {
            this.mHorizontalDimension.apply(this.mState, constraintWidget, 0);
            this.mVerticalDimension.apply(this.mState, this.mConstraintWidget, 1);
            dereference();
            applyConnection(this.mConstraintWidget, this.mLeftToLeft, Constraint.LEFT_TO_LEFT);
            applyConnection(this.mConstraintWidget, this.mLeftToRight, Constraint.LEFT_TO_RIGHT);
            applyConnection(this.mConstraintWidget, this.mRightToLeft, Constraint.RIGHT_TO_LEFT);
            applyConnection(this.mConstraintWidget, this.mRightToRight, Constraint.RIGHT_TO_RIGHT);
            applyConnection(this.mConstraintWidget, this.mStartToStart, Constraint.START_TO_START);
            applyConnection(this.mConstraintWidget, this.mStartToEnd, Constraint.START_TO_END);
            applyConnection(this.mConstraintWidget, this.mEndToStart, Constraint.END_TO_START);
            applyConnection(this.mConstraintWidget, this.mEndToEnd, Constraint.END_TO_END);
            applyConnection(this.mConstraintWidget, this.mTopToTop, Constraint.TOP_TO_TOP);
            applyConnection(this.mConstraintWidget, this.mTopToBottom, Constraint.TOP_TO_BOTTOM);
            applyConnection(this.mConstraintWidget, this.mBottomToTop, Constraint.BOTTOM_TO_TOP);
            applyConnection(this.mConstraintWidget, this.mBottomToBottom, Constraint.BOTTOM_TO_BOTTOM);
            applyConnection(this.mConstraintWidget, this.mBaselineToBaseline, Constraint.BASELINE_TO_BASELINE);
            int i = this.mHorizontalChainStyle;
            if (i != 0) {
                this.mConstraintWidget.setHorizontalChainStyle(i);
            }
            int i2 = this.mVerticalChainStyle;
            if (i2 != 0) {
                this.mConstraintWidget.setVerticalChainStyle(i2);
            }
            this.mConstraintWidget.setHorizontalBiasPercent(this.mHorizontalBias);
            this.mConstraintWidget.setVerticalBiasPercent(this.mVerticalBias);
        }
    }
}
