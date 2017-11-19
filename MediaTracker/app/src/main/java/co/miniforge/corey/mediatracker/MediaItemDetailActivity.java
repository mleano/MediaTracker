package co.miniforge.corey.mediatracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    Button saveBtn;
    MediaItem mediaItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_item_detail);

        //Call function to locate and store views
        locateViews();

        //Call function to get the intent, check if has a string extre, and if so
        //create a JSONObject then MediaItem
        getIntentData();

        //Call function to bind click listener to save button. When the save button
        //is clicked, the media item info are updated
        bindFunctionality();
    }

    //Function to locate and store the views
    private void locateViews() {
        title = (EditText) findViewById(R.id.title_input);
        description = (EditText) findViewById(R.id.description_input);
        url = (EditText) findViewById(R.id.url_input);
        saveBtn = (Button) findViewById(R.id.save_btn);
    }


    //Function to get intent, check if it has an extra from MyListActivity,
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
            } else {
                Log.e("ERROR", "Could not create new media item");
            }
        } else {
            Log.i("MSG", "No intent extra to create new media item.");
        }
    }

    //Function to bind click listener to the save button. When clicked
    //the media item should be updated.
    private void bindFunctionality() {
        //When the Save button is clicked, update the media item retrieved
        //from the intent with the values that are in the EditText fields
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call function to prompt a confirmation dialog before saving
                promptConfirmation();

                //Create intent that would direct back to MyListActivity
                Intent intent = new Intent(getApplicationContext(), MyListActivity.class);

                //Reassign title, description, and url of media item with values from
                //the string extra
                mediaItem.title = title.getText().toString();
                mediaItem.description = description.getText().toString();
                mediaItem.url = url.getText().toString();

                //Pass the media item into the intent as a string extra using the tag
                // in the MyListActivity
                intent.putExtra(MyListActivity.mediaExtra, mediaItem.toJson().toString());

                startActivity(intent);
            }
        });
    }

    //Function that prompts for a confirmation dialog
    private void promptConfirmation() {

        //https://developer.android.com/guide/topics/ui/dialogs.html#AlertDialog
        //Make sure to put the code in the activity, the builder requires an activity to be passed in
        //import android.support.v7.app for the alert dialog

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Save Changes").setMessage("Are you sure you want to save these changes?");

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Put the start activity with intent code here
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Do nothing, unless you want this button to go back to
                // ListActivity without putting an intent extra
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
