// Generated code from Icepick. Do not modify!
package com.mngh.floattube.fragments.subscription;
import android.os.Bundle;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;

import java.util.Map;
import java.util.HashMap;

public class SubscriptionFragment$$Icepick<T extends SubscriptionFragment> extends com.mngh.floattube.fragments.BaseStateFragment$$Icepick<T> {

  private final static Map<String, Bundler<?>> BUNDLERS = new HashMap<String, Bundler<?>>();
  static {
        
  }

  private final static Helper H = new Helper("com.mngh.floattube.fragments.subscription.SubscriptionFragment$$Icepick.", BUNDLERS);

  public void restore(T target, Bundle state) {
    if (state == null) return;
    target.itemsListState = H.getParcelable(state, "itemsListState");
    target.importExportOptionsState = H.getParcelable(state, "importExportOptionsState");
    super.restore(target, state);
  }

  public void save(T target, Bundle state) {
    super.save(target, state);
    H.putParcelable(state, "itemsListState", target.itemsListState);
    H.putParcelable(state, "importExportOptionsState", target.importExportOptionsState);
  }
}