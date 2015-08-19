package fr.haidon.livingshortcuts;

import android.graphics.drawable.Drawable;

public class ApplicationListModel {
    public String label;
    public String name;
    public Drawable icon;

    public ApplicationListModel(String label, String name, Drawable icon) {
        this.label = label;
        this.name = name;
        this.icon = icon;
    }
}
