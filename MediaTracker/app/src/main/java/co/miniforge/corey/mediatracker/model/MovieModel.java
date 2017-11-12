package co.miniforge.corey.mediatracker.model;

import org.json.JSONObject;

/**
 * Created by corey on 10/20/17.
 */

public class MovieModel extends MediaItem {

    //Fields
    public int myRating;
    public String genre;

    public MovieModel(JSONObject jsonObject) {
        super(jsonObject);
    }
}
