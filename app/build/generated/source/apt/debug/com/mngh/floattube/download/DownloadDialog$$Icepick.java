// Generated code from Icepick. Do not modify!
package com.mngh.floattube.download;
import android.os.Bundle;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;

import java.util.Map;
import java.util.HashMap;

public class DownloadDialog$$Icepick<T extends DownloadDialog> extends Object<T> {

  private final static Map<String, Bundler<?>> BUNDLERS = new HashMap<String, Bundler<?>>();
  static {
              
  }

  private final static Helper H = new Helper("com.mngh.floattube.download.DownloadDialog$$Icepick.", BUNDLERS);

  public void restore(T target, Bundle state) {
    if (state == null) return;
    target.currentInfo = H.getSerializable(state, "currentInfo");
    target.wrappedAudioStreams = H.getSerializable(state, "wrappedAudioStreams");
    target.wrappedVideoStreams = H.getSerializable(state, "wrappedVideoStreams");
    target.selectedVideoIndex = H.getInt(state, "selectedVideoIndex");
    target.selectedAudioIndex = H.getInt(state, "selectedAudioIndex");
    super.restore(target, state);
  }

  public void save(T target, Bundle state) {
    super.save(target, state);
    H.putSerializable(state, "currentInfo", target.currentInfo);
    H.putSerializable(state, "wrappedAudioStreams", target.wrappedAudioStreams);
    H.putSerializable(state, "wrappedVideoStreams", target.wrappedVideoStreams);
    H.putInt(state, "selectedVideoIndex", target.selectedVideoIndex);
    H.putInt(state, "selectedAudioIndex", target.selectedAudioIndex);
  }
}