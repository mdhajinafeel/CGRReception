package com.codringreen.receptionloading.view.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.codringreen.receptionloading.R;
import com.codringreen.receptionloading.constants.NavigationType;
import com.codringreen.receptionloading.helper.PreferenceManager;
import com.codringreen.receptionloading.model.MenuModel;
import com.codringreen.receptionloading.utils.CircularImageView;
import com.codringreen.receptionloading.utils.CustomProgress;
import com.codringreen.receptionloading.view.adapter.NavigationAdapters;
import com.codringreen.receptionloading.viewmodel.DashboardViewModel;
import com.codringreen.receptionloading.viewmodel.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class DashboardActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawerLayout;
    private CircularImageView imgProfile;
    private ListView lstMenu;
    private List<MenuModel> menuModels;
    private Toolbar toolbar;
    private AppCompatTextView txtName;
    private AppCompatTextView txtNoDataFound;
    private AppCompatTextView tvNoReception;
    private boolean exitCode = false;

    private DashboardViewModel dashboardViewModel;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void initVariable(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_dashboard);
        initComponents();
    }

    private void initComponents() {
        try {
            hideKeyboard(this);
            drawerLayout = findViewById(R.id.drawerLayout);
            toolbar = findViewById(R.id.toolbar);
            lstMenu = findViewById(R.id.lstMenu);
            txtName = findViewById(R.id.txtName);
            AppCompatTextView txtLogout = findViewById(R.id.txtLogout);
            imgProfile = findViewById(R.id.imgProfile);
            tvNoReception = findViewById(R.id.tvNoReception);
            AppCompatTextView txtVersion = findViewById(R.id.txtVersion);
            AppCompatImageView imgClose = findViewById(R.id.imgClose);

            Bundle extras = getIntent().getExtras();

            setMenus();
            setUserProfile();

            PackageInfo packageInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
            txtVersion.setText(String.format("%s: %s.%s", "Version ", packageInfo.versionName, packageInfo.versionCode));
            txtVersion.setPaintFlags(txtVersion.getPaintFlags() | 8);

            imgClose.setOnClickListener(v -> closeDrawers());
            txtLogout.setOnClickListener(v -> closeDrawers());
            lstMenu.setOnItemClickListener(this);

            dashboardViewModel = new ViewModelProvider(this, viewModelFactory).get(DashboardViewModel.class);

            if (extras != null && extras.containsKey("DownloadMaster") && extras.getString("DownloadMaster") != null && Objects.requireNonNull(extras.getString("DownloadMaster")).equalsIgnoreCase("Yes")) {
                dashboardViewModel.getMasterDownload();
            }

            dashboardViewModel.getError().observe(this, s -> showDialog(s, dashboardViewModel.getErrorTitle(), null));

            dashboardViewModel.getDownloadState().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if(aBoolean) {
                        Toast.makeText(getApplicationContext(), R.string.data_synced_success, Toast.LENGTH_SHORT).show();
                    }
                }
            });

            dashboardViewModel.getProgressBar().observe(this, aBoolean -> {
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

            getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawers();
                        return;
                    }
                    if (exitCode) {
                        System.exit(0);
                    }
                    exitCode = true;
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.exitapp), Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(() -> exitCode = false,10000);
                }
            });

            txtLogout.setOnClickListener(v -> {
                closeDrawers();
                showConfirmation();
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error) + " : " + e.getMessage(), 0).show();
            e.printStackTrace();
        }
    }

    private void setMenus() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            setupDrawerToggle();
            lstMenu.setAdapter(new NavigationAdapters(this, R.layout.row_menu_item, getMenuListItems()));
        }
    }

    private void setUserProfile() {
        try {
            txtName.setText(PreferenceManager.INSTANCE.getKeyName());
            Glide.with(this).load(PreferenceManager.INSTANCE.getKeyPhoto()).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(R.drawable.ic_default_user).error(R.drawable.ic_default_user)).into(imgProfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupDrawerToggle() {
        try {
            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
            actionBarDrawerToggle.syncState();
            actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
            actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_menu);
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
            drawerLayout.setDrawerLockMode(0);
            drawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.notificationBarColor));
            actionBarDrawerToggle.setToolbarNavigationClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private List<MenuModel> getMenuListItems() {
        menuModels = new ArrayList<>();
        menuModels.add(new MenuModel(R.drawable.ic_dashboard, getString(R.string.dashboard), NavigationType.HOME));
        menuModels.add(new MenuModel(R.drawable.ic_sync, getString(R.string.synchronization), NavigationType.SYNCHRONIZATION));
        menuModels.add(new MenuModel(R.drawable.ic_farm, getString(R.string.farm_loading), NavigationType.FARM));
        menuModels.add(new MenuModel(R.drawable.ic_reception, getString(R.string.reception), NavigationType.RECEPTION));
        menuModels.add(new MenuModel(R.drawable.ic_dispatch, getString(R.string.dispatch), NavigationType.DISPATCH));
        menuModels.add(new MenuModel(R.drawable.ic_database, getString(R.string.export_database), NavigationType.EXPORT_DATABASE));
        return menuModels;
    }

    private void closeDrawers() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        new Bundle().putString("Title", menuModels.get(position).getMenuName());
        closeDrawers();

        NavigationType selectedType = menuModels.get(position).getNavigationType();
        switch (selectedType) {
            case SYNCHRONIZATION:
                dashboardViewModel.getMasterDownload();
                break;

            case RECEPTION:
                //startActivity(new Intent(this, ReceptionActivity.class));
                break;

            case DISPATCH:
                //  startActivity(new Intent(this, ContainerActivity.class));
                break;

            case EXPORT_DATABASE:
                //if (checkPermission()) {
                //     exportDB();
                //  } else {
                //     requestPermission();
                // }
                break;
            case HOME:
                break;
            case FARM:
                startActivity(new Intent(DashboardActivity.this, FarmActivity.class));
                break;
            default:
                // No action for HOME or unknown cases
                break;
        }
    }

    private void showConfirmation() {
        try {
            showDialogWithCancel(getString(R.string.logout_confirmation), getString(R.string.confirmation), (dialogInterface, i) -> {
                dialogInterface.dismiss();
                PreferenceManager.INSTANCE.clearLoginDetails();
                startActivity(new Intent(DashboardActivity.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}