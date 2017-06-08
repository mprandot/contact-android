package com.dev.marcio.contactproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import beans.Contact;
import persistence.ContactRepository;

public class SearchContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);

        final ListView listView = (ListView) findViewById(R.id.listContacts);
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = (Contact) listView.getItemAtPosition(position);
                showContactDetails(contact);
            }
        });

    }


    public void searchContact(View view) {

        EditText search = (EditText) findViewById(R.id.edtSearch);

        ListView listView = (ListView) findViewById(R.id.listContacts);

        ContactRepository cr = new ContactRepository(this);

        ArrayList<Contact> contacts = cr.searchContact(search.getText().toString());

        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this,
                android.R.layout.simple_list_item_1, contacts);
        listView.setAdapter(adapter);
    }

    private void showContactDetails(Contact contact) {
        Intent details = new Intent(this, ContactDetails.class);

        Bundle params = new Bundle();
        params.putSerializable("contact", contact);

        details.putExtras(params);

        startActivity(details);
    }

}
