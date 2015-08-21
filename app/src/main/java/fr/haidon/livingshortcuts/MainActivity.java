package fr.haidon.livingshortcuts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import fr.haidon.livingshortcuts.packageslist.PackageInfoModel;
import fr.haidon.livingshortcuts.packageslist.PackagesListFragment;
import fr.haidon.livingshortcuts.sharedapplication.SharedApplication;
import fr.haidon.livingshortcuts.sharedapplication.SharedApplicationModel;

public class MainActivity extends AppCompatActivity implements Observer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((SharedApplication)getApplication()).addObserver(this);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable != null) {
            SharedApplicationModel model = (SharedApplicationModel) observable;
            PackageInfoModel packageModel = model.getAssociatedPackage();

            // TODO do association changed stuff (update 3D UI)
            ((ImageView)findViewById(R.id.icon_validate)).setImageDrawable(packageModel != null ? packageModel.icon : null);
            if (packageModel != null) Toast.makeText(this, String.format("%s associated", packageModel.label), Toast.LENGTH_SHORT).show();
            findViewById(R.id.icon_validate).setVisibility(packageModel != null ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public void startApplication(View view) {
        PackageInfoModel model = ((SharedApplication) getApplication()).getAssociatedPackage();
        if (model == null) return;
        startActivity(getPackageManager().getLaunchIntentForPackage(model.name));
    }

    public void associate(View item) {
        SharedApplication application = (SharedApplication) getApplication();

        if (application.getFocusedItem() != null) {
            displayFragment();
        } else {
            // TODO try to get an item focused on vuforia side
            // If so, update the application focused item
            String scannedItem = (new Random().nextBoolean()) ? null : "ItemFocused";

            application.setFocusedItem(scannedItem);

            if (scannedItem == null)
                Toast.makeText(this, "Missing details on the model", Toast.LENGTH_SHORT).show();
            else
                displayFragment();
        }
    }

    public void displayFragment() {
        Fragment fragment = new PackagesListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(fragment, "list_fragment");
        transaction.commit();
    }
}
