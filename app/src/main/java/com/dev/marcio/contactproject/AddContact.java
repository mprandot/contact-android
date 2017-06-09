package com.dev.marcio.contactproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Arrays;

import beans.Contact;
import persistence.ContactRepository;
import util.ContactValidator;

public class AddContact extends AppCompatActivity {
    private Bitmap pic;
    private static final int PICK_PHOTO_FOR_AVATAR = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    public boolean storeContact(View view) throws Exception {

        byte[] img = new byte[0];

        if (pic != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            pic.compress(Bitmap.CompressFormat.PNG, 100, bos);
            img = bos.toByteArray();
        }

        Contact contact = new Contact(
                ((EditText) findViewById(R.id.edtName)).getText().toString(),
                ((EditText) findViewById(R.id.edtAddress)).getText().toString(),
                ((EditText) findViewById(R.id.edtCity)).getText().toString(),
                ((EditText) findViewById(R.id.edtPhone1)).getText().toString(),
                ((EditText) findViewById(R.id.edtPhone2)).getText().toString(),
                img);

        if(!ContactValidator.validate(this, contact)){
            return false;
        }

        Log.d(getClass().getSimpleName(), "storeContact: " + Arrays.toString(contact.getPic()));

        ContactRepository contactRepository = new ContactRepository(this);

        contactRepository.store(contact);

        Toast.makeText(AddContact.this, "Contato adicionado!", Toast.LENGTH_SHORT).show();

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
