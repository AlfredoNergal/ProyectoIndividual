package com.example.aplicacionpoo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NuevoGasto extends AppCompatActivity {

    static Spinner spinner;
    static TextView textView;
    static EditText txtgasto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_gasto);

        spinner= (Spinner) findViewById(R.id.spinner);
        textView= (TextView) findViewById(R.id.tv_tipogasto);
        txtgasto= (EditText) findViewById(R.id.txt_gasto);
        //Sirve para hacer la conexion con el codigo xml de los datos del combobox(spinner)
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.tipo_gasto,
                R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Sirve para conseguir el elemento seleccionado en el spinner(combobox) y colocarlo en un textview(label)
                String vartipogasto;
                vartipogasto= spinner.getSelectedItem().toString();
                textView.setText(vartipogasto);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Toast.makeText(this, "Debes Selecionar un tipo de Gasto!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Metodo Para Registrar Datos en  BD
    public void Registrar(View view){
        ConexionBD admin = new ConexionBD(this, "administracion", null, 1);
        SQLiteDatabase liteDatabase= admin.getWritableDatabase();

        String gasto= txtgasto.getText().toString();
        String descripcion=textView.getText().toString();
        if(!gasto.isEmpty()){

            ContentValues registro= new ContentValues();
            registro.put("cantidad", gasto);
            registro.put("descripcion", descripcion);

            liteDatabase.insert("datos", null, registro);
            liteDatabase.close();

            txtgasto.setText("");

            Toast.makeText(this, "Datos Guardados", Toast.LENGTH_SHORT).show();


        }else{
            Toast.makeText(this, "Debes llenar los Campos", Toast.LENGTH_SHORT).show();
        }
    }



}
