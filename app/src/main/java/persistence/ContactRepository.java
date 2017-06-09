package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.dev.marcio.contactproject.MainActivity;

import java.util.ArrayList;
import java.util.List;

import beans.Contact;

/**
 * Created by marcio on 04/06/17.
 */
public class ContactRepository {
    private ConnectionSqlLite connectionSqLite = null;

    public ContactRepository(Context context) {
        connectionSqLite = new ConnectionSqlLite(context);
    }

    public void store(Contact contact) throws Exception {
        SQLiteDatabase db = connectionSqLite.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("name", contact.getName());
            values.put("city", contact.getCity());
            values.put("address", contact.getAddress());
            values.put("phone1", contact.getPhone1());
            values.put("phone2", contact.getPhone2());
            values.put("pic", contact.getPic());

            if (contact.getId() == null) {
                Log.d(getClass().getSimpleName(), "Inserting...");
                db.insertOrThrow("contacts", (String) null, values);
            } else {
                Log.d(getClass().getSimpleName(), "Updating...");
                db.update("contacts", values, "id = ?", new String[]{String.valueOf(contact.getId())});
            }
            Log.d(getClass().getSimpleName(), "SUCESSO!!!!!!!!!!!!!!!!");
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Erro ao salvar contato", e);
            throw e;
        } finally {
            db.close();
        }
    }

    public ArrayList<Contact> searchContact(String filter) {

        SQLiteDatabase db = this.connectionSqLite.getReadableDatabase();
        ArrayList<Contact> contacts = new ArrayList<>();

        try {
            Cursor cursor;

            if (filter.length() > 0) {
                cursor = db.query(false, "contacts", new String[]{}, "name LIKE ?", new String[]{"%" + filter + "%"}, null, null, null,
                        null);
            } else {
                cursor = db.rawQuery("select * from contacts order by name", null);
            }

            if (cursor.getCount() >0) {
                if (cursor.moveToFirst()) {
                    do {
                        Contact contact = new Contact();
                        contact.setId(cursor.getInt(cursor.getColumnIndex("id")));
                        contact.setName(cursor.getString(cursor.getColumnIndex("name")));
                        contact.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                        contact.setCity(cursor.getString(cursor.getColumnIndex("city")));
                        contact.setPhone1(cursor.getString(cursor.getColumnIndex("phone1")));
                        contact.setPhone2(cursor.getString(cursor.getColumnIndex("phone2")));
                        contact.setPic(cursor.getBlob(cursor.getColumnIndex("pic")));
                        contacts.add(contact);

                    } while (cursor.moveToNext());
                }

                cursor.close();
            }


            return contacts;
        } catch (Exception ex) {
            Log.e(this.getClass().getSimpleName(), "Erro na consulta", ex);
            return null;
        } finally {
            db.close();
        }
    }

    public boolean destroy(Integer id) throws Exception {
        SQLiteDatabase db = this.connectionSqLite.getWritableDatabase();

        try {
            db.delete("contacts", "id = ?", new String[]{String.valueOf(id)});
        } catch (Exception ex) {
            Log.e(this.getClass().getSimpleName(), "Erro ao deletar", ex);
            return false;
        } finally {
            db.close();
        }

        return true;

    }
}
