package com.bluehomestudio.quickoperationdemo;

import android.app.Application;
import android.graphics.Color;

import com.bluehomestudio.quickoperation.FSBHelper;
import com.bluehomestudio.quickoperation.FSButton;

/**
 * Created by mohamedmoamen on 11/30/17.
 */

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FSButton.setUp(this) ;
        FSButton.getInstance().setTargetClass(HelpActivity.class);
        FSButton.getInstance().setIcon(R.drawable.help);
        FSButton.getInstance().setBackgroundColor(Color.WHITE);

    }

}
