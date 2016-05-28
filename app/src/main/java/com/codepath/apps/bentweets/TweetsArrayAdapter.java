package com.codepath.apps.bentweets;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.bentweets.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by cbenedict on 5/26/2016.
 */
//Taking the tweet objects and turning them into View displayed in the List
public class TweetsArrayAdapter extends ArrayAdapter<Tweet>  {


    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context,android.R.layout.simple_list_item_1,tweets);
        //android.R.layout.simple_list_item_1
    }

    //Override and Custom Template
    //View older pattern
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //1- Get the tweet
        Tweet tweet=getItem(position);
        //2-Find or Inflate the template
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_tweet,parent,false);
        }
        //Find the subView to fill the data into the template
        ImageView ivProfileName= (ImageView) convertView.findViewById(R.id.ivProfileImage);
        TextView tvUserName=(TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvBody=(TextView) convertView.findViewById(R.id.tvBody);
        TextView tvTimeStamp=(TextView) convertView.findViewById(R.id.tvTimeStamp);
        TextView tvName=(TextView) convertView.findViewById(R.id.tvName);
        //Populate Data to subView
        tvUserName.setText(tweet.getUser().getScreenName());
        tvBody.setText(tweet.getBody());
        tvTimeStamp.setText(tweet.getCreatedAt());
        tvName.setText(tweet.getUser().getName());
        ivProfileName.setImageResource(android.R.color.transparent);//clear out the old image for a recycleView

        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileName);
        //Return the view to be inserted into the list
        return  convertView;

    }
}
