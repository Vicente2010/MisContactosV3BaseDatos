package android.oscarbetanzos.com.miscontactos.db;

import android.content.ContentValues;
import android.content.Context;
import android.oscarbetanzos.com.miscontactos.R;
import android.oscarbetanzos.com.miscontactos.pojo.Contacto;

import java.util.ArrayList;

public class ConstructorContactos {
    private static final int LIKE = 1;
    private Context context;

    public ConstructorContactos(Context context) {
        this.context = context;
    }

    public ArrayList<Contacto> obtenerDatos(){


        BaseDatos db = new BaseDatos(context);
        insertarTresContactos(db);
        return db.obtenerTodosLosContactos();

    }

    public void insertarTresContactos(BaseDatos baseDatos){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBasesDatos.TABLE_CONTACTS_NOMBRE,"Oscar Betanzos");
        contentValues.put(ConstantesBasesDatos.TABLE_CONTACTS_TELEFONO,"2224342910");
        contentValues.put(ConstantesBasesDatos.TABLE_CONTACTS_EMAIL,"oscar@gmail.com");
        contentValues.put(ConstantesBasesDatos.TABLE_CONTACTS_FOTO,R.drawable.wine);

        baseDatos.insertarContacto(contentValues);

        contentValues.put(ConstantesBasesDatos.TABLE_CONTACTS_NOMBRE,"Omar Betanzos");
        contentValues.put(ConstantesBasesDatos.TABLE_CONTACTS_TELEFONO,"2211603633");
        contentValues.put(ConstantesBasesDatos.TABLE_CONTACTS_EMAIL,"omar@gmail.com");
        contentValues.put(ConstantesBasesDatos.TABLE_CONTACTS_FOTO,R.drawable.globe);

        baseDatos.insertarContacto(contentValues);

        contentValues.put(ConstantesBasesDatos.TABLE_CONTACTS_NOMBRE,"Nani Gonzalez");
        contentValues.put(ConstantesBasesDatos.TABLE_CONTACTS_TELEFONO,"2221243059");
        contentValues.put(ConstantesBasesDatos.TABLE_CONTACTS_EMAIL,"nani@gmail.com");
        contentValues.put(ConstantesBasesDatos.TABLE_CONTACTS_FOTO,R.drawable.orange);

        baseDatos.insertarContacto(contentValues);
    }

    //Este proceso recibe contacto del ContactoAdaptador al recibir la acción del botón like
    //Conecta con la base de datos para insertar un like en la Tabla Like
    public void darLikeContacto(Contacto contacto){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBasesDatos.TABLE_LIKES_CONTACT_ID_CONTACTO,contacto.getId());
        contentValues.put(ConstantesBasesDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES, LIKE);
        db.insertarLikeContacto(contentValues);
        db.close();
    }

    //Esta parte conecta con la base de datos para obtener el número de likes de un contacto
    public int obtenerLikesContacto(Contacto contacto){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesContacto(contacto);
    }

}
