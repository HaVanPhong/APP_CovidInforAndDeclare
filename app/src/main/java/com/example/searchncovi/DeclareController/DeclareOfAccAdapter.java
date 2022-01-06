package com.example.searchncovi.DeclareController;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchncovi.Model.Account;
import com.example.searchncovi.Model.Declarations;
import com.example.searchncovi.R;

import java.util.List;

public class DeclareOfAccAdapter extends RecyclerView.Adapter<DeclareOfAccAdapter.ViewHolder> {
    List<Declarations> list;
    Context context;

    public List<Declarations> getList() {
        return list;
    }

    public void setList(List<Declarations> list) {
        this.list = list;
    }

    public DeclareOfAccAdapter(List<Declarations> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public DeclareOfAccAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_declare, parent, false);
        DeclareOfAccAdapter.ViewHolder viewHolder = new DeclareOfAccAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeclareOfAccAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Declarations declarations= list.get(position);
        holder.tvNgayKhaiBao.setText("Ngày khai báo: "+ declarations.getCreatedAt());
        holder.tvDien.setText("Thuộc diện: "+ declarations.getDuringPast().getFx());
        holder.cbFever.setChecked(declarations.getSympton().isFever());
        holder.cbSThr.setChecked(declarations.getSympton().isSoreThroat());
        holder.cbCough.setChecked(declarations.getSympton().isCough());
        holder.cbLTaste.setChecked(declarations.getSympton().isLoseOfTaste());
        holder.cbStuffy.setChecked(declarations.getSympton().isStuffy());
        holder.cbTired.setChecked(declarations.getSympton().isTired());
        holder.cbTX14.setChecked(declarations.getDuringPast().isContact14());
        holder.cbGoAbroad.setChecked(declarations.getDuringPast().isGoAbroadOr());
        holder.cbIso.setChecked(declarations.getDuringPast().isIsolation());
        holder.cbLPP.setChecked(declarations.getDuringPast().isClosePeopleHasSympton());
        holder.cbTreat.setChecked(declarations.getDuringPast().isTreatment());
    }

    @Override
    public int getItemCount() {
        if (list!=null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNgayKhaiBao, tvDien;
        CheckBox cbFever, cbSThr, cbCough, cbLTaste, cbStuffy, cbTired, cbTX14, cbGoAbroad, cbIso, cbLPP, cbTreat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNgayKhaiBao= itemView.findViewById(R.id.tvNgayKhaiBao);
            tvDien= itemView.findViewById(R.id.tvFx);
            cbFever= itemView.findViewById(R.id.cbFever);
            cbSThr= itemView.findViewById(R.id.cbSrThroat);
            cbCough= itemView.findViewById(R.id.cbCaugh);
            cbLTaste= itemView.findViewById(R.id.cbLoseTaste);
            cbStuffy= itemView.findViewById(R.id.cbStuffy);
            cbTired= itemView.findViewById(R.id.cbTired);
            cbTX14= itemView.findViewById(R.id.cbTX14);
            cbGoAbroad= itemView.findViewById(R.id.cbGoAbroad);
            cbIso= itemView.findViewById(R.id.cbIsolution);
            cbLPP= itemView.findViewById(R.id.cbClPP);
            cbTreat= itemView.findViewById(R.id.cbTreat);
        }
    }
}