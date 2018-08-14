package android.oscarbetanzos.com.miscontactos;

import android.oscarbetanzos.com.miscontactos.adapter.ContactoAdaptador;
import android.oscarbetanzos.com.miscontactos.adapter.PageAdapter;
import android.oscarbetanzos.com.miscontactos.fragment.PerfilFragment;
import android.oscarbetanzos.com.miscontactos.fragment.RecyclerViewFragment;
import android.oscarbetanzos.com.miscontactos.pojo.Contacto;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar =  (Toolbar) findViewById(R.id.toolbar);
        tabLayout =(TabLayout) findViewById(R.id.tabLayout);
        viewPager =(ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();

        if (toolbar!= null){
            setSupportActionBar(toolbar);
        }

    }



    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_face);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_contacto);
    }
}


