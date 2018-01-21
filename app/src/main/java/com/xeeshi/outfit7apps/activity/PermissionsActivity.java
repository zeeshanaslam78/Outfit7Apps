package com.xeeshi.outfit7apps.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.xeeshi.outfit7apps.R;
import com.xeeshi.outfit7apps.adapter.PermissionAdapter;
import com.xeeshi.outfit7apps.data.AppList;
import com.xeeshi.outfit7apps.data.Permission;
import com.xeeshi.outfit7apps.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZEESHAN on 06/01/2018.
 */

public class PermissionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        if (null != getSupportActionBar())
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView = findViewById(R.id.listview_permission);

        final AppList appList = getIntent().getParcelableExtra(MainActivity.APP_LIST_OBJECT);
        if (null != appList) {

            List<Permission> permissionList = new ArrayList<>();
            if ( null != appList.getRequestedPermissions() && appList.getRequestedPermissions().size() > 0) {
                for (int i = 0; i < appList.getRequestedPermissions().size(); i++) {

                    Permission permission = new Permission();
                    permission.setLabel(Utils.getPermissionLabel( appList.getRequestedPermissions().get(i), getPackageManager()).toString());

                    CharSequence descriptionCharSequence = Utils.getPermissionDescription( appList.getRequestedPermissions().get(i), getPackageManager());
                    if (null != descriptionCharSequence)
                        permission.setDescription(descriptionCharSequence.toString());
                    else
                        permission.setDescription(null);


                    permission.setIcon(Utils.getPermissionIcon( appList.getRequestedPermissions().get(i), getPackageManager() ));

                    permissionList.add(permission);
                }
            }

            PermissionAdapter permissionAdapter = new PermissionAdapter(PermissionsActivity.this, permissionList);
            listView.setAdapter(permissionAdapter);

        }

        listView.setOnItemClickListener((parent, view, position, id) -> {

            Permission permission = (Permission) parent.getItemAtPosition(position);
            if (null != permission) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PermissionsActivity.this);
                if (null != permission.getDescription() && permission.getDescription().length()>0)
                    builder.setMessage(permission.getDescription());
                else
                    builder.setMessage("Description not found.");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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
