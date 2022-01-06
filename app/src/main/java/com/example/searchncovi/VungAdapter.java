package com.example.searchncovi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchncovi.databinding.ItemSearchNcoviBinding;
import com.example.searchncovi.databinding.ItemVungCovidBinding;

import java.util.ArrayList;
import java.util.List;

import static com.example.searchncovi.R.drawable.bg_do;

public class VungAdapter extends RecyclerView.Adapter<VungAdapter.ViewHolder> {

    private List<InformationNcoviItem> list;
    private Context context;

    public VungAdapter(List<InformationNcoviItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public VungAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemVungCovidBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_vung_covid , parent, false);
        return new VungAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VungAdapter.ViewHolder holder, int position) {
        InformationNcoviItem item = list.get(position);
        holder.binding.tvHomNay.setText("+"+ item.getCasesToday()+"");
        holder.binding.tvTongSoCa.setText(item.getConfirmed()+"");
        holder.binding.tvTuVong.setText(item.getDeaths()+"");
        holder.binding.tvTenTinh.setText(item.getCountry_Region()+"");
        if (item.getCasesToday()>=200)
            holder.binding.lnBG.setBackgroundResource(R.drawable.bg_do);
        else if (item.getCasesToday()>=50)
            holder.binding.lnBG.setBackgroundResource(R.drawable.bg_cam);
        else
            holder.binding.lnBG.setBackgroundResource(R.drawable.bg_xanh);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemVungCovidBinding binding;

        public ViewHolder(@NonNull ItemVungCovidBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void filterList(ArrayList<InformationNcoviItem> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }

}
