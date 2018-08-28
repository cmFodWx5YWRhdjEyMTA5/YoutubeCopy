// Generated code from Icepick. Do not modify!
package com.mngh.floattube.history;
import android.os.Bundle;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;

import java.util.Map;
import java.util.HashMap;

public class HistoryFragment$$Icepick<T extends HistoryFragment> extends Object<T> {

  private final static Map<String, Bundler<?>> BUNDLERS = new HashMap<String, Bundler<?>>();
  static {
      
  }

  private final static Helper H = new Helper("com.mngh.floattube.history.HistoryFragment$$Icepick.", BUNDLERS);

  public void restore(T target, Bundle state) {
    if (state == null) return;
    target.mRecyclerViewState = H.getParcelable(state, "mRecyclerViewState");
    super.restore(target, state);
  }

  public void save(T target, Bundle state) {
    super.save(target, state);
    H.putParcelable(state, "mRecyclerViewState", target.mRecyclerViewState);
  }
}