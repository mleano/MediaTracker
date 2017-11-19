package co.miniforge.corey.mediatracker.model;

import org.json.JSONObject;

/**
 * Created by Marlene on 11/18/2017.
 */

public class YoutubeModel extends MediaItem {
    //Fields
    public String youtuberName;
    public int duration;

    //Constructor
    public YoutubeModel(JSONObject jsonObject) {
        super(jsonObject);
    }
}
