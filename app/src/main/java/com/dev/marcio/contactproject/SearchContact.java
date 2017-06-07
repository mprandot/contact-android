package com.dev.marcio.contactproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import beans.Contact;

public class SearchContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);


        final EditText search = (EditText) findViewById(R.id.edtSearch);

        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(getClass().getSimpleName(), s.toString());
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

//        search.addTextChangedListener(fieldValidatorTextWatcher);
//
//        ListView listView = (ListView) findViewById(R.id.listContacts);
//
//        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this,
//                android.R.layout.simple_list_item_1, usuarios);
//        listView.setAdapter(adapter);


    }


}
