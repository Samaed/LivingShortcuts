package fr.haidon.livingshortcuts;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ApplicationListAdapter extends ArrayAdapter<ApplicationListModel> {

    public ApplicationListAdapter(Context context, List<ApplicationListModel> values) {
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
