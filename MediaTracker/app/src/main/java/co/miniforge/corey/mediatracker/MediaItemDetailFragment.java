package co.miniforge.corey.mediatracker;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import co.miniforge.corey.mediatracker.model.MediaItem;


/**
 * A simple {@link Fragment} subclass.
 */
public class MediaItemDetailFragment extends Fragment {

    //FIELDS
    private MediaItem mediaItem;
    EditText title;
    EditText description;
    EditText url;
    Button saveBtn;


    //CONSTRUCTOR
    public MediaItemDetailFragment() {
        // Required empty public constructor
    }


    //METHODS
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_media_item_detail, container, false); //was split up
        View inflated = inflater.inflate(R.layout.fragment_media_item_detail, container, false);

        locateViews(inflated);

        return inflated;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindData();
    }

    public static MediaItemDetailFragment create(MediaItem item) {
        MediaItemDetailFragment fragment = new MediaItemDetailFragment();
        fragment.mediaItem = item;
        return fragment;
    }

    //Function to locate and store the views
    private void locateViews(View view) {
        title = (EditText) findViewById(R.id.title_input);
        description = (EditText) findViewById(R.id.description_input);
        url = (EditText) findViewById(R.id.url_input);
        saveBtn = (Button) findViewById(R.id.save_btn);
    }

    //Function to bind click listener to the save button. When clicked
    //the media item should be updated.
    private void bindData() {
        //When the Save button is clicked, update the media item retrieved
        //from the intent with the values that are in the EditText fields
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call function to prompt a confirmation dialog before saving
                promptConfirmation();
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

                if(mediaItem != null) {

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
