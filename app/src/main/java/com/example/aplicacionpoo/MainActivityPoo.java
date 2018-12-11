package com.example.aplicacionpoo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import static com.example.aplicacionpoo.R.*;

public class MainActivityPoo extends AppCompatActivity {

    private TextView mTextMessage, descripcion, cantidad;
    private FloatingActionMenu actionMenu;
    private FloatingActionButton btnIngreso;
    private FloatingActionButton btnGasto;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case id.navigation_home:
                    mTextMessage.setText(string.nav_inicio);
                    descripcion.setVisibility(View.INVISIBLE);
                    cantidad.setVisibility(View.INVISIBLE);

                    return true;
                case id.navigation_dashboard:
                    mTextMessage.setText(string.nav_Presupuesto);
                    descripcion.setVisibility(View.VISIBLE);
                    cantidad.setVisibility(View.VISIBLE);

                    return true;
                case id.navigation_notifications:
                    mTextMessage.setText(string.nav_estadisticas);
                    descripcion.setVisibility(View.INVISIBLE);
                    cantidad.setVisibility(View.INVISIBLE);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main_poo);

        mTextMessage = (TextView) findViewById(id.txt_pantalla);
        actionMenu=(FloatingActionMenu) findViewById(id.fab_principal);
        actionMenu.setClosedOnTouchOutside(true);
        btnIngreso= (FloatingActionButton) findViewById(R.id.btn_ingreso);
        btnGasto= (FloatingActionButton) findViewById(R.id.btn_gasto);
        descripcion= (TextView) findViewById(R.id.txt_descripcion);
        cantidad= (TextView) findViewById(R.id.txt_cantidad);

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



        BottomNavigationView navigation = (BottomNavigationView) findViewById(id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(mipmap.ic_launcher);

    }


    //Metodo para consultar productos
    public void Mostrar(View view){

        ConexionBD admin = new ConexionBD(this, "administracion", null, 1);
        SQLiteDatabase liteDatabase= admin.getWritableDatabase();

        Cursor fila=  liteDatabase.rawQuery("select descripcion, cantidad from datos", null);


        if(fila.moveToFirst()){
            descripcion.setText(fila.getString(0));
            cantidad.setText(fila.getString(1));

            liteDatabase.close();
        } else {
            Toast.makeText(this,"No existe el artículo", Toast.LENGTH_SHORT).show();
            liteDatabase.close();
        }



    }

}
