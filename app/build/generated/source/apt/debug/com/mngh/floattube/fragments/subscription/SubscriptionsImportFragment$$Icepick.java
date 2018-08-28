// Generated code from Icepick. Do not modify!
package com.mngh.floattube.fragments.subscription;
import android.os.Bundle;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;

import java.util.Map;
import java.util.HashMap;

public class SubscriptionsImportFragment$$Icepick<T extends SubscriptionsImportFragment> extends Object<T> {

  private final static Map<String, Bundler<?>> BUNDLERS = new HashMap<String, Bundler<?>>();
  static {
      
  }

  private final static Helper H = new Helper("com.mngh.floattube.fragments.subscription.SubscriptionsImportFragment$$Icepick.", BUNDLERS);

  public void restore(T target, Bundle state) {
    if (state == null) return;
    target.currentServiceId = H.getInt(state, "currentServiceId");
    super.restore(target, state);
  }

  public void save(T target, Bundle state) {
    super.save(target, state);
    H.putInt(state, "currentServiceId", target.currentServiceId);
  }
}