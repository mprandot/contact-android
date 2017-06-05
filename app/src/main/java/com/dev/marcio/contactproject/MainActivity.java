package com.dev.marcio.contactproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void getAddView(View view) {
        Intent addView = new Intent(this, AddContact.class);
        startActivity(addView);
    }

    public void getSearchView(View view) {
        Intent searchContact = new Intent(this, SearchContact.class);
        startActivity(searchContact);

    }
}
