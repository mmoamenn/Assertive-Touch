package com.bluehomestudio.quickoperation;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;


/**
 * Created by mohamedmoamen on 11/29/17.
 */

public class ATButton implements View.OnTouchListener {

    @SuppressLint("StaticFieldLeak")
    private static ATButton instance = null;
    private ATButtonHelper ATButtonHelper;
    private Application mApp;
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private View quickActionButtonLayout;
    private boolean isAttached;
    private ImageView quickActionButton;
    private Class<?> mTargetCls;

    /**
     * private constructor
     *
     * @param app application class
     */
    private ATButton(Application app) {
        mApp = app;

        //attach ActivityLifecycleCallbacks
        ATButtonHelper = new ATButtonHelper(app, this);

        //configure button
        setupView();
    }

    /**
     * Single tone quick operation button
     */
    public static void setup(Application app) {

        synchronized (ATButton.class) {
            if (instance == null) {
                instance = new ATButton(app);
            }
        }
    }

    public static ATButton getInstance() {
        if (instance == null) {
            Log.d("ATButton", "Please call setUp(Application app) in your application class");
        }
        return instance;
    }

    /**
     * Function to setup component view
     */
    private void setupView() {

        DisplayMetrics metrics = new DisplayMetrics();
        windowManager = (WindowManager) mApp.getSystemService(Context.WINDOW_SERVICE);
        assert windowManager != null;
        windowManager.getDefaultDisplay().getMetrics(metrics);
        quickActionButtonLayout = LayoutInflater.from(mApp).inflate(R.layout.quick_button_view, null);

        //check if view attached to window or not
        quickActionButtonLayout.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                isAttached = true;
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                isAttached = false;
            }
        });


        //quick button and attach listener
        quickActionButton = quickActionButtonLayout.findViewById(R.id.image);
        quickActionButton.setOnTouchListener(this);

        layoutParams = new WindowManager.LayoutParams(
                150, 150,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_TOAST,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );
        layoutParams.gravity = Gravity.TOP | Gravity.START;
        layoutParams.alpha = 0.6f;
        layoutParams.x = 30;
        layoutParams.y = 30;
    }

    private final int MINI_DISTANCE = 25;
    private int initialX;
    private int initialY;
    private float initialTouchX;
    private float initialTouchY;
    private boolean isTransplant;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(final View v, MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                initialX = layoutParams.x;
                initialY = layoutParams.y;
                initialTouchX = event.getRawX();
                initialTouchY = event.getRawY();
                return true;

            case MotionEvent.ACTION_MOVE:

                layoutParams.x = initialX + (int) (event.getRawX() - initialTouchX);
                layoutParams.y = initialY + (int) (event.getRawY() - initialTouchY);
                windowManager.updateViewLayout(v, layoutParams);

                return true;

            case MotionEvent.ACTION_UP:

                float changedDistance = event.getRawX() - initialTouchX;
                if (Math.abs(changedDistance) < MINI_DISTANCE) {
                    changeTransparentStatus();
                }
                return true;

        }
        return false;
    }


    /**
     * Function to change transparency status
     */
    private void changeTransparentStatus() {
        if (isTransplant) {

            layoutParams.alpha = 0.6f;
            isTransplant = false;

            //open activity from quick action
            Intent intent = new Intent(mApp, mTargetCls);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mApp.startActivity(intent);

        } else {

            layoutParams.alpha = 1f;
            isTransplant = true;

        }
        windowManager.updateViewLayout(quickActionButton, layoutParams);

    }

    void tempHide() {
        if (isAttached) {
            windowManager.removeView(quickActionButtonLayout);
        }
    }

    public void show() {
        if (!isAttached) {
            windowManager.addView(quickActionButtonLayout, layoutParams);
            ATButtonHelper.changeStatus(true);
        }
    }

    public void hide() {
        if (isAttached) {
            windowManager.removeView(quickActionButtonLayout);
            ATButtonHelper.changeStatus(false);
        }
    }

    public boolean isViewAttached() {
        return isAttached;
    }

    public void setSize(int height, int width) {
        layoutParams.height = height;
        layoutParams.width = width;
        windowManager.updateViewLayout(quickActionButton, layoutParams);
    }

    public void setIcon(int icon) {
        quickActionButton.setImageResource(icon);
        quickActionButton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    }

    public void setBackgroundColor(int color) {

        Drawable mDrawable = mApp.getResources().getDrawable(R.drawable.circle_button);
        mDrawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            quickActionButton.setBackgroundDrawable(mDrawable);
        } else {
            quickActionButton.setBackground(mDrawable);
        }
    }

    public void setTargetClass(Class<?> targetCls) {
        mTargetCls = targetCls;
    }

    String getTargetName() {
        return mTargetCls.getName();
    }

}

