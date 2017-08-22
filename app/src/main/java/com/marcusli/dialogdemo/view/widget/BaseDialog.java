package com.marcusli.dialogdemo.view.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by MarcusLi on 2017/4/25.
 */
public abstract class BaseDialog {
    protected Context context;
    protected View view;
    private AlertDialog dialog;

    /**
     * 显示Dialog
     */
    public void showDialog() {
        Log.e("TAG", "" + (dialog == null));
        if (dialog != null && isDialogShowing()) {
//            Log.e("TAG", "" + "这里");
            return;
        }
        dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        view = initView(context);
        //这里得放在dialog.show后面
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(null);
        window.setGravity(Gravity.CENTER_HORIZONTAL);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (outMetrics.widthPixels * 0.9);
        window.setAttributes(lp);
        window.setContentView(view);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog = null;
//                Log.e("TAG", "DialogDismiss");
            }
        });
    }

    /**
     * 隐藏Dialog
     */
    public void hideDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
            dialog = null;
        }
    }

    /**
     * 初始化Dialog界面
     *
     * @param context
     * @return
     */
    protected abstract View initView(Context context);

    public boolean isDialogShowing() {
        if (dialog != null && dialog.isShowing()) {
            return true;
        }
        return false;
    }
}
