package util;

import android.content.Context;
import android.widget.Toast;

import beans.Contact;

/**
 * Created by marcio on 08/06/17.
 */
public class ContactValidator {

    public static boolean validate(Context context, Contact contact) {

        if(contact.getName().length() <= 0){
            Toast.makeText(context, "Informe um nome", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(contact.getPhone1().length() <= 0){
            Toast.makeText(context, "Informe um telefone", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


}
