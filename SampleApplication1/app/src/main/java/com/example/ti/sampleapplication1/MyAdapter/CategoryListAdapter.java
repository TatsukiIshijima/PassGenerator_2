package com.example.ti.sampleapplication1.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ti.sampleapplication1.R;
import com.example.ti.sampleapplication1.tools.CategoryListItem;

import java.util.List;

/**
 * Created by TI on 2016/01/17.
 */
public class CategoryListAdapter extends ArrayAdapter<CategoryListItem> {

    private LayoutInflater mLayoutInflater;
    //private int resourceId;
    //private List<CategoryListItem> mItems;

    public CategoryListAdapter(Context context, List<CategoryListItem> objects) {
        super(context, 0, objects);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.category_list_row, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.category_item_text);
            holder.imageid = (ImageView) convertView.findViewById(R.id.categroy_item_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String ContentTitle = getItem(position).getmTitle();
        int ContentIamgeId = getItem(position).getmImageId();

        holder.title.setText(ContentTitle);
        holder.imageid.setImageResource(ContentIamgeId);

        return convertView;
    }

    private class ViewHolder {

        TextView title;
        ImageView imageid;
    }
}
