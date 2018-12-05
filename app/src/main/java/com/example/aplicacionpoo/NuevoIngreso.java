package com.example.aplicacionpoo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NuevoIngreso extends AppCompatActivity {

    EditText txtingreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_ingreso);

        txtingreso= (EditText) findViewById(R.id.txt_cantidadingreso);
    }


    public void RegistrarIngreso(View view){
        ConexionBD admin = new ConexionBD(this, "administracion", null, 1);
        SQLiteDatabase liteDatabase= admin.getWritableDatabase();

        String ingreso= txtingreso.getText().toString();
        if(!ingreso.isEmpty()){

            ContentValues registro= new ContentValues();
            registro.put("Ingreso", ingreso);

            liteDatabase.insert("datos", null, registro);
            liteDatabase.close();

            txtingreso.setText("");

            Toast.makeText(this, "Datos Guardados", Toast.LENGTH_SHORT).show();


        }else{
            Toast.makeText(this, "Debes llenar los Campos", Toast.LENGTH_SHORT).show();
        }
    }
}
