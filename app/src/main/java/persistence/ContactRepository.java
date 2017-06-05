package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.dev.marcio.contactproject.MainActivity;

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

            if (contact.getId() == null) {
                db.insertOrThrow("contacts", (String) null, values);
            } else {
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
}
