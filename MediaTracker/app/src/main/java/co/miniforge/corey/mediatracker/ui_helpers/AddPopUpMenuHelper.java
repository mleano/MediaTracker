package co.miniforge.corey.mediatracker.ui_helpers;

import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;

import co.miniforge.corey.mediatracker.MyListActivity;
import co.miniforge.corey.mediatracker.R;
import co.miniforge.corey.mediatracker.model.MediaItem;
import co.miniforge.corey.mediatracker.model.MediaItemType;

/**
 * Created by Marlene on 11/19/2017.
 */

public class AddPopUpMenuHelper implements PopupMenu.OnMenuItemClickListener {

    MyListActivity activity;

    public AddPopUpMenuHelper(MyListActivity activity){
        this.activity = activity;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.generic:
                activity.addMediaItem(new MediaItem(MediaItemType.Generic));
                break;
            case R.id.tv:
                activity.addMediaItem(new MediaItem(MediaItemType.TV));
                break;
            case R.id.movie:
                activity.addMediaItem(new MediaItem(MediaItemType.Movie));
                break;
            case R.id.youtube:
                activity.addMediaItem(new MediaItem(MediaItemType.YouTube));
                break;
        }

        return true;
    }
}