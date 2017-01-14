package com.example.chueyee.noodle;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private Button register, cancel;
    private EditText email, password, confirm;
    private String emailCheck, passwordCheck, confirmCheck;
    private int _UserID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final UserRepo repo = new UserRepo(this);
        final User user = new User();

        cancel = (Button) findViewById(R.id.cancelBtn);
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        register = (Button) findViewById(R.id.registerBtn);
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                email = (EditText) findViewById(R.id.emailET);
                emailCheck = email.getText().toString();

                password = (EditText) findViewById(R.id.passwordET);
                passwordCheck = password.getText().toString();

                confirm = (EditText) findViewById(R.id.confirmET);
                confirmCheck = confirm.getText().toString();

                user.user_ID = _UserID;
                user.email = emailCheck;
                user.password = passwordCheck;

                if(repo.checkEmailExists(emailCheck))
                    Toast.makeText(Register.this, "Email Already Exist", Toast.LENGTH_SHORT).show();

                else if(passwordCheck.equals(confirmCheck))
                {
                    if(_UserID == 0)
                    {
                        _UserID = repo.insert(user);

                        Context context = getApplicationContext();
                        CharSequence text = "Successfully Registered!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context, text, duration).show();
                    }
                    else
                    {
                        repo.update(user);
                        Context context = getApplicationContext();
                        CharSequence text = "Updated User!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context, text, duration).show();
                    }
                    finish();
                }
                else
                    Toast.makeText(Register.this, "Passwords Do Not Match!", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
