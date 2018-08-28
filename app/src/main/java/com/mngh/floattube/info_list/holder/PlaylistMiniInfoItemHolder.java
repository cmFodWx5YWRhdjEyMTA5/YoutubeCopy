package com.mngh.floattube.info_list.holder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mngh.floattube.util.ImageDisplayConstants;
import com.mngh.floattube.util.ImageDisplayConstants;

import com.mngh.floattube.R;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.playlist.PlaylistInfoItem;
import com.mngh.floattube.info_list.InfoItemBuilder;
import com.mngh.floattube.util.ImageDisplayConstants;

public class PlaylistMiniInfoItemHolder extends InfoItemHolder {
    public final ImageView itemThumbnailView;
    public final TextView itemStreamCountView;
    public final TextView itemTitleView;
    public final TextView itemUploaderView;

    public PlaylistMiniInfoItemHolder(InfoItemBuilder infoItemBuilder, int layoutId, ViewGroup parent) {
        super(infoItemBuilder, layoutId, parent);

        itemThumbnailView = itemView.findViewById(com.mngh.floattube.R.id.itemThumbnailView);
        itemTitleView = itemView.findViewById(com.mngh.floattube.R.id.itemTitleView);
        itemStreamCountView = itemView.findViewById(com.mngh.floattube.R.id.itemStreamCountView);
        itemUploaderView = itemView.findViewById(com.mngh.floattube.R.id.itemUploaderView);
    }

    public PlaylistMiniInfoItemHolder(InfoItemBuilder infoItemBuilder, ViewGroup parent) {
        this(infoItemBuilder, com.mngh.floattube.R.layout.list_playlist_mini_item, parent);
    }

    @Override
    public void updateFromItem(final InfoItem infoItem) {
        if (!(infoItem instanceof PlaylistInfoItem)) return;
        final PlaylistInfoItem item = (PlaylistInfoItem) infoItem;

        itemTitleView.setText(item.getName());
        itemStreamCountView.setText(String.valueOf(item.getStreamCount()));
        itemUploaderView.setText(item.getUploaderName());

        itemBuilder.getImageLoader()
                .displayImage(item.getThumbnailUrl(), itemThumbnailView,
                		ImageDisplayConstants.DISPLAY_THUMBNAIL_OPTIONS);

        itemView.setOnClickListener(view -> {
            if (itemBuilder.getOnPlaylistSelectedListener() != null) {
                itemBuilder.getOnPlaylistSelectedListener().selected(item);
            }
        });

        itemView.setLongClickable(true);
        itemView.setOnLongClickListener(view -> {
            if (itemBuilder.getOnPlaylistSelectedListener() != null) {
                itemBuilder.getOnPlaylistSelectedListener().held(item);
            }
            return true;
        });
    }
}
