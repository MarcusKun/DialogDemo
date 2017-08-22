package com.marcusli.dialogdemo.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.marcusli.dialogdemo.R;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by MarcusLi on 2017/4/25.
 */

public class RadioAdapter extends BaseSelectAdapter {
    //表示当前选中的RadioButton,-1代表未选中状态
    private int currentSelect = -1;

    public RadioAdapter(Context context, List list, Class clazz, String showColum) {
        super(context, list, clazz, showColum);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_dialog_radio, null);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_item_dialog_radio);
            holder.rbCheck = (RadioButton) convertView.findViewById(R.id.rb_item_dialog_radio);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        try {
            holder.tvTitle.setText(getMethod.invoke(list.get(position)).toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        holder.rbCheck.setChecked(false);
        if (position == currentSelect) {
            holder.rbCheck.setChecked(true);
        }
        return convertView;
    }

    /**
     * 设置选中项
     * @param currentSelect
     */
    public void setCurrentSelect(int currentSelect) {
        this.currentSelect = currentSelect;
        this.notifyDataSetChanged();
    }

    /**
     * 返回选中项
     * @return
     */
    public int getCurrentSelect(){
        return currentSelect;
    }

    private class ViewHolder {
        TextView tvTitle;
        RadioButton rbCheck;
    }
}
