package com.example.ti.sampleapplication1.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ti.sampleapplication1.MyAdapter.CategoryListAdapter;
import com.example.ti.sampleapplication1.R;
import com.example.ti.sampleapplication1.tools.CategoryListItem;

import java.util.List;

/**
 * Created by TI on 2016/01/23.
 */
public class Classify_Fragment extends Fragment{

    public Classify_Fragment() {

    }

    private ListView mListView;
    private List<CategoryListItem> mCategoryList;
    private CategoryListAdapter mCategoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        getActivity().setTitle("分類");

        final View rootView = inflater.inflate(R.layout.classify_fragment, container, false);

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

                //element.setTitlenumber(position);

                Fragment fragment = new Category_Fragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                //Bundle bundle = new Bundle();
                // フラグメントに渡す値を設定
                //bundle.putInt("position", position);
                //fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.contents, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return rootView;
    }
}
