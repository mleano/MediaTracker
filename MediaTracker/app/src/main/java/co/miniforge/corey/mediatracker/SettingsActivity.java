package co.miniforge.corey.mediatracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import co.miniforge.corey.mediatracker.ui_helpers.ThemeHelper;

public class SettingsActivity extends AppCompatActivity {
    //Fields
    ThemeHelper themeHelper;
    Button toggleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Initialize ThemeHelper
        themeHelper = new ThemeHelper(getApplicationContext());

        //Store a reference to the toggle theme button
        toggleBtn = (Button) findViewById(R.id.theme_toggle_btn);

        //When the toggle button is clicked, change the theme
        toggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeHelper.enableDarkTheme(!themeHelper.darkThemeEnabled());
            }
        });
    }
}
