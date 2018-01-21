package com.xeeshi.outfit7apps.activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import com.xeeshi.outfit7apps.BuildConfig;
import com.xeeshi.outfit7apps.R;
import com.xeeshi.outfit7apps.adapter.AppAdapter;
import com.xeeshi.outfit7apps.data.AppList;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String APP_LIST_OBJECT = "APP_LIST_OBJECT";

    private ListView listViewApp;
    private ViewSwitcher viewSwitcher;

    Observable<List<AppList>> listObservable;
    Observer<List<AppList>> listObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewApp = findViewById(R.id.listview_app);
        viewSwitcher = findViewById(android.R.id.empty);
        listViewApp.setEmptyView(viewSwitcher);

        listObservable = Observable.create(e -> {

            final List<PackageInfo> packageInfoList = getPackageManager().getInstalledPackages(PackageManager.GET_PERMISSIONS);
            List<AppList> appLists = new ArrayList<>();
            for (int i = 0; i < packageInfoList.size(); i++) {
                PackageInfo packageInfo = packageInfoList.get(i);
                if ( (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0 ) {

                    if (packageInfo.packageName.contains("com.outfit7")) {
                        AppList appList = new AppList();
                        appList.setName(packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
                        appList.setIcon(packageInfo.applicationInfo.loadIcon(getPackageManager()));

                        appList.setPackageName(packageInfo.packageName);
                        appList.setVersionName(packageInfo.versionName);
                        appList.setVersionCode(String.valueOf(packageInfo.versionCode));

                        appList.setRequestedPermissions( packageInfo.requestedPermissions );

                        appLists.add(appList);
                    }
                }
            }
            e.onNext(appLists);

            e.onComplete();
        });

        listObserver = new Observer<List<AppList>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<AppList> appLists) {
                AppAdapter appAdapter = new AppAdapter(MainActivity.this, appLists);
                listViewApp.setAdapter(appAdapter);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                viewSwitcher.setDisplayedChild(1);
            }
        };

        listObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listObserver);

        listViewApp.setOnItemClickListener((parent, view, position, id) -> {
            AppList appList = (AppList) parent.getItemAtPosition(position);
            if (BuildConfig.DEBUG) Log.d(TAG, "AppList " + appList.toString());
            Intent appDetailPageIntent = new Intent(MainActivity.this, AppDetailActivity.class);
            appDetailPageIntent.putExtra(APP_LIST_OBJECT, appList);
            startActivity(appDetailPageIntent);
        });
    }

}
