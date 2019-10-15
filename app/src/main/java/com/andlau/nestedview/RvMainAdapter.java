package com.andlau.nestedview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * @Description:
 * @Author: Lxq
 * @Date: 2019-10-15
 */
public class RvMainAdapter extends BaseRvAdapter<String, RvMainAdapter.ViewHolder> {

    public RvMainAdapter(Context context) {
        super(context);
    }

    @Override
    protected ViewHolder doOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.adapetr_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void doOnBindViewHolder(ViewHolder holder, int position) {
        String s = datas.get(position);
        holder.tvTitle.setText(s);
        holder.rvItem.setLayoutManager(new LinearLayoutManager(mContext));
        RvItemAdapter adapter = new RvItemAdapter(mContext);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(String.format("Item:%d", i + 1));
        }
        adapter.setDatas(datas);
        holder.rvItem.setAdapter(adapter);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        RecyclerView rvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            rvItem = itemView.findViewById(R.id.rv_item);
        }
    }
}
