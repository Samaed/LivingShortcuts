package fr.haidon.livingshortcuts.sharedapplication;

import android.app.Application;
import android.content.res.Configuration;

import java.util.Observer;

import fr.haidon.livingshortcuts.packageslist.PackageInfoModel;

public class SharedApplication extends Application {

    private SharedApplicationModel model;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        model = new SharedApplicationModel();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        model.clear();
    }

    public void setAssociatedPackage(PackageInfoModel packageModel) {
        model.setAssociatedPackage(packageModel);
    }

    public PackageInfoModel getAssociatedPackage() {
        return model.getAssociatedPackage();
    }

    public String getFocusedItem() {
        return model.getFocusedItem();
    }

    public void setFocusedItem(String value) {
        model.setFocusedItem(value);
    }

    public void addObserver(Observer observer) {
        model.addObserver(observer);
    }

    public void deleteObserver(Observer observer) {
        model.deleteObserver(observer);
    }
}
