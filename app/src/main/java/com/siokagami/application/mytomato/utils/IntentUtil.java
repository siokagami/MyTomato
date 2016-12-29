package com.siokagami.application.mytomato.utils;

import android.content.Context;
import android.content.Intent;

import com.siokagami.application.mytomato.view.FindPasswordActivity;
import com.siokagami.application.mytomato.view.LoginActivity;
import com.siokagami.application.mytomato.view.MainPageFragment;
import com.siokagami.application.mytomato.view.RegisterActivity;
import com.siokagami.application.mytomato.view.TomatoNavigationMenu;

/**
 * Created by siokagami on 16/12/28.
 */

public class IntentUtil {
    public static Intent showMainPage(Context context)
    {
        Intent intent  = new Intent(context, TomatoNavigationMenu.class);
        return intent;
    }
    public static Intent showLoginActivity(Context context)
    {
        Intent intent  = new Intent(context, LoginActivity.class);
        return intent;
    }
    public static Intent showRegisterActivity(Context context)
    {
        Intent intent  = new Intent(context, RegisterActivity.class);
        return intent;
    }
    public static Intent showFindPasswordActivity(Context context)
    {
        Intent intent  = new Intent(context, FindPasswordActivity.class);
        return intent;
    }
}
