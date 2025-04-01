package com.codringreen.receptionloading.view.activities;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import com.codringreen.receptionloading.R;
import com.codringreen.receptionloading.db.entity.FarmDataEntry;
import com.codringreen.receptionloading.db.entity.FarmDetails;
import com.codringreen.receptionloading.model.FarmData;
import com.codringreen.receptionloading.utils.CommonUtils;
import com.codringreen.receptionloading.viewmodel.FarmViewModel;
import com.codringreen.receptionloading.viewmodel.ViewModelFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class FarmDataActivity extends BaseActivity {

    private LinearLayout containerLayout, lnrFarmDetails1;
    private AppCompatTextView tvTotalPieces, tvTotalGrossVolume, tvTotalNetVolume, tvInventoryOrder, tvSupplierName, tvMeasurementSystem, tvProductName;
    private final List<List<AppCompatEditText>> allRowsEditTexts = new ArrayList<>();
    private FarmDetails farmDetails;
    private final DecimalFormat df = new DecimalFormat("0.000");
    private FarmViewModel farmViewModel;
    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void initVariable(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_farm_data);
        initComponents();
    }

    private void initComponents() {
        try {
            hideKeyboard(this);

            AppCompatImageView imgBack = findViewById(R.id.imgBack);
            AppCompatTextView txtTitle = findViewById(R.id.txtTitle);
            AppCompatTextView txtSubTitle = findViewById(R.id.txtSubTitle);

            txtTitle.setText(getString(R.string.farm_details));
            imgBack.setOnClickListener(v -> finish());

            containerLayout = findViewById(R.id.containerLayout);
            AppCompatButton btnAddData = findViewById(R.id.btnAddData);
            AppCompatButton btnSave = findViewById(R.id.btnSave);
            AppCompatImageView ivExpand = findViewById(R.id.ivExpand);
            lnrFarmDetails1 = findViewById(R.id.lnrFarmDetails1);

            farmViewModel = new ViewModelProvider(this, viewModelFactory).get(FarmViewModel.class);

            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                farmDetails = (FarmDetails) bundle.getSerializable("FarmDetail");

                txtSubTitle.setText(Objects.requireNonNull(farmDetails).getInventoryOrder());
                txtSubTitle.setVisibility(View.VISIBLE);

                tvTotalPieces = findViewById(R.id.tvTotalPieces);
                tvTotalGrossVolume = findViewById(R.id.tvTotalGrossVolume);
                tvTotalNetVolume = findViewById(R.id.tvTotalNetVolume);
                tvInventoryOrder = findViewById(R.id.tvInventoryOrder);
                tvSupplierName = findViewById(R.id.tvSupplierName);
                tvMeasurementSystem = findViewById(R.id.tvMeasurementSystem);
                tvProductName = findViewById(R.id.tvProductName);

                btnAddData.setOnClickListener(v -> addNewRow(false, null));
                btnSave.setOnClickListener(v -> {
                    List<FarmData> farmDataList = getAllValues();

                    double totalGrossVolume = 0;
                    double totalNetVolume = 0;
                    int totalPieces = 0;
                    for (FarmData farmData : farmDataList) {
                        if (farmData.getCircumference() > 0 && farmData.getLength() > 0 && farmData.getPieces() > 0) {
                            totalGrossVolume = totalGrossVolume + farmData.getGrossVolume();
                            totalNetVolume = totalNetVolume + farmData.getNetVolume();
                            totalPieces = totalPieces + farmData.getPieces();
                        }
                    }

                    tvTotalGrossVolume.setText(df.format(totalGrossVolume));
                    tvTotalNetVolume.setText(df.format(totalNetVolume));
                    tvTotalPieces.setText(String.valueOf(totalPieces));

                    List<FarmDataEntry> farmDataEntryList = new ArrayList<>();
                    for (FarmData farmData : farmDataList) {
                        if (farmData.getCircumference() > 0 && farmData.getLength() > 0 && farmData.getPieces() > 0) {
                            FarmDataEntry farmDataEntry = new FarmDataEntry();
                            farmDataEntry.setInventoryOrder(farmData.getInventoryOrder());
                            farmDataEntry.setCircumference(farmData.getCircumference());
                            farmDataEntry.setLength(farmData.getLength());
                            farmDataEntry.setCircAllowance(farmData.getCircAllowance());
                            farmDataEntry.setLengthAllowance(farmData.getLengthAllowance());
                            farmDataEntry.setPieces(farmData.getPieces());
                            farmDataEntry.setGrossVolume(farmData.getGrossVolume());
                            farmDataEntry.setNetVolume(farmData.getNetVolume());
                            farmDataEntryList.add(farmDataEntry);
                        }
                    }

                    if (!farmDataEntryList.isEmpty()) {
                        farmViewModel.saveOrUpdateFarmData(farmDataEntryList, farmDetails.getInventoryOrder(), farmDetails.getSupplierId(),
                                totalPieces, totalGrossVolume, totalNetVolume);
                    }
                });

                ivExpand.setOnClickListener(v -> {
                    if (lnrFarmDetails1.getVisibility() == View.VISIBLE) {
                        lnrFarmDetails1.setVisibility(View.GONE);
                    } else {
                        lnrFarmDetails1.setVisibility(View.VISIBLE);
                    }
                });

                tvTotalPieces.setText(String.valueOf(Objects.requireNonNull(farmDetails).getTotalPieces()));

                DecimalFormat df = new DecimalFormat("0.000");
                tvTotalGrossVolume.setText(df.format(farmDetails.getGrossVolume()));
                tvTotalNetVolume.setText(df.format(farmDetails.getNetVolume()));
                tvSupplierName.setText(farmDetails.getSupplierName());
                tvMeasurementSystem.setText(String.format("%s - %s", farmDetails.getDescription(), farmDetails.getMeasurementSystem()));
                tvInventoryOrder.setText(farmDetails.getInventoryOrder());
                tvProductName.setText(farmDetails.getProductName());

                if (farmDetails.getTotalPieces() > 0) {
                    List<FarmDataEntry> farmDataEntryList = farmViewModel.getFarmDataEntryLists(farmDetails.getInventoryOrder());
                    if (!farmDataEntryList.isEmpty()) {
                        for (FarmDataEntry farmDataEntry : farmDataEntryList) {
                            addNewRow(true, farmDataEntry);
                        }
                    }
                }
            } else {
                finish();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addNewRow(boolean isEdit, FarmDataEntry farmDataEntry) {

        DecimalFormat df = new DecimalFormat("#");

        LinearLayout newRow = new LinearLayout(this);
        newRow.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        newRow.setOrientation(LinearLayout.HORIZONTAL);
        newRow.setWeightSum(4);

        Drawable editTextBackground = ContextCompat.getDrawable(this, R.drawable.rectangle_border_black);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.montserrat_semibold);
        List<AppCompatEditText> rowEditTexts = new ArrayList<>();

        // Create EditTexts dynamically
        for (int i = 0; i < 3; i++) {
            AppCompatEditText editText = new AppCompatEditText(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.setMargins(getResources().getDimensionPixelSize(R.dimen.medium_margin),
                    getResources().getDimensionPixelSize(R.dimen.small_margin),
                    getResources().getDimensionPixelSize(R.dimen.medium_margin),
                    getResources().getDimensionPixelSize(R.dimen.margin_normal));
            editText.setLayoutParams(params);
            editText.setBackground(editTextBackground);
            editText.setGravity(Gravity.CENTER);
            editText.setSingleLine(true);
            editText.setTextColor(ContextCompat.getColor(this, R.color.black));
            editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_xmedium1));
            editText.setTypeface(typeface);

            if (i == 0) {
                editText.setTag("etCircumference");
            } else if (i == 1) {
                editText.setTag("etLength");
            } else {
                editText.setTag("etPieces");
            }

            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            
            if(isEdit && farmDataEntry != null) {
                if (i == 0) {
                    editText.setText(df.format(farmDataEntry.getCircumference()));
                } else if (i == 1) {
                    editText.setText(df.format(farmDataEntry.getLength()));
                } else {
                    editText.setText(df.format(farmDataEntry.getPieces()));
                }
            }

            rowEditTexts.add(editText); // Store reference
            newRow.addView(editText);
        }

        // Create Delete Button
        AppCompatButton deleteButton = new AppCompatButton(this);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.35f);
        params1.setMargins(getResources().getDimensionPixelSize(R.dimen.medium_margin),
                getResources().getDimensionPixelSize(R.dimen.small_margin),
                getResources().getDimensionPixelSize(R.dimen.medium_margin),
                getResources().getDimensionPixelSize(R.dimen.margin_normal));
        deleteButton.setLayoutParams(params1);
        deleteButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_button_reset));
        deleteButton.setText("X");
        deleteButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));

        // Remove the row when delete button is clicked
        deleteButton.setOnClickListener(v -> {
            containerLayout.removeView(newRow);
            allRowsEditTexts.remove(rowEditTexts); // Remove from list
        });

        newRow.addView(deleteButton);
        containerLayout.addView(newRow);

        allRowsEditTexts.add(rowEditTexts);
    }

    private List<FarmData> getAllValues() {
        List<FarmData> farmDataList = new ArrayList<>();

        for (List<AppCompatEditText> row : allRowsEditTexts) {
            FarmData farmData = new FarmData();
            farmData.setInventoryOrder(farmDetails.getInventoryOrder());
            farmData.setCircAllowance(farmDetails.getCircAllowance());
            farmData.setLengthAllowance(farmDetails.getLengthAllowance());
            for (AppCompatEditText editText : row) {
                if (!Objects.requireNonNull(editText.getText()).toString().isEmpty()) {
                    if (editText.getTag().equals("etCircumference")) {
                        farmData.setCircumference(Double.parseDouble(Objects.requireNonNull(editText.getText()).toString()));
                    } else if (editText.getTag().equals("etLength")) {
                        farmData.setLength(Double.parseDouble(Objects.requireNonNull(editText.getText()).toString()));
                    } else if (editText.getTag().equals("etPieces")) {
                        farmData.setPieces(Integer.parseInt(Objects.requireNonNull(editText.getText()).toString()));
                    }
                }
            }

            double grossVolume = 0;
            double netVolume = 0;
            if (farmDetails.getMeasurementSystem().equalsIgnoreCase("Hoppus")
                    || farmDetails.getMeasurementSystem().equalsIgnoreCase("Fixed Price")) {
                grossVolume = CommonUtils.truncateDecimal((farmData.getCircumference() * farmData.getCircumference() * farmData.getLength()) / 16000000, 3).doubleValue() * farmData.getPieces();
                netVolume = CommonUtils.truncateDecimal(((farmData.getCircumference() - farmData.getCircAllowance()) * (farmData.getCircumference() - farmData.getCircAllowance())
                        * (farmData.getLength() - farmData.getLengthAllowance())) / 16000000, 3).doubleValue() * farmData.getPieces();
            } else if (farmDetails.getMeasurementSystem().equalsIgnoreCase("Geo")) {
                grossVolume = CommonUtils.roundValue((farmData.getCircumference() * farmData.getCircumference() * farmData.getLength() * 0.0796) / 1000000, 3).doubleValue() * farmData.getPieces();
                netVolume = CommonUtils.roundValue(((farmData.getCircumference() - farmData.getCircAllowance()) * (farmData.getCircumference() - farmData.getCircAllowance())
                        * (farmData.getLength() - farmData.getLengthAllowance()) * 0.0796) / 1000000, 3).doubleValue() * farmData.getPieces();
            } else if (farmDetails.getMeasurementSystem().equalsIgnoreCase("Per piece")) {
                grossVolume = CommonUtils.calculatePieceFormula(farmData.getCircumference(), farmData.getLength()) * farmData.getPieces();
                netVolume = CommonUtils.calculatePieceFormula(farmData.getCircumference(), farmData.getLength()) * farmData.getPieces();
            }

            farmData.setGrossVolume(grossVolume);
            farmData.setNetVolume(netVolume);

            farmDataList.add(farmData);
        }
        return farmDataList;
    }
}