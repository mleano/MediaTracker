package co.miniforge.corey.mediatracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import co.miniforge.corey.mediatracker.json_helpers.JsonWrapper;
import co.miniforge.corey.mediatracker.media_recycler.MediaRecyclerAdapter;
import co.miniforge.corey.mediatracker.media_store.MediaStorageUtil;
import co.miniforge.corey.mediatracker.model.MediaItem;
import co.miniforge.corey.mediatracker.model.MediaItemType;

public class MyListActivity extends AppCompatActivity {
    public static String mediaExtra = "mediaExtra";

    RecyclerView media_list_recycler;

    FloatingActionButton add_media_item_button;

    MediaStorageUtil storageUtil;

    List<MediaItem> mediaItems = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        storageUtil = new MediaStorageUtil(getApplicationContext());

        locateViews();

        bindData();

        handleIntent();
    }

    void handleIntent(){
        if(getIntent() != null){
            Intent intent = getIntent();

            if(intent.hasExtra(mediaExtra)){
                try {
                    JSONObject json = new JSONObject(intent.getStringExtra(mediaExtra));
                    MediaItem item = new MediaItem(json);

                    for(int i = 0; i < mediaItems.size(); i++){
                        if(mediaItems.get(i).id.equals(item.id)){
                            mediaItems.set(i, item);
                            break;
                        }
                    }

                    storageUtil.saveMediaData(mediaItems);
                } catch (Exception e){
                    Log.e("handleIntentErr", String.format("Could not update item from intent: %s", e.getMessage()));
                }
            }
        }
    }

    void locateViews(){
        media_list_recycler = (RecyclerView) findViewById(R.id.media_list_recycler);
        add_media_item_button = (FloatingActionButton) findViewById(R.id.add_media_item_button);
    }

    void bindData(){
        add_media_item_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create new empty media item
                MediaItem item = new MediaItem();
                mediaItems.add(item);
                storageUtil.saveMediaData(mediaItems);
                updateMediaItems(mediaItems);
            }
        });

        setUpRecyclerView();

        updateMediaItems(storageUtil.getMediaDataList());

//        MediaItem item = new MediaItem();
//        item.type = MediaItemType.Movie;
//        JSONObject jsonObject = item.toJson();
//        item = new MediaItem(jsonObject);

//        //Create a json object
//        JSONObject myJson = new JSONObject();
//        //put a value in the object using my JsonWrapper
//        myJson = JsonWrapper.put("test", 5, myJson);
//        //Get the value from my object using the JsonWrapper
//        Toast.makeText(getApplicationContext(),
//                (int)JsonWrapper.get("test", myJson) + "",
//                Toast.LENGTH_SHORT).show();

        //Update list every 10 sec
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                updateMediaItems(storageUtil.getMediaDataList());

                //Update every 10 sec
                handler.postDelayed(this, 10000);
            }
        };
        handler.post(runnable);
    }

    public void updateMediaItems(List<MediaItem> mediaItems){
        this.mediaItems = mediaItems;
        //TEST----
        MediaItem obj = new MediaItem();
        obj.type = MediaItemType.TV;
        this.mediaItems.add(obj);
        //-----/
        ((MediaRecyclerAdapter)media_list_recycler.getAdapter()).updateList(this.mediaItems);
    }

    void setUpRecyclerView(){
        MediaRecyclerAdapter adapter = new MediaRecyclerAdapter();
        media_list_recycler.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        media_list_recycler.setLayoutManager(manager);
    }
}
