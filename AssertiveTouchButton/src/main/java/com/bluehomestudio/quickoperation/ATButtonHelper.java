package com.bluehomestudio.quickoperation;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by mohamedmoamen on 11/30/17.
 */

public class ATButtonHelper implements Application.ActivityLifecycleCallbacks {

    private int activityCounter;
    private ATButton mATButton;
    private boolean isShow, isBackground;


    ATButtonHelper(Application application, ATButton ATButton) {

        application.registerActivityLifecycleCallbacks(this);
        mATButton = ATButton;
    }

    void changeStatus(boolean isShow) {
        this.isShow = isShow;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {

        //hide button when open quick operation
        if (mATButton.getTargetName() != null
                && mATButton.getTargetName().equals(activity.getClass().getName())) {
            mATButton.tempHide();
        }

        //reshow when come from background
        if (isBackground && isShow) {
            mATButton.show();
        }

        activityCounter++;
    }

    @Override
    public void onActivityPaused(Activity activity) {

        //show button when close quick operation
        if (mATButton.getTargetName() != null
                && mATButton.getTargetName().equals(activity.getClass().getName())) {
            if (isShow) {
                mATButton.show();
            }
        }

        activityCounter--;
    }

    @Override
    public void onActivityStopped(Activity activity) {

        //hide quick button when application closed
        if (activityCounter == 0) {
            mATButton.tempHide();
            isBackground = true;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

}
