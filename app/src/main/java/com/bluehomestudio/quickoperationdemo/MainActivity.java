package com.bluehomestudio.quickoperationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bluehomestudio.quickoperation.FSButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onDemoButtonsPressed(View view) {

        switch (view.getId()) {
            case R.id.b_help:
                FSButton.getInstance().setIcon(R.drawable.help);
                break;

            case R.id.b_weather:
                FSButton.getInstance().setIcon(R.drawable.clouds);
                break;

            case R.id.b_calculator:
                FSButton.getInstance().setIcon(R.drawable.calculator);
                break;

            case R.id.b_150:
                FSButton.getInstance().setSize(150 , 150);
                break;

            case R.id.b_200:
                FSButton.getInstance().setSize(200 , 200);
                break;

            case R.id.b_300:
                FSButton.getInstance().setSize(300 , 300);
                break;

        }
    }


}
