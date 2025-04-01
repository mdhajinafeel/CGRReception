package com.codringreen.receptionloading.view.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codringreen.receptionloading.R;
import com.codringreen.receptionloading.db.entity.FarmDetails;
import com.codringreen.receptionloading.db.entity.MeasurementSystems;
import com.codringreen.receptionloading.db.entity.PurchaseContract;
import com.codringreen.receptionloading.db.entity.SupplierProductTypes;
import com.codringreen.receptionloading.db.entity.SupplierProducts;
import com.codringreen.receptionloading.db.entity.Suppliers;
import com.codringreen.receptionloading.db.entity.Warehouses;
import com.codringreen.receptionloading.helper.PreferenceManager;
import com.codringreen.receptionloading.utils.CommonUtils;
import com.codringreen.receptionloading.utils.DividerItemDecoration;
import com.codringreen.receptionloading.view.adapter.CommonRecyclerViewAdapter;
import com.codringreen.receptionloading.view.adapter.ViewHolder;
import com.codringreen.receptionloading.viewmodel.FarmViewModel;
import com.codringreen.receptionloading.viewmodel.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class CreateFarmActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatTextView tvSupplierName;
    private AppCompatTextView tvProductName;
    private AppCompatTextView tvProductType;
    private AppCompatEditText etInventoryOrder;
    private AppCompatTextView tvMeasurementSystem;
    private AppCompatEditText etReceivedDate;
    private AppCompatTextView tvWarehouse;
    private AppCompatTextView tvPurchaseContract;
    private AppCompatEditText etTruckPlateNumber;
    private AppCompatTextView txtNoDataFound;
    private AppCompatButton btnNext;
    private List<Suppliers> suppliersList, suppliersArrayList;
    private List<Warehouses> warehousesList, warehousesArrayList;
    private List<SupplierProducts> suppliersProductsList, suppliersProductsArrayList;
    private List<SupplierProductTypes> suppliersProductTypesList, suppliersProductTypesArrayList;
    private List<PurchaseContract> purchaseContractList, purchaseContractArrayList;
    private List<MeasurementSystems> measurementSystemsList, measurementSystemsArrayList;
    private CommonRecyclerViewAdapter<Suppliers> suppliersCommonRecyclerViewAdapter;
    private CommonRecyclerViewAdapter<SupplierProducts> suppliersProductsCommonRecyclerViewAdapter;
    private CommonRecyclerViewAdapter<SupplierProductTypes> supplierProductTypesCommonRecyclerViewAdapter;
    private CommonRecyclerViewAdapter<Warehouses> warehousesCommonRecyclerViewAdapter;
    private CommonRecyclerViewAdapter<PurchaseContract> purchaseContractCommonRecyclerViewAdapter;
    private CommonRecyclerViewAdapter<MeasurementSystems> measurementSystemsCommonRecyclerViewAdapter;
    private FarmViewModel farmViewModel;
    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void initVariable(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_create_farm);
        initComponents();
    }

    private void initComponents() {
        try {
            hideKeyboard(this);

            AppCompatImageView imgBack = findViewById(R.id.imgBack);
            AppCompatTextView txtTitle = findViewById(R.id.txtTitle);

            txtTitle.setText(getString(R.string.create_farm));
            imgBack.setOnClickListener(v -> finish());

            farmViewModel = new ViewModelProvider(this, viewModelFactory).get(FarmViewModel.class);

            tvSupplierName = findViewById(R.id.tvSupplierName);
            etReceivedDate = findViewById(R.id.etReceivedDate);
            tvProductName = findViewById(R.id.tvProductName);
            tvProductType = findViewById(R.id.tvProductType);
            tvMeasurementSystem = findViewById(R.id.tvMeasurementSystems);
            tvWarehouse = findViewById(R.id.tvWarehouse);
            tvPurchaseContract = findViewById(R.id.tvPurchaseContract);
            etInventoryOrder = findViewById(R.id.etInventoryOrder);
            etTruckPlateNumber = findViewById(R.id.etTruckPlateNumber);
            btnNext = findViewById(R.id.btnNext);
            btnNext.setOnClickListener(this);

            fetchData();

            tvSupplierName.setOnClickListener(v -> showDataDialog("SUPPLIERS"));
            tvWarehouse.setOnClickListener(v -> showDataDialog("WAREHOUSES"));
            tvProductName.setOnClickListener(v -> showDataDialog("PRODUCTS"));
            tvProductType.setOnClickListener(v -> showDataDialog("PRODUCTTYPES"));
            tvPurchaseContract.setOnClickListener(v -> showDataDialog("PURCHASECONTRACTS"));
            tvMeasurementSystem.setOnClickListener(v -> showDataDialog("MEASUREMENTSYSTEMS"));

            etInventoryOrder.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    validateFields();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            etTruckPlateNumber.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    validateFields();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            PreferenceManager.INSTANCE.setLastTempReceptionId("");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error) + " : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void fetchData() {
        etReceivedDate.setText(CommonUtils.convertTimeStampToDate(CommonUtils.getCurrentLocalDateTimeStamp(), "dd/MM/yyyy"));
        suppliersList = new ArrayList<>();
        suppliersList = farmViewModel.fetchSupplierList();
        warehousesList = new ArrayList<>();
        warehousesList = farmViewModel.fetchWarehouseList();
    }


    private void showDataDialog(final String type) {
        try {
            final Dialog dialog = new Dialog(this, R.style.DialogTheme);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            Window window = dialog.getWindow();
            Objects.requireNonNull(window);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());
            // Set the width to 80% of screen width & height to 50% of screen height
            layoutParams.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8);
            layoutParams.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.8);
            layoutParams.gravity = Gravity.CENTER; // Keep dialog centered
            window.setAttributes(layoutParams);
            dialog.setContentView(R.layout.list_dialog);
            AppCompatTextView txtDialogTitle = dialog.findViewById(R.id.txtDialogTitle);
            txtNoDataFound = dialog.findViewById(R.id.txtNoDataFound);
            AppCompatEditText edtSearch = dialog.findViewById(R.id.edtSearch);
            AppCompatImageView appCompatImageView = dialog.findViewById(R.id.imgClose);
            RecyclerView recyclerView = dialog.findViewById(R.id.rvList);
            appCompatImageView.setOnClickListener(view -> dialog.dismiss());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.addItemDecoration(new DividerItemDecoration(this));
            if (type.equalsIgnoreCase("SUPPLIERS")) {
                suppliersArrayList = new ArrayList<>();
                txtDialogTitle.setText(R.string.supplier_name);
                edtSearch.setHint(R.string.search_by_supplier);

                suppliersCommonRecyclerViewAdapter = new CommonRecyclerViewAdapter<Suppliers>(this, suppliersList, R.layout.row_dialog_list) {
                    @Override
                    public void onPostBindViewHolder(ViewHolder holder, Suppliers suppliers) {
                        if (suppliers != null) {
                            if (suppliers.isSelected()) {
                                holder.setViewVisibility(R.id.imgSelected, 0);
                                holder.setViewTypeface(R.id.txtName, ResourcesCompat.getFont(holder.itemView.getContext(), R.font.montserrat_bold));
                            } else {
                                holder.setViewVisibility(R.id.imgSelected, 8);
                                holder.setViewTypeface(R.id.txtName, ResourcesCompat.getFont(holder.itemView.getContext(), R.font.montserrat_medium));
                            }
                            holder.setViewText(R.id.txtName, suppliers.getSupplierName() + " - " + suppliers.getSupplierCode());
                        }
                    }
                };

                recyclerView.setAdapter(suppliersCommonRecyclerViewAdapter);
                suppliersCommonRecyclerViewAdapter.setOnItemClickListener((view, i) -> {
                    Suppliers suppliers;
                    try {
                        if (!suppliersArrayList.isEmpty()) {
                            suppliers = suppliersArrayList.get(i);
                        } else {
                            suppliers = suppliersList.get(i);
                        }
                        tvSupplierName.setText(suppliers.getSupplierName());
                        farmViewModel.selectedSupplier(suppliersList, suppliersArrayList, suppliers, i);
                        suppliersProductsList = new ArrayList<>();
                        suppliersProductsList = farmViewModel.fetchSupplierProducts(suppliers.getSupplierId());
                        tvProductName.setText("");
                        tvProductName.setHint(R.string.select);
                        tvProductType.setText("");
                        tvProductType.setHint(R.string.select);
                        farmViewModel.selectedSuppliersProducts = null;
                        farmViewModel.selectedSuppliersProductTypes = null;
                        if (suppliersProductsList != null && !suppliersProductsList.isEmpty()) {
                            tvProductName.setEnabled(true);
                            tvProductName.setFocusable(true);
                            tvProductName.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rectangle_border_black));
                        } else {
                            tvProductName.setEnabled(false);
                            tvProductName.setFocusable(false);
                            tvProductName.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.disabled_background));
                            tvProductType.setEnabled(false);
                            tvProductType.setFocusable(false);
                            tvProductType.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.disabled_background));
                        }
                        purchaseContractList = new ArrayList<>();
                        purchaseContractArrayList = new ArrayList<>();
                        measurementSystemsList = new ArrayList<>();
                        measurementSystemsArrayList = new ArrayList<>();
                        tvMeasurementSystem.setText("");
                        tvMeasurementSystem.setHint(R.string.select);
                        farmViewModel.selectedMeasurementSystem = null;
                        tvPurchaseContract.setText("");
                        tvPurchaseContract.setHint(R.string.select);
                        farmViewModel.selectedPurchaseContract = null;
                        validateFields();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();

                });
                dialog.show();
                edtSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable editable) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
                        filter(charSequence.toString(), type);
                    }
                });
            } else if (type.equalsIgnoreCase("PRODUCTS")) {
                suppliersProductsArrayList = new ArrayList<>();
                txtDialogTitle.setText(R.string.wood_species);
                edtSearch.setHint(R.string.search_supplier_product);
                suppliersProductsCommonRecyclerViewAdapter = new CommonRecyclerViewAdapter<SupplierProducts>(this, suppliersProductsList, R.layout.row_dialog_list) {
                    @Override
                    public void onPostBindViewHolder(ViewHolder holder, SupplierProducts suppliersProducts) {
                        if (suppliersProducts != null) {
                            if (suppliersProducts.isSelected()) {
                                holder.setViewVisibility(R.id.imgSelected, 0);
                                holder.setViewTypeface(R.id.txtName, ResourcesCompat.getFont(holder.itemView.getContext(), R.font.montserrat_bold));
                            } else {
                                holder.setViewVisibility(R.id.imgSelected, 8);
                                holder.setViewTypeface(R.id.txtName, ResourcesCompat.getFont(holder.itemView.getContext(), R.font.montserrat_medium));
                            }
                            holder.setViewText(R.id.txtName, suppliersProducts.getProductName());
                        }
                    }
                };
                recyclerView.setAdapter(suppliersProductsCommonRecyclerViewAdapter);
                suppliersProductsCommonRecyclerViewAdapter.setOnItemClickListener((view, i) -> {
                    SupplierProducts supplierProducts;
                    try {
                        if (!suppliersProductsArrayList.isEmpty()) {
                            supplierProducts = suppliersProductsArrayList.get(i);
                        } else {
                            supplierProducts = suppliersProductsList.get(i);
                        }
                        tvProductName.setText(supplierProducts.getProductName());
                        farmViewModel.selectedSupplierProducts(suppliersProductsList, suppliersProductsArrayList, supplierProducts, i);
                        suppliersProductTypesList = new ArrayList<>();
                        suppliersProductTypesList = farmViewModel.fetchSuppliersProductsType(supplierProducts.getSupplierId(), supplierProducts.getProductId());
                        tvProductType.setText("");
                        tvProductType.setHint(R.string.select);
                        farmViewModel.selectedSuppliersProductTypes = null;
                        if (suppliersProductTypesList != null && !suppliersProductTypesList.isEmpty()) {
                            tvProductType.setEnabled(true);
                            tvProductType.setFocusable(true);
                            tvProductType.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rectangle_border_black));
                        } else {
                            tvProductType.setEnabled(false);
                            tvProductType.setFocusable(false);
                            tvProductType.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.disabled_background));
                        }
                        purchaseContractList = new ArrayList<>();
                        purchaseContractArrayList = new ArrayList<>();
                        measurementSystemsList = new ArrayList<>();
                        measurementSystemsArrayList = new ArrayList<>();
                        tvMeasurementSystem.setText("");
                        tvMeasurementSystem.setHint(R.string.select);
                        farmViewModel.selectedMeasurementSystem = null;
                        tvPurchaseContract.setText("");
                        tvPurchaseContract.setHint(R.string.select);
                        farmViewModel.selectedPurchaseContract = null;
                        validateFields();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();

                });
                dialog.show();
                edtSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable editable) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
                        filter(charSequence.toString(), type);
                    }
                });
            } else if (type.equalsIgnoreCase("PRODUCTTYPES")) {
                suppliersProductTypesArrayList = new ArrayList<>();
                txtDialogTitle.setText(R.string.wood_type);
                edtSearch.setHint(R.string.search_supplier_product_type);
                supplierProductTypesCommonRecyclerViewAdapter = new CommonRecyclerViewAdapter<SupplierProductTypes>(this, suppliersProductTypesList, R.layout.row_dialog_list) {
                    @Override
                    public void onPostBindViewHolder(ViewHolder holder, SupplierProductTypes suppliersProductTypes) {
                        if (suppliersProductTypes != null) {
                            if (suppliersProductTypes.isSelected()) {
                                holder.setViewVisibility(R.id.imgSelected, 0);
                                holder.setViewTypeface(R.id.txtName, ResourcesCompat.getFont(holder.itemView.getContext(), R.font.montserrat_bold));
                            } else {
                                holder.setViewVisibility(R.id.imgSelected, 8);
                                holder.setViewTypeface(R.id.txtName, ResourcesCompat.getFont(holder.itemView.getContext(), R.font.montserrat_medium));
                            }
                            holder.setViewText(R.id.txtName, suppliersProductTypes.getProductTypeName());
                        }
                    }
                };
                recyclerView.setAdapter(supplierProductTypesCommonRecyclerViewAdapter);
                supplierProductTypesCommonRecyclerViewAdapter.setOnItemClickListener((view, i) -> {
                    SupplierProductTypes supplierProductTypes;
                    List<Integer> productTypesList = new ArrayList<>();
                    measurementSystemsList = new ArrayList<>();
                    try {
                        if (!suppliersProductTypesArrayList.isEmpty()) {
                            supplierProductTypes = suppliersProductTypesArrayList.get(i);
                        } else {
                            supplierProductTypes = suppliersProductTypesList.get(i);
                        }
                        tvProductType.setText(supplierProductTypes.getProductTypeName());
                        farmViewModel.selectedSuppliersProductTypes(suppliersProductTypesList, suppliersProductTypesArrayList, supplierProductTypes, i);
                        purchaseContractList = new ArrayList<>();
                        productTypesList = new ArrayList<>();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (farmViewModel.selectedSuppliersProductTypes.getProductTypeId() != 1 && farmViewModel.selectedSuppliersProductTypes.getProductTypeId() != 3) {
                        if (farmViewModel.selectedSuppliersProductTypes.getProductTypeId() == 2 || farmViewModel.selectedSuppliersProductTypes.getProductTypeId() == 4) {
                            productTypesList.add(2);
                            productTypesList.add(4);
                        }
                        purchaseContractList = farmViewModel.fetchPurchaseContractList(farmViewModel.selectedSupplier.getSupplierId(), farmViewModel.selectedSuppliersProducts.getProductId(), productTypesList);
                        measurementSystemsList = farmViewModel.fetchMeasurementSystemList(productTypesList);
                        if (measurementSystemsList != null && !measurementSystemsList.isEmpty()) {
                            tvMeasurementSystem.setEnabled(true);
                            tvMeasurementSystem.setFocusable(true);
                            tvMeasurementSystem.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rectangle_border_black));

                            tvPurchaseContract.setEnabled(true);
                            tvPurchaseContract.setFocusable(true);
                            tvPurchaseContract.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rectangle_border_black));

                        } else {
                            tvMeasurementSystem.setEnabled(false);
                            tvMeasurementSystem.setFocusable(false);
                            tvMeasurementSystem.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.disabled_background));

                            tvPurchaseContract.setEnabled(false);
                            tvPurchaseContract.setFocusable(false);
                            tvPurchaseContract.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.disabled_background));
                        }
                        tvMeasurementSystem.setText("");
                        tvMeasurementSystem.setHint(R.string.select);
                        farmViewModel.selectedMeasurementSystem = null;
                        tvPurchaseContract.setText("");
                        tvPurchaseContract.setHint(R.string.select);
                        farmViewModel.selectedPurchaseContract = null;
                        validateFields();
                        dialog.dismiss();
                    } else {
                        productTypesList.add(1);
                        productTypesList.add(3);

                        purchaseContractList = farmViewModel.fetchPurchaseContractList(farmViewModel.selectedSupplier.getSupplierId(), farmViewModel.selectedSuppliersProducts.getProductId(), productTypesList);
                        measurementSystemsList = farmViewModel.fetchMeasurementSystemList(productTypesList);
                        if (measurementSystemsList != null && !measurementSystemsList.isEmpty()) {
                            tvMeasurementSystem.setEnabled(true);
                            tvMeasurementSystem.setFocusable(true);
                            tvMeasurementSystem.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rectangle_border_black));

                            tvPurchaseContract.setEnabled(true);
                            tvPurchaseContract.setFocusable(true);
                            tvPurchaseContract.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rectangle_border_black));

                        } else {
                            tvMeasurementSystem.setEnabled(false);
                            tvMeasurementSystem.setFocusable(false);
                            tvMeasurementSystem.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.disabled_background));

                            tvPurchaseContract.setEnabled(false);
                            tvPurchaseContract.setFocusable(false);
                            tvPurchaseContract.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.disabled_background));
                        }
                        tvMeasurementSystem.setText("");
                        tvMeasurementSystem.setHint(R.string.select);
                        farmViewModel.selectedMeasurementSystem = null;
                        tvPurchaseContract.setText("");
                        tvPurchaseContract.setHint(R.string.select);
                        farmViewModel.selectedPurchaseContract = null;
                        validateFields();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                edtSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable editable) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
                        filter(charSequence.toString(), type);
                    }
                });
            } else if (type.equalsIgnoreCase("PURCHASECONTRACTS")) {
                purchaseContractArrayList = new ArrayList<>();
                txtDialogTitle.setText(R.string.purchase_contract);
                edtSearch.setHint(R.string.search_purchase_contract);
                purchaseContractCommonRecyclerViewAdapter = new CommonRecyclerViewAdapter<PurchaseContract>(this, purchaseContractList, R.layout.row_dialog_list) {
                    @Override
                    public void onPostBindViewHolder(ViewHolder holder, PurchaseContract purchaseContract) {
                        if (purchaseContract != null) {
                            if (purchaseContract.isSelected()) {
                                holder.setViewVisibility(R.id.imgSelected, 0);
                                holder.setViewTypeface(R.id.txtName, ResourcesCompat.getFont(holder.itemView.getContext(), R.font.montserrat_bold));
                            } else {
                                holder.setViewVisibility(R.id.imgSelected, 8);
                                holder.setViewTypeface(R.id.txtName, ResourcesCompat.getFont(holder.itemView.getContext(), R.font.montserrat_medium));
                            }

                            if(purchaseContract.getDescription() != null && !purchaseContract.getDescription().isEmpty()) {
                                holder.setViewText(R.id.txtName, purchaseContract.getContractCode() + " - " + purchaseContract.getPurchaseUnit() + " - " + purchaseContract.getDescription());
                            } else {
                                holder.setViewText(R.id.txtName, purchaseContract.getContractCode() + " - " + purchaseContract.getPurchaseUnit());
                            }
                        }
                    }
                };
                recyclerView.setAdapter(purchaseContractCommonRecyclerViewAdapter);
                purchaseContractCommonRecyclerViewAdapter.setOnItemClickListener((view, i) -> {
                    PurchaseContract purchaseContract;
                    try {
                        if (!purchaseContractArrayList.isEmpty()) {
                            purchaseContract = purchaseContractArrayList.get(i);
                        } else {
                            purchaseContract = purchaseContractList.get(i);
                        }
                        tvPurchaseContract.setText(String.format("%s - %s", purchaseContract.getDescription(), purchaseContract.getPurchaseUnit()));
                        farmViewModel.selectedPurchaseContract(purchaseContractList, purchaseContractArrayList, purchaseContract, i);
                        validateFields();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();

                });
                dialog.show();
                edtSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable editable) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
                        filter(charSequence.toString(), type);
                    }
                });
            } else if (type.equalsIgnoreCase("MEASUREMENTSYSTEMS")) {
                measurementSystemsArrayList = new ArrayList<>();
                txtDialogTitle.setText(R.string.measurement_systems);
                edtSearch.setHint(R.string.search_measurement_system);
                measurementSystemsCommonRecyclerViewAdapter = new CommonRecyclerViewAdapter<MeasurementSystems>(this, measurementSystemsList, R.layout.row_dialog_list) {
                    @Override
                    public void onPostBindViewHolder(ViewHolder holder, MeasurementSystems measurementSystem) {
                        if (measurementSystem != null) {
                            if (measurementSystem.isSelected()) {
                                holder.setViewVisibility(R.id.imgSelected, 0);
                                holder.setViewTypeface(R.id.txtName, ResourcesCompat.getFont(holder.itemView.getContext(), R.font.montserrat_bold));
                            } else {
                                holder.setViewVisibility(R.id.imgSelected, 8);
                                holder.setViewTypeface(R.id.txtName, ResourcesCompat.getFont(holder.itemView.getContext(), R.font.montserrat_medium));
                            }
                            holder.setViewText(R.id.txtName, measurementSystem.getMeasurementSystemName());
                        }
                    }
                };
                recyclerView.setAdapter(measurementSystemsCommonRecyclerViewAdapter);
                measurementSystemsCommonRecyclerViewAdapter.setOnItemClickListener(new CommonRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public final void onItemClick(View view, int i) {
                        MeasurementSystems measurementSystems;
                        try {
                            if (!measurementSystemsArrayList.isEmpty()) {
                                measurementSystems = measurementSystemsArrayList.get(i);
                            } else {
                                measurementSystems = measurementSystemsList.get(i);
                            }
                            tvMeasurementSystem.setText(measurementSystems.getMeasurementSystemName());
                            farmViewModel.selectedMeasurementSystem(measurementSystemsList, measurementSystemsArrayList, measurementSystems, i);
                            validateFields();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();

                    }
                });
                dialog.show();
                edtSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable editable) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
                        filter(charSequence.toString(), type);
                    }
                });
            } else if (type.equalsIgnoreCase("WAREHOUSES")) {
                warehousesArrayList = new ArrayList<>();
                txtDialogTitle.setText(R.string.warehouse);
                edtSearch.setHint(R.string.search_warehouse);
                warehousesCommonRecyclerViewAdapter = new CommonRecyclerViewAdapter<Warehouses>(this, warehousesList, R.layout.row_dialog_list) {
                    @Override
                    public void onPostBindViewHolder(ViewHolder holder, Warehouses warehouse) {
                        if (warehouse != null) {
                            if (warehouse.isSelected()) {
                                holder.setViewVisibility(R.id.imgSelected, 0);
                                holder.setViewTypeface(R.id.txtName, ResourcesCompat.getFont(holder.itemView.getContext(), R.font.montserrat_bold));
                            } else {
                                holder.setViewVisibility(R.id.imgSelected, 8);
                                holder.setViewTypeface(R.id.txtName, ResourcesCompat.getFont(holder.itemView.getContext(), R.font.montserrat_medium));
                            }
                            holder.setViewText(R.id.txtName, warehouse.getWarehouseName());
                        }
                    }
                };
                recyclerView.setAdapter(warehousesCommonRecyclerViewAdapter);
                warehousesCommonRecyclerViewAdapter.setOnItemClickListener((view, i) -> {
                    Warehouses warehouses;
                    try {
                        if (!warehousesArrayList.isEmpty()) {
                            warehouses = warehousesArrayList.get(i);
                        } else {
                            warehouses = warehousesList.get(i);
                        }
                        tvWarehouse.setText(warehouses.getWarehouseName());
                        farmViewModel.selectedWarehouse(warehousesList, warehousesArrayList, warehouses, i);
                        validateFields();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                });
                dialog.show();
                edtSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable editable) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
                        filter(charSequence.toString(), type);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filter(String searchText, String type) {
        try {
            if (type.equalsIgnoreCase("SUPPLIERS")) {
                suppliersArrayList = new ArrayList<>();
                for (Suppliers suppliers : suppliersList) {
                    if (suppliers.getSupplierName().toLowerCase().contains(searchText.toLowerCase())
                            || suppliers.getSupplierCode().toLowerCase().contains(searchText.toLowerCase())) {
                        suppliersArrayList.add(suppliers);
                    }
                }
                if (suppliersArrayList.isEmpty()) {
                    txtNoDataFound.setVisibility(View.VISIBLE);
                } else {
                    txtNoDataFound.setVisibility(View.GONE);
                }
                suppliersCommonRecyclerViewAdapter.filterList(suppliersArrayList);
            } else if (type.equalsIgnoreCase("PRODUCTS")) {
                suppliersProductsArrayList = new ArrayList<>();
                for (SupplierProducts supplierProducts : suppliersProductsList) {
                    if (supplierProducts.getProductName().toLowerCase().contains(searchText.toLowerCase())) {
                        suppliersProductsArrayList.add(supplierProducts);
                    }
                }
                if (suppliersProductsArrayList.isEmpty()) {
                    txtNoDataFound.setVisibility(View.VISIBLE);
                } else {
                    txtNoDataFound.setVisibility(View.GONE);
                }
                suppliersProductsCommonRecyclerViewAdapter.filterList(suppliersProductsArrayList);
            } else if (type.equalsIgnoreCase("PRODUCTTYPES")) {
                suppliersProductTypesArrayList = new ArrayList<>();
                for (SupplierProductTypes supplierProductTypes : suppliersProductTypesList) {
                    if (supplierProductTypes.getProductTypeName().toLowerCase().contains(searchText.toLowerCase())) {
                        suppliersProductTypesArrayList.add(supplierProductTypes);
                    }
                }
                if (suppliersProductTypesArrayList.isEmpty()) {
                    txtNoDataFound.setVisibility(View.VISIBLE);
                } else {
                    txtNoDataFound.setVisibility(View.GONE);
                }
                supplierProductTypesCommonRecyclerViewAdapter.filterList(suppliersProductTypesArrayList);
            } else if (type.equalsIgnoreCase("PURCHASECONTRACTS")) {
                purchaseContractArrayList = new ArrayList<>();
                for (PurchaseContract purchaseContract : purchaseContractList) {
                    if (purchaseContract.getContractCode().toLowerCase().contains(searchText.toLowerCase()) || purchaseContract.getPurchaseUnit().toLowerCase().contains(searchText.toLowerCase())) {
                        purchaseContractArrayList.add(purchaseContract);
                    }
                }
                if (purchaseContractArrayList.isEmpty()) {
                    txtNoDataFound.setVisibility(View.VISIBLE);
                } else {
                    txtNoDataFound.setVisibility(View.GONE);
                }
                purchaseContractCommonRecyclerViewAdapter.filterList(purchaseContractArrayList);
            } else if (type.equalsIgnoreCase("MEASUREMENTSYSTEMS")) {
                measurementSystemsArrayList = new ArrayList<>();
                for (MeasurementSystems measurementSystems : measurementSystemsList) {
                    if (measurementSystems.getMeasurementSystemName().toLowerCase().contains(searchText.toLowerCase())) {
                        measurementSystemsArrayList.add(measurementSystems);
                    }
                }
                if (measurementSystemsArrayList.isEmpty()) {
                    txtNoDataFound.setVisibility(View.VISIBLE);
                } else {
                    txtNoDataFound.setVisibility(View.GONE);
                }
                measurementSystemsCommonRecyclerViewAdapter.filterList(measurementSystemsArrayList);
            } else if (type.equalsIgnoreCase("WAREHOUSES")) {
                warehousesArrayList = new ArrayList<>();
                for (Warehouses warehouses : warehousesList) {
                    if (warehouses.getWarehouseName().toLowerCase().contains(searchText.toLowerCase())) {
                        warehousesArrayList.add(warehouses);
                    }
                }
                if (suppliersArrayList.isEmpty()) {
                    txtNoDataFound.setVisibility(View.VISIBLE);
                } else {
                    txtNoDataFound.setVisibility(View.GONE);
                }
                warehousesCommonRecyclerViewAdapter.filterList(warehousesArrayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnNext) {
                saveFarmDetails();
        }
    }

    private void saveFarmDetails() {
        try {
            FarmDetails farmDetail = new FarmDetails();
            farmDetail.setFarmId(0);
            farmDetail.setSupplierId(farmViewModel.selectedSupplier.getSupplierId());
            farmDetail.setProductId(farmViewModel.selectedSuppliersProducts.getProductId());
            farmDetail.setProductTypeId(farmViewModel.selectedSuppliersProductTypes.getProductTypeId());
            farmDetail.setInventoryOrder(Objects.requireNonNull(etInventoryOrder.getText()).toString());
            farmDetail.setPurchaseContractId(farmViewModel.selectedPurchaseContract.getContractId());
            farmDetail.setPurchaseDate(Objects.requireNonNull(etReceivedDate.getText()).toString());
            farmDetail.setTruckPlateNumber(Objects.requireNonNull(etTruckPlateNumber.getText()).toString());
            farmDetail.setSupplierName(farmViewModel.selectedSupplier.getSupplierName());
            farmDetail.setProductName(farmViewModel.selectedSuppliersProducts.getProductName());
            farmDetail.setMeasurementSystem(farmViewModel.selectedPurchaseContract.getPurchaseUnit());
            farmDetail.setCircAllowance(farmViewModel.selectedPurchaseContract.getCircAllowance());
            farmDetail.setLengthAllowance(farmViewModel.selectedPurchaseContract.getLengthAllowance());
            farmDetail.setDescription(farmViewModel.selectedPurchaseContract.getDescription());
            farmDetail.setTotalPieces(0);
            farmDetail.setGrossVolume(0);
            farmDetail.setNetVolume(0);

            String tempFarmId = "F" + CommonUtils.getCurrentLocalDateTimeStamp();
            if (!Objects.equals(PreferenceManager.INSTANCE.getLastTempReceptionId(), "")) {
                farmDetail.setTempFarmId(PreferenceManager.INSTANCE.getLastTempReceptionId());
            } else {
                farmDetail.setTempFarmId(tempFarmId);
            }

            if (!Objects.equals(PreferenceManager.INSTANCE.getLastTempReceptionId(), "")) {
                startActivity(new Intent(CreateFarmActivity.this, FarmDataActivity.class)
                        .putExtra("FarmDetail", farmDetail));
            } else {
                PreferenceManager.INSTANCE.setLastTempReceptionId(tempFarmId);
                if(farmViewModel.saveFarmDetails(farmDetail) > 0) {
                    startActivity(new Intent(CreateFarmActivity.this, FarmDataActivity.class)
                            .putExtra("FarmDetail", farmDetail));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validateFields() {
        try {
            if (farmViewModel.selectedSupplier != null && farmViewModel.selectedSupplier.getSupplierId() > 0) {
                if (farmViewModel.selectedSuppliersProducts != null && farmViewModel.selectedSuppliersProducts.getSupplierProductId() > 0) {
                    if (farmViewModel.selectedSuppliersProductTypes != null && farmViewModel.selectedSuppliersProductTypes.getProductTypeId() > 0) {
                        if(Objects.requireNonNull(etTruckPlateNumber.getText()).length() > 0) {
                            if (Objects.requireNonNull(etInventoryOrder.getText()).length() > 0) {
                                btnNext.setEnabled(farmViewModel.selectedPurchaseContract != null && farmViewModel.selectedPurchaseContract.getContractId() > 0);
                            } else {
                                btnNext.setEnabled(false);
                            }
                        } else {
                            btnNext.setEnabled(false);
                        }
                    } else {
                        btnNext.setEnabled(false);
                    }
                } else {
                    btnNext.setEnabled(false);
                }
            } else {
                btnNext.setEnabled(false);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getString(R.string.error) + " : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}