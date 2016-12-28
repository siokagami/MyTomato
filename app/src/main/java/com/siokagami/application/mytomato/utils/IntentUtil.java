package com.siokagami.application.mytomato.utils;

import android.content.Context;
import android.content.Intent;

import com.siokagami.application.mytomato.view.MainPageFragment;
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
}
