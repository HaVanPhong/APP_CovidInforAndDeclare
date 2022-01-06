package com.example.searchncovi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchncovi.databinding.ItemSearchNcoviBinding;

import java.util.ArrayList;
import java.util.List;

public class ItemSearchNcoviAdapter extends RecyclerView.Adapter<ItemSearchNcoviAdapter.ViewHolder> {

    private List<InformationNcoviItem> list;
    private Context context;

    public ItemSearchNcoviAdapter(List<InformationNcoviItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemSearchNcoviAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSearchNcoviBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_search_ncovi, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InformationNcoviItem item = list.get(position);
        holder.binding.tvCountry.setText(item.getCountry_Region());
        holder.binding.tvConfirmed.setText(String.valueOf(item.getConfirmed()));
        holder.binding.tvDeaths.setText(String.valueOf(item.getDeaths()));
        holder.binding.tvRecovered.setText(String.valueOf(item.getRecovered()));
        holder.binding.tvTreating.setText(String.valueOf(item.getTreating()));
        holder.binding.tvCasesToday.setText(String.valueOf(item.getCasesToday()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemSearchNcoviBinding binding;

        public ViewHolder(@NonNull ItemSearchNcoviBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void filterList(ArrayList<InformationNcoviItem> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }

}
