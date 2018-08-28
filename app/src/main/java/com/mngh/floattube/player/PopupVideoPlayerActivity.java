package com.mngh.floattube.player;

import android.content.Intent;
import android.view.MenuItem;

import com.mngh.floattube.R;

import static com.mngh.floattube.player.PopupVideoPlayer.ACTION_CLOSE;

public final class PopupVideoPlayerActivity extends ServicePlayerActivity {

    private static final String TAG = "PopupVideoPlayerActivity";

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public String getSupportActionTitle() {
        return getResources().getString(com.mngh.floattube.R.string.title_activity_popup_player);
    }

    @Override
    public Intent getBindIntent() {
        return new Intent(this, PopupVideoPlayer.class);
    }

    @Override
    public void startPlayerListener() {
        if (player != null && player instanceof PopupVideoPlayer.VideoPlayerImpl) {
            ((PopupVideoPlayer.VideoPlayerImpl) player).setActivityListener(this);
        }
    }

    @Override
    public void stopPlayerListener() {
        if (player != null && player instanceof PopupVideoPlayer.VideoPlayerImpl) {
            ((PopupVideoPlayer.VideoPlayerImpl) player).removeActivityListener(this);
        }
    }

    @Override
    public int getPlayerOptionMenuResource() {
        return com.mngh.floattube.R.menu.menu_play_queue_popup;
    }

    @Override
    public boolean onPlayerOptionSelected(MenuItem item) {
        if (item.getItemId() == com.mngh.floattube.R.id.action_switch_background) {
            this.player.setRecovery();
            getApplicationContext().sendBroadcast(getPlayerShutdownIntent());
            getApplicationContext().startService(getSwitchIntent(BackgroundPlayer.class));
            return true;
        }
        return false;
    }

    @Override
    public Intent getPlayerShutdownIntent() {
        return new Intent(PopupVideoPlayer.ACTION_CLOSE);
    }
}
