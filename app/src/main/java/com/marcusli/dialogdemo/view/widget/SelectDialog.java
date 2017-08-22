package com.marcusli.dialogdemo.view.widget;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.marcusli.dialogdemo.R;
import com.marcusli.dialogdemo.view.adapter.CheckBoxAdapter;
import com.marcusli.dialogdemo.view.adapter.RadioAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MarcusLi on 2017/4/25.
 */
public class SelectDialog extends BaseDialog implements View.OnClickListener {
    public static final int RADIOBUTTON = 1;//单选
    public static final int CHECKBOX = 2;//多选

    private int mode = 1;//当前模式(单选还是多选)

    private ListView lvContent;
    private TextView tvTitle;
    private Button btnOk;
    private Button btnCancel;

    private List list;
    private String showColum;
    private Class clazz;

    private OnOkClickListener listener;

    public void setListener(OnOkClickListener listener) {
        this.listener = listener;
    }

    public interface OnOkClickListener {
        void okClick(List<Integer> resultId);
    }

    /**
     * @param context   上下文
     * @param mode      显示模式,单选还是多选
     * @param list      list数据
     * @param clazz     bean对象
     * @param showColum bean对象中用于显示的字段名
     */
    public SelectDialog(Context context, int mode, List list, Class clazz, String showColum) {
        Log.e("TAG",this.toString());
        this.context = context;
        this.mode = mode;
        this.list = list;
        this.showColum = showColum;
        this.clazz = clazz;
    }

    @Override
    protected View initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_select, null);
        tvTitle = (TextView) view.findViewById(R.id.tv_dialog_select_title);
        lvContent = (ListView) view.findViewById(R.id.lv_dialog_select_content);
        btnOk = (Button) view.findViewById(R.id.btn_dialog_select_ok);
        btnCancel = (Button) view.findViewById(R.id.btn_dialog_select_cancel);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        if (mode == RADIOBUTTON) {
            initRadioDialog();
        } else if (mode == CHECKBOX) {
            initCheckBoxDialog();
        }
        return view;
    }

    private RadioAdapter radioAdapter;
    private CheckBoxAdapter checkBoxAdapter;
    //初始化CheckBox数据
    private void initCheckBoxDialog() {
        checkBoxAdapter = new CheckBoxAdapter(context, list, clazz, showColum);
        lvContent.setAdapter(checkBoxAdapter);
        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                checkBoxAdapter.setSelectState(view, position);
            }
        });
    }
    //初始化Radio数据
    private void initRadioDialog() {
        radioAdapter = new RadioAdapter(context, list, clazz, showColum);
        lvContent.setAdapter(radioAdapter);
        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                radioAdapter.setCurrentSelect(position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_select_ok:
                List<Integer> result = null;
                if (mode == RADIOBUTTON) {
                    int current = radioAdapter.getCurrentSelect();
                    if (current != -1) {
                        result = new ArrayList<>();
                        result.add(current);
                    }
                } else if (mode == CHECKBOX) {
                    List<Integer> selectItem = checkBoxAdapter.getSelectItem();
                    if (selectItem != null && selectItem.size() > 0) {
                        result = selectItem;
                    }
                }
                if (listener != null && result != null) {
                    listener.okClick(result);
                }
                hideDialog();
                break;
            case R.id.btn_dialog_select_cancel:
                hideDialog();
                break;
        }
    }
}
