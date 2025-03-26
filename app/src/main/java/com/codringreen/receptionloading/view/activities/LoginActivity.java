package com.codringreen.receptionloading.view.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codringreen.receptionloading.R;
import com.codringreen.receptionloading.helper.PreferenceManager;
import com.codringreen.receptionloading.model.response.OriginDataResponse;
import com.codringreen.receptionloading.utils.DividerItemDecoration;
import com.codringreen.receptionloading.view.adapter.CommonRecyclerViewAdapter;
import com.codringreen.receptionloading.view.adapter.ViewHolder;
import com.codringreen.receptionloading.viewmodel.LoginViewModel;
import com.codringreen.receptionloading.utils.CustomProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private AppCompatEditText etCountry;
    private AppCompatImageView imgLogo;
    private LoginViewModel loginViewModel;
    private CommonRecyclerViewAdapter<OriginDataResponse> originCommonRecyclerViewAdapter;
    private List<OriginDataResponse> originsArrayList;
    private List<OriginDataResponse> originsList;
    private AppCompatTextView txtNoDataFound;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void initVariable(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            lockPortraitMode();
        }
        setContentView(R.layout.activity_login);
        initComponents();
    }

    private void initComponents() {
        try {
            if (getApplicationContext() == null) {
                throw new IllegalStateException("Application context is null");
            }

            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            AppCompatEditText etUsername = findViewById(R.id.etUsername);
            AppCompatEditText etPassword = findViewById(R.id.etPassword);
            etCountry = findViewById(R.id.etCountry);
            final AppCompatButton btnLogin = findViewById(R.id.btnLogin);

            etCountry.setSingleLine(true);
            etUsername.setSingleLine(true);
            etPassword.setSingleLine(true);
            etCountry.setFocusableInTouchMode(false);
            etCountry.setFocusable(false);
            etCountry.setOnClickListener(v -> showDataDialog());

            loginViewModel = new ViewModelProvider(this, viewModelFactory).get(LoginViewModel.class);

            LiveData<Boolean> loginBtnState = loginViewModel.getLoginBtnState();
            Objects.requireNonNull(btnLogin);
            loginBtnState.observe(this, btnLogin::setEnabled);

            loginViewModel.getError().observe(this, s -> showDialog(s, loginViewModel.getErrorTitle(), null));

            loginViewModel.getLoginStatus().observe(this, aBoolean -> startMainActivity());

            loginViewModel.getProgressBar().observe(this, aBoolean -> {
                if (aBoolean) {
                    if (getApplicationContext() != null) {
                        CustomProgress.getInstance(this).showProgress(this);
                    } else {
                        CustomProgress.getInstance(this).hideProgress();
                    }
                } else {
                    CustomProgress.getInstance(this).hideProgress();
                }
            });

            btnLogin.setOnClickListener(this);
            loginViewModel.getOrigins();

            loginViewModel.getOriginStatus().observe(this, aBoolean -> {
                originsList = new ArrayList<>(loginViewModel.origins);
            });

            etUsername.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    loginViewModel.setUserName(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            etPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    loginViewModel.setPassword(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void startMainActivity() {
        try {
            if (PreferenceManager.INSTANCE.isLoggedIn()) {
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class)
                        .putExtra("DownloadMaster", "Yes")
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            loginViewModel.postLogin();
        }
    }

    private void showDataDialog() {
        try {
            final Dialog dialog = new Dialog(this, R.style.DialogTheme);
            dialog.requestWindowFeature(1);
            Window window = dialog.getWindow();
            Objects.requireNonNull(window);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.9);
            layoutParams.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.9);
            layoutParams.gravity = Gravity.CENTER;
            window.setAttributes(layoutParams);
            dialog.setContentView(R.layout.list_dialog);
            AppCompatTextView appCompatTextView = dialog.findViewById(R.id.txtDialogTitle);
            this.txtNoDataFound = dialog.findViewById(R.id.txtNoDataFound);
            AppCompatEditText appCompatEditText = dialog.findViewById(R.id.edtSearch);
            AppCompatImageView appCompatImageView = dialog.findViewById(R.id.imgClose);
            RecyclerView recyclerView = dialog.findViewById(R.id.rvList);
            appCompatImageView.setOnClickListener(view -> dialog.dismiss());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.addItemDecoration(new DividerItemDecoration(this));
            originsArrayList = new ArrayList<>();
            appCompatTextView.setText(R.string.select_country);
            appCompatEditText.setHint(R.string.search_country);
            originCommonRecyclerViewAdapter = new CommonRecyclerViewAdapter<OriginDataResponse>(this, this.originsList, R.layout.row_dialog_list) {
                @Override
                public void onPostBindViewHolder(ViewHolder holder, OriginDataResponse originResponse) {
                    if (originResponse != null) {
                        if (originResponse.isSelected()) {
                            holder.setViewVisibility(R.id.imgSelected, 0);
                            holder.setViewTypeface(R.id.txtName, ResourcesCompat.getFont(holder.itemView.getContext(), R.font.montserrat_bold));
                        } else {
                            holder.setViewVisibility(R.id.imgSelected, 8);
                            holder.setViewTypeface(R.id.txtName, ResourcesCompat.getFont(holder.itemView.getContext(), R.font.montserrat_regular));
                        }
                        holder.setViewText(R.id.txtName, originResponse.getOriginName());
                    }
                }
            };
            recyclerView.setAdapter(originCommonRecyclerViewAdapter);
            originCommonRecyclerViewAdapter.setOnItemClickListener((view, i) -> {
                OriginDataResponse originDataResponse;
                try {
                    if (!originsArrayList.isEmpty()) {
                        originDataResponse = originsArrayList.get(i);
                    } else {
                        originDataResponse = originsList.get(i);
                    }
                    etCountry.setText(originDataResponse.getOriginName());
                    loginViewModel.setOriginId(originDataResponse.getOriginId());
                    loginViewModel.selectedOrigin(originsList, originsArrayList, originDataResponse, i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

            });
            dialog.show();
            appCompatEditText.addTextChangedListener(new TextWatcher() { // from class: com.codringreen.receptionloading.view.activities.LoginActivity.4
                @Override
                public void afterTextChanged(Editable editable) {
                }

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    LoginActivity.this.filter(charSequence.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filter(String searchText) {
        try {
            originsArrayList = new ArrayList<>();
            for (OriginDataResponse originDataResponse : this.originsList) {
                if (originDataResponse.getOriginName().toLowerCase().contains(searchText.toLowerCase())) {
                    this.originsArrayList.add(originDataResponse);
                }
            }
            if (originsArrayList.isEmpty()) {
                txtNoDataFound.setVisibility(View.VISIBLE);
            } else {
                txtNoDataFound.setVisibility(View.GONE);
            }
            originCommonRecyclerViewAdapter.filterList(originsArrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void lockPortraitMode() {
        getWindow().setAttributes(new WindowManager.LayoutParams() {{
            preferredDisplayModeId = 1;
        }});
    }
}