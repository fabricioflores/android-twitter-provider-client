package com.example.fabricioflores.twitterproviderclient;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fabricioflores.twitterproviderclient.models.Query;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ListView listView;
    private ArrayList<Query> contactos;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listadoContactos);
        context = getApplicationContext();
        contactos = cargarDatos();

        ArrayAdapter adaptador = new ArrayAdapter<Query>(
                this,
                R.layout.layout_contacto,
                contactos
        );
        listView.setAdapter(adaptador);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                long id = contactos.get(myItemInt).getId();
                Intent intent = new Intent(context, TweetsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("query_id", id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public ArrayList<Query> cargarDatos() {
        ArrayList<Query> listaContactos = new ArrayList<>();

        // Obtener un cursor al proveedor de contactos y recuperar todos
        Uri URI_Contactos = Uri.parse("content://com.fabricioflores.queryProvider/queries");  // "content://com.android.contacts/contacts"

        // Obtener content resolver y recuperar contactos
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(URI_Contactos, null, null, null, null);

        // Si hay datos -> cargar en la lista
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String textFromCursor =
                        cursor.getString(cursor.getColumnIndex("text"));
                long idFromCursor = cursor.getLong(cursor.getColumnIndex("_id"));
                Query query = new Query();
                query.setText(textFromCursor);
                query.setId(idFromCursor);
                listaContactos.add(query);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return listaContactos;
    }
}
