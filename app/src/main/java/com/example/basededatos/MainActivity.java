package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_codigo, et_descripcion, et_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_codigo = (EditText) findViewById(R.id.txt_codigo);
        et_descripcion = (EditText) findViewById(R.id.txt_descripcion);
        et_precio = (EditText) findViewById(R.id.txt_precio);
    }

    //Método para dar de alta a los productos
    public void Registrar (View view){
        // Instanciamos la base de datos con los cuatro argumentos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        // Abrimos la base de datos
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();

            // Guardamos en la base de datos los valores que el usuario ha escrito
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            // Los insertamos dentro de la tabla "articulos" que hemos creado
            BaseDeDatos.insert("articulos", null, registro);

            // Cerramos la base de datos
            BaseDeDatos.close();

            //Limpiamos los campos
            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            Toast.makeText(this, "El producto se ha grabado de forma correcta", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}