package com.siokagami.application.mytomato;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.siokagami.application.mytomato.bean.User;
import com.siokagami.application.mytomato.databinding.ActivityMainBinding;
import com.siokagami.application.mytomato.view.TomatoNavigationMenu;

public class MainActivity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    public void init()
    {
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TomatoNavigationMenu.class));
            }
        });
    }
}
