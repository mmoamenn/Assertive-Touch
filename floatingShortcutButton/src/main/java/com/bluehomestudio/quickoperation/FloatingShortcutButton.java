package com.bluehomestudio.quickoperation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;


/**
 * Created by mohamedmoamen on 11/29/17.
 */

class FloatingShortcutButton implements View.OnTouchListener {

    private static FloatingShortcutButton instance = null;
    private Context mContext;
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private View quickActionButtonLayout;
    private boolean isAttached;
    private ImageView quickActionButton;
    private Intent helpActivityIntent;

    /**
     * private constructor
     *
     * @param context caller context
     */
    private FloatingShortcutButton(Context context) {
        mContext = context;
        setupView();
    }

    /**
     * Single tone quick operation button
     */
    static FloatingShortcutButton getInstance(Context context) {

        synchronized (FloatingShortcutButton.class) {
            if (instance == null) {
                instance = new FloatingShortcutButton(context);
            }
        }
        return instance;
    }

    /**
     * Function to setup component view
     */
    private void setupView() {

        DisplayMetrics metrics = new DisplayMetrics();
        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        assert windowManager != null;
        windowManager.getDefaultDisplay().getMetrics(metrics);
        quickActionButtonLayout = LayoutInflater.from(mContext).inflate(R.layout.quick_button_view, null);

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
            helpActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(helpActivityIntent);

        } else {

            layoutParams.alpha = 1f;
            isTransplant = true;

        }
        windowManager.updateViewLayout(quickActionButton, layoutParams);

    }

    void show() {
        if (!isAttached) {
            windowManager.addView(quickActionButtonLayout, layoutParams);
        }

    }

    void hide() {
        if (isAttached) {
            windowManager.removeViewImmediate(quickActionButtonLayout);
        }
    }

    void setSize(int height, int width) {
        layoutParams.height = height;
        layoutParams.width = width;
        windowManager.updateViewLayout(quickActionButton, layoutParams);
    }

    void setIcon(int icon) {
        quickActionButton.setImageResource(icon);
        quickActionButton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    }

    void setBackgroundColor(int color){
        Drawable mDrawable = mContext.getResources().getDrawable(R.drawable.circle_button);
        mDrawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            quickActionButton.setBackgroundDrawable(mDrawable);
        } else {
            quickActionButton.setBackground(mDrawable);
        }
    }

    /**
     * quick operation activity
     *
     * @param helpActivityIntent help activity
     */
    void setTargetActivity(Intent helpActivityIntent) {
        this.helpActivityIntent = helpActivityIntent;
    }

}

