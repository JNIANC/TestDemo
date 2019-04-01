package com.example.jnc.testDemo;

import android.app.Application;
import android.content.Context;

import java.util.ConcurrentModificationException;

import de.nik.android.mmsdk.IXposedHookLoadPackage;
import de.nik.android.mmsdk.XC_MethodHook;
import de.nik.android.mmsdk.XposedHelpers;
import de.nik.android.mmsdk.callbacks.XC_LoadPackage;
import de.nik.android.mmsdk.mmsdkBridge;

public class hookGames implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("com.tencent.tmgp.sgame")){
            mmsdkBridge.log("loadPackageName:"+loadPackageParam.packageName);
            XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                }
            });
        }

    }
}
