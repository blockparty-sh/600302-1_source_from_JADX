package androidx.constraintlayout.motion.widget;

import androidx.constraintlayout.motion.widget.MotionScene.Transition;
import androidx.constraintlayout.widget.ConstraintSet;

public class TransitionBuilder {
    private static final String TAG = "TransitionBuilder";

    public static void validate(MotionLayout motionLayout) {
        if (motionLayout.mScene != null) {
            MotionScene motionScene = motionLayout.mScene;
            if (!motionScene.validateLayout(motionLayout)) {
                throw new RuntimeException("MotionLayout doesn't have the right motion scene.");
            } else if (motionScene.mCurrentTransition == null || motionScene.getDefinedTransitions().isEmpty()) {
                throw new RuntimeException("Invalid motion layout. Motion Scene doesn't have any transition.");
            }
        } else {
            throw new RuntimeException("Invalid motion layout. Layout missing Motion Scene.");
        }
    }

    public static Transition buildTransition(MotionScene motionScene, int i, int i2, ConstraintSet constraintSet, int i3, ConstraintSet constraintSet2) {
        Transition transition = new Transition(i, motionScene, i2, i3);
        updateConstraintSetInMotionScene(motionScene, transition, constraintSet, constraintSet2);
        return transition;
    }

    private static void updateConstraintSetInMotionScene(MotionScene motionScene, Transition transition, ConstraintSet constraintSet, ConstraintSet constraintSet2) {
        int startConstraintSetId = transition.getStartConstraintSetId();
        int endConstraintSetId = transition.getEndConstraintSetId();
        motionScene.setConstraintSet(startConstraintSetId, constraintSet);
        motionScene.setConstraintSet(endConstraintSetId, constraintSet2);
    }
}
