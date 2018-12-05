package com.example.aplicacionpoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class MainActivityPoo extends AppCompatActivity {

   // private TextView mTextMessage;
    private FloatingActionMenu actionMenu;
    private FloatingActionButton btnIngreso;
    private FloatingActionButton btnGasto;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.nav_inicio);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.nav_Presupuesto);
                    return true;
                case R.id.navigation_notifications:
                   // mTextMessage.setText(R.string.nav_estadisticas);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_poo);

        //mTextMessage = (TextView) findViewById(R.id.message);
        actionMenu=(FloatingActionMenu) findViewById(R.id.fab_principal);
        actionMenu.setClosedOnTouchOutside(true);
        btnIngreso= (FloatingActionButton) findViewById(R.id.btn_ingreso);
        btnGasto= (FloatingActionButton) findViewById(R.id.btn_gasto);


        //Sirve para abrir una nueva Activity desde un click de boton
        //******************************************************************************
        btnIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(), NuevoIngreso.class);
                startActivity(i);
            }
        });

        btnGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(), NuevoGasto.class);
                startActivity(i);
            }
        });


    //*******************************************************************************



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

    }

}
