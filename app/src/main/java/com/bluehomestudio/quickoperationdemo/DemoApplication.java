package com.bluehomestudio.quickoperationdemo;

import android.app.Application;
import android.graphics.Color;

import com.bluehomestudio.quickoperation.FSBController;

/**
 * Created by mohamedmoamen on 11/30/17.
 */

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FSBController.setUpInstance(this);
        FSBController.getInstance().setStartActivity(LoginActivity.class);
        FSBController.getInstance().setTargetActivity(HelpActivity.class);
        FSBController.getInstance().setIcon(R.drawable.help);
        FSBController.getInstance().setBackground(Color.WHITE);

    }

}
