package android.oscarbetanzos.com.miscontactos.db;

public class ConstantesBasesDatos {

    public static final String DATABASE_NAME ="contactos";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_CONTACTS           = "contacto";
    public static final String TABLE_CONTACTS_ID        = "id";
    public static final String TABLE_CONTACTS_NOMBRE    = "nombre";
    public static final String TABLE_CONTACTS_TELEFONO  = "telefono";
    public static final String TABLE_CONTACTS_EMAIL     = "email";
    public static final String TABLE_CONTACTS_FOTO      = "foto";


    public static String TABLE_LIKES_CONTACT                    = "contacto_likes";
    public static String TABLE_LIKES_CONTACT_ID                 = "id";
    public static final String TABLE_LIKES_CONTACT_ID_CONTACTO  = "id_contacto";
    public static final String TABLE_LIKES_CONTACT_NUMERO_LIKES = "numero_likes";
}

//PARA USAR EL DEBUGGER DE CONSOLA
//En la Terminal:
//1. Ir a la dirección del SDK Manager CON \platform-tools\
//2. ver el emulador con  .\adb devices y dirá algo como: emulator-5554
//3. Entrar al emulador usando .\adb -s emulator-5554 shell
//5. En el emulador saldrá algo cómo:  'root@generic_x86:/ #' poner  ' sqlite3 \data\data\android.oscarbetanzos.com.miscontactos\databases\contactos'
/*6. Habiendo ya accesado correctamente podremos ejecutar varios comandos para bases de datos como
    .tables  - para ver tablas
    .databes
    .schema contacto  - para ver el esquema de la tabla
    SELECT * FROM contacto;  - para ver lo que hay en la tabla contacto
    SELECT * FROM contacto_likes; - para ver lo que hay en la tabla contacto_likes;
    INSERT INTO contacto_likes (id_contacto,numero_likes) VALUES (5,1); - para agregar un registro
 */
//7. Para salir de SQLite usar .quit
//8. Salir del emulador: exit