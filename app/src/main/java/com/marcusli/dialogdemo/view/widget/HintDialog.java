package com.marcusli.dialogdemo.view.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.marcusli.dialogdemo.R;

/**
 * Created by MarcusLi on 2017/4/26.
 * 提示对话框
 */
public class HintDialog extends BaseDialog implements View.OnClickListener {
    private TextView tvTitle;//标题文字
    private TextView tvContent;//正文
    private Button btnOk;
    private Button btnCancel;

    private OnButtonClickListener listener;

    private String title;
    private String content;

    public HintDialog(Context context) {
        this.context = context;
    }

    public HintDialog(Context context, String title, String content) {
        this.title = title;
        this.content = content;
        this.context = context;
    }

    @Override
    protected View initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_hint, null);
        tvTitle = (TextView) view.findViewById(R.id.tv_hint_dialog_title);
        tvContent = (TextView) view.findViewById(R.id.tv_hint_dialog_content);
        btnOk = (Button) view.findViewById(R.id.btn_hint_dialog_ok);
        btnCancel = (Button) view.findViewById(R.id.btn_hint_dialog_cancel);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        return view;
    }

    /**
     * 设置标题文字
     *
     * @param title
     */
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    /**
     * 设置正文内容
     *
     * @param content
     */
    public void setContent(String content) {
        tvContent.setText(content);
    }

    /**
     * 设置OK按钮的显示
     *
     * @param okText
     */
    public void setBtnOkText(String okText) {
        btnOk.setText(okText);
    }

    /**
     * 设置取消按钮显示的文字
     *
     * @param cancelText
     */
    public void setBtnCancelText(String cancelText) {
        btnCancel.setText(cancelText);
    }

    public void setListener(OnButtonClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_hint_dialog_ok:
                if (listener != null) {
                    listener.okClick();
                }
                hideDialog();
                break;
            case R.id.btn_hint_dialog_cancel:
                hideDialog();
                break;
            default:
                break;
        }
    }

    public interface OnButtonClickListener {
        void okClick();
    }
}
