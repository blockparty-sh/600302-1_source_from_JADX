package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;

class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i) {
        int i2;
        ChainHead[] chainHeadArr;
        int i3;
        if (i == 0) {
            int i4 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i2 = i4;
            i3 = 0;
        } else {
            i3 = 2;
            int i5 = constraintWidgetContainer.mVerticalChainsSize;
            i2 = i5;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i6 = 0; i6 < i2; i6++) {
            ChainHead chainHead = chainHeadArr[i6];
            chainHead.define();
            applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, chainHead);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        if (r2.mHorizontalChainStyle == 2) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0048, code lost:
        if (r2.mVerticalChainStyle == 2) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004c, code lost:
        r5 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x038f  */
    /* JADX WARNING: Removed duplicated region for block: B:292:0x0390 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void applyChainConstraints(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r36, androidx.constraintlayout.solver.LinearSystem r37, int r38, int r39, androidx.constraintlayout.solver.widgets.ChainHead r40) {
        /*
            r0 = r36
            r9 = r37
            r1 = r40
            androidx.constraintlayout.solver.widgets.ConstraintWidget r10 = r1.mFirst
            androidx.constraintlayout.solver.widgets.ConstraintWidget r11 = r1.mLast
            androidx.constraintlayout.solver.widgets.ConstraintWidget r12 = r1.mFirstVisibleWidget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r13 = r1.mLastVisibleWidget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r1.mHead
            float r3 = r1.mTotalWeight
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r1.mFirstMatchConstraintWidget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r1.mLastMatchConstraintWidget
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r0.mListDimensionBehaviors
            r4 = r4[r38]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r7 = 1
            if (r4 != r5) goto L_0x0021
            r4 = 1
            goto L_0x0022
        L_0x0021:
            r4 = 0
        L_0x0022:
            r5 = 2
            if (r38 != 0) goto L_0x0038
            int r8 = r2.mHorizontalChainStyle
            if (r8 != 0) goto L_0x002b
            r8 = 1
            goto L_0x002c
        L_0x002b:
            r8 = 0
        L_0x002c:
            int r14 = r2.mHorizontalChainStyle
            if (r14 != r7) goto L_0x0032
            r14 = 1
            goto L_0x0033
        L_0x0032:
            r14 = 0
        L_0x0033:
            int r15 = r2.mHorizontalChainStyle
            if (r15 != r5) goto L_0x004c
            goto L_0x004a
        L_0x0038:
            int r8 = r2.mVerticalChainStyle
            if (r8 != 0) goto L_0x003e
            r8 = 1
            goto L_0x003f
        L_0x003e:
            r8 = 0
        L_0x003f:
            int r14 = r2.mVerticalChainStyle
            if (r14 != r7) goto L_0x0045
            r14 = 1
            goto L_0x0046
        L_0x0045:
            r14 = 0
        L_0x0046:
            int r15 = r2.mVerticalChainStyle
            if (r15 != r5) goto L_0x004c
        L_0x004a:
            r5 = 1
            goto L_0x004d
        L_0x004c:
            r5 = 0
        L_0x004d:
            r15 = r8
            r8 = r10
            r16 = r14
            r14 = r5
            r5 = 0
        L_0x0053:
            r21 = 0
            if (r5 != 0) goto L_0x012e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r8.mListAnchors
            r6 = r6[r39]
            if (r4 != 0) goto L_0x0063
            if (r14 == 0) goto L_0x0060
            goto L_0x0063
        L_0x0060:
            r23 = 4
            goto L_0x0065
        L_0x0063:
            r23 = 1
        L_0x0065:
            int r24 = r6.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r6.mTarget
            if (r7 == 0) goto L_0x0077
            if (r8 == r10) goto L_0x0077
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r6.mTarget
            int r7 = r7.getMargin()
            int r24 = r24 + r7
        L_0x0077:
            r7 = r24
            if (r14 == 0) goto L_0x0085
            if (r8 == r10) goto L_0x0085
            if (r8 == r12) goto L_0x0085
            r24 = r3
            r23 = r5
            r3 = 6
            goto L_0x0095
        L_0x0085:
            if (r15 == 0) goto L_0x008f
            if (r4 == 0) goto L_0x008f
            r24 = r3
            r23 = r5
            r3 = 4
            goto L_0x0095
        L_0x008f:
            r24 = r3
            r3 = r23
            r23 = r5
        L_0x0095:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r6.mTarget
            if (r5 == 0) goto L_0x00c2
            if (r8 != r12) goto L_0x00aa
            androidx.constraintlayout.solver.SolverVariable r5 = r6.mSolverVariable
            r25 = r15
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r15 = r6.mTarget
            androidx.constraintlayout.solver.SolverVariable r15 = r15.mSolverVariable
            r26 = r2
            r2 = 5
            r9.addGreaterThan(r5, r15, r7, r2)
            goto L_0x00b8
        L_0x00aa:
            r26 = r2
            r25 = r15
            androidx.constraintlayout.solver.SolverVariable r2 = r6.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r6.mTarget
            androidx.constraintlayout.solver.SolverVariable r5 = r5.mSolverVariable
            r15 = 6
            r9.addGreaterThan(r2, r5, r7, r15)
        L_0x00b8:
            androidx.constraintlayout.solver.SolverVariable r2 = r6.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r6.mTarget
            androidx.constraintlayout.solver.SolverVariable r5 = r5.mSolverVariable
            r9.addEquality(r2, r5, r7, r3)
            goto L_0x00c6
        L_0x00c2:
            r26 = r2
            r25 = r15
        L_0x00c6:
            if (r4 == 0) goto L_0x00fd
            int r2 = r8.getVisibility()
            r3 = 8
            if (r2 == r3) goto L_0x00ec
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r8.mListDimensionBehaviors
            r2 = r2[r38]
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r3) goto L_0x00ec
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            int r3 = r39 + 1
            r2 = r2[r3]
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r8.mListAnchors
            r3 = r3[r39]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            r5 = 5
            r6 = 0
            r9.addGreaterThan(r2, r3, r6, r5)
            goto L_0x00ed
        L_0x00ec:
            r6 = 0
        L_0x00ed:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            r2 = r2[r39]
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r0.mListAnchors
            r3 = r3[r39]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            r5 = 6
            r9.addGreaterThan(r2, r3, r6, r5)
        L_0x00fd:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            int r3 = r39 + 1
            r2 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x011e
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r2.mOwner
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x011e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r3.mOwner
            if (r3 == r8) goto L_0x011c
            goto L_0x011e
        L_0x011c:
            r21 = r2
        L_0x011e:
            if (r21 == 0) goto L_0x0125
            r8 = r21
            r5 = r23
            goto L_0x0126
        L_0x0125:
            r5 = 1
        L_0x0126:
            r3 = r24
            r15 = r25
            r2 = r26
            goto L_0x0053
        L_0x012e:
            r26 = r2
            r24 = r3
            r25 = r15
            if (r13 == 0) goto L_0x0157
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r11.mListAnchors
            int r3 = r39 + 1
            r2 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0157
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            r2 = r2[r3]
            androidx.constraintlayout.solver.SolverVariable r5 = r2.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r11.mListAnchors
            r3 = r6[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            int r2 = r2.getMargin()
            int r2 = -r2
            r6 = 5
            r9.addLowerThan(r5, r3, r2, r6)
        L_0x0157:
            if (r4 == 0) goto L_0x0173
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            int r2 = r39 + 1
            r0 = r0[r2]
            androidx.constraintlayout.solver.SolverVariable r0 = r0.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r2]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r2 = r4[r2]
            int r2 = r2.getMargin()
            r4 = 6
            r9.addGreaterThan(r0, r3, r2, r4)
        L_0x0173:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r0 = r1.mWeightedMatchConstraintsWidgets
            if (r0 == 0) goto L_0x022c
            int r2 = r0.size()
            r3 = 1
            if (r2 <= r3) goto L_0x022c
            boolean r4 = r1.mHasUndefinedWeights
            if (r4 == 0) goto L_0x018a
            boolean r4 = r1.mHasComplexMatchWeights
            if (r4 != 0) goto L_0x018a
            int r4 = r1.mWidgetsMatchCount
            float r4 = (float) r4
            goto L_0x018c
        L_0x018a:
            r4 = r24
        L_0x018c:
            r5 = 0
            r7 = r21
            r6 = 0
            r28 = 0
        L_0x0192:
            if (r6 >= r2) goto L_0x022c
            java.lang.Object r8 = r0.get(r6)
            androidx.constraintlayout.solver.widgets.ConstraintWidget r8 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r8
            float[] r15 = r8.mWeight
            r15 = r15[r38]
            int r17 = (r15 > r5 ? 1 : (r15 == r5 ? 0 : -1))
            if (r17 >= 0) goto L_0x01c2
            boolean r15 = r1.mHasComplexMatchWeights
            if (r15 == 0) goto L_0x01bb
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r15 = r8.mListAnchors
            int r17 = r39 + 1
            r15 = r15[r17]
            androidx.constraintlayout.solver.SolverVariable r15 = r15.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r8 = r8.mListAnchors
            r8 = r8[r39]
            androidx.constraintlayout.solver.SolverVariable r8 = r8.mSolverVariable
            r3 = 0
            r5 = 4
            r9.addEquality(r15, r8, r3, r5)
            r5 = 6
            goto L_0x01db
        L_0x01bb:
            r5 = 4
            r3 = 1065353216(0x3f800000, float:1.0)
            r3 = 0
            r15 = 1065353216(0x3f800000, float:1.0)
            goto L_0x01c4
        L_0x01c2:
            r5 = 4
            r3 = 0
        L_0x01c4:
            int r22 = (r15 > r3 ? 1 : (r15 == r3 ? 0 : -1))
            if (r22 != 0) goto L_0x01e0
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r15 = r8.mListAnchors
            int r22 = r39 + 1
            r15 = r15[r22]
            androidx.constraintlayout.solver.SolverVariable r15 = r15.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r8 = r8.mListAnchors
            r8 = r8[r39]
            androidx.constraintlayout.solver.SolverVariable r8 = r8.mSolverVariable
            r3 = 0
            r5 = 6
            r9.addEquality(r15, r8, r3, r5)
        L_0x01db:
            r24 = r0
            r20 = r2
            goto L_0x0222
        L_0x01e0:
            r3 = 0
            r5 = 6
            if (r7 == 0) goto L_0x021b
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r7.mListAnchors
            r3 = r3[r39]
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r7.mListAnchors
            int r20 = r39 + 1
            r7 = r7[r20]
            androidx.constraintlayout.solver.SolverVariable r7 = r7.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r8.mListAnchors
            r5 = r5[r39]
            androidx.constraintlayout.solver.SolverVariable r5 = r5.mSolverVariable
            r24 = r0
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r8.mListAnchors
            r0 = r0[r20]
            androidx.constraintlayout.solver.SolverVariable r0 = r0.mSolverVariable
            r20 = r2
            androidx.constraintlayout.solver.ArrayRow r2 = r37.createRow()
            r27 = r2
            r29 = r4
            r30 = r15
            r31 = r3
            r32 = r7
            r33 = r5
            r34 = r0
            r27.createRowEqualMatchDimensions(r28, r29, r30, r31, r32, r33, r34)
            r9.addConstraint(r2)
            goto L_0x021f
        L_0x021b:
            r24 = r0
            r20 = r2
        L_0x021f:
            r7 = r8
            r28 = r15
        L_0x0222:
            int r6 = r6 + 1
            r2 = r20
            r0 = r24
            r3 = 1
            r5 = 0
            goto L_0x0192
        L_0x022c:
            if (r12 == 0) goto L_0x0296
            if (r12 == r13) goto L_0x0232
            if (r14 == 0) goto L_0x0296
        L_0x0232:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r10.mListAnchors
            r0 = r0[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            int r2 = r39 + 1
            r1 = r1[r2]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r10.mListAnchors
            r3 = r3[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x024d
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r10.mListAnchors
            r3 = r3[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x024f
        L_0x024d:
            r3 = r21
        L_0x024f:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r4 = r4[r2]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x0261
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r4 = r4[r2]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            androidx.constraintlayout.solver.SolverVariable r4 = r4.mSolverVariable
            r5 = r4
            goto L_0x0263
        L_0x0261:
            r5 = r21
        L_0x0263:
            if (r12 != r13) goto L_0x026d
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r2]
        L_0x026d:
            if (r3 == 0) goto L_0x04c7
            if (r5 == 0) goto L_0x04c7
            if (r38 != 0) goto L_0x0278
            r2 = r26
            float r2 = r2.mHorizontalBiasPercent
            goto L_0x027c
        L_0x0278:
            r2 = r26
            float r2 = r2.mVerticalBiasPercent
        L_0x027c:
            r4 = r2
            int r6 = r0.getMargin()
            int r7 = r1.getMargin()
            androidx.constraintlayout.solver.SolverVariable r2 = r0.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r8 = r1.mSolverVariable
            r10 = 5
            r0 = r37
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r8
            r8 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x04c7
        L_0x0296:
            if (r25 == 0) goto L_0x0394
            if (r12 == 0) goto L_0x0394
            int r0 = r1.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x02a7
            int r0 = r1.mWidgetsCount
            int r1 = r1.mWidgetsMatchCount
            if (r0 != r1) goto L_0x02a7
            r17 = 1
            goto L_0x02a9
        L_0x02a7:
            r17 = 0
        L_0x02a9:
            r14 = r12
            r15 = r14
        L_0x02ab:
            if (r14 == 0) goto L_0x04c7
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r0 = r14.mNextChainWidget
            r0 = r0[r38]
            r8 = r0
        L_0x02b2:
            if (r8 == 0) goto L_0x02c1
            int r0 = r8.getVisibility()
            r6 = 8
            if (r0 != r6) goto L_0x02c3
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r0 = r8.mNextChainWidget
            r8 = r0[r38]
            goto L_0x02b2
        L_0x02c1:
            r6 = 8
        L_0x02c3:
            if (r8 != 0) goto L_0x02ce
            if (r14 != r13) goto L_0x02c8
            goto L_0x02ce
        L_0x02c8:
            r18 = r8
            r19 = 6
            goto L_0x0387
        L_0x02ce:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r39]
            androidx.constraintlayout.solver.SolverVariable r1 = r0.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            if (r2 == 0) goto L_0x02dd
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x02df
        L_0x02dd:
            r2 = r21
        L_0x02df:
            if (r15 == r14) goto L_0x02ea
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r15.mListAnchors
            int r3 = r39 + 1
            r2 = r2[r3]
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x0301
        L_0x02ea:
            if (r14 != r12) goto L_0x0301
            if (r15 != r14) goto L_0x0301
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r10.mListAnchors
            r2 = r2[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x02ff
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r10.mListAnchors
            r2 = r2[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x0301
        L_0x02ff:
            r2 = r21
        L_0x0301:
            int r0 = r0.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r3 = r14.mListAnchors
            int r4 = r39 + 1
            r3 = r3[r4]
            int r3 = r3.getMargin()
            if (r8 == 0) goto L_0x0323
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r8.mListAnchors
            r5 = r5[r39]
            androidx.constraintlayout.solver.SolverVariable r7 = r5.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r6 = r14.mListAnchors
            r6 = r6[r4]
            androidx.constraintlayout.solver.SolverVariable r6 = r6.mSolverVariable
            r35 = r7
            r7 = r6
            r6 = r35
            goto L_0x0336
        L_0x0323:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r11.mListAnchors
            r5 = r5[r4]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x032e
            androidx.constraintlayout.solver.SolverVariable r6 = r5.mSolverVariable
            goto L_0x0330
        L_0x032e:
            r6 = r21
        L_0x0330:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r14.mListAnchors
            r7 = r7[r4]
            androidx.constraintlayout.solver.SolverVariable r7 = r7.mSolverVariable
        L_0x0336:
            if (r5 == 0) goto L_0x033d
            int r5 = r5.getMargin()
            int r3 = r3 + r5
        L_0x033d:
            if (r15 == 0) goto L_0x0348
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r15.mListAnchors
            r5 = r5[r4]
            int r5 = r5.getMargin()
            int r0 = r0 + r5
        L_0x0348:
            if (r1 == 0) goto L_0x02c8
            if (r2 == 0) goto L_0x02c8
            if (r6 == 0) goto L_0x02c8
            if (r7 == 0) goto L_0x02c8
            if (r14 != r12) goto L_0x035a
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r39]
            int r0 = r0.getMargin()
        L_0x035a:
            r5 = r0
            if (r14 != r13) goto L_0x0368
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r4]
            int r0 = r0.getMargin()
            r18 = r0
            goto L_0x036a
        L_0x0368:
            r18 = r3
        L_0x036a:
            if (r17 == 0) goto L_0x036f
            r20 = 6
            goto L_0x0371
        L_0x036f:
            r20 = 4
        L_0x0371:
            r4 = 1056964608(0x3f000000, float:0.5)
            r0 = r37
            r3 = r5
            r22 = 6
            r23 = 4
            r5 = r6
            r19 = 6
            r6 = r7
            r7 = r18
            r18 = r8
            r8 = r20
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x0387:
            int r0 = r14.getVisibility()
            r8 = 8
            if (r0 == r8) goto L_0x0390
            r15 = r14
        L_0x0390:
            r14 = r18
            goto L_0x02ab
        L_0x0394:
            r8 = 8
            r19 = 6
            if (r16 == 0) goto L_0x04c7
            if (r12 == 0) goto L_0x04c7
            int r0 = r1.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x03a9
            int r0 = r1.mWidgetsCount
            int r1 = r1.mWidgetsMatchCount
            if (r0 != r1) goto L_0x03a9
            r17 = 1
            goto L_0x03ab
        L_0x03a9:
            r17 = 0
        L_0x03ab:
            r14 = r12
            r15 = r14
        L_0x03ad:
            if (r14 == 0) goto L_0x0469
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r0 = r14.mNextChainWidget
            r0 = r0[r38]
        L_0x03b3:
            if (r0 == 0) goto L_0x03c0
            int r1 = r0.getVisibility()
            if (r1 != r8) goto L_0x03c0
            androidx.constraintlayout.solver.widgets.ConstraintWidget[] r0 = r0.mNextChainWidget
            r0 = r0[r38]
            goto L_0x03b3
        L_0x03c0:
            if (r14 == r12) goto L_0x0456
            if (r14 == r13) goto L_0x0456
            if (r0 == 0) goto L_0x0456
            if (r0 != r13) goto L_0x03cb
            r7 = r21
            goto L_0x03cc
        L_0x03cb:
            r7 = r0
        L_0x03cc:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r39]
            androidx.constraintlayout.solver.SolverVariable r1 = r0.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            if (r2 == 0) goto L_0x03da
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
        L_0x03da:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r15.mListAnchors
            int r3 = r39 + 1
            r2 = r2[r3]
            androidx.constraintlayout.solver.SolverVariable r2 = r2.mSolverVariable
            int r0 = r0.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r14.mListAnchors
            r4 = r4[r3]
            int r4 = r4.getMargin()
            if (r7 == 0) goto L_0x0402
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r7.mListAnchors
            r5 = r5[r39]
            androidx.constraintlayout.solver.SolverVariable r6 = r5.mSolverVariable
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r5.mTarget
            if (r8 == 0) goto L_0x03ff
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r8 = r5.mTarget
            androidx.constraintlayout.solver.SolverVariable r8 = r8.mSolverVariable
            goto L_0x0415
        L_0x03ff:
            r8 = r21
            goto L_0x0415
        L_0x0402:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r5 = r14.mListAnchors
            r5 = r5[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x040d
            androidx.constraintlayout.solver.SolverVariable r6 = r5.mSolverVariable
            goto L_0x040f
        L_0x040d:
            r6 = r21
        L_0x040f:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r8 = r14.mListAnchors
            r8 = r8[r3]
            androidx.constraintlayout.solver.SolverVariable r8 = r8.mSolverVariable
        L_0x0415:
            if (r5 == 0) goto L_0x041c
            int r5 = r5.getMargin()
            int r4 = r4 + r5
        L_0x041c:
            r18 = r4
            if (r15 == 0) goto L_0x0429
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r15.mListAnchors
            r3 = r4[r3]
            int r3 = r3.getMargin()
            int r0 = r0 + r3
        L_0x0429:
            r3 = r0
            if (r17 == 0) goto L_0x042f
            r20 = 6
            goto L_0x0431
        L_0x042f:
            r20 = 4
        L_0x0431:
            if (r1 == 0) goto L_0x044d
            if (r2 == 0) goto L_0x044d
            if (r6 == 0) goto L_0x044d
            if (r8 == 0) goto L_0x044d
            r4 = 1056964608(0x3f000000, float:0.5)
            r0 = r37
            r5 = r6
            r6 = r8
            r22 = r7
            r7 = r18
            r18 = r15
            r15 = 8
            r8 = r20
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0453
        L_0x044d:
            r22 = r7
            r18 = r15
            r15 = 8
        L_0x0453:
            r0 = r22
            goto L_0x045a
        L_0x0456:
            r18 = r15
            r15 = 8
        L_0x045a:
            int r1 = r14.getVisibility()
            if (r1 == r15) goto L_0x0461
            goto L_0x0463
        L_0x0461:
            r14 = r18
        L_0x0463:
            r15 = r14
            r8 = 8
            r14 = r0
            goto L_0x03ad
        L_0x0469:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r10.mListAnchors
            r1 = r1[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            int r3 = r39 + 1
            r10 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r2 = r11.mListAnchors
            r2 = r2[r3]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r14 = r2.mTarget
            if (r1 == 0) goto L_0x04b6
            if (r12 == r13) goto L_0x0490
            androidx.constraintlayout.solver.SolverVariable r2 = r0.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r1 = r1.mSolverVariable
            int r0 = r0.getMargin()
            r15 = 4
            r9.addEquality(r2, r1, r0, r15)
            goto L_0x04b7
        L_0x0490:
            r15 = 4
            if (r14 == 0) goto L_0x04b7
            androidx.constraintlayout.solver.SolverVariable r2 = r0.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r3 = r1.mSolverVariable
            int r4 = r0.getMargin()
            r5 = 1056964608(0x3f000000, float:0.5)
            androidx.constraintlayout.solver.SolverVariable r6 = r10.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r7 = r14.mSolverVariable
            int r8 = r10.getMargin()
            r17 = 4
            r0 = r37
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r17
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x04b7
        L_0x04b6:
            r15 = 4
        L_0x04b7:
            if (r14 == 0) goto L_0x04c7
            if (r12 == r13) goto L_0x04c7
            androidx.constraintlayout.solver.SolverVariable r0 = r10.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r1 = r14.mSolverVariable
            int r2 = r10.getMargin()
            int r2 = -r2
            r9.addEquality(r0, r1, r2, r15)
        L_0x04c7:
            if (r25 != 0) goto L_0x04cb
            if (r16 == 0) goto L_0x052e
        L_0x04cb:
            if (r12 == 0) goto L_0x052e
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            int r2 = r39 + 1
            r1 = r1[r2]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            if (r3 == 0) goto L_0x04e0
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            androidx.constraintlayout.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x04e2
        L_0x04e0:
            r3 = r21
        L_0x04e2:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r1.mTarget
            if (r4 == 0) goto L_0x04eb
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r1.mTarget
            androidx.constraintlayout.solver.SolverVariable r4 = r4.mSolverVariable
            goto L_0x04ed
        L_0x04eb:
            r4 = r21
        L_0x04ed:
            if (r11 == r13) goto L_0x04fe
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r4 = r4[r2]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r5 = r4.mTarget
            if (r5 == 0) goto L_0x04fc
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            androidx.constraintlayout.solver.SolverVariable r4 = r4.mSolverVariable
            goto L_0x04fe
        L_0x04fc:
            r4 = r21
        L_0x04fe:
            r5 = r4
            if (r12 != r13) goto L_0x0509
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r39]
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r2]
        L_0x0509:
            if (r3 == 0) goto L_0x052e
            if (r5 == 0) goto L_0x052e
            r4 = 1056964608(0x3f000000, float:0.5)
            int r6 = r0.getMargin()
            if (r13 != 0) goto L_0x0516
            goto L_0x0517
        L_0x0516:
            r11 = r13
        L_0x0517:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor[] r7 = r11.mListAnchors
            r2 = r7[r2]
            int r7 = r2.getMargin()
            androidx.constraintlayout.solver.SolverVariable r2 = r0.mSolverVariable
            androidx.constraintlayout.solver.SolverVariable r8 = r1.mSolverVariable
            r10 = 5
            r0 = r37
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r8
            r8 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x052e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Chain.applyChainConstraints(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer, androidx.constraintlayout.solver.LinearSystem, int, int, androidx.constraintlayout.solver.widgets.ChainHead):void");
    }
}
