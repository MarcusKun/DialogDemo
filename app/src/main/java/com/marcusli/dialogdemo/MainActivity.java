package com.marcusli.dialogdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.marcusli.dialogdemo.entity.User;
import com.marcusli.dialogdemo.view.widget.HintDialog;
import com.marcusli.dialogdemo.view.widget.ProgressDialog;
import com.marcusli.dialogdemo.view.widget.SelectDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] titles;
    private List<Button> btnList;
    private LinearLayout rootView;

    private SelectDialog radioDialog;
    private SelectDialog checkDialog;
    private HintDialog hintDialog;
    private ProgressDialog progressDialog;

    private List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        titles = new String[]{getResources().getString(R.string.btn_loading_dialog),
                getResources().getString(R.string.btn_hint_dialog),
                getResources().getString(R.string.btn_radio_dialog),
                getResources().getString(R.string.btn_check_dialog)};
        btnList = new ArrayList<>();
        list = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            User user = new User();
            user.setUsername("编号:" + i);
            user.setPassword(i + "");
            user.setAddress("地址:" + i);
            list.add(user);
        }
    }

    private void initView() {
        rootView = new LinearLayout(this);
        rootView.setGravity(Gravity.CENTER_HORIZONTAL);
        rootView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        rootView.setOrientation(LinearLayout.VERTICAL);
        setContentView(rootView);
        for (int i = 0; i < titles.length; ++i) {
            Button btn = new Button(this);
            btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            btn.setText(titles[i]);
            btn.setOnClickListener(this);
            btnList.add(btn);
            rootView.addView(btn);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnList.get(0)) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(MainActivity.this);
            }
            progressDialog.showDialog();
        } else if (v == btnList.get(1)) {
            if (hintDialog == null) {
                hintDialog = new HintDialog(MainActivity.this, "标题", "内容");
            }
            hintDialog.showDialog();
            hintDialog.setListener(new HintDialog.OnButtonClickListener() {
                @Override
                public void okClick() {
                    Toast.makeText(MainActivity.this, "提示对话框", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (v == btnList.get(2)) {
            if (radioDialog == null) {
                radioDialog = new SelectDialog(MainActivity.this, SelectDialog.RADIOBUTTON, list, User.class, "username");
            }
            radioDialog.showDialog();
            radioDialog.setListener(new SelectDialog.OnOkClickListener() {
                @Override
                public void okClick(List<Integer> resultId) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Integer i : resultId) {
                        stringBuffer.append(i + "_");
                    }
                    Toast.makeText(MainActivity.this, stringBuffer, Toast.LENGTH_SHORT).show();
                }
            });
        } else if (v == btnList.get(3)) {
            if (checkDialog == null) {
                checkDialog = new SelectDialog(MainActivity.this, SelectDialog.CHECKBOX, list, User.class, "username");
            }
            checkDialog.showDialog();
            checkDialog.setListener(new SelectDialog.OnOkClickListener() {
                @Override
                public void okClick(List<Integer> resultId) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Integer i : resultId) {
                        stringBuffer.append(i + "_");
                    }
                    Toast.makeText(MainActivity.this, stringBuffer, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
