package co.miniforge.corey.mediatracker.ui_helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import co.miniforge.corey.mediatracker.R;

/**
 * Created by Marlene on 11/18/2017.
 */

public class ThemeHelper {
    //Fields
    private SharedPreferences sharedPref;
    private int themeLightBg;
    private int themeLightContainer;
    private int themeLightText;
    private int themeDarkBg;
    private int themeDarkContainer;
    private int themeDarkText;


    //Constructors
    public ThemeHelper(Context context){
        try{
            themeLightBg = ContextCompat.getColor(context, R.color.themeLightBg);
            themeLightContainer = ContextCompat.getColor(context, R.color.themeLightContainer);
            themeLightText = ContextCompat.getColor(context, R.color.themeLightText);
            themeDarkBg = ContextCompat.getColor(context, R.color.themeDarkBg);
            themeDarkContainer = ContextCompat.getColor(context, R.color.themeDarkContainer);
            themeDarkText = ContextCompat.getColor(context, R.color.themeDarkText);

            //This stores a simple key/value pair for the current theme
            sharedPref = context.getSharedPreferences("theme", 0);
        } catch (Exception e) {

        }
    }


    //Methods
    public boolean darkThemeEnabled(){
        return sharedPref.getBoolean("darkTheme", false);
    }

    public void enableDarkTheme(boolean enabled){
        sharedPref.edit().putBoolean("darkTheme", enabled).apply();
    }

    public void themeTextView(TextView...textviews) {
        boolean dark = darkThemeEnabled();

        for(TextView textView : textviews) {
            textView.setTextColor(dark ? themeDarkText : themeLightText);
        }
    }

    public void themeImageContainer(View...containers) {
        boolean dark = darkThemeEnabled();

        for(View view : containers) {
            view.setBackgroundColor(dark ? themeDarkContainer : themeLightContainer);
        }
    }

    public void themeBackground(View rootview) {
        boolean dark = darkThemeEnabled();

        rootview.setBackgroundColor(dark ? themeDarkBg : themeLightBg);
    }


}
