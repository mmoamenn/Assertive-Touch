package com.bluehomestudio.quickoperationdemo;

import android.app.Application;
import android.graphics.Color;

import com.bluehomestudio.quickoperation.FloatingShortcutButtonController;

/**
 * Created by mohamedmoamen on 11/30/17.
 */

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FloatingShortcutButtonController floatingShortcutButtonController = new FloatingShortcutButtonController(this);
        floatingShortcutButtonController.setStartActivityName(LoginActivity.class);
        floatingShortcutButtonController.setHelpActivityName(HelpActivity.class);
        floatingShortcutButtonController.setButtonIcon(R.drawable.help , Color.WHITE);
    }


}
