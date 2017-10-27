package co.miniforge.corey.mediatracker.model;

import org.json.JSONObject;

/**
 * Created by corey on 10/20/17.
 */

public class TVItem extends MediaItem {
    //Fields
    public int currentEpisodesWatched;
    public int totalEpisodes;

    public TVItem(JSONObject jsonObject) {
        super(jsonObject);

        try {
            currentEpisodesWatched = jsonObject.getInt("currentEpisodesWatched");
            totalEpisodes = jsonObject.getInt("totalEpisodes");
        } catch (Exception e) {
            //TODO: Create exception logger
        }
    }

    @Override
    public JSONObject toJson(){
        JSONObject result = super.toJson();

        try {
            result.put("currentEpisodesWatched", currentEpisodesWatched);
            result.put("totalEpisodes", totalEpisodes);
        } catch (Exception e){
            //TODO: Create exception logger
        }

        return result;
    }
}
