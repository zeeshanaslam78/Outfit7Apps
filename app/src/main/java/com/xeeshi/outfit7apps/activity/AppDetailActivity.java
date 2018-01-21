package com.xeeshi.outfit7apps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xeeshi.outfit7apps.R;
import com.xeeshi.outfit7apps.data.AppList;

/**
 * Created by ZEESHAN on 06/01/2018.
 */

public class AppDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail);

        if (null != getSupportActionBar())
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imgAppIcon = findViewById(R.id.img_app_icon);
        TextView txtAppName = findViewById(R.id.txt_app_name);
        TextView txtPackageName = findViewById(R.id.txt_package_name);
        TextView txtVersionName = findViewById(R.id.txt_version_name);
        TextView txtVersionCode = findViewById(R.id.txt_version_code);

        Button btnAppPermissions = findViewById(R.id.btn_app_permissions);
        Button btnOpenApp = findViewById(R.id.btn_open_app);

        final AppList appList = getIntent().getParcelableExtra(MainActivity.APP_LIST_OBJECT);
        if (null != appList) {
            imgAppIcon.setImageDrawable(appList.getIcon());
            txtAppName.setText(appList.getName());
            txtPackageName.setText(appList.getPackageName());
            txtVersionName.setText(appList.getVersionName());
            txtVersionCode.setText(appList.getVersionCode());
        }

        btnAppPermissions.setOnClickListener(v -> {
            Intent appDetailPageIntent = new Intent(AppDetailActivity.this, PermissionsActivity.class);
            appDetailPageIntent.putExtra(MainActivity.APP_LIST_OBJECT, appList);
            startActivity(appDetailPageIntent);
        });

        btnOpenApp.setOnClickListener(v -> {
            if (appList != null) {
                Intent launchApp = getPackageManager().getLaunchIntentForPackage(appList.getPackageName());
                startActivity(launchApp);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
