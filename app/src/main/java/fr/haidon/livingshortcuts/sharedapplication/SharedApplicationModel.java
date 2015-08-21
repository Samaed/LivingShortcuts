package fr.haidon.livingshortcuts.sharedapplication;

import java.util.HashMap;
import java.util.Observable;

import fr.haidon.livingshortcuts.packageslist.PackageInfoModel;

public class SharedApplicationModel extends Observable {

    // TODO change the String part for the vuforia model if able to
    private HashMap<String, PackageInfoModel> associationTable;
    private String focusedItem;

    public SharedApplicationModel() {
        associationTable = new HashMap<>();
        focusedItem = null;
    }

    public void setAssociatedPackage(PackageInfoModel packageModel) {
        if (focusedItem == null) return;

        associationTable.put(focusedItem, packageModel);
        setChanged();
        notifyObservers();
    }

    public PackageInfoModel getAssociatedPackage() {
        if (focusedItem == null) return null;
        return associationTable.containsKey(focusedItem) ? associationTable.get(focusedItem) : null;
    }

    public String getFocusedItem() {
        return focusedItem;
    }

    public void setFocusedItem(String value) {
        focusedItem = value;
        setChanged();
        notifyObservers();
    }

    public void clear() {
        associationTable.clear();
        associationTable = null;
        focusedItem = null;
    }

}
