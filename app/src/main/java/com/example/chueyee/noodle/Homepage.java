package com.example.chueyee.noodle;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class Homepage extends AppCompatActivity {
    private ImageButton newsfeed, create, salesfeed;
    private ListView listView;
    private TextView profile;
    private EditText searchBtn;
    public String searchWord, passEmail, passEmail2;


    ServiceRepo repo = new ServiceRepo(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //----------------------------------------------------------------------------------
        // NEWSFEED -- SERVICE LIST
        //----------------------------------------------------------------------------------
        ArrayList<HashMap<String, String>> servicelist = new ArrayList<HashMap<String, String>>();

        servicelist.addAll(repo.getServiceList());

        if(servicelist != null)
        {
            HashMap<String, String> listofservice = new HashMap<String, String>();
            String servicename = Service.COLUMN_SERVICE_NAME;
            String serviceprice = Service.COLUMN_SERVICE_PRICE;
            String servicedescription = Service.COLUMN_SERVICE_DESCRIPTION;

            servicelist.add(listofservice);
        }

        String[] from = {"service name", "service description", "service price"};
        int[] to = {R.id.service_name, R.id.service_description, R.id.price_value};

        final ListView listService = (ListView) findViewById(R.id.newsfeed);
        ListAdapter adapter = new SimpleAdapter(Homepage.this, servicelist, R.layout.content_homepage_display, from, to);


        listService.setAdapter(adapter);
        listService.setClickable(true);
        listService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClassName(getPackageName(), getPackageName() + ".ShowService");
                intent.putExtra("selected", listService.getAdapter().getItem(position).toString());
                startActivity(intent);
            }
        });


        //----------------------------------------------------------------------------------
        // PROFILE PAGE
        //----------------------------------------------------------------------------------

        final Bundle extras = getIntent().getExtras();
        profile = (TextView) findViewById(R.id.profileBtn);
        profile.setText(extras.getString("getEmail"));
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passEmail = profile.getText().toString();
                Intent intent = new Intent(Homepage.this, ProfilePage.class);
                intent.putExtra("getEmail", passEmail);
                startActivity(intent);
            }
        });

        //----------------------------------------------------------------------------------
        // SEARCH
        //----------------------------------------------------------------------------------

        listView = (ListView) findViewById(R.id.newsfeed);
        searchBtn = (EditText) findViewById(R.id.search);


        //----------------------------------------------------------------------------------
        // Service List
        //----------------------------------------------------------------------------------

        newsfeed = (ImageButton) findViewById(R.id.newsfeedBtn);
        newsfeed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //----------------------------------------------------------------------------------
                // NEWSFEED -- SERVICE LIST
                //----------------------------------------------------------------------------------
                ArrayList<HashMap<String, String>> servicelist = new ArrayList<HashMap<String, String>>();

                servicelist.addAll(repo.getServiceList());

                if(servicelist != null)
                {
                    HashMap<String, String> listofservice = new HashMap<String, String>();

                    String servicename = Service.COLUMN_SERVICE_NAME;
                    String serviceprice = Service.COLUMN_SERVICE_PRICE;
                    String servicedescription = Service.COLUMN_SERVICE_DESCRIPTION;

                    servicelist.add(listofservice);
                }

                String[] from = {"service name", "service description", "service price"};
                int[] to = {R.id.service_name, R.id.service_description, R.id.price_value};

                final ListView newsfeed = (ListView) findViewById(R.id.newsfeed);
                ListAdapter adapter = new SimpleAdapter(Homepage.this, servicelist, R.layout.content_homepage_display, from, to);

                newsfeed.setAdapter(adapter);
                newsfeed.setClickable(true);
                newsfeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent();
                        intent.setClassName(getPackageName(), getPackageName() + ".ShowService");
                        intent.putExtra("selected", newsfeed.getAdapter().getItem(position).toString());
                        startActivity(intent);
                    }
                });
            }
        });

        //----------------------------------------------------------------------------------
        // CREATE A SERVICE
        //----------------------------------------------------------------------------------
        create = (ImageButton) findViewById(R.id.createBtn);
        create.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                passEmail2 = extras.getString("getEmail");
                Intent intent = new Intent(Homepage.this, CreateService.class);
                intent.putExtra("getEmail", passEmail2);
                startActivity(intent);
            }
        });

        //----------------------------------------------------------------------------------
        // Sales List
        //----------------------------------------------------------------------------------
        salesfeed = (ImageButton) findViewById(R.id.salesBtn);
        salesfeed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //----------------------------------------------------------------------------------
                // NEWSFEED -- SALES LIST
                //----------------------------------------------------------------------------------
                ArrayList<HashMap<String, String>> saleslist = new ArrayList<HashMap<String, String>>();

                saleslist.addAll(repo.getSalesList());

                if(saleslist != null)
                {
                    HashMap<String, String> listofservice = new HashMap<String, String>();

                    String servicename = Service.COLUMN_SERVICE_NAME;
                    String serviceprice = Service.COLUMN_SERVICE_PRICE;
                    String servicedescription = Service.COLUMN_SERVICE_DESCRIPTION;

                    saleslist.add(listofservice);
                }

                String[] from = {"service name", "service description", "service price"};
                int[] to = {R.id.service_name, R.id.service_description, R.id.price_value};

                final ListView newsfeed = (ListView) findViewById(R.id.newsfeed);
                ListAdapter adapter = new SimpleAdapter(Homepage.this, saleslist, R.layout.content_homepage_display, from, to);

                newsfeed.setAdapter(adapter);
                newsfeed.setClickable(true);
                newsfeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent();
                        intent.setClassName(getPackageName(), getPackageName() + ".ShowService");
                        intent.putExtra("selected", newsfeed.getAdapter().getItem(position).toString());
                        startActivity(intent);
                    }
                });
            }
        });

    }



}
