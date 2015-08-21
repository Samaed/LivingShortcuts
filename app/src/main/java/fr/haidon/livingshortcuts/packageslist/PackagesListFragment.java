package fr.haidon.livingshortcuts.packageslist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

import fr.haidon.livingshortcuts.R;
import fr.haidon.livingshortcuts.sharedapplication.SharedApplication;

public class PackagesListFragment extends DialogFragment {

    private PackagesListAdapter listAdapter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // TODO get the sharedapplication item and if associated already, hightlight the right thing or something like that

        listAdapter = createAdapter();

        builder.setTitle(R.string.list_application_title)
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                }
            })
            .setAdapter(listAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                PackageInfoModel item = listAdapter.getItem(id);
                SharedApplication application = (SharedApplication) getActivity().getApplication();
                application.setAssociatedPackage(item);
                }
            });

        return builder.create();
    }

    private PackagesListAdapter createAdapter() {
        List<PackageInfoModel> models = new ArrayList<>();

        final PackageManager packageManager = getActivity().getPackageManager();

        // TODO load the list at the start of the activity maybe

        List<PackageInfo> packages = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);

        for (PackageInfo p : packages) {
            // TODO correct the flag part
            if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
                models.add(new PackageInfoModel(p.applicationInfo.loadLabel(packageManager).toString(), p.packageName, p.applicationInfo.loadIcon(packageManager)));
        }

        return new PackagesListAdapter(getActivity(), models);
    }
}
