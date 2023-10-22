package com.example.garage_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private int vehicleType;
    private List<Integer> mPicture;
    private List<String> mPrice;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public MyRecyclerViewAdapter(Context context, int vehicleType, List<String> data, List<Integer> mPic, List<String> prices) {
        this.mInflater = LayoutInflater.from(context);
        this.vehicleType = vehicleType;
        this.mData = data;
        this.mPicture = mPic;
        this.mPrice = prices;
    }



    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position) {
        String vehicle = mData.get(position);
        String vPrice = mPrice.get(position);
        Glide.with(mInflater.getContext())
                .load(mPicture.get(position))
                .fitCenter()
                .apply(new RequestOptions().override(600, 200))
                .into(holder.myImageView);

        holder.vehicleNameTV.setText(vehicle);
        holder.vehiclePriceTV.setText(vPrice);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView vehicleNameTV;
        TextView vehiclePriceTV;
        ImageView myImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myImageView  = itemView.findViewById(R.id.imageView);
            vehicleNameTV = itemView.findViewById(R.id.RCvehicleName);
            vehiclePriceTV = itemView.findViewById(R.id.RCVehiclePrice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    String getItem(int id){
        return mData.get(id);
    }
    int getPicture(int id) {return mPicture.get(id);}
    int getVehicleType() {return vehicleType;}

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

