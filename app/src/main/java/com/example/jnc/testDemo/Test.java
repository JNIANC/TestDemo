package com.example.jnc.testDemo;

import android.telephony.TelephonyManager;

import de.nik.android.mmsdk.IXposedHookLoadPackage;
import de.nik.android.mmsdk.XC_MethodReplacement;
import de.nik.android.mmsdk.XposedHelpers;
import de.nik.android.mmsdk.callbacks.XC_LoadPackage;


/**
 * Created by JNC on 2018/5/17.
 */

public  class Test implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
          if (!loadPackageParam.packageName.contains("com.example.jnc.testDemo"))
              return;
        XposedHelpers.findAndHookMethod(TelephonyManager.class, "getSubscriberId", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                return "Hook success";
            }
        });
    }
}

