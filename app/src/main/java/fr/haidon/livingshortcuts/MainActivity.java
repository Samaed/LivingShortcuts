package fr.haidon.livingshortcuts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable != null && data != null) {
            SharedApplicationModel.Type message = (SharedApplicationModel.Type) data;
            SharedApplicationModel model = (SharedApplicationModel) observable;

            if (message == SharedApplicationModel.Type.ITEM_FOCUSED) {
                if (model.getFocusedItem() != null)
                    Log.d("ItemFocusedReceived", model.getFocusedItem());
                else
                    Log.d("ItemFocusedLost",":'(");
            } else if (message == SharedApplicationModel.Type.ASSOCIATION_CHANGED) {
                // TODO do association changed stuff (update UI)
            }

            findViewById(R.id.button_validate).setVisibility(model.getAssociatedPackage() != null ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public void startApplication(View view) {
        PackageInfoModel model = ((SharedApplication) getApplication()).getAssociatedPackage();
        if (model == null) return;
        startActivity(getPackageManager().getLaunchIntentForPackage(model.name));
    }

    public void debugFocus(View item) {
        SharedApplication app = (SharedApplication) getApplication();

        Log.d("DebugPushed","Test");

        if (app.getFocusedItem() == null)
            app.setFocusedItem("item");
        else
            app.setFocusedItem(null);
    }

    public void displayFragment(View item) {

        SharedApplication application = (SharedApplication) getApplication();

        if (application.getFocusedItem() != null) {
            Fragment fragment = new PackagesListFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(fragment, "list_fragment");
            transaction.commit();
        } else {
            // TODO try to get an item focused on vuforia side
            // If so, update the application focused item
        }
    }
}
