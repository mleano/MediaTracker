package co.miniforge.corey.mediatracker.model;

import org.json.JSONObject;

import co.miniforge.corey.mediatracker.json_helpers.JsonWrapper;

/**
 * Created by corey on 10/20/17.
 */

public class TVItem extends MediaItem {
    //Fields
    public int currentEpisodesWatched;
    public int totalEpisodes;

    public TVItem(JSONObject jsonObject) {
        super(jsonObject);

        // This section of code will pull explicit values related to the TV items and assign them
        // to the TV item model
        // Get the values from the json wrapper first so we can check if null with ternary
        Object currentEpValue = JsonWrapper.get("currentEpisodesWatched", jsonObject);
        Object totalEpValue = JsonWrapper.get("totalEpisodes", jsonObject);

        // Assign value from JSON object using ternary operators
        currentEpisodesWatched = currentEpValue == null ? 0 : (int)currentEpValue;
        totalEpisodes = totalEpValue == null ? 0 : (int)totalEpValue;
    }

    @Override
    public JSONObject toJson(){
        JSONObject result = super.toJson();

        // This section of code will put the TVItem specific values into the json object that
        // we got from our MediaItem class's toJson.
        result = JsonWrapper.put("currentEpisodesWatched", currentEpisodesWatched, result);
        result = JsonWrapper.put("totalEpisodes", totalEpisodes, result);

        return result;
    }
}
