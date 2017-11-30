package com.bluehomestudio.quickoperationdemo;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Intent;

import com.bluehomestudio.quickoperation.QuickButtonController;

/**
 * Created by mohamedmoamen on 11/30/17.
 */

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        QuickButtonController quickButtonController = new QuickButtonController(this);
        quickButtonController.setStartActivityName(LoginActivity.class);
        quickButtonController.setHelpActivityName(HelpActivity.class);
    }


}
