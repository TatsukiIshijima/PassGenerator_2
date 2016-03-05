package com.example.ti.sampleapplication1.tools;

import android.content.Context;

import com.example.ti.sampleapplication1.MyApplication;

import java.util.ArrayList;
import java.util.List;

import greendao.Mail;
import greendao.MailDao;

/**
 * Created by TI on 2016/01/22.
 */
public class PassWordListItem {

    private String PassTitle;
    private String UserId;
    private String PassWord;

    public String getPassTitle() {
        return this.PassTitle;
    }

    public void setPassTitle(String passTitle) {
        this.PassTitle = passTitle;
    }

    public String getUserId() {
        return this.UserId;
    }

    public void setUserId(String userId) {
        this.UserId = userId;
    }

    public String getPassWord() {
        return this.PassWord;
    }

    public void setPassWord(String passWord) {
        this.PassWord = passWord;
    }

    public static List<PassWordListItem> contentItem(int titleNumber) {
        List<PassWordListItem> items = new ArrayList<>();

        String[] title0 = {"SNS", "Web", "マイコンピュータ", "マイナビ", "リクナビ", "キャリタス", "会員"};
        String[] title1 = {"1", "2", "3", "4", "5", "6", "7"};
        String[] title2 = {"r", "t", "f", "g", "h", "j", "k"};

        for (int i = 0; i < title1.length; i++) {
            PassWordListItem item = new PassWordListItem();

            if (titleNumber == 0) {
                item.setPassTitle(title0[i]);
            } else if (titleNumber == 1) {
                item.setPassTitle(title1[i]);
            } else if (titleNumber == 2) {
                item.setPassTitle(title2[i]);
            } else {
                item.setPassTitle(title0[i]);
            }
            items.add(item);
        }
        return items;
    }
}
