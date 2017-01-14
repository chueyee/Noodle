package com.example.chueyee.noodle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShowService extends AppCompatActivity {
    Button contactBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_service);

        Intent intent = getIntent();
        String grab = intent.getStringExtra("selected");

        ((TextView)(findViewById(R.id.service_page_description))).setText(showDescription(grab));
        ((TextView)(findViewById(R.id.service_page_name))).setText(showName(grab));
        ((TextView)(findViewById(R.id.service_page_price))).setText(showPrice(grab));

        cancelBtn = (Button) findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }

    public static String showDescription(String input)
    {
        String re = input.substring(input.indexOf("description="));
        String re2 = re.substring(12);
        String re3 = re2.replaceAll("[}]", "");

        return re3;
    }

    public static String showName(String input)
    {
        String re = input.substring(input.indexOf("name="));
        String re2 = re.substring(5);
        String re3 = re2.split("\\, service")[0];

        return re3;

    }

    public static String showPrice(String input)
    {
        String re = input.substring(input.indexOf("price="));
        String re2 = re.substring(6);
        String re3 = re2.split("\\, service")[0];

        return re3;
    }


}
