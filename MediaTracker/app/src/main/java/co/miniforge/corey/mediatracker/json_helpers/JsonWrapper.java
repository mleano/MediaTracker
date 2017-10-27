package co.miniforge.corey.mediatracker.json_helpers;

import org.json.JSONObject;

/**
 * Created by corey on 10/26/17.
 */

public class JsonWrapper {

    // Get (value) from json object using tag (tag)
    public static Object get(String tag, JSONObject jsonObject){
        try {
            return jsonObject.get(tag);
        } catch (Exception e){
            JSONExceptionLogger.logError(e);
        }

        return null;
    }

    // Put (value) into json object with tag (tag)
    public static JSONObject put(String tag, Object o, JSONObject jsonObject){
        //try / catch
        try {
            jsonObject.put(tag, o);
        } catch (Exception e){
            JSONExceptionLogger.logError(e);
        }

        return jsonObject;
    }
}
