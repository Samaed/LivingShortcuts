package fr.haidon.livingshortcuts.packageslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.haidon.livingshortcuts.R;

public class PackagesListAdapter extends ArrayAdapter<PackageInfoModel> {

    public PackagesListAdapter(Context context, List<PackageInfoModel> values) {
        super(context, -1, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.application_list_element, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.app_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.app_icon);
        textView.setText(getItem(position).label);
        imageView.setImageDrawable(getItem(position).icon);
        return rowView;
    }
}
