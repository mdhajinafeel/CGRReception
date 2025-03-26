package com.codringreen.receptionloading.view.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codringreen.receptionloading.R;
import com.codringreen.receptionloading.db.entity.FarmDetails;
import com.codringreen.receptionloading.db.entity.PurchaseContract;
import com.codringreen.receptionloading.utils.DividerItemDecoration;
import com.codringreen.receptionloading.view.adapter.CommonRecyclerViewAdapter;
import com.codringreen.receptionloading.view.adapter.ViewHolder;
import com.codringreen.receptionloading.viewmodel.FarmViewModel;
import com.codringreen.receptionloading.viewmodel.ViewModelFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FarmActivity extends BaseActivity {

    private FarmViewModel farmViewModel;
    @Inject
    ViewModelFactory viewModelFactory;
    private List<FarmDetails> farmDetailsList;
    private RecyclerView rvFarmLists;
    private CommonRecyclerViewAdapter<FarmDetails> farmDetailsCommonRecyclerViewAdapter;
    private AppCompatTextView tvNoFarm;

    @Override
    protected void initVariable(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_farm);
        initComponents();
    }

    private void initComponents() {
        try {
            hideKeyboard(this);

            AppCompatImageView imgBack = findViewById(R.id.imgBack);
            AppCompatTextView txtTitle = findViewById(R.id.txtTitle);
            AppCompatButton btnCreateFarm = findViewById(R.id.btnCreateFarm);

            farmViewModel = new ViewModelProvider(this, viewModelFactory).get(FarmViewModel.class);

            txtTitle.setText(getString(R.string.farm_loading));
            imgBack.setOnClickListener(v -> finish());
            btnCreateFarm.setOnClickListener(v -> startActivity(new Intent(FarmActivity.this, CreateFarmActivity.class)));

            tvNoFarm = findViewById(R.id.tvNoFarm);
            rvFarmLists = findViewById(R.id.rvFarmLists);

            fetchData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fetchData() {
        farmDetailsList = new ArrayList<>();
        farmDetailsList = farmViewModel.fetchFarmDetails();
        DecimalFormat df = new DecimalFormat("0.000");

        if(!farmDetailsList.isEmpty()) {
            farmDetailsCommonRecyclerViewAdapter = new CommonRecyclerViewAdapter<FarmDetails>(this, farmDetailsList, R.layout.row_item_farm_data) {
                @Override
                public void onPostBindViewHolder(ViewHolder holder, FarmDetails farmDetails) {
                    if (farmDetails != null) {
                        holder.setViewText(R.id.tvInventoryOrder, farmDetails.getInventoryOrder());
                        holder.setViewText(R.id.tvSupplierName, farmDetails.getSupplierName());
                        holder.setViewText(R.id.tvReceivedDate, farmDetails.getPurchaseDate());
                        holder.setViewText(R.id.tvTotalGrossVolume, df.format(farmDetails.getGrossVolume()));
                        holder.setViewText(R.id.tvTotalPieces, String.valueOf(farmDetails.getTotalPieces()));

                        holder.getView(R.id.imgEdit).setOnClickListener(v -> startActivity(new Intent(FarmActivity.this, FarmDataActivity.class)
                                .putExtra("FarmDetail", farmDetails)));
                    }
                }
            };

            rvFarmLists.setLayoutManager(new LinearLayoutManager(this));
            rvFarmLists.addItemDecoration(new DividerItemDecoration(this));
            rvFarmLists.setAdapter(farmDetailsCommonRecyclerViewAdapter);
            rvFarmLists.setVisibility(View.VISIBLE);
            tvNoFarm.setVisibility(View.GONE);
        } else {
            rvFarmLists.setVisibility(View.GONE);
            tvNoFarm.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}