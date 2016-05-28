package com.codepath.apps.bentweets;

import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.bentweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TimelineActivity extends ActionBarActivity {
private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter ltweets;
    private ListView lvTweets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        //Find the ListView
        lvTweets=(ListView) findViewById(R.id.lvTweets);
        //Create the ArrayList (data source)
        tweets=new ArrayList<>();
        //Construct the adapter from the data source
        ltweets=new TweetsArrayAdapter(this,tweets);
        //Connect the adapter to the List View
        lvTweets.setAdapter(ltweets);
/*
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                customLoadMoreDataFromApi(page);
                // or customLoadMoreDataFromApi(totalItemsCount);
                return true; // ONLY if more data is actually being loaded; false otherwise.
            }
            public  int getFooterViewType(){
                return -1;
            }
        });

*/
        //Get the Client
        client=TwitterApplication.getRestClient();//Singleton Client
        populateTimeline();
    }


    //Send a API request to get the Timeline
    //Fill the ListView by creating the tweet objects for JSON
    private void populateTimeline() {
        client.getHomeTimeline(new JsonHttpResponseHandler(){
            //SUCCES

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json){
                Log.d("DEBUG",json.toString());
            //DESERIALIZE JSON
            //CREATE MODEL AND PUT THEM TO THE ADAPTER
            //LOAD THE MODEL DATA INTO LISTVIEW
                ArrayList<Tweet>  tweets= Tweet.fromJSONArray(json);
                ltweets.addAll(tweets);
            }
            //FAILURE
            @Override
            public void onFailure(int statusCode, Header[] headers,Throwable throwable, JSONObject errorResponse){
                Toast.makeText(TimelineActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }
    // Append more data into the adapter
    public void customLoadMoreDataFromApi(int offset) {
        // This method probably sends out a network request and appends new data items to your adapter.
        // Use the offset value and add it as a parameter to your API request to retrieve paginated data.
        // Deserialize API response and then construct new objects to append to the adapter
    }

}
