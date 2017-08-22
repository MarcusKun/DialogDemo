package com.marcusli.dialogdemo.view.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.marcusli.dialogdemo.R;
import com.marcusli.dialogdemo.view.utils.AnimUtils;

/**
 * Created by MarcusLi on 2017/4/25.
 */
public class ProgressDialog extends BaseDialog {
    public ProgressDialog(Context context) {
        this.context = context;
    }

    private static final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static final int MP = ViewGroup.LayoutParams.MATCH_PARENT;
    @Override
    protected View initView(Context context) {
        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams LLparams = new LinearLayout.LayoutParams(WC, WC);
        LLparams.gravity = Gravity.CENTER;
        layout.setMinimumWidth(px2dip(context, 220));
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(px2dip(context, 5), px2dip(context, 10), px2dip(context, 5), px2dip(context, 10));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundResource(R.drawable.loading_layout_bg);

        ProgressBar progressBar = new ProgressBar(context);
        ViewGroup.LayoutParams PBparams = new ViewGroup.LayoutParams((int) context.getResources().getDimension(R.dimen.progress_size), (int) context.getResources().getDimension(R.dimen.progress_size));
        progressBar.setIndeterminate(true);
        progressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.progress_spinner_cicle_white));
        progressBar.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.progress_spinner_cicle_white));
        progressBar.setLayoutParams(PBparams);

        TextView textView = new TextView(context);
        LinearLayout.LayoutParams TVparams = new LinearLayout.LayoutParams(MP, WC);
        TVparams.setMargins(px2dip(context, 5), px2dip(context, 10), px2dip(context, 5), px2dip(context, 0));
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTextColor(Color.WHITE);
        textView.setText(R.string.progress_text);
        textView.setTextSize(context.getResources().getDimension(R.dimen.progress_text_size));

        layout.addView(progressBar);
        layout.addView(textView);
        AnimUtils.progressDialogEnter(layout);
        return layout;
    }

    /**
     * dip转px函数。
     *
     * @param context 程序上下文
     * @param dpValue dip值
     * @return px值
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
