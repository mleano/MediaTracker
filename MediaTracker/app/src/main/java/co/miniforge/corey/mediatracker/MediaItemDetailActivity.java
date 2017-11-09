package co.miniforge.corey.mediatracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import co.miniforge.corey.mediatracker.model.MediaItem;

/**
 * This activity will display the contents of a media item and allow the user to update the contents
 * of the item. When the user clicks the save button, the activity should create an intent that goes
 * back to MyListActivity and puts the MediaItem into the intent (If you are stuck on that, read through
 * the code in MyListActivity)
 */
public class MediaItemDetailActivity extends AppCompatActivity {

    //Fields
    EditText title;
    EditText description;
    EditText url;
    MediaItem mediaItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_item_detail);

        //Call function to get the intent, check if has a string extre, and if so
        //create a JSONObject then MediaItem
        getIntentData();



        //For the save button:
        //When the Save button is clicked, update the media item retrieved
        //from the intent with the values that are in the EditText fields

        //Pass this media item into the intent as a string extra using the
        //tag that is in the MyListActivity

    }

    //Helper function to locate and store the views
    private void locateViews() {
        title = (EditText) findViewById(R.id.)
    }


    //Helper function to get intent, check if it has an extra from MyListActivity,
    //if so create a new JSONObject from the string extra, then create a MediaItem
    //from that JSONObject.
    private void getIntentData() {
        //If it has a string extra, create a new
        if(getIntent().hasExtra(MyListActivity.mediaExtra)) {
            //Retrieve the extra media data
            String mediaExtraData = getIntent().getStringExtra(MyListActivity.mediaExtra);

            //Initialize JSONObject
            JSONObject mediaObject = null;

            //Create JSONObject
            try {
                mediaObject = new JSONObject(mediaExtraData);
            } catch(JSONException e) {
                Log.e("Error", "Could not create JSONObject " + e.getStackTrace());
            }

            //Create a new MediaItemfrom the JSONObject
            if (mediaObject != null) {
                mediaItem = new MediaItem(mediaObject);
            }
        }
    }

    private void locateViews() {

    }
}
