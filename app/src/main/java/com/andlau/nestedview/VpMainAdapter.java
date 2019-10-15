package com.andlau.nestedview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Lxq
 * @Date: 2019-10-15
 */
public class VpMainAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> data;
    private final LayoutInflater mInflater;

    public VpMainAdapter(Context context, List<String> data) {
        mContext = context;
        this.data = data;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.adapetr_main, container, false);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        RecyclerView rvItem = view.findViewById(R.id.rv_item);

        String s = data.get(position);
        tvTitle.setText(s);
        rvItem.setLayoutManager(new LinearLayoutManager(mContext));
        RvItemAdapter adapter = new RvItemAdapter(mContext);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(String.format("Item:%d", i + 1));
        }
        adapter.setDatas(datas);
        rvItem.setAdapter(adapter);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
