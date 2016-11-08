package com.example.fabricioflores.twitterproviderclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fabricioflores.twitterproviderclient.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.example.fabricioflores.twitterproviderclient.R;

/**
 * Created by fabricioflores on 3/11/16.
 */

public class TweetsAdapter extends ArrayAdapter<Tweet> {
    private final Context contexto;

    public TweetsAdapter(Context context, ArrayList<Tweet> array_tweets) {
        super(context, -1, array_tweets);
        this.contexto = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) contexto
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layoutPersona = inflater.inflate(R.layout.custom_list_layout, parent, false);

        ImageView image = (ImageView) layoutPersona.findViewById(R.id.imagePlaceholder);
        TextView name = (TextView) layoutPersona.findViewById(R.id.userName);
        TextView text = (TextView) layoutPersona.findViewById(R.id.textTweet);

        Tweet tweet = getItem(position);

        Picasso.with(contexto).load(tweet.getUserImage()).into(image);
        name.setText("@" + tweet.getUserName());
        text.setText(tweet.getText());
        return layoutPersona;
    }

}
