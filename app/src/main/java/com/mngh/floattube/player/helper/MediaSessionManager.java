package com.mngh.floattube.player.helper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.KeyEvent;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector;
import com.mngh.floattube.player.mediasession.DummyPlaybackPreparer;
import com.mngh.floattube.player.mediasession.MediaSessionCallback;
import com.mngh.floattube.player.mediasession.PlayQueueNavigator;
import com.mngh.floattube.player.mediasession.PlayQueuePlaybackController;
import com.mngh.floattube.player.mediasession.DummyPlaybackPreparer;
import com.mngh.floattube.player.mediasession.MediaSessionCallback;
import com.mngh.floattube.player.mediasession.PlayQueueNavigator;
import com.mngh.floattube.player.mediasession.PlayQueuePlaybackController;

import com.mngh.floattube.player.mediasession.DummyPlaybackPreparer;
import com.mngh.floattube.player.mediasession.MediaSessionCallback;
import com.mngh.floattube.player.mediasession.PlayQueueNavigator;
import com.mngh.floattube.player.mediasession.PlayQueuePlaybackController;

public class MediaSessionManager {
    private static final String TAG = "MediaSessionManager";

    @NonNull private final MediaSessionCompat mediaSession;
    @NonNull private final MediaSessionConnector sessionConnector;

    public MediaSessionManager(@NonNull final Context context,
                               @NonNull final Player player,
                               @NonNull final MediaSessionCallback callback) {
        this.mediaSession = new MediaSessionCompat(context, TAG);
        this.sessionConnector = new MediaSessionConnector(mediaSession,
                new PlayQueuePlaybackController(callback));
        this.sessionConnector.setQueueNavigator(new PlayQueueNavigator(mediaSession, callback));
        this.sessionConnector.setPlayer(player, new DummyPlaybackPreparer());
    }

    @Nullable
    @SuppressWarnings("UnusedReturnValue")
    public KeyEvent handleMediaButtonIntent(final Intent intent) {
        return MediaButtonReceiver.handleIntent(mediaSession, intent);
    }
}
