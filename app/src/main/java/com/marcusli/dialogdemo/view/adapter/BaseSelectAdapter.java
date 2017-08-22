package com.marcusli.dialogdemo.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by MarcusLi on 2017/4/25.
 */

public abstract class BaseSelectAdapter extends BaseAdapter {
    protected Context context;
    protected List list;
    protected Method getMethod;//get方法
    protected final String TAG = this.getClass().getSimpleName();
    protected Class clazz;
    protected LayoutInflater inflater;

    /**
     * @param context
     * @param list  列表
     * @param clazz     bean对象
     * @param showColum   bean对象中用于显示的字段名
     */
    public BaseSelectAdapter(Context context, List list, Class clazz, String showColum) {
        this.context = context;
        this.inflater  = LayoutInflater.from(context);
        this.list = list;
        this.clazz = clazz;
        Log.e(TAG,clazz.getName());
        //获取泛型类里的某个方法
        String methodName = "get" + showColum.substring(0, 1).toUpperCase() + showColum.substring(1);
        try {
            Method method = clazz.getDeclaredMethod(methodName);
            this.getMethod = method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Log.e(TAG, "找不到这个方法!");
        }
    }
}
