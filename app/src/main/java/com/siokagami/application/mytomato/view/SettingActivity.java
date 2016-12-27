package com.siokagami.application.mytomato.view;

import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.siokagami.application.mytomato.R;
import com.siokagami.application.mytomato.utils.PrefUtils;

public class SettingActivity extends PreferenceActivity {
    private EditTextPreference pref_tomato_work_tag_ed;
    private ListPreference pref_tomato_work_time_list;
    private ListPreference pref_tomato_rest_time_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPreferenceScreen();
    }

    private void initPreferenceScreen()
    {
        addPreferencesFromResource(R.xml.preference_setting);
        pref_tomato_work_time_list = (ListPreference)findPreference("pref_tomato_work_time_list");
        pref_tomato_work_time_list.setSummary(PrefUtils.getMyTomatoWorkTime(SettingActivity.this)/1000/60+" 分钟");
        pref_tomato_work_time_list.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                PrefUtils.setMyTomatoWorkTime(SettingActivity.this, Long.parseLong(o.toString()));
                preference.setSummary(PrefUtils.getMyTomatoWorkTime(SettingActivity.this)/1000/60+" 分钟");

                return true;
            }
        });

        pref_tomato_rest_time_list = (ListPreference)findPreference("pref_tomato_rest_time_list");
        pref_tomato_rest_time_list.setSummary(PrefUtils.getMyTomatoRestTime(SettingActivity.this)/1000/60+" 分钟");
        pref_tomato_rest_time_list.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                PrefUtils.setMyTomatoRestTime(SettingActivity.this, Long.parseLong(o.toString()));
                preference.setSummary(PrefUtils.getMyTomatoRestTime(SettingActivity.this)/1000/60+" 分钟");
                return true;
            }
        });

        pref_tomato_work_tag_ed = (EditTextPreference)findPreference("pref_tomato_work_tag_ed");
        pref_tomato_work_tag_ed.setSummary(PrefUtils.getUserWorkTag(SettingActivity.this));
        pref_tomato_work_tag_ed.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                PrefUtils.setUserWorkTag(SettingActivity.this, o.toString());
                preference.setSummary(PrefUtils.getUserWorkTag(SettingActivity.this));
                return true;
            }
        });

    }
}
