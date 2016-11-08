package com.example.fabricioflores.twitterproviderclient;

import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ListView listView;
    private ArrayList<String> contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listadoContactos);

        contactos = cargarDatos();

        ArrayAdapter adaptador = new ArrayAdapter<String>(
                this,
                R.layout.layout_contacto,
                contactos
        );
        listView.setAdapter(adaptador);
    }

    public ArrayList<String> cargarDatos() {
        ArrayList<String> listaContactos = new ArrayList<>();

        // Obtener un cursor al proveedor de contactos y recuperar todos
        Uri URI_Contactos = Uri.parse("content://com.fabricioflores.queryProvider/queries");  // "content://com.android.contacts/contacts"

        // Obtener content resolver y recuperar contactos
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(URI_Contactos, null, null, null, null);
        Log.i("", "Número contactos=" + Integer.toString(cursor.getCount()));

        // Si hay datos -> cargar en la lista
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String namePrimary =
                        cursor.getString(cursor.getColumnIndex("text"));
                listaContactos.add(namePrimary);
                Log.i("", "Contacto: " + namePrimary);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return listaContactos;
    }
}
