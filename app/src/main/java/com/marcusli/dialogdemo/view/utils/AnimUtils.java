package com.marcusli.dialogdemo.view.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

/**
 * Created by MarcusLi on 2017/4/25.
 * 动画工具类
 */
public class AnimUtils {
    /**
     * Progress开始时执行的
     * @param view
     */
    public static void progressDialogEnter(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(1000);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);

        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        view.startAnimation(animationSet);
    }

    public static void progressDialogExit(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(1000);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0, 1, 0,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);

        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        view.startAnimation(animationSet);
    }


}
