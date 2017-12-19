package com.bluehomestudio.quickoperation;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by mohamedmoamen on 11/30/17.
 */

public class FSBHelper implements Application.ActivityLifecycleCallbacks {

    private int activityCounter;
    private FSButton mFsButton;


    public FSBHelper(Application application, FSButton fsButton) {

        application.registerActivityLifecycleCallbacks(this);
        mFsButton = fsButton;
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
        if (mFsButton.getTargetName() != null
                && mFsButton.getTargetName()
                .equals(activity.getClass().getName())) {
            mFsButton.hide();
        }

        activityCounter++;
    }

    @Override
    public void onActivityPaused(Activity activity) {

        //show button when close quick operation
        if (mFsButton.getTargetName() != null
                && mFsButton.getTargetName()
                .equals(activity.getClass().getName())) {
            mFsButton.show();
        }

        activityCounter--;
    }

    @Override
    public void onActivityStopped(Activity activity) {

        //hide quick button when application closed
        if (activityCounter == 0) {
            mFsButton.hide();
        }

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

}
