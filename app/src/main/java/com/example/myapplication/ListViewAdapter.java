package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<PersonData> {

    public ListViewAdapter(Context context, int resource, List<PersonData> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            //初回起動,データが1度も表示されてない場合
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            //データがすでに表示されている場合
            holder = (ViewHolder) convertView.getTag();

        }
        PersonData items = getItem(position);

        holder.textName.setText(items.getName());
        holder.textSubName.setText(items.getSubName());
        holder.textPhoneNumber.setText(items.getPhoneNumber());

        return convertView;
    }

    private static class ViewHolder {
        public TextView textName;
        public TextView textSubName;
        public TextView textPhoneNumber;

        public ViewHolder(View view) {
            textName = view.findViewById(R.id.text_name);
            textSubName = view.findViewById(R.id.text_sub_name);
            textPhoneNumber = view.findViewById(R.id.text_number);
        }
    }
}
