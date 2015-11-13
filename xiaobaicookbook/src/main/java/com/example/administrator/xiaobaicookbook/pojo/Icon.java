package com.example.administrator.xiaobaicookbook.pojo;

import android.graphics.drawable.Drawable;

/**
 * Created by Jason on 2015-11-12.
 */
public class Icon {
    private String label;
    private Drawable icon;
    private int category;

    public Icon() {
    }

    public Icon(String label, int category, Drawable icon) {
        this.label = label;
        this.category = category;
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
