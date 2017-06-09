package com.dev.marcio.contactproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

import beans.Contact;
import persistence.ContactRepository;
import util.ContactValidator;

public class ContactDetails extends AppCompatActivity {

    private Contact contact;
    private Bitmap pic;
    private static final int PICK_PHOTO_FOR_AVATAR = 0;
    private ContactRepository repository = new ContactRepository(this);


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

        if (Arrays.toString(contact.getPic()).length() > 10) {
            ((ImageView) findViewById(R.id.imageView)).setImageBitmap(BitmapFactory.decodeByteArray(contact.getPic(), 0, contact.getPic().length));
            findViewById(R.id.imageView).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.imageView).setVisibility(View.GONE);
        }

    }

    public void deleteContact(View view) throws Exception {

        repository.destroy(contact.getId());
        Toast.makeText(ContactDetails.this, "Contato excluído!", Toast.LENGTH_SHORT).show();
        Intent searchContact = new Intent(this, SearchContact.class);
        startActivity(searchContact);

    }

    public boolean updateContact(View view) throws Exception {
        byte[] img = new byte[0];

        if (pic != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            pic.compress(Bitmap.CompressFormat.PNG, 100, bos);
            img = bos.toByteArray();
        }
        contact.setName(((EditText) findViewById(R.id.edtName)).getText().toString());
        contact.setAddress(((EditText) findViewById(R.id.edtAddress)).getText().toString());
        contact.setCity(((EditText) findViewById(R.id.edtCity)).getText().toString());
        contact.setPhone1(((EditText) findViewById(R.id.edtPhone1)).getText().toString());
        contact.setPhone2(((EditText) findViewById(R.id.edtPhone2)).getText().toString());
        contact.setPic(img);

        if (!ContactValidator.validate(this, contact)) {
            return false;
        }

        repository.store(contact);

        Toast.makeText(ContactDetails.this, "Contato atualizado!", Toast.LENGTH_SHORT).show();

        Intent searchContact = new Intent(this, SearchContact.class);
        startActivity(searchContact);
        return true;
    }

    public void selectPic(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            try {
                InputStream inputStream = getBaseContext().getContentResolver().openInputStream(data.getData());
                pic = BitmapFactory.decodeStream(inputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
