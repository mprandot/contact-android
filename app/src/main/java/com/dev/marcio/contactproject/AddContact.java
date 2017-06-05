package com.dev.marcio.contactproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import beans.Contact;
import persistence.ContactRepository;

public class AddContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    public void storeContact(View view) throws Exception {

        EditText edtName = (EditText) findViewById(R.id.edtName);
        EditText edtAddress = (EditText) findViewById(R.id.edtAddress);
        EditText edtCity = (EditText) findViewById(R.id.edtCity);
        EditText edtPhone1 = (EditText) findViewById(R.id.edtPhone1);
        EditText edtPhone2 = (EditText) findViewById(R.id.edtPhone2);


        Contact contact = new Contact(
                edtName.getText().toString(),
                edtAddress.getText().toString(),
                edtCity.getText().toString(),
                edtPhone1.getText().toString(),
                edtPhone2.getText().toString());


        ContactRepository contactRepository = new ContactRepository(this);

        contactRepository.store(contact);


    }
}
