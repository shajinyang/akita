package com.sjy.akita_ui.poper.popAnim;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.sjy.akita_ui.R;

public class AnimationUtil {

    /**
     * Animates a view so that it slides in from the left of it's container.
     *
     * @param context
     * @param view
     */
    public static void slideInFromLeft(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.akita_anim_slide_from_left);
    }

    /**
     * Animates a view so that it slides from its current position, out of view to the left.
     *
     * @param context
     * @param view
     */
    public static void slideOutToLeft(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.akita_anim_slide_to_left);
    }

    /**
     * Animates a view so that it slides in the from the right of it's container.
     *
     * @param context
     * @param view
     */
    public static void slideInFromRight(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.akita_anim_slide_from_right);
    }

    /**
     * Animates a view so that it slides from its current position, out of view to the right.
     *
     * @param context
     * @param view
     */
    public static void slideOutToRight(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.akita_anim_slide_to_right);
    }

    /**
     * Animates a view so that it slides in the from the top of it's container.
     *
     * @param context
     * @param view
     */
    public static void slideInFromTop(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.akita_anim_slide_from_top);
    }

    /**
     * Animates a view so that it slides from its current position, out of view to the top.
     * @param context
     * @param view
     */
    public static void slideOutToTop(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.akita_anim_slide_to_top);
    }

    /**
     * Animates a view so that it slides in the from the bottom of it's container.
     *
     * @param context
     * @param view
     */
    public static void slideInFromBottom(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.akita_anim_slide_from_bottom);
    }

    /**
     * Animates a view so that it slides from its current position, out of view to the bottom.
     * @param context
     * @param view
     */
    public static void slideOutToBottom(Context context, View view) {
        runSimpleAnimation(context, view, R.anim.akita_anim_slide_to_bottom);
    }


    public static void alphaIn(Context context, View view){
        runSimpleAnimation(context, view, R.anim.akita_anim_alpha_in);
    }

    public static void alphaOut(Context context, View view){
        runSimpleAnimation(context, view, R.anim.akita_anim_alpha_out);
    }

    /**
     * Runs a simple animation on a View with no extra parameters.
     *
     * @param context
     * @param view
     * @param animationId
     */
    private static void runSimpleAnimation(Context context, View view, int animationId) {
        view.startAnimation(AnimationUtils.loadAnimation(
                context, animationId
        ));
    }

}