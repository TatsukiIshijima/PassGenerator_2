package com.example.ti.sampleapplication1.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.ti.sampleapplication1.Element;
import com.example.ti.sampleapplication1.MyApplication;
import com.example.ti.sampleapplication1.R;

import java.util.Random;

import greendao.Bank;
import greendao.BankDao;
import greendao.Computer;
import greendao.ComputerDao;
import greendao.Credit;
import greendao.CreditDao;
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
 * Created by TI on 2016/01/17.
 */
public class Regist_Fragment extends Fragment {

    public Regist_Fragment() {

    }

    Random random = new Random();
    final Element element = new Element();
    final String number = "0123456789";
    final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final String lower = "abcdefghijklmnopqrstuvwxyz";
    final String symbol = "!#$%&()=-_~^+@*?[]";

    private TextView passLen;
    private EditText editTitle, editId, editPass;
    private SeekBar seekBar;
    private ToggleButton numButton, upperButton, lowerButton, symbolButton;
    private Button saveButton, createButton;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(false);
        getActivity().setTitle("登録");

        View rootView = inflater.inflate(R.layout.regist_fragment, container, false);

        editTitle = (EditText) rootView.findViewById(R.id.EditTitle);
        editId = (EditText) rootView.findViewById(R.id.EditID);
        editPass = (EditText) rootView.findViewById(R.id.EditPass);
        seekBar = (SeekBar) rootView.findViewById(R.id.SeekBar);
        passLen = (TextView) rootView.findViewById(R.id.PWlength);
        numButton = (ToggleButton) rootView.findViewById(R.id.NumBotton);
        upperButton = (ToggleButton) rootView.findViewById(R.id.UpperButton);
        lowerButton = (ToggleButton) rootView.findViewById(R.id.LowerButton);
        symbolButton = (ToggleButton) rootView.findViewById(R.id.SymbolButton);
        saveButton = (Button) rootView.findViewById(R.id.SaveButton);
        createButton = (Button) rootView.findViewById(R.id.CreateButton);

        numButton.setChecked(true);
        upperButton.setChecked(true);
        lowerButton.setChecked(true);
        symbolButton.setChecked(true);
        passLen.setText("パスワード桁数：" + seekBar.getProgress());

        //element.setNumber(number);
        //element.setUpper(upper);
        //element.setLower(lower);
        //element.setSymbol(symbol);
        element.setLength(seekBar.getProgress());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            // ドラックした時
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                passLen.setText("パスワード桁数：" + seekBar.getProgress());
            }

            @Override
            // 触れた時
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            // 離した時
            public void onStopTrackingTouch(SeekBar seekBar) {
                element.setLength(seekBar.getProgress());
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numButton.isChecked() || upperButton.isChecked() || lowerButton.isChecked() || symbolButton.isChecked()) {

                    StringBuffer buffer = new StringBuffer();
                    String password = new String();

                    if (numButton.isChecked()) {
                        //element.setNumber(number);
                        //buffer.append(element.getNumber());
                        buffer.append(number);
                    }

                    if (upperButton.isChecked()) {
                        //element.setUpper(upper);
                        //buffer.append(element.getUpper());
                        buffer.append(upper);
                    }

                    if (lowerButton.isChecked()) {
                        //element.setLower(lower);
                        //buffer.append(element.getLower());
                        buffer.append(lower);
                    }

                    if (symbolButton.isChecked()) {
                        //element.setSymbol(symbol);
                        //buffer.append(element.getSymbol());
                        buffer.append(symbol);
                    }

                    // パスワード生成
                    for (int k = 0; k < element.getLength(); k++) {
                        password += buffer.charAt((int) (Math.random() * buffer.length()));
                    }

                    //element.setPassword(password);
                    editPass.setText(password);
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder listDialog = new AlertDialog.Builder(getActivity());

                if (!editTitle.getText().toString().equals("")) {

                    final CharSequence[] items = {"メール","SNS","Web","コンピュータ","ショッピング","クレジットカード", "銀行", "その他"};
                    listDialog.setTitle("カテゴリー");
                    listDialog.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            switch (which) {

                                case 0:
                                    insertMail(editTitle.getText().toString(), editId.getText().toString(), editPass.getText().toString());
                                    Toast.makeText(getContext(), "登録しました。", Toast.LENGTH_SHORT).show();
                                    editTitle.setText(null);
                                    editId.setText(null);
                                    editPass.setText(null);
                                    break;
                                case 1:
                                    insertSNS(editTitle.getText().toString(), editId.getText().toString(), editPass.getText().toString());
                                    Toast.makeText(getContext(), "登録しました。", Toast.LENGTH_SHORT).show();
                                    editTitle.setText(null);
                                    editId.setText(null);
                                    editPass.setText(null);
                                    break;
                                case 2:
                                    insertWeb(editTitle.getText().toString(), editId.getText().toString(), editPass.getText().toString());
                                    Toast.makeText(getContext(), "登録しました。", Toast.LENGTH_SHORT).show();
                                    editTitle.setText(null);
                                    editId.setText(null);
                                    editPass.setText(null);
                                    break;
                                case 3:
                                    insertComputer(editTitle.getText().toString(), editId.getText().toString(), editPass.getText().toString());
                                    Toast.makeText(getContext(), "登録しました。", Toast.LENGTH_SHORT).show();
                                    editTitle.setText(null);
                                    editId.setText(null);
                                    editPass.setText(null);
                                    break;
                                case 4:
                                    insertShopping(editTitle.getText().toString(), editId.getText().toString(), editPass.getText().toString());
                                    Toast.makeText(getContext(), "登録しました。", Toast.LENGTH_SHORT).show();
                                    editTitle.setText(null);
                                    editId.setText(null);
                                    editPass.setText(null);
                                    break;
                                case 5:
                                    insertCredit(editTitle.getText().toString(), editId.getText().toString(), editPass.getText().toString());
                                    Toast.makeText(getContext(), "登録しました。", Toast.LENGTH_SHORT).show();
                                    editTitle.setText(null);
                                    editId.setText(null);
                                    editPass.setText(null);
                                    break;
                                case 6:
                                    insertBank(editTitle.getText().toString(), editId.getText().toString(), editPass.getText().toString());
                                    Toast.makeText(getContext(), "登録しました。", Toast.LENGTH_SHORT).show();
                                    editTitle.setText(null);
                                    editId.setText(null);
                                    editPass.setText(null);
                                    break;
                                case 7:
                                    insertOther(editTitle.getText().toString(), editId.getText().toString(), editPass.getText().toString());
                                    Toast.makeText(getContext(), "登録しました。", Toast.LENGTH_SHORT).show();
                                    editTitle.setText(null);
                                    editId.setText(null);
                                    editPass.setText(null);
                                    break;
                                default:
                                    break;
                            }

                            //insertMail(editTitle.getText().toString(), editId.getText().toString(), editPass.getText().toString());
                            //insertWeb(editTitle.getText().toString(), editId.getText().toString(), editPass.getText().toString());
                        }
                    });
                    listDialog.create().show();
                } else {
                    listDialog.setMessage("タイトルを入力してください");
                    listDialog.setPositiveButton(android.R.string.ok, null);
                    listDialog.show();
                }
            }
        });
        return rootView;
    }

    private void insertMail(String title, String userid, String password) {
        Mail mail = new Mail();
        mail.setTitle(title);
        mail.setUserid(userid);
        mail.setPassword(password);
        getMailDao(this.getContext()).insertOrReplace(mail);
    }

    private void insertSNS(String title, String userid, String password) {
        SNS sns = new SNS();
        sns.setTitle(title);
        sns.setUserid(userid);
        sns.setPassword(password);
        getSNSDao(this.getContext()).insertOrReplace(sns);
    }

    private void insertWeb(String title, String userid, String password) {
        Web web = new Web();
        web.setTitle(title);
        web.setUserid(userid);
        web.setPassword(password);
        getWebDao(this.getContext()).insertOrReplace(web);
    }

    private void insertComputer(String title, String userid, String password) {
        Computer computer = new Computer();
        computer.setTitle(title);
        computer.setUserid(userid);
        computer.setPassword(password);
        getComputerDao(this.getContext()).insertOrReplace(computer);
    }

    private void insertShopping(String title, String userid, String password) {
        Shopping shopping = new Shopping();
        shopping.setTitle(title);
        shopping.setUserid(userid);
        shopping.setPassword(password);
        getShoppingDao(this.getContext()).insertOrReplace(shopping);
    }

    private void insertCredit(String title, String userid, String password) {
        Credit credit = new Credit();
        credit.setTitle(title);
        credit.setUserid(userid);
        credit.setPassword(password);
        getCreditDao(this.getContext()).insertOrReplace(credit);
    }

    private void insertBank(String title, String userid, String password) {
        Bank bank = new Bank();
        bank.setTitle(title);
        bank.setUserid(userid);
        bank.setPassword(password);
        getBankDao(this.getContext()).insertOrReplace(bank);
    }

    private void insertOther(String title, String userid, String password) {
        Other other = new Other();
        other.setTitle(title);
        other.setUserid(userid);
        other.setPassword(password);
        getOtherDao(this.getContext()).insertOrReplace(other);
    }

    public static MailDao getMailDao(Context c) {
        return ((MyApplication) c.getApplicationContext()).getDaoSession().getMailDao();
    }

    public static SNSDao getSNSDao(Context c) {
        return ((MyApplication) c.getApplicationContext()).getDaoSession().getSNSDao();
    }

    public static WebDao getWebDao(Context c) {
        return ((MyApplication) c.getApplicationContext()).getDaoSession().getWebDao();
    }

    public static ComputerDao getComputerDao(Context c) {
        return ((MyApplication) c.getApplicationContext()).getDaoSession().getComputerDao();
    }

    public static ShoppingDao getShoppingDao(Context c) {
        return ((MyApplication) c.getApplicationContext()).getDaoSession().getShoppingDao();
    }

    public static CreditDao getCreditDao(Context c) {
        return ((MyApplication) c.getApplicationContext()).getDaoSession().getCreditDao();
    }

    public static BankDao getBankDao(Context c) {
        return ((MyApplication) c.getApplicationContext()).getDaoSession().getBankDao();
    }

    public static OtherDao getOtherDao(Context c) {
        return ((MyApplication) c.getApplicationContext()).getDaoSession().getOtherDao();
    }
}
