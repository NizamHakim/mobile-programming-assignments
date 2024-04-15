package com.example.tugas6_listview;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class MhsAdapter extends ArrayAdapter<Mhs> {
    private final Context mContext;
    private final int mResource;

    public MhsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Mhs> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.list_row, parent, false);
        }

        TextView list_nrp = convertView.findViewById(R.id.list_nrp);
        TextView list_nama = convertView.findViewById(R.id.list_nama);
        ShapeableImageView list_image = convertView.findViewById(R.id.list_img);

        Mhs mhs = getItem(position);
        list_nrp.setText(mhs.getNRP());
        list_nama.setText(mhs.getNama());
        Glide.with(mContext).load(Uri.parse(mhs.getImage())).into(list_image);

        return convertView;
    }
}
