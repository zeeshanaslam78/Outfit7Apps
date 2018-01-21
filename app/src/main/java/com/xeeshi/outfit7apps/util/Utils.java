package com.xeeshi.outfit7apps.util;

import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.xeeshi.outfit7apps.BuildConfig;

/**
 * Created by ZEESHAN on 06/01/2018.
 */

public class Utils {

    private static final String ANDROID = "android";

    public static boolean hasPermission(String packageName, String permission, final PackageManager pkgmanager) {
        return PackageManager.PERMISSION_GRANTED == pkgmanager.checkPermission(permission, packageName);
    }

    /**
     * Returns permissions' name (human-readable label) by permission key
     */
    public static CharSequence getPermissionLabel(String permission, PackageManager packageManager) {
        try {
            PermissionInfo permissionInfo = packageManager.getPermissionInfo(permission, 0);
            return permissionInfo.loadLabel(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Drawable getPermissionIcon(String permission, PackageManager packageManager) {
        try {
            if (BuildConfig.DEBUG) Log.d("getPermissionIcon", "package " + permission);
            PermissionInfo permissionInfo = packageManager.getPermissionInfo(permission, 0);
            PermissionGroupInfo groupInfo = packageManager.getPermissionGroupInfo(permissionInfo.group, 0);
            return packageManager.getResourcesForApplication(ANDROID).getDrawable(groupInfo.icon);
        } catch (PackageManager.NameNotFoundException|Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static CharSequence getPermissionDescription(String permission, PackageManager packageManager) {
        try {
            PermissionInfo permissionInfo = packageManager.getPermissionInfo(permission, 0);
            return permissionInfo.loadDescription(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
