package co.miniforge.corey.mediatracker.ui_helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Marlene on 11/18/2017.
 */

public class ThemeHelper {
    //Fields
    private SharedPreferences sharedPref;
    private int themeLightBg;
    private int themeDarkBg;


    //Constructors
    public ThemeHelper(Context context){}


    //Methods
    public boolean darkThemeEnabled() {
        return false;
    }

    public void enableDarkTheme(boolean enabled) {}

    public void themeTextView(TextView...textviews) {}

    public void themeImageContainer(View...containers) {}

    public void themeBackground(View rootview) {}


}
