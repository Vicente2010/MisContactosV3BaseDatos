package android.oscarbetanzos.com.miscontactos.fragment;

import android.oscarbetanzos.com.miscontactos.adapter.ContactoAdaptador;
import android.oscarbetanzos.com.miscontactos.pojo.Contacto;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos);

    public void inicializarAdaptadorRV(ContactoAdaptador adaptador );


}
