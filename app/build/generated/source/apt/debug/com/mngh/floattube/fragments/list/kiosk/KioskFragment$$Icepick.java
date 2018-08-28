// Generated code from Icepick. Do not modify!
package com.mngh.floattube.fragments.list.kiosk;
import android.os.Bundle;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;

import java.util.Map;
import java.util.HashMap;

public class KioskFragment$$Icepick<T extends KioskFragment> extends com.mngh.floattube.fragments.list.BaseListInfoFragment$$Icepick<T> {

  private final static Map<String, Bundler<?>> BUNDLERS = new HashMap<String, Bundler<?>>();
  static {
      
  }

  private final static Helper H = new Helper("com.mngh.floattube.fragments.list.kiosk.KioskFragment$$Icepick.", BUNDLERS);

  public void restore(T target, Bundle state) {
    if (state == null) return;
    target.kioskId = H.getString(state, "kioskId");
    super.restore(target, state);
  }

  public void save(T target, Bundle state) {
    super.save(target, state);
    H.putString(state, "kioskId", target.kioskId);
  }
}