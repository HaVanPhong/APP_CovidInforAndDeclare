package com.example.searchncovi;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.searchncovi.DeclareController.AccountDeclaredAdapter;
//import com.example.searchncovi.DeclareController.ApiController;
//import com.example.searchncovi.DeclareController.DeclareOfAccAdapter;
//import com.example.searchncovi.DeclareController.IOnClickAccount;
//import com.example.searchncovi.Model.Account;
//import com.example.searchncovi.Model.Declarations;
//import com.example.searchncovi.Model.DuringPast;
//import com.example.searchncovi.Model.RequestBodyDeclare;
//import com.example.searchncovi.Model.ResponseEntityAccount;
//import com.example.searchncovi.Model.ResponseEntityDeclare;
//import com.example.searchncovi.Model.ResponseLogin;
//import com.example.searchncovi.Model.Sympton;
import com.example.searchncovi.databinding.FragmentDeclareBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeclareFragment extends Fragment {


    public DeclareFragment() {

    }

    public static DeclareFragment newInstance(String param1, String param2) {
        DeclareFragment fragment = new DeclareFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    FragmentDeclareBinding binding;
    List<Declarations> declarationsList;
    List<Account> accounts;
    AccountDeclaredAdapter accountDeclaredAdapter;
    DeclareOfAccAdapter declareOfAccAdapter;
    Account currentAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_declare, container, false);

        getAllAccount();
        displayLogin();


        binding.edtSearchDeclare.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                List<Account> list = new ArrayList<>();
                int size = accounts.size();
                for (int i = 0; i < size; i++) {
                    if (accounts.get(i).getFullname().toLowerCase().contains(str.toLowerCase())) {
                        list.add(accounts.get(i));
                    }
                }
                accountDeclaredAdapter.setList(list);
                binding.revAccountDeclare.setAdapter(accountDeclaredAdapter);
            }
        });

        binding.btnAddDecalre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDeclare();
            }
        });



        return binding.getRoot();
    }

    private void addDeclare() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.form_declare);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtributes = window.getAttributes();
        windowAtributes.gravity = Gravity.CENTER_VERTICAL;
        window.setAttributes(windowAtributes);

        dialog.setCancelable(true);

        TextView tvNguoiKB;
        Button btnSend;
        Spinner spnFxF;
        CheckBox cbFever, cbSThr, cbCough, cbLTaste, cbStuffy, cbTired, cbTX14, cbGoAbroad, cbIso, cbLPP, cbTreat;
        tvNguoiKB = dialog.findViewById(R.id.tvNguoiKB);
        btnSend = dialog.findViewById(R.id.btnSenDeclare);
        spnFxF = dialog.findViewById(R.id.spnFxF);
        cbFever = dialog.findViewById(R.id.cbFeverF);
        cbSThr = dialog.findViewById(R.id.cbSrThroatF);
        cbCough = dialog.findViewById(R.id.cbCaughF);
        cbLTaste = dialog.findViewById(R.id.cbLoseTasteF);
        cbStuffy = dialog.findViewById(R.id.cbStuffyF);
        cbTired = dialog.findViewById(R.id.cbTiredF);
        cbTX14 = dialog.findViewById(R.id.cbTX14F);
        cbGoAbroad = dialog.findViewById(R.id.cbGoAbroadF);
        cbIso = dialog.findViewById(R.id.cbIsolutionF);
        cbLPP = dialog.findViewById(R.id.cbClPPF);
        cbTreat = dialog.findViewById(R.id.cbTreatF);

        tvNguoiKB.setText("Khai báo y tế: " + currentAccount.getFullname());

        String[] Fxs = {"Không", "F4", "F3", "F2", "F1", "F0"};
        final String[] fx = {"Không"};
        spnFxF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fx[0] = Fxs[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapterFx = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, Fxs);
        adapterFx.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFxF.setAdapter(adapterFx);
//        spnFxF.


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sympton sympton = new Sympton(cbFever.isChecked(), cbCough.isChecked(), cbSThr.isChecked(), cbLTaste.isChecked(), cbTired.isChecked(), cbStuffy.isChecked());
                DuringPast duringPast = new DuringPast(fx[0], cbTX14.isChecked(), cbGoAbroad.isChecked(), cbIso.isChecked(), cbLPP.isChecked(), cbTreat.isChecked());
                RequestBodyDeclare bodyDeclare = new RequestBodyDeclare(sympton, duringPast, currentAccount.getId());
                ApiController.apiService.declare(bodyDeclare).enqueue(new Callback<RequestBodyDeclare>() {
                    @Override
                    public void onResponse(Call<RequestBodyDeclare> call, Response<RequestBodyDeclare> response) {
                        if (response.isSuccessful()) {
                            getAllAccount();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getContext(), "Không thành công" + currentAccount.getFullname(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RequestBodyDeclare> call, Throwable t) {
                        Toast.makeText(getContext(), "Lỗi đường truyền", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        dialog.show();
    }

    private void displayLogin() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_login);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtributes = window.getAttributes();
        windowAtributes.gravity = Gravity.CENTER_VERTICAL;
        window.setAttributes(windowAtributes);

        dialog.setCancelable(false);

        EditText edtUsername = dialog.findViewById(R.id.edtUser);
        EditText edtPass = dialog.findViewById(R.id.edtPass);
        EditText edtRePass = dialog.findViewById(R.id.edtRePass);
        EditText edtFullName = dialog.findViewById(R.id.edtFullName);
        EditText edtPhoneNumber = dialog.findViewById(R.id.edtPhoneNumber);
        EditText edtAddress = dialog.findViewById(R.id.edtAddress);
        EditText edtDob = dialog.findViewById(R.id.edtNgaySinh);

        TextView tvDK = dialog.findViewById(R.id.tvDK);

        final boolean[] confirmDisplay = {true};

        Button btnLogin = dialog.findViewById(R.id.btnDN);
        Button btnRegister = dialog.findViewById(R.id.btnDK);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPass.getText().toString().trim();
                if (username.compareTo("") == 0 || password.compareTo("") == 0) {
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    Account account = new Account(null, username, password, null, null, null, null);
                    ApiController.apiService.login(account).enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                currentAccount = response.body().getData();
                                Log.d("lg", "onResponse: " + currentAccount.getFullname());
                                dialog.dismiss();
                            } else {
                                Toast.makeText(getContext(), "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseLogin> call, Throwable t) {
                            Toast.makeText(getContext(), "Lỗi kết nối đường truyền", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPass.getText().toString().trim();
                String rePassword = edtRePass.getText().toString().trim();
                String fullname = edtFullName.getText().toString().trim();
                String phone = edtPhoneNumber.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                String dob = edtDob.getText().toString().trim();
                if (username.compareTo("") == 0 || password.compareTo("") == 0 || rePassword.compareTo("") == 0 || fullname.compareTo("") == 0 ||
                        phone.compareTo("") == 0 || address.compareTo("") == 0 || dob.compareTo("") == 0) {
                    Toast.makeText(getContext(), "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                } else if (password.compareTo(rePassword) != 0) {
                    Toast.makeText(getContext(), "Hai mật khẩu chưa trùng khớp", Toast.LENGTH_SHORT).show();
                } else {
                    Account account= new Account(null, username, password, fullname, phone, address, dob);
                    ApiController.apiService.register(account).enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(getContext(), "Đăng ký thành công. Hãy đăng nhập!", Toast.LENGTH_SHORT).show();
                                int view= View.GONE;
                                edtRePass.setVisibility(view);
                                edtFullName.setVisibility(view);
                                edtPhoneNumber.setVisibility(view);
                                edtAddress.setVisibility(view);
                                edtDob.setVisibility(view);
                                btnRegister.setVisibility(view);
                                confirmDisplay[0]=true;
                            }else {
//                                Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(getContext(), "Tên tài khoản hoặc số điện thoại đã có người sử dụng", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseLogin> call, Throwable t) {
                            Toast.makeText(getContext(), "Lỗi đường truyền", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        tvDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int view = View.VISIBLE;
                if (!confirmDisplay[0]) {
                    view = View.GONE;
                }
                edtRePass.setVisibility(view);
                edtFullName.setVisibility(view);
                edtPhoneNumber.setVisibility(view);
                edtAddress.setVisibility(view);
                edtDob.setVisibility(view);
                btnRegister.setVisibility(view);
                confirmDisplay[0]= !confirmDisplay[0];
            }
        });


        dialog.show();
    }

    private void getAllAccount() {
        ApiController.apiService.getAllAccount().enqueue(new Callback<ResponseEntityAccount>() {
            @Override
            public void onResponse(Call<ResponseEntityAccount> call, Response<ResponseEntityAccount> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 404) {
                        binding.tvE.setVisibility(View.VISIBLE);
                    } else {
                        accounts = response.body().getData();
                        renderRevAccount(accounts);
                    }
                }else {
                    binding.tvE.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseEntityAccount> call, Throwable t) {
                binding.tvE.setVisibility(View.VISIBLE);
            }
        });
    }

    private void renderRevAccount(List<Account> accs) {
        accountDeclaredAdapter = new AccountDeclaredAdapter(accs, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.revAccountDeclare.setLayoutManager(layoutManager);
        binding.revAccountDeclare.setAdapter(accountDeclaredAdapter);

        accountDeclaredAdapter.setiOnClickAccount(new IOnClickAccount() {
            @Override
            public void iOnClickAccount(Account account, int pos) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_detail_declare);

                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAtributes = window.getAttributes();
                windowAtributes.gravity = Gravity.CENTER_VERTICAL;
                window.setAttributes(windowAtributes);

                dialog.setCancelable(true);

                TextView tvName = dialog.findViewById(R.id.tvNameD);
                RecyclerView recyclerView = dialog.findViewById(R.id.revDetailDeclare);

                tvName.setText(account.getFullname());
                Log.d("cc", "iOnClickAccount account id: " + account.getId());
                ApiController.apiService.getDeclarationsOfAcc(account.getId()).enqueue(new Callback<ResponseEntityDeclare>() {
                    @Override
                    public void onResponse(Call<ResponseEntityDeclare> call, Response<ResponseEntityDeclare> response) {
                        Log.d("cc", "onResponse not success:" + response.isSuccessful());
                        Log.d("cc", "onResponse not success:" + response.errorBody());
                        if (response.isSuccessful()) {
                            Log.d("cc", "onResponse: " + response.body().getMessage());
                            if (response.body().getStatus() == 404) {
                                Toast.makeText(getContext(), "Chưa có khai báo", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                            declarationsList = response.body().getData();
                            declareOfAccAdapter = new DeclareOfAccAdapter(declarationsList, getContext());
                            RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager1);
                            recyclerView.setAdapter(declareOfAccAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseEntityDeclare> call, Throwable t) {
                        Toast.makeText(getContext(), "Chưa có khai báo", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}