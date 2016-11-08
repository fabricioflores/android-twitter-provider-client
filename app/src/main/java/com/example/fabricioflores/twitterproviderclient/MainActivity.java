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
    private ArrayList<Query> queries;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listadoContactos);
        context = getApplicationContext();
        queries = loadData();

        ArrayAdapter adaptador = new ArrayAdapter<>(
                this,
                R.layout.layout_contacto,
                queries
        );
        listView.setAdapter(adaptador);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                long id = queries.get(myItemInt).getId();
                Intent intent = new Intent(context, TweetsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("query_id", id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public ArrayList<Query> loadData() {
        ArrayList<Query> finalArray = new ArrayList<>();

        Uri URI_Queries = Uri.parse("content://com.fabricioflores.queryProvider/queries");

        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(URI_Queries, null, null, null, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String textFromCursor =
                        cursor.getString(cursor.getColumnIndex("text"));
                long idFromCursor = cursor.getLong(cursor.getColumnIndex("_id"));
                Query query = new Query();
                query.setText(textFromCursor);
                query.setId(idFromCursor);
                finalArray.add(query);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return finalArray;
    }
}
