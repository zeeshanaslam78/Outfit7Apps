package com.xeeshi.outfit7apps.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xeeshi.outfit7apps.R;
import com.xeeshi.outfit7apps.data.Permission;

import java.util.List;

/**
 * Created by ZEESHAN on 06/01/2018.
 */

public class PermissionAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<Permission> permissionList;

    public PermissionAdapter(Context context, List<Permission> permissionList) {
        this.permissionList = permissionList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return permissionList.size();
    }

    @Override
    public Object getItem(int position) {
        return permissionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.permission_list_item, parent, false);
            viewHolder.imgPermissionIcon = convertView.findViewById(R.id.img_permission_icon);
            viewHolder.txtPermissionLabel = convertView.findViewById(R.id.txt_permission_label);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String label = permissionList.get(position).getLabel();
        if (null != label && label.length()>0)
            viewHolder.txtPermissionLabel.setText(label);

        Drawable icon = permissionList.get(position).getIcon();
        if (null!=icon)
            viewHolder.imgPermissionIcon.setImageDrawable(icon);

        return convertView;
    }

    static class ViewHolder {
        ImageView imgPermissionIcon;
        TextView txtPermissionLabel;
    }

}
