package com.marcusli.dialogdemo.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.marcusli.dialogdemo.R;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MarcusLi on 2017/4/25.
 */

public class CheckBoxAdapter extends BaseSelectAdapter{
    private HashMap<Integer, Boolean> isSelected;
    private List<Integer> selectItem;//用来保存选中项

    public CheckBoxAdapter(Context context, List list, Class clazz, String showColum) {
        super(context, list, clazz, showColum);
        isSelected = new HashMap<>();
        selectItem = new ArrayList<>();
        for (int i = 0; i < list.size(); ++i) {
            isSelected.put(i, false);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
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
            convertView = inflater.inflate(R.layout.item_dialog_checkbox, null);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_item_dialog_check);
            holder.cbCheck = (CheckBox) convertView.findViewById(R.id.cb_item_dialog_check);
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
        holder.cbCheck.setChecked(getIsSelected().get(position));
        return convertView;
    }

    public HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    /**
     * 设置选中状态
     */
    public void setSelectState(View view, int position) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.cbCheck.toggle();
        getIsSelected().put(position, holder.cbCheck.isChecked());
    }

    /**
     * 获取选中项
     * 把选中项加入到结果集中
     *
     * @return
     */
    public List getSelectItem() {
        for (int i = 0; i < isSelected.size(); ++i) {
            if (isSelected.get(i)) {
                selectItem.add(i);
            }
        }
        return selectItem;
    }

    /**
     * 设置选中的项目
     *
     * @param selectItem
     */
    public void setSelectItem(List<Integer> selectItem) {
        this.selectItem = selectItem;
    }

    private class ViewHolder {
        TextView tvTitle;
        CheckBox cbCheck;
    }
}
