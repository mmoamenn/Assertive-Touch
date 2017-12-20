package com.bluehomestudio.quickoperationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bluehomestudio.quickoperation.ATButton;

/**
 * Created by mohamedmoamen on 11/30/17.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ATButton.getInstance().show();
    }


    public void onLoginPressed(View view) {
        if(view.getId() ==  R.id.b_login){
            startActivity(new Intent(this, MainActivity.class));
        }else if(view.getId() ==  R.id.b_help){
            startActivity(new Intent(this, HelpActivity.class));
        }else if(view.getId() ==  R.id.b_hide){
            ATButton.getInstance().hide();
        }else if(view.getId() == R.id.b_show){
            ATButton.getInstance().show();
        }
    }

}
