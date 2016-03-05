package com.example.ti.sampleapplication1.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ti.sampleapplication1.R;
import com.example.ti.sampleapplication1.tools.PassWordListItem;

import java.util.List;

/**
 * Created by TI on 2016/01/22.
 */
public class PassWordListAdapter extends ArrayAdapter<PassWordListItem> {

    private LayoutInflater mLayoutInflater;
    //private int resourceId;
    //private List<CategoryListItem> mItems;

    public PassWordListAdapter(Context context, List<PassWordListItem> objects) {
        super(context, 0, objects);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.password_list_row, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.password_item_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String ContentTitle = getItem(position).getPassTitle();

        holder.title.setText(ContentTitle);

        return convertView;
    }

    private class ViewHolder {

        TextView title;
    }
}
