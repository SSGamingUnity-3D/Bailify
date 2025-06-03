package com.example.excusegenerator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;

public class FavoriteExcuseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<HashMap<String, String>> data;
    private DatabaseHelper dbHelper;

    public FavoriteExcuseAdapter(Context context, ArrayList<HashMap<String, String>> data, DatabaseHelper dbHelper) {
        this.context = context;
        this.data = data;
        this.dbHelper = dbHelper;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public HashMap<String, String> getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position; // Could improve with real ID if available
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false);
            holder = new ViewHolder();
            holder.excuseText = convertView.findViewById(R.id.text1);
            holder.infoText = convertView.findViewById(R.id.text2);
            //holder.editBtn = convertView.findViewById(R.id.btn_edit);
            holder.deleteBtn = convertView.findViewById(R.id.btn_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final HashMap<String, String> item = getItem(position);

        holder.excuseText.setText(item.get("excuse"));
        holder.infoText.setText(item.get("info"));

//        holder.editBtn.setOnClickListener(v -> {
//            // TODO: Implement edit functionality (launch edit screen or dialog)
//            Toast.makeText(context, "Edit clicked: " + item.get("excuse"), Toast.LENGTH_SHORT).show();
//        });

        holder.deleteBtn.setOnClickListener(v -> {
            String excuseToDelete = item.get("excuse");
            boolean deleted = dbHelper.deleteFavoriteExcuseByText(excuseToDelete);
            if (deleted) {
                Toast.makeText(context, "Deleted: " + excuseToDelete, Toast.LENGTH_SHORT).show();
                data.remove(position);
                notifyDataSetChanged();
            } else {
                Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView excuseText;
        TextView infoText;
        //ImageButton editBtn;
        ImageButton deleteBtn;
    }
}
