package co.miniforge.corey.mediatracker.json_helpers;

import android.util.Log;

/**
 * Created by corey on 10/26/17.
 */

public class JSONExceptionLogger {
    public static void logError(Exception e){
        Log.e("JsonError", "There was an error that occurred: \n" + e.getMessage());
        e.printStackTrace();
    }
}
