package com.example.searchncovi.DeclareController;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchncovi.Model.Account;
import com.example.searchncovi.R;

import java.util.List;

public class AccountDeclaredAdapter extends RecyclerView.Adapter<AccountDeclaredAdapter.ViewHolder> {
    List<Account> list;
    Context context;
    IOnClickAccount iOnClickAccount;

    public List<Account> getList() {
        return list;
    }

    public void setList(List<Account> list) {
        this.list = list;
    }

    public IOnClickAccount getiOnClickAccount() {
        return iOnClickAccount;
    }

    public void setiOnClickAccount(IOnClickAccount iOnClickAccount) {
        this.iOnClickAccount = iOnClickAccount;
    }

    public AccountDeclaredAdapter(List<Account> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AccountDeclaredAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_account_declared, parent, false);
        AccountDeclaredAdapter.ViewHolder viewHolder = new AccountDeclaredAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AccountDeclaredAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Account account= list.get(position);
        holder.tvName.setText(account.getFullname());
        holder.tvPhoneNumber.setText(account.getPhoneNumber());
        holder.tvAddress.setText(account.getAddress());
        holder.tvDob.setText(account.getDob());

        holder.lnItemAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickAccount.iOnClickAccount(account, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list!=null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhoneNumber, tvDob, tvAddress;
        LinearLayout lnItemAccount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName= itemView.findViewById(R.id.tvName);
            tvPhoneNumber= itemView.findViewById(R.id.tvPhoneNumber);
            tvDob= itemView.findViewById(R.id.tvDob);
            tvAddress= itemView.findViewById(R.id.tvAddress);
            lnItemAccount= itemView.findViewById(R.id.lnItemAccount);
        }
    }
}