package com.siokagami.application.mytomato.view;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.siokagami.application.mytomato.R;
import com.siokagami.application.mytomato.bean.UserLoginQuery;
import com.siokagami.application.mytomato.presenter.UserLoginPresenter;
import com.siokagami.application.mytomato.presenter.inf.UserLoginPresenterInf;
import com.siokagami.application.mytomato.utils.IntentUtil;
import com.siokagami.application.mytomato.utils.PrefUtils;
import com.siokagami.application.mytomato.view.inf.LoginActivityInf;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginActivityInf {
    private UserLoginPresenterInf userLoginPresenterInf = new UserLoginPresenter(LoginActivity.this);
    private EditText etLoginPhone;
    private EditText etLoginPassword;
    private Button emailSignInButton;
    private TextView tvLoginFindPassword;
    private TextView tvLoginRegister;


    private UserLoginQuery query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkIsLogin();
        setupActionBar();
        init();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle("登录");
        }
    }

    private void init() {
        etLoginPhone = (EditText) findViewById(R.id.et_login_phone);
        etLoginPassword = (EditText) findViewById(R.id.et_login_password);
        emailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        tvLoginFindPassword = (TextView) findViewById(R.id.tv_login_find_password);
        tvLoginRegister = (TextView) findViewById(R.id.tv_login_register);
        tvLoginFindPassword.setOnClickListener(this);
        tvLoginRegister.setOnClickListener(this);

        emailSignInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.email_sign_in_button:
                query = new UserLoginQuery();
                query.password = etLoginPassword.getText().toString().trim();
                query.phone = etLoginPhone.getText().toString().trim();
                userLoginPresenterInf.doUserLogin(LoginActivity.this, query);
                break;
            case R.id.tv_login_find_password:
                startActivity(IntentUtil.showFindPasswordActivity(LoginActivity.this));
                break;
            case R.id.tv_login_register:
                startActivity(IntentUtil.showRegisterActivity(LoginActivity.this));
                break;
        }
    }


    @Override
    public void loginSuccess() {
        finish();
    }

    private void checkIsLogin() {
        if (!PrefUtils.getUserAccessToken(LoginActivity.this).equals("")) {
            startActivity(IntentUtil.showMainPage(LoginActivity.this));
            finish();

        }
    }
}
