package com.andlau.nestedview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseRvAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> datas;
    protected OnItemClickListener<T> onItemClickListener;
    protected OnItemClickListener<T> onItemLongClickListener;
    protected final Context mContext;
    protected final LayoutInflater mLayoutInflater;

    public BaseRvAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.datas = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemClickListener<T> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener<T> {
        void onObjectItemClicked(T t, int position);
    }

    public List<T> getDatas() {
        return datas;
    }

    public void clear() {
        if (datas != null && datas.size() > 0) {
            datas.clear();
            this.notifyDataSetChanged();
        }
    }

    public final T getItem(int position) {
        if (position < 0 || position >= datas.size()) {
            return null;
        }

        return datas.get(position);
    }

    public final void updateItem(int position, T model) {
        if (model == null || position < 0 || position >= datas.size()) {
            return;
        }
        datas.set(position, model);
        notifyItemChanged(position);
    }

    public final void updateItem(T model) {
        if (model == null) {
            return;
        }
        int index = datas.indexOf(model);
        datas.set(index, model);
        notifyItemChanged(index);
    }

    public final void removeItemAt(int position) {
        if (position < 0 || position >= datas.size()) {
            return;
        }
        datas.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItems(List<T> data) {
        this.validateUsersCollection(data);
        this.datas.removeAll(data);
        notifyDataSetChanged();
    }

    public void removeItem(T data) {
        this.datas.remove(data);
        notifyDataSetChanged();
    }

    public void setDatas(List<T> datas) {
        this.validateUsersCollection(datas);
        this.datas.clear();
        this.datas.addAll(datas);
        this.notifyDataSetChanged();
    }

    public void addData(T data) {
        this.validateUsersCollection(datas);
        this.datas.add(data);
        this.notifyItemInserted(datas.size() - 1);
    }

    public void addData(List<T> datas) {
        this.validateUsersCollection(datas);
        this.datas.addAll(datas);
        this.notifyDataSetChanged();
    }

    public void addDataWithAnim(List<T> datas) {
        this.validateUsersCollection(datas);
        this.datas.addAll(datas);
        this.notifyItemRangeInserted(this.datas.size() - datas.size(), datas.size());
    }

    public void addData(int index, T data) {
        this.datas.add(index, data);
        this.notifyItemInserted(index);
    }

    public void addData(int index, List<T> datas) {
        this.validateUsersCollection(datas);
        this.datas.addAll(index, datas);
        this.notifyDataSetChanged();
    }

    private void validateUsersCollection(Collection<T> collection) {
        if (collection == null) {
            throw new RuntimeException("must set data");
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return doOnCreateViewHolder(parent, viewType);
    }

    protected abstract VH doOnCreateViewHolder(ViewGroup parent, int viewType);

    protected abstract void doOnBindViewHolder(VH holder, int position);

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        doOnBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return (this.datas != null) ? this.datas.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}