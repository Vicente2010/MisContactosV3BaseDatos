package android.oscarbetanzos.com.miscontactos.presentador;

import android.content.Context;
import android.oscarbetanzos.com.miscontactos.db.ConstructorContactos;
import android.oscarbetanzos.com.miscontactos.fragment.IRecyclerViewFragmentView;
import android.oscarbetanzos.com.miscontactos.pojo.Contacto;

import java.util.ArrayList;
//Esta clase funciona como una especie de intermediario entre la DataBase y el View

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter{
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorContactos constructorContactos;
    private ArrayList<Contacto> contactos;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerContactosBaseDatos();

    }

    //Esta parte conecta con la BD y obtiene los datos
    @Override
    public void obtenerContactosBaseDatos() {
        constructorContactos = new ConstructorContactos(context);
        contactos = constructorContactos.obtenerDatos();
        mostrarContactosRV();
    }

    //Este método conecta con el View y muestra los datos obtenidos
    @Override
    public void mostrarContactosRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(contactos));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }
}
