package com.bluehomestudio.quickoperationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bluehomestudio.quickoperation.FSBHelper;
import com.bluehomestudio.quickoperation.FSButton;

/**
 * Created by mohamedmoamen on 11/30/17.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FSButton.getInstance().show();
    }


    public void onLoginPressed(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

}
