package android.oscarbetanzos.com.miscontactos.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.oscarbetanzos.com.miscontactos.pojo.Contacto;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {
    private  Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBasesDatos.DATABASE_NAME, null, ConstantesBasesDatos.DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCrearTablaContacto = "CREATE TABLE " + ConstantesBasesDatos.TABLE_CONTACTS + "(" +
                ConstantesBasesDatos.TABLE_CONTACTS_ID          + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBasesDatos.TABLE_CONTACTS_NOMBRE      + " TEXT, " +
                ConstantesBasesDatos.TABLE_CONTACTS_TELEFONO    + " TEXT, " +
                ConstantesBasesDatos.TABLE_CONTACTS_EMAIL       + " TEXT, " +
                ConstantesBasesDatos.TABLE_CONTACTS_FOTO        + " INTEGER" +
                ")";

        String queryCrearTablaLikesContacto = "CREATE TABLE " + ConstantesBasesDatos.TABLE_LIKES_CONTACT + "(" +
                ConstantesBasesDatos.TABLE_LIKES_CONTACT_ID             +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBasesDatos.TABLE_LIKES_CONTACT_ID_CONTACTO    +  " INTEGER, " +
                ConstantesBasesDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES   +  " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBasesDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + ") " +
                "REFERENCES " + ConstantesBasesDatos.TABLE_CONTACTS + "(" + ConstantesBasesDatos.TABLE_CONTACTS_ID +")" +
                ")";

        sqLiteDatabase.execSQL(queryCrearTablaContacto);
        sqLiteDatabase.execSQL(queryCrearTablaLikesContacto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBasesDatos.TABLE_CONTACTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBasesDatos.TABLE_LIKES_CONTACT);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Contacto> obtenerTodosLosContactos(){
        ArrayList<Contacto> contactos = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBasesDatos.TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        while (registros.moveToNext()){
            Contacto contactoActual = new Contacto();
            contactoActual.setId        (registros.getInt   (0));
            contactoActual.setNombre    (registros.getString(1));
            contactoActual.setTelefono  (registros.getString(2));
            contactoActual.setEmail     (registros.getString(3));
            contactoActual.setFoto      (registros.getInt   (4));

            String queryLikes = "SELECT COUNT(" + ConstantesBasesDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES + ") as likes " +
                                " FROM "  + ConstantesBasesDatos.TABLE_LIKES_CONTACT +
                                " WHERE " + ConstantesBasesDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + "=" + contactoActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes,null);
            if (registrosLikes.moveToNext()){
                contactoActual.setLikes(registrosLikes.getInt(0));
            }else{
                contactoActual.setLikes(0);
            }

            contactos.add(contactoActual);
        }

        db.close();
        return contactos;
    }

    public void insertarContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBasesDatos.TABLE_CONTACTS,null,contentValues);
        db.close();
    }

    public void insertarLikeContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBasesDatos.TABLE_LIKES_CONTACT,null,contentValues);
        db.close();
    }

    public int obtenerLikesContacto(Contacto contacto){
        int likes = 0;

        String query  = "SELECT COUNT(" + ConstantesBasesDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES + ")" +
                        " FROM " + ConstantesBasesDatos.TABLE_LIKES_CONTACT +
                        " WHERE " + ConstantesBasesDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + "=" +
                        contacto.getId();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor registros = db.rawQuery(query,null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();

        return likes;
    }
}
