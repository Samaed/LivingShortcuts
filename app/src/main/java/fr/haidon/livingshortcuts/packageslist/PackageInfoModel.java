package fr.haidon.livingshortcuts.packageslist;

import android.graphics.drawable.Drawable;

public class PackageInfoModel {
    public String label;
    public String name;
    public Drawable icon;

    public PackageInfoModel(String label, String name, Drawable icon) {
        this.label = label;
        this.name = name;
        this.icon = icon;
    }
}
