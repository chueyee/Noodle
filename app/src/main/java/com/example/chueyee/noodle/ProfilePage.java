package com.example.chueyee.noodle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ProfilePage extends AppCompatActivity {
    private Button logout, edit, myServicesBtn;
    private ImageButton profilePictureBtn;
    public TextView profileName;
    public String myEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        final Bundle extras = getIntent().getExtras();
        profileName = (TextView) findViewById(R.id.emailTV);
        profileName.setText(extras.getString("getEmail"));



        logout = (Button) findViewById(R.id.logoutBtn);
        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(ProfilePage.this, "Successfully Logged Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfilePage.this, Login.class);
                startActivity(intent);
            }
        });

        myServicesBtn = (Button) findViewById(R.id.myServices);
        myServicesBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myEmail = extras.getString("getEmail");
                Intent intent = new Intent(ProfilePage.this, MyServicesPage.class);
                intent.putExtra("getEmail", myEmail);
                startActivity(intent);
            }
        });

    }

}
