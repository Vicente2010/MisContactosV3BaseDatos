package android.oscarbetanzos.com.miscontactos.fragment;

import android.os.Bundle;
import android.oscarbetanzos.com.miscontactos.R;
import android.oscarbetanzos.com.miscontactos.adapter.ContactoAdaptador;
import android.oscarbetanzos.com.miscontactos.pojo.Contacto;
import android.oscarbetanzos.com.miscontactos.presentador.IRecyclerViewFragmentPresenter;
import android.oscarbetanzos.com.miscontactos.presentador.RecyclerViewFragmentPresenter;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView{
    private ArrayList<Contacto> contactos;
    private RecyclerView listaContactos;
    private IRecyclerViewFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inicia la view y declara el RecyclerView
        View v = inflater.inflate(R.layout.fragment_recyclerview,container,false);
        listaContactos = (RecyclerView) v.findViewById(R.id.rvContactos);

        //El presentador usa este IRVFview, obtiene los datos y los presenta usando los
        //métodos implementados debajo
        presenter = new RecyclerViewFragmentPresenter(this,getContext());
        return v;

    }

    //ESTOS SON LOS MÉTODOS IMPLEMENTADOS DE LA INTERFAZ IRecyclerViewFragmentView
    //Esta parte define el Layout en el que se presenta el fragment (cardviews)
    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaContactos.setLayoutManager(llm);
    }

    //Esta es la parte que crea el adaptador (ContactoAdaptador)
    @Override
    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos) {
        ContactoAdaptador adaptador = new ContactoAdaptador(contactos, getActivity());
        return adaptador;
    }

    //Esta es la parte que inicia el adaptador(pone el adaptador en el Recycler View)
    @Override
    public void inicializarAdaptadorRV(ContactoAdaptador adaptador) {
        listaContactos.setAdapter(adaptador);
    }
}
