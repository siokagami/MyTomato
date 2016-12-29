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
import com.siokagami.application.mytomato.bean.FindPasswordQuery;
import com.siokagami.application.mytomato.service.MyTomatoAPI;
import com.siokagami.application.mytomato.utils.DateParseUtil;
import com.siokagami.application.mytomato.utils.IntentUtil;
import com.siokagami.application.mytomato.utils.PrefUtils;
import com.siokagami.application.mytomato.utils.StringUtils;
import com.siokagami.application.mytomato.widget.TomatoCountdownTimer;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class FindPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etFindPasswordPhone;
    private EditText etFindPasswordCode;
    private Button btnFindPasswordCode;
    private EditText etfindPassword;
    private Button btnFindPasswordButton;
    private  TomatoCountdownTimer tomatoCountdownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        setupActionBar();
        init();
        initCountDown();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("更改密码");
        }
    }

    private void init() {
        etFindPasswordPhone = (EditText) findViewById(R.id.et_find_password_phone);
        etFindPasswordCode = (EditText) findViewById(R.id.et_find_password_code);
        btnFindPasswordCode = (Button) findViewById(R.id.btn_find_password_code);
        etfindPassword = (EditText) findViewById(R.id.et_find_password_password);
        btnFindPasswordButton = (Button) findViewById(R.id.btn_find_password_button);
        btnFindPasswordCode.setOnClickListener(this);
        btnFindPasswordButton.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_find_password_code:
                if (etFindPasswordPhone.getText().toString().length() != 11) {
                    Toast.makeText(FindPasswordActivity.this, "手机号异常", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isEmpty(etFindPasswordPhone.getText().toString())) {
                    Toast.makeText(FindPasswordActivity.this, "请输入手机号!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Observable<Void> getCheckCode = MyTomatoAPI.myTomatoService.getCheckCode(etFindPasswordPhone.getText().toString());
                getCheckCode
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                Toast.makeText(FindPasswordActivity.this, "发送成功!", Toast.LENGTH_SHORT).show();
                                tomatoCountdownTimer.start();

                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {

                            }
                        });


                break;
            case R.id.btn_find_password_button:
                if (etFindPasswordPhone.getText().toString().length() != 11) {
                    Toast.makeText(FindPasswordActivity.this, "手机号异常", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isEmpty(etFindPasswordPhone.getText().toString())) {
                    Toast.makeText(FindPasswordActivity.this, "请输入手机号!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etFindPasswordCode.getText().toString().length() != 6) {
                    Toast.makeText(FindPasswordActivity.this, "验证码异常", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isEmpty(etFindPasswordCode.getText().toString())) {
                    Toast.makeText(FindPasswordActivity.this, "请输入验证码!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isEmpty(etfindPassword.getText().toString())) {
                    Toast.makeText(FindPasswordActivity.this, "请输入密码!", Toast.LENGTH_SHORT).show();
                    return;
                }
                FindPasswordQuery query = new FindPasswordQuery();
                query.phone = etFindPasswordPhone.getText().toString().trim();
                query.code = etFindPasswordCode.getText().toString().trim();
                query.password = etfindPassword.getText().toString().trim();

                Observable<Void> changePassword = MyTomatoAPI.myTomatoService.changePassword(query);
                changePassword
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                Toast.makeText(FindPasswordActivity.this, "重置成功,请重新登录", Toast.LENGTH_SHORT).show();
                                startActivity(IntentUtil.showLoginActivity(FindPasswordActivity.this));
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Toast.makeText(FindPasswordActivity.this, "失败请重试!", Toast.LENGTH_SHORT).show();

                            }
                        });
                break;
        }
    }

    private void initCountDown() {
        tomatoCountdownTimer = new TomatoCountdownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished, int percent) {
                btnFindPasswordCode.setText(millisUntilFinished/1000+"秒");
                btnFindPasswordCode.setClickable(false);
            }

            @Override
            public void onFinish() {
                btnFindPasswordCode.setText("发送验证码");
                btnFindPasswordCode.setClickable(false);
            }
        };
    }
}
