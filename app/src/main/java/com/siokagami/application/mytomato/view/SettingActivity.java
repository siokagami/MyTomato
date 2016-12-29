package com.siokagami.application.mytomato.view;

import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.siokagami.application.mytomato.R;
import com.siokagami.application.mytomato.presenter.UserSettingPresenter;
import com.siokagami.application.mytomato.presenter.inf.UserSettingPresenterInf;
import com.siokagami.application.mytomato.utils.PrefUtils;

public class SettingActivity extends AppCompatPreferenceActivity implements SettingInf {
    private EditTextPreference pref_tomato_work_tag_ed;
    private EditTextPreference pref_tomato_nickname_ed;
    private ListPreference pref_tomato_work_time_list;
    private ListPreference pref_tomato_rest_time_list;
    private ListPreference pref_tomato_sex_list;
    private UserSettingPresenterInf userSettingPresenterInf = new UserSettingPresenter(SettingActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        initPreferenceScreen();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("偏好设置");
        }
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


    private void initPreferenceScreen() {
        addPreferencesFromResource(R.xml.preference_setting);
        pref_tomato_work_time_list = (ListPreference) findPreference("pref_tomato_work_time_list");
        pref_tomato_work_time_list.setSummary(PrefUtils.getMyTomatoWorkTime(SettingActivity.this) / 1000 / 60 + " 分钟");
        pref_tomato_work_time_list.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                PrefUtils.setMyTomatoWorkTime(SettingActivity.this, Long.parseLong(o.toString()));
                preference.setSummary(PrefUtils.getMyTomatoWorkTime(SettingActivity.this) / 1000 / 60 + " 分钟");

                return true;
            }
        });

        pref_tomato_rest_time_list = (ListPreference) findPreference("pref_tomato_rest_time_list");
        pref_tomato_rest_time_list.setSummary(PrefUtils.getMyTomatoRestTime(SettingActivity.this) / 1000 / 60 + " 分钟");
        pref_tomato_rest_time_list.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                PrefUtils.setMyTomatoRestTime(SettingActivity.this, Long.parseLong(o.toString()));
                preference.setSummary(PrefUtils.getMyTomatoRestTime(SettingActivity.this) / 1000 / 60 + " 分钟");
                return true;
            }
        });

        pref_tomato_work_tag_ed = (EditTextPreference) findPreference("pref_tomato_work_tag_ed");
        pref_tomato_work_tag_ed.setSummary(PrefUtils.getUserWorkTag(SettingActivity.this));
        pref_tomato_work_tag_ed.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                PrefUtils.setUserWorkTag(SettingActivity.this, o.toString());
                preference.setSummary(PrefUtils.getUserWorkTag(SettingActivity.this));
                return true;
            }
        });

        pref_tomato_nickname_ed = (EditTextPreference) findPreference("pref_tomato_nickname_ed");
        pref_tomato_nickname_ed.setSummary(PrefUtils.getUserName(SettingActivity.this));
        pref_tomato_nickname_ed.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                userSettingPresenterInf.setUserName(SettingActivity.this, o.toString());
                PrefUtils.setUserName(SettingActivity.this, o.toString());
                preference.setSummary(PrefUtils.getUserName(SettingActivity.this));
                return false;
            }
        });

        pref_tomato_sex_list = (ListPreference) findPreference("pref_tomato_sex_list");
        if (PrefUtils.getUserSex(SettingActivity.this) == 0) {
            pref_tomato_sex_list.setSummary("男");
        } else {
            pref_tomato_sex_list.setSummary("女");
        }
        pref_tomato_sex_list.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                userSettingPresenterInf.setUserSex(SettingActivity.this, Integer.parseInt(o.toString()));
                PrefUtils.setUserSex(SettingActivity.this, Integer.parseInt(o.toString()));
                if (PrefUtils.getUserSex(SettingActivity.this) == 0) {
                    preference.setSummary("男");

                } else {
                    preference.setSummary("女");

                }

                return false;
            }
        });

    }

    @Override
    public void showSuccess() {
        Toast.makeText(SettingActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailure() {
        Toast.makeText(SettingActivity.this, "修改失败", Toast.LENGTH_SHORT).show();

    }
}
