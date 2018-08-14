package android.oscarbetanzos.com.miscontactos.adapter;

import android.app.Activity;
import android.content.Intent;
import android.oscarbetanzos.com.miscontactos.db.ConstructorContactos;
import android.oscarbetanzos.com.miscontactos.pojo.Contacto;
import android.oscarbetanzos.com.miscontactos.DetalleContacto;
import android.oscarbetanzos.com.miscontactos.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder>{

    ArrayList<Contacto> contactos;
    Activity activity;

    public  ContactoAdaptador(ArrayList<Contacto> contactos, Activity activity){
        this.contactos = contactos;
        this.activity =activity;
    }


    //infla el layout y lo pasa al viewholder para que obtenga los views
    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto,parent,false);
        return new ContactoViewHolder(v);
    }

    //Asocia cada elemento de la lista a cada view
    @Override
    public void onBindViewHolder(final ContactoViewHolder holder, int position) {
        final Contacto contacto = contactos.get(position);
        holder.imgFoto.setImageResource(contacto.getFoto());
        holder.tvNombreCV.setText(contacto.getNombre());
        holder.tvTelefonoCV.setText(contacto.getTelefono());
        holder.tvLikes.setText(String.valueOf(contacto.getLikes()));

        //Proceso del detalleContacto al dar click a la Imagen
        holder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, contacto.getNombre(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, DetalleContacto.class);
                intent.putExtra("nombre",contacto.getNombre());
                intent.putExtra("telefono",contacto.getTelefono());
                intent.putExtra("email",contacto.getEmail());
                activity.startActivity(intent);
            }
        });

        //Proceso de dar Like con el boton de like
        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"Diste like a " + contacto.getNombre(), Toast.LENGTH_SHORT).show();
                ConstructorContactos constructorContactos = new ConstructorContactos(activity);
                constructorContactos.darLikeContacto(contacto);
                holder.tvLikes.setText(String.valueOf(constructorContactos.obtenerLikesContacto(contacto)));
            }
        });
    }

    @Override
    public int getItemCount() {  //Cantidad de elementos que contiene la lista de contactos
        return contactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvTelefonoCV;
        private ImageButton btnLike;
        private TextView tvLikes;

        public ContactoViewHolder(View itemView) {
            super(itemView);

            imgFoto      = (ImageView)      itemView.findViewById(R.id.imgFoto);
            tvNombreCV   = (TextView)       itemView.findViewById(R.id.tvNombreCV);
            tvTelefonoCV = (TextView)       itemView.findViewById(R.id.tvTelefonoCV);
            btnLike      = (ImageButton)    itemView.findViewById(R.id.btnLike);
            tvLikes      = (TextView)       itemView.findViewById(R.id.tvLikes);
        }
    }
}
