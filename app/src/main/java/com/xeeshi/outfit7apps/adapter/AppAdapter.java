package com.xeeshi.outfit7apps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xeeshi.outfit7apps.R;
import com.xeeshi.outfit7apps.data.AppList;

import java.util.List;

/**
 * Created by ZEESHAN on 05/01/2018.
 */

public class AppAdapter extends BaseAdapter {

    private List<AppList> appsList;
    private LayoutInflater layoutInflater;

    public AppAdapter(Context context, List<AppList> appsList) {
        this.appsList = appsList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return appsList.size();
    }

    @Override
    public Object getItem(int position) {
        return appsList.get(position);
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
            convertView = layoutInflater.inflate(R.layout.app_list_item, parent, false);
            viewHolder.txtAppName = convertView.findViewById(R.id.txt_app_name);
            viewHolder.imgAppIcon = convertView.findViewById(R.id.img_app_icon);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtAppName.setText(appsList.get(position).getName());
        viewHolder.imgAppIcon.setImageDrawable(appsList.get(position).getIcon());

        return convertView;
    }

    static class ViewHolder {
        TextView txtAppName;
        ImageView imgAppIcon;
    }

}
