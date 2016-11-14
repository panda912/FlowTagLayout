package com.sgb.flowtaglayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sgb.library.FlowAdapter;
import com.sgb.library.FlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    private FlowLayout mFlowLayout;
    private MyAdapter mAdapter;

    private List<String> mTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTags = new ArrayList<>(asList(getResources().getStringArray(R.array.lol)));

        mFlowLayout = (FlowLayout) findViewById(R.id.flowlayout);
        mFlowLayout.setAdapter(mAdapter = new MyAdapter(mTags));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_none_choice:
                mFlowLayout.setChoiceMode(FlowLayout.CHOICE_MODE_NONE);
                break;
            case R.id.menu_single_choice:
                mFlowLayout.setChoiceMode(FlowLayout.CHOICE_MODE_SINGLE);
                break;
            case R.id.menu_mult_choice:
                mFlowLayout.setChoiceMode(FlowLayout.CHOICE_MODE_MULTIPLE);
                break;
            case R.id.menu_max_chexked:
                mFlowLayout.setChoiceMode(FlowLayout.CHOICE_MODE_MULTIPLE);
                mFlowLayout.setMaxChecked(3);
                break;
            case R.id.menu_clear:
                mFlowLayout.clearChoices();
                break;
            case R.id.menu_update:
                mTags.addAll(new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.lol_update))));
                mAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyAdapter extends FlowAdapter<String> {
        public MyAdapter(List<String> dataList) {
            super(dataList);
        }

        @Override
        public View getView(int position, FlowLayout parent) {
            TextView view = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

            String obj = getItem(position);
            view.setText(obj);

            return view;
        }
    }
}
