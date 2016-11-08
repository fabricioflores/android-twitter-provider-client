package com.example.fabricioflores.twitterproviderclient;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.fabricioflores.twitterproviderclient.models.Query;
import com.example.fabricioflores.twitterproviderclient.models.Tweet;

import java.util.ArrayList;

public class TweetsActivity extends Activity {


    private ListView listView;
    private ArrayList<Tweet> contactos;
    private long queryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        queryId = bundle.getLong("query_id");

        contactos = cargarDatos();
        Log.i("nada", contactos.size() + "");
    }

    public ArrayList<Tweet> cargarDatos() {
        ArrayList<Tweet> listaContactos = new ArrayList<>();

        // Obtener un cursor al proveedor de contactos y recuperar todos
        Uri URI_Contactos = Uri.parse("content://com.fabricioflores.tweetProvider/tweets");  // "content://com.android.contacts/contacts"

        // Obtener content resolver y recuperar contactos
        ContentResolver cr = getContentResolver();
        String selection = "query_id=" + queryId;
        Cursor cursor = cr.query(URI_Contactos, null, selection, null, null);

        // Si hay datos -> cargar en la lista
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String textFromCursor =
                        cursor.getString(cursor.getColumnIndex("text"));
                String imageUrl = cursor.getString(cursor.getColumnIndex("userimage"));
                String username = cursor.getString(cursor.getColumnIndex("username"));
                Tweet tweet = new Tweet();
                tweet.setText(textFromCursor);
                tweet.setUserImage(imageUrl);
                tweet.setUserName(username);
                listaContactos.add(tweet);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return listaContactos;
    }
}
