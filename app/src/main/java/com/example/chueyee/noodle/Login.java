package com.example.chueyee.noodle;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private Button login, register, forgot;
    private EditText email, password;
    public String checkEmail, checkPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SQLiteDatabase db = openOrCreateDatabase(UserDBHandler.DATABASE_NAME, MODE_PRIVATE, null);
        final UserRepo repo = new UserRepo(this);

        login = (Button) findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                email = (EditText) findViewById(R.id.emailET);
                checkEmail = email.getText().toString();

                password = (EditText) findViewById(R.id.passwordET);
                checkPassword = password.getText().toString();

                if(repo.checkEmailExists(checkEmail) && checkPassword.equals(repo.checkPassword(checkEmail)))
                {
                    Toast.makeText(Login.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Homepage.class);
                    intent.putExtra("getEmail", checkEmail);
                    startActivity(intent);
                }
                else
                    Toast.makeText(Login.this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();

            }
        });


        register = (Button) findViewById(R.id.registerBtn);
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        forgot = (Button) findViewById(R.id.forgotBtn);
        forgot.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Login.this, ForgotPassword.class));
            }
        });


    }
    @Override
    public void onBackPressed()
    {

    }

}

