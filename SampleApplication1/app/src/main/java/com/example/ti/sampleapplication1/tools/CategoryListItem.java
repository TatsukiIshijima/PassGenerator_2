package com.example.ti.sampleapplication1.tools;

import com.example.ti.sampleapplication1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TI on 2016/01/17.
 */
public class CategoryListItem {
    private int mImageId;
    private String mTitle;

    public int getmImageId() {
        return mImageId;
    }

    public void setmImageId(int id) {
        mImageId = id;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String name) {
        mTitle = name;
    }

    public static List<CategoryListItem> contentItem() {
        List<CategoryListItem> items = new ArrayList<>();

        final String[] category = {"メール","SNS", "Web","コンピュータ","ショッピング","クレジットカード", "銀行", "その他"};
        final int[] imageId = {R.drawable.mail, R.drawable.sns,R.drawable.webpage, R.drawable.computer, R.drawable.shop, R.drawable.creditcard, R.drawable.bank, R.drawable.file};

        for (int i=0; i<category.length; i++) {
            CategoryListItem item = new CategoryListItem();
            item.setmTitle(category[i]);
            item.setmImageId(imageId[i]);
            items.add(item);
        };
        return items;
    }
}
