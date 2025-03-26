package com.codringreen.receptionloading.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.Iterator;
import java.util.List;

public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private List<T> mDataList;
    private int mLayoutId;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public CommonRecyclerViewAdapter(Context context, List<T> dataList, int layoutId) {
        mContext = context;
        mDataList = dataList;
        mLayoutId = layoutId;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, holder.getLayoutPosition());
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mOnItemLongClickListener.onItemLongClick(v, holder.getLayoutPosition());
                }
            });
        }
        onPostBindViewHolder(holder, mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return (mDataList == null) ? 0 : mDataList.size();
    }

    public abstract void onPostBindViewHolder(ViewHolder holder, T t);

    public T getItem(int position) {
        return mDataList.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, int position);
    }

    public void add(T t) {
        mDataList.add(t);
        notifyItemInserted(mDataList.size() - 1);
    }

    public void addAll(List<T> items) {
        for (T t : mDataList) {
            add(t);
        }
    }

    public void filterList(List<T> filterdNames) {
        this.mDataList = filterdNames;
        notifyDataSetChanged();
    }

    public void updateData(List<T> viewModels) {
        mDataList.clear();
        mDataList.addAll(viewModels);
        notifyDataSetChanged();
    }
}