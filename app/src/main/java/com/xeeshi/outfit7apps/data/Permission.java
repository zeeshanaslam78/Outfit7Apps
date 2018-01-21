package com.xeeshi.outfit7apps.data;

import android.graphics.drawable.Drawable;

/**
 * Created by ZEESHAN on 06/01/2018.
 */

public class Permission {

    private String label;
    private String description;
    private Drawable icon;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
