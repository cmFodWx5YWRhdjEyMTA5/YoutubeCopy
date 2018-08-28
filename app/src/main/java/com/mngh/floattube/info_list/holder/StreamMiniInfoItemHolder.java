package com.mngh.floattube.info_list.holder;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mngh.floattube.info_list.InfoItemBuilder;
import com.mngh.floattube.util.ImageDisplayConstants;
import com.mngh.floattube.util.Localization;
import com.mngh.floattube.info_list.InfoItemBuilder;
import com.mngh.floattube.util.ImageDisplayConstants;
import com.mngh.floattube.util.Localization;

import com.mngh.floattube.R;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.stream.StreamType;
import com.mngh.floattube.info_list.InfoItemBuilder;
import com.mngh.floattube.util.ImageDisplayConstants;
import com.mngh.floattube.util.Localization;

public class StreamMiniInfoItemHolder extends InfoItemHolder {

    public final ImageView itemThumbnailView;
    public final TextView itemVideoTitleView;
    public final TextView itemUploaderView;
    public final TextView itemDurationView;

    StreamMiniInfoItemHolder(InfoItemBuilder infoItemBuilder, int layoutId, ViewGroup parent) {
        super(infoItemBuilder, layoutId, parent);

        itemThumbnailView = itemView.findViewById(com.mngh.floattube.R.id.itemThumbnailView);
        itemVideoTitleView = itemView.findViewById(com.mngh.floattube.R.id.itemVideoTitleView);
        itemUploaderView = itemView.findViewById(com.mngh.floattube.R.id.itemUploaderView);
        itemDurationView = itemView.findViewById(com.mngh.floattube.R.id.itemDurationView);
    }

    public StreamMiniInfoItemHolder(InfoItemBuilder infoItemBuilder, ViewGroup parent) {
        this(infoItemBuilder, com.mngh.floattube.R.layout.list_stream_mini_item, parent);
    }

    @Override
    public void updateFromItem(final InfoItem infoItem) {
        if (!(infoItem instanceof StreamInfoItem)) return;
        final StreamInfoItem item = (StreamInfoItem) infoItem;

        itemVideoTitleView.setText(item.getName());
        itemUploaderView.setText(item.getUploaderName());

        if (item.getDuration() > 0) {
            itemDurationView.setText(Localization.getDurationString(item.getDuration()));
            itemDurationView.setBackgroundColor(ContextCompat.getColor(itemBuilder.getContext(),
                    com.mngh.floattube.R.color.duration_background_color));
            itemDurationView.setVisibility(View.VISIBLE);
        } else if (item.getStreamType() == StreamType.LIVE_STREAM) {
            itemDurationView.setText(com.mngh.floattube.R.string.duration_live);
            itemDurationView.setBackgroundColor(ContextCompat.getColor(itemBuilder.getContext(),
                    com.mngh.floattube.R.color.live_duration_background_color));
            itemDurationView.setVisibility(View.VISIBLE);
        } else {
            itemDurationView.setVisibility(View.GONE);
        }

        // Default thumbnail is shown on error, while loading and if the url is empty
        itemBuilder.getImageLoader()
                .displayImage(item.getThumbnailUrl(),
                        itemThumbnailView,
                        ImageDisplayConstants.DISPLAY_THUMBNAIL_OPTIONS);

        itemView.setOnClickListener(view -> {
            if (itemBuilder.getOnStreamSelectedListener() != null) {
                itemBuilder.getOnStreamSelectedListener().selected(item);
            }
        });

        switch (item.getStreamType()) {
            case AUDIO_STREAM:
            case VIDEO_STREAM:
            case LIVE_STREAM:
            case AUDIO_LIVE_STREAM:
                enableLongClick(item);
                break;
            case FILE:
            case NONE:
            default:
                disableLongClick();
                break;
        }
    }

    private void enableLongClick(final StreamInfoItem item) {
        itemView.setLongClickable(true);
        itemView.setOnLongClickListener(view -> {
            if (itemBuilder.getOnStreamSelectedListener() != null) {
                itemBuilder.getOnStreamSelectedListener().held(item);
            }
            return true;
        });
    }

    private void disableLongClick() {
        itemView.setLongClickable(false);
        itemView.setOnLongClickListener(null);
    }
}
