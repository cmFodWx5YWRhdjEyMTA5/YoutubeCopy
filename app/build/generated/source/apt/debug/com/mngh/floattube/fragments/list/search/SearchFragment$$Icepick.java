// Generated code from Icepick. Do not modify!
package com.mngh.floattube.fragments.list.search;
import android.os.Bundle;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;

import java.util.Map;
import java.util.HashMap;

public class SearchFragment$$Icepick<T extends SearchFragment> extends com.mngh.floattube.fragments.BaseStateFragment$$Icepick<T> {

  private final static Map<String, Bundler<?>> BUNDLERS = new HashMap<String, Bundler<?>>();
  static {
              
  }

  private final static Helper H = new Helper("com.mngh.floattube.fragments.list.search.SearchFragment$$Icepick.", BUNDLERS);

  public void restore(T target, Bundle state) {
    if (state == null) return;
    target.filterItemCheckedId = H.getInt(state, "filterItemCheckedId");
    target.serviceId = H.getInt(state, "serviceId");
    target.searchQuery = H.getString(state, "searchQuery");
    target.lastSearchedQuery = H.getString(state, "lastSearchedQuery");
    target.wasSearchFocused = H.getBoolean(state, "wasSearchFocused");
    super.restore(target, state);
  }

  public void save(T target, Bundle state) {
    super.save(target, state);
    H.putInt(state, "filterItemCheckedId", target.filterItemCheckedId);
    H.putInt(state, "serviceId", target.serviceId);
    H.putString(state, "searchQuery", target.searchQuery);
    H.putString(state, "lastSearchedQuery", target.lastSearchedQuery);
    H.putBoolean(state, "wasSearchFocused", target.wasSearchFocused);
  }
}