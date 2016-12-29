package com.siokagami.application.mytomato.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.siokagami.application.mytomato.R;
import com.siokagami.application.mytomato.bean.UserRegisterQuery;
import com.siokagami.application.mytomato.service.MyTomatoAPI;
import com.siokagami.application.mytomato.utils.IntentUtil;
import com.siokagami.application.mytomato.utils.StringUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText etRegisterPhone;
    private EditText etRegisterPassword;
    private EditText etRegisterPasswordConfirm;
    private Button btnSignUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupActionBar();
        init();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("注册");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_button:
                if (!etRegisterPassword.getText().toString().equals(etRegisterPasswordConfirm.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isEmpty(etRegisterPassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etRegisterPhone.getText().toString().length() != 11) {
                    Toast.makeText(RegisterActivity.this, "手机号异常", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isEmpty(etRegisterPhone.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入手机号!", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserRegisterQuery query = new UserRegisterQuery();
                query.nickname = etRegisterPhone.getText().toString().trim();
                query.phone = etRegisterPhone.getText().toString().trim();
                query.password = etRegisterPasswordConfirm.getText().toString().trim();
                Observable<Void> userRegister = MyTomatoAPI.myTomatoService.userRegister(query);
                userRegister
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Void>() {
                                       @Override
                                       public void call(Void aVoid) {
                                           Toast.makeText(RegisterActivity.this, "注册成功,请重新登录!", Toast.LENGTH_SHORT).show();
                                           startActivity(IntentUtil.showLoginActivity(RegisterActivity.this));
                                           finish();

                                       }
                                   }, new Action1<Throwable>() {
                                       @Override
                                       public void call(Throwable throwable) {
                                           Toast.makeText(RegisterActivity.this, "失败请重试!", Toast.LENGTH_SHORT).show();


                                       }
                                   }
                        );

                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(IntentUtil.showLoginActivity(RegisterActivity.this));
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void init() {

        etRegisterPhone = (EditText) findViewById(R.id.et_register_phone);
        etRegisterPassword = (EditText) findViewById(R.id.et_register_password);
        etRegisterPasswordConfirm = (EditText) findViewById(R.id.et_register_password_confirm);
        btnSignUpButton = (Button) findViewById(R.id.btn_sign_up_button);
        btnSignUpButton.setOnClickListener(this);

    }
}
