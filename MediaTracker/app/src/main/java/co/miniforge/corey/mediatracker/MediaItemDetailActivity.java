package co.miniforge.corey.mediatracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONObject;

import co.miniforge.corey.mediatracker.model.MediaItem;

/**
 * This activity will display the contents of a media item and allow the user to update the contents
 * of the item. When the user clicks the save button, the activity should create an intent that goes
 * back to MyListActivity and puts the MediaItem into the intent (If you are stuck on that, read through
 * the code in MyListActivity)
 */
public class MediaItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_item_detail);

        //Get the intent

        //Check if the intent has an extra with the tag from MyListActivity

        //If it does, then create a new JSONObect from the string extra

        //Create a new MediaItem from the JSONObject

        //Save it as a class-scope variable



        //For the save button:
        //When the Save button is clicked, update the media item retrieved
        //from the intent with the values that are in the EditText fields

        //Pass this media item into the intent as a string extra using the
        //tag that is in the MyListActivity

    }

    private void getIntentData() {
        if(getIntent().hasExtra(MyListActivity.mediaExtra))
    }
}
