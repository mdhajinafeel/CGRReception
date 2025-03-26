package com.codringreen.receptionloading.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.codringreen.receptionloading.R;
import com.codringreen.receptionloading.model.MenuModel;

import java.util.List;

import javax.annotation.Nonnull;

public class NavigationAdapters extends ArrayAdapter<MenuModel> {
    private LayoutInflater inflater;
    private int layoutResourceId;
    private Context mContext;
    private List<MenuModel> menuModels;

    public NavigationAdapters(Context myContext, int layoutResourceId, List<MenuModel> menuModels) {
        super(myContext, layoutResourceId, menuModels);
        this.layoutResourceId = layoutResourceId;
        this.mContext = myContext;
        this.menuModels = menuModels;
        this.inflater = LayoutInflater.from(myContext);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    @Nonnull
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = this.inflater.inflate(this.layoutResourceId, parent, false);
            viewHolder.imageViewIcon = view.findViewById(R.id.imageViewIcon);
            viewHolder.textViewName = view.findViewById(R.id.textViewName);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MenuModel menuModel = this.menuModels.get(position);
        try {
            viewHolder.imageViewIcon.setImageResource(menuModel.getMenuIcon());
            viewHolder.textViewName.setText(menuModel.getMenuName());
            return view;
        } catch (Exception e) {
            throw e;
        }
    }

    private class ViewHolder {
        private AppCompatImageView imageViewIcon;
        private AppCompatTextView textViewName;

        private ViewHolder() {
        }
    }
}