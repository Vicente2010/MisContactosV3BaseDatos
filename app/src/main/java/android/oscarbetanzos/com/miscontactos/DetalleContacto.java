package android.oscarbetanzos.com.miscontactos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetalleContacto extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        //miActionBar = (ActionBar) findViewById(R.id.miActionBar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        Bundle parametros = getIntent().getExtras();
        if (parametros!=null) {
            String nombre = parametros.getString(getResources().getString(R.string.pnombre));
            String telefono = parametros.getString(getResources().getString(R.string.ptelefono));
            String email = parametros.getString(getResources().getString(R.string.pemail));

            tvNombre = (TextView) findViewById(R.id.tvNombre);
            tvTelefono = (TextView) findViewById(R.id.tvTelefono);
            tvEmail = (TextView) findViewById(R.id.tvEmail);

            tvNombre.setText(nombre);
            tvTelefono.setText(telefono);
            tvEmail.setText(email);
        }
    }

    public void llamar(View v) {
        String telefono = tvTelefono.getText().toString();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono)));
    }

    public void mandarEmail(View v){
        String email = tvEmail.getText().toString();
        Intent emailIntent = new Intent((Intent.ACTION_SEND));
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL,email);
        emailIntent.setType("messsage/rfc822");
        startActivity(Intent.createChooser(emailIntent,"EMAIL "));
    }

}
