package com.example.ti.sampleapplication1.Fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ti.sampleapplication1.MyAdapter.CategoryListAdapter;
import com.example.ti.sampleapplication1.R;
import com.example.ti.sampleapplication1.tools.CategoryListItem;

import java.util.List;

/**
 * Created by TI on 2016/01/17.
 */
public class Category_Fragment extends Fragment {

    public Category_Fragment() {
    }

    //final Element element = new Element();

    private ListView mListView;
    private  List<CategoryListItem> mCategoryList;
    private CategoryListAdapter mCategoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(false);
        getActivity().setTitle("カテゴリー");

        final View rootView = inflater.inflate(R.layout.category_fragment, container, false);

        // Listviewのインスタンスを取得
        mListView = (ListView) rootView.findViewById(R.id.listView);
        mCategoryList = CategoryListItem.contentItem();
        mCategoryAdapter = new CategoryListAdapter(getActivity(), mCategoryList);

        mListView.setAdapter(mCategoryAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // クリックしたタイトルの表示
                CategoryListItem categoryitem = (CategoryListItem) mListView.getAdapter().getItem(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                //dialog.setMessage("Selected " + position + " : " + categoryitem.getmTitle());
                //dialog.setPositiveButton(android.R.string.ok, null);
                //dialog.show();

                Fragment fragment = new PassWord_List_Fragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                // フラグメントに渡す値を設定
                bundle.putInt("position", position);
                fragment.setArguments(bundle);

                // アニメーション
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.fade_out);

                fragmentTransaction.replace(R.id.contents, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        rootView.findViewById(R.id.create_new).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment fragment = new Regist_Fragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                // アニメーション
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.fade_out);

                fragmentTransaction.replace(R.id.contents, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }
}
