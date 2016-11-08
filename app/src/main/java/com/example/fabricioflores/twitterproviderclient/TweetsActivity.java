package com.example.fabricioflores.twitterproviderclient;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fabricioflores.twitterproviderclient.adapters.TweetsAdapter;
import com.example.fabricioflores.twitterproviderclient.models.Tweet;

import java.util.ArrayList;

public class TweetsActivity extends Activity {

    private ArrayList<Tweet> tweets;
    private long queryId;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        queryId = bundle.getLong("query_id");

        tweets = loadData();

        list = (ListView) findViewById(R.id.artistList);
        TweetsAdapter adapter = new TweetsAdapter(getBaseContext(), tweets);
        list.setAdapter(adapter);
    }

    public ArrayList<Tweet> loadData() {
        ArrayList<Tweet> finalList = new ArrayList<>();

        Uri URI_Tweets = Uri.parse("content://com.fabricioflores.tweetProvider/tweets");

        ContentResolver cr = getContentResolver();
        String selection = "query_id=" + queryId;
        Cursor cursor = cr.query(URI_Tweets, null, selection, null, null);

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
                finalList.add(tweet);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return finalList;
    }
}
