package com.bluehomestudio.quickoperationdemo;

import android.app.Application;
import android.graphics.Color;

import com.bluehomestudio.quickoperation.ATButton;

/**
 * Created by mohamedmoamen on 11/30/17.
 */

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ATButton.setup(this) ;
        ATButton.getInstance().setTargetClass(HelpActivity.class);
        ATButton.getInstance().setIcon(R.drawable.help);
        ATButton.getInstance().setBackgroundColor(Color.WHITE);

    }

}
