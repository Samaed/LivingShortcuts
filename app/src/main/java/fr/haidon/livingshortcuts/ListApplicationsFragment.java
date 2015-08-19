package fr.haidon.livingshortcuts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListApplicationsFragment extends DialogFragment {

    private ApplicationListAdapter listAdapter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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
                    ApplicationListModel item = listAdapter.getItem(id);
                    Toast.makeText(getActivity(), item.label + " selected", Toast.LENGTH_LONG).show();

                    // TODO change the test to the association in memory of the item.name
                    // To test the application we will launch the package
                    getActivity().startActivity(getActivity().getPackageManager().getLaunchIntentForPackage(item.name));
                }
            });

        return builder.create();
    }

    private ApplicationListAdapter createAdapter() {
        List<ApplicationListModel> models = new ArrayList<>();

        final PackageManager packageManager = getActivity().getPackageManager();
        List<PackageInfo> packages = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);

        for (PackageInfo p : packages) {
            // TODO correct the flag part
            if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
                models.add(new ApplicationListModel(p.applicationInfo.loadLabel(packageManager).toString(), p.packageName, p.applicationInfo.loadIcon(packageManager)));
        }

        return new ApplicationListAdapter(getActivity(), models);
    }
}
