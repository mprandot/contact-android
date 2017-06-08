package com.dev.marcio.contactproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import beans.Contact;
import persistence.ContactRepository;

public class ContactDetails extends AppCompatActivity {

    private Contact contact;
    private ContactRepository repository= new ContactRepository(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Intent contactIntent = getIntent();
        Bundle bundle = contactIntent.getExtras();

        contact = (Contact) bundle.getSerializable("contact");

        Log.d(getClass().getSimpleName(), contact.getId().toString());

        showDetails();

    }


    private void showDetails() {
        ((EditText) findViewById(R.id.edtName)).setText(contact.getName());
        ((EditText) findViewById(R.id.edtAddress)).setText(contact.getAddress());
        ((EditText) findViewById(R.id.edtCity)).setText(contact.getCity());
        ((EditText) findViewById(R.id.edtPhone1)).setText(contact.getPhone1());
        ((EditText) findViewById(R.id.edtPhone2)).setText(contact.getPhone2());
    }

    public void deleteContact(View view) throws Exception {

        repository.destroy(contact.getId());
        Toast.makeText(ContactDetails.this, "Contato excluído!", Toast.LENGTH_SHORT).show();

        Intent searchContact = new Intent(this, SearchContact.class);
        startActivity(searchContact);

    }

    public void updateContact(View view) throws Exception {

        contact.setName(((EditText) findViewById(R.id.edtName)).getText().toString());
        contact.setAddress(((EditText) findViewById(R.id.edtAddress)).getText().toString());
        contact.setCity(((EditText) findViewById(R.id.edtCity)).getText().toString());
        contact.setPhone1(((EditText) findViewById(R.id.edtPhone1)).getText().toString());
        contact.setPhone2(((EditText) findViewById(R.id.edtPhone2)).getText().toString());

        repository.store(contact);

        Toast.makeText(ContactDetails.this, "Contato atualizado!", Toast.LENGTH_SHORT).show();
    }
}
