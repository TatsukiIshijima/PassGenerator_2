package com.example.ti.sampleapplication1.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ti.sampleapplication1.MyAdapter.PassWordListAdapter;
import com.example.ti.sampleapplication1.R;
import com.example.ti.sampleapplication1.tools.PassWordListItem;

import java.util.ArrayList;
import java.util.List;

import greendao.Bank;
import greendao.BankDao;
import greendao.Computer;
import greendao.ComputerDao;
import greendao.Credit;
import greendao.CreditDao;
import greendao.DaoMaster;
import greendao.Mail;
import greendao.MailDao;
import greendao.Other;
import greendao.OtherDao;
import greendao.SNS;
import greendao.SNSDao;
import greendao.Shopping;
import greendao.ShoppingDao;
import greendao.Web;
import greendao.WebDao;

/**
 * Created by TI on 2016/01/20.
 */
public class PassWord_List_Fragment extends Fragment {

    public PassWord_List_Fragment() {
    }

    private ListView mListView;
    private List<PassWordListItem> mPassWordList;
    private PassWordListAdapter mPassWordListAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        setHasOptionsMenu(false);
        //getActivity().setTitle("一覧");

        View view = inflater.inflate(R.layout.password_list_fragment, container, false);

        // フラグメントの値を受け取る
        final int num = getArguments().getInt("position");

        switch (num) {
            case 0:
                getActivity().setTitle("メール");
                mPassWordList = loadMail();
                break;
            case 1:
                getActivity().setTitle("SNS");
                mPassWordList = loadSNS();
                break;
            case 2:
                getActivity().setTitle("Web");
                mPassWordList = loadWeb();
                break;
            case 3:
                getActivity().setTitle("コンピュータ");
                mPassWordList = loadComputer();
                break;
            case 4:
                getActivity().setTitle("ショッピング");
                mPassWordList = loadShopping();
                break;
            case 5:
                getActivity().setTitle("クレジットカード");
                mPassWordList = loadCredit();
                break;
            case 6:
                getActivity().setTitle("銀行");
                mPassWordList = loadBank();
                break;
            case 7:
                getActivity().setTitle("その他");
                mPassWordList = loadOther();
                break;
            default:
                break;
        }

        // Listviewのインスタンスを取得
        mListView = (ListView) view.findViewById(R.id.passlistView);
        //mPassWordList = PassWordListItem.contentItem(num);
        //mPassWordList = this.loadMail();
        //mPassWordList = this.loadWeb();
        mPassWordListAdapter = new PassWordListAdapter(getActivity(),mPassWordList);

        mListView.setAdapter(mPassWordListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                final PassWordListItem passworditem = (PassWordListItem) mListView.getAdapter().getItem(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle(passworditem.getPassTitle());
                dialog.setMessage("ユーザーID : " + passworditem.getUserId() + "\n" + "\n" + "パスワード : " + passworditem.getPassWord());
                dialog.setPositiveButton(android.R.string.ok, null);
                dialog.setNeutralButton("削除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (num) {
                            case 0:
                                deleteMail(passworditem.getPassTitle(), passworditem.getUserId(), passworditem.getPassWord());
                                Toast.makeText(getContext(), "削除しました。", Toast.LENGTH_SHORT).show();
                                mPassWordList = loadMail();
                                break;
                            case 1:
                                deleteSNS(passworditem.getPassTitle(), passworditem.getUserId(), passworditem.getPassWord());
                                Toast.makeText(getContext(), "削除しました。", Toast.LENGTH_SHORT).show();
                                mPassWordList = loadSNS();
                                break;
                            case 2:
                                deleteWeb(passworditem.getPassTitle(), passworditem.getUserId(), passworditem.getPassWord());
                                Toast.makeText(getContext(), "削除しました。", Toast.LENGTH_SHORT).show();
                                mPassWordList = loadWeb();
                                break;
                            case 3:
                                deleteComputer(passworditem.getPassTitle(), passworditem.getUserId(), passworditem.getPassWord());
                                Toast.makeText(getContext(), "削除しました。", Toast.LENGTH_SHORT).show();
                                mPassWordList = loadComputer();
                                break;
                            case 4:
                                deleteShopping(passworditem.getPassTitle(), passworditem.getUserId(), passworditem.getPassWord());
                                Toast.makeText(getContext(), "削除しました。", Toast.LENGTH_SHORT).show();
                                mPassWordList = loadShopping();
                                break;
                            case 5:
                                deleteCredit(passworditem.getPassTitle(), passworditem.getUserId(), passworditem.getPassWord());
                                Toast.makeText(getContext(), "削除しました。", Toast.LENGTH_SHORT).show();
                                mPassWordList = loadCredit();
                                break;
                            case 6:
                                deleteBank(passworditem.getPassTitle(), passworditem.getUserId(), passworditem.getPassWord());
                                Toast.makeText(getContext(), "削除しました。", Toast.LENGTH_SHORT).show();
                                mPassWordList = loadBank();
                                break;
                            case 7:
                                deleteOther(passworditem.getPassTitle(), passworditem.getUserId(), passworditem.getPassWord());
                                Toast.makeText(getContext(), "削除しました。", Toast.LENGTH_SHORT).show();
                                mPassWordList = loadOther();
                                break;
                            default:
                                break;
                        }
                        mPassWordListAdapter = new PassWordListAdapter(getActivity(),mPassWordList);
                        mListView.setAdapter(mPassWordListAdapter);
                    }
                });
                dialog.show();
                //container.removeAllViews();
                //view = inflater.inflate(R.layout.password_list_fragment, container, false);
            }
        });
        return view;
    }

    private  List<PassWordListItem> loadMail() {
        List<PassWordListItem> items = new ArrayList<>();
        List<Mail> list = Regist_Fragment.getMailDao(getContext()).loadAll();

        for (Mail mail : list) {
            PassWordListItem item = new PassWordListItem();
            item.setPassTitle(mail.getTitle());
            item.setUserId(mail.getUserid());
            item.setPassWord(mail.getPassword());
            items.add(item);
        }
        return items;
    }

    private List<PassWordListItem> loadSNS() {
        List<PassWordListItem> items = new ArrayList<>();
        List<SNS> list = Regist_Fragment.getSNSDao(getContext()).loadAll();

        for (SNS sns : list) {
            PassWordListItem item = new PassWordListItem();
            item.setPassTitle(sns.getTitle());
            item.setUserId(sns.getUserid());
            item.setPassWord(sns.getPassword());
            items.add(item);
        }
        return items;
    }

    private List<PassWordListItem> loadWeb() {
        List<PassWordListItem> items = new ArrayList<>();
        List<Web> list = Regist_Fragment.getWebDao(getContext()).loadAll();

        for (Web web : list) {
            PassWordListItem item = new PassWordListItem();
            item.setPassTitle(web.getTitle());
            item.setUserId(web.getUserid());
            item.setPassWord(web.getPassword());
            items.add(item);
        }
        return items;
    }

    private List<PassWordListItem> loadComputer() {
        List<PassWordListItem> items = new ArrayList<>();
        List<Computer> list = Regist_Fragment.getComputerDao(getContext()).loadAll();

        for (Computer computer : list) {
            PassWordListItem item = new PassWordListItem();
            item.setPassTitle(computer.getTitle());
            item.setUserId(computer.getUserid());
            item.setPassWord(computer.getPassword());
            items.add(item);
        }
        return items;
    }

    private List<PassWordListItem> loadShopping() {
        List<PassWordListItem> items = new ArrayList<>();
        List<Shopping> list = Regist_Fragment.getShoppingDao(getContext()).loadAll();

        for (Shopping shopping : list) {
            PassWordListItem item = new PassWordListItem();
            item.setPassTitle(shopping.getTitle());
            item.setUserId(shopping.getUserid());
            item.setPassWord(shopping.getPassword());
            items.add(item);
        }
        return  items;
    }

    private List<PassWordListItem> loadCredit() {
        List<PassWordListItem> items = new ArrayList<>();
        List<Credit> list = Regist_Fragment.getCreditDao(getContext()).loadAll();

        for (Credit credit : list) {
            PassWordListItem item = new PassWordListItem();
            item.setPassTitle(credit.getTitle());
            item.setUserId(credit.getUserid());
            item.setPassWord(credit.getPassword());
            items.add(item);
        }
        return items;
    }

    private List<PassWordListItem> loadBank() {
        List<PassWordListItem> items = new ArrayList<>();
        List<Bank> list = Regist_Fragment.getBankDao(getContext()).loadAll();

        for (Bank bank : list) {
            PassWordListItem item = new PassWordListItem();
            item.setPassTitle(bank.getTitle());
            item.setUserId(bank.getUserid());
            item.setPassWord(bank.getPassword());
            items.add(item);
        }
        return items;
    }

    private List<PassWordListItem> loadOther() {
        List<PassWordListItem> items = new ArrayList<>();
        List<Other> list = Regist_Fragment.getOtherDao(getContext()).loadAll();

        for (Other other : list) {
            PassWordListItem item = new PassWordListItem();
            item.setPassTitle(other.getTitle());
            item.setUserId(other.getUserid());
            item.setPassWord(other.getPassword());
            items.add(item);
        }
        return items;
    }

    private void deleteMail(String title, String userid, String password) {
        //Regist_Fragment.getMailDao(getContext()).deleteByKey(id);
        Mail mail = new Mail();
        mail.setTitle(title);
        mail.setUserid(userid);
        mail.setPassword(password);
        MailDao mailDao = Regist_Fragment.getMailDao(getContext());
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "example-db", null);
        SQLiteDatabase db =helper.getWritableDatabase();
        //db.delete(mailDao.getTablename(), "TITLE like '%" + mail.getTitle() + "%' AND USERID like '%" + mail.getUserid() + "%' AND PASSWORD like '%" + mail.getPassword() + "%'", null);
        db.delete(mailDao.getTablename(), "TITLE = '" + mail.getTitle().toString() + "' AND USERID = '" + mail.getUserid().toString() + "' AND PASSWORD = '" + mail.getPassword().toString() + "'", null);
    }

    private void deleteSNS(String title, String userid, String password) {
        SNS sns = new SNS();
        sns.setTitle(title);
        sns.setUserid(userid);
        sns.setPassword(password);
        SNSDao snsDao = Regist_Fragment.getSNSDao(getContext());
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "example-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        //db.delete(snsDao.getTablename(), "TITLE like '%" + sns.getTitle() + "%' AND USERID like '%" + sns.getUserid() + "%' AND PASSWORD like '%" + sns.getPassword() + "%'", null);
        db.delete(snsDao.getTablename(), "TITLE = '" + sns.getTitle().toString() + "' AND USERID = '" + sns.getUserid().toString() + "' AND PASSWORD = '" + sns.getPassword().toString() + "'", null);
    }

    private void deleteWeb(String title, String userid, String password) {
        //Regist_Fragment.getWebDao(getContext()).deleteByKey(id);
        Web web = new Web();
        web.setTitle(title);
        web.setUserid(userid);
        web.setPassword(password);
        WebDao webDao = Regist_Fragment.getWebDao(getContext());
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "example-db", null);
        SQLiteDatabase db =helper.getWritableDatabase();
        //db.delete(webDao.getTablename(), "TITLE like '%" + web.getTitle() + "%' AND USERID like '%" + web.getUserid() + "%' AND PASSWORD like '%" + web.getPassword() + "%'", null);
        db.delete(webDao.getTablename(), "TITLE = '" + web.getTitle().toString() + "' AND USERID = '" + web.getUserid().toString() + "' AND PASSWORD = '" + web.getPassword().toString() + "'", null);
    }

    private void deleteComputer(String title, String userid, String password) {
        //Regist_Fragment.getComputerDao(getContext()).deleteByKey(id);
        Computer computer = new Computer();
        computer.setTitle(title);
        computer.setUserid(userid);
        computer.setPassword(password);
        ComputerDao computerDao = Regist_Fragment.getComputerDao(getContext());
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "example-db", null);
        SQLiteDatabase db =helper.getWritableDatabase();
        //db.delete(computerDao.getTablename(), "TITLE like '%" + computer.getTitle() + "%' AND USERID like '%" + computer.getUserid() + "%' AND PASSWORD like '%" + computer.getPassword() + "%'", null);
        db.delete(computerDao.getTablename(), "TITLE = '" + computer.getTitle().toString() + "' AND USERID = '" + computer.getUserid().toString() + "' AND PASSWORD = '" + computer.getPassword().toString() + "'", null);
    }

    private void deleteShopping(String title, String userid, String password) {
        //Regist_Fragment.getShoppingDao(getContext()).deleteByKey(id);
        Shopping shopping = new Shopping();
        shopping.setTitle(title);
        shopping.setUserid(userid);
        shopping.setPassword(password);
        ShoppingDao shoppingDao = Regist_Fragment.getShoppingDao(getContext());
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "example-db", null);
        SQLiteDatabase db =helper.getWritableDatabase();
        //db.delete(shoppingDao.getTablename(), "TITLE like '%" + shopping.getTitle() + "%' AND USERID like '%" + shopping.getUserid() + "%' AND PASSWORD like '%" + shopping.getPassword() + "%'", null);
        db.delete(shoppingDao.getTablename(), "TITLE = '" + shopping.getTitle().toString() + "' AND USERID = '" + shopping.getUserid().toString() + "' AND PASSWORD = '" + shopping.getPassword().toString() + "'", null);
    }

    private void deleteCredit(String title, String userid, String password) {
        //Regist_Fragment.getCreditDao(getContext()).deleteByKey(id);
        Credit credit = new Credit();
        credit.setTitle(title);
        credit.setUserid(userid);
        credit.setPassword(password);
        CreditDao creditDao = Regist_Fragment.getCreditDao(getContext());
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "example-db", null);
        SQLiteDatabase db =helper.getWritableDatabase();
        //db.delete(creditDao.getTablename(), "TITLE like '%" + credit.getTitle() + "%' AND USERID like '%" + credit.getUserid() + "%' AND PASSWORD like '%" + credit.getPassword() + "%'", null);
        db.delete(creditDao.getTablename(), "TITLE = '" + credit.getTitle().toString() + "' AND USERID = '" + credit.getUserid().toString() + "' AND PASSWORD = '" + credit.getPassword().toString() + "'", null);
    }

    private void deleteBank(String title, String userid, String password) {
        Bank bank = new Bank();
        bank.setTitle(title);
        bank.setUserid(userid);
        bank.setPassword(password);
        BankDao bankDao = Regist_Fragment.getBankDao(getContext());
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "example-db", null);
        SQLiteDatabase db =helper.getWritableDatabase();
        //db.delete(bankDao.getTablename(), "TITLE like '%" + bank.getTitle() + "%' AND USERID like '%" + bank.getUserid() + "%' AND PASSWORD like '%" + bank.getPassword() + "%'", null);
        db.delete(bankDao.getTablename(), "TITLE = '" + bank.getTitle().toString() + "' AND USERID = '" + bank.getUserid().toString() + "' AND PASSWORD = '" + bank.getPassword().toString() + "'", null);
        //Regist_Fragment.getBankDao(getContext()).deleteByKey(id);
    }

    private void deleteOther(String title, String userid, String password) {
        Other other = new Other();
        other.setTitle(title);
        other.setUserid(userid);
        other.setPassword(password);
        OtherDao otherDao = Regist_Fragment.getOtherDao(getContext());
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "example-db", null);
        SQLiteDatabase db =helper.getWritableDatabase();
        //db.delete(otherDao.getTablename(), "TITLE like '%" + other.getTitle() + "%' AND USERID like '%" + other.getUserid() + "%' AND PASSWORD like '%" + other.getPassword() + "%'", null);
        db.delete(otherDao.getTablename(), "TITLE = '" + other.getTitle().toString() + "' AND USERID = '" + other.getUserid().toString() + "' AND PASSWORD = '" + other.getPassword().toString() + "'", null);
    }
}
