package com.example.jnc.testDemo;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.SystemProperties;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import javax.security.auth.callback.Callback;

import de.nik.android.mmsdk.IXposedHookLoadPackage;
import de.nik.android.mmsdk.XC_MethodHook;
import de.nik.android.mmsdk.XposedHelpers;
import de.nik.android.mmsdk.callbacks.XC_LoadPackage;
import de.nik.android.mmsdk.mmsdkBridge;

public class hookMusic implements IXposedHookLoadPackage {


    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if ("cmccwm.mobilemusic".equals(loadPackageParam.packageName)){
            mmsdkBridge.log("loadPackageName:"+loadPackageParam.packageName);
           // XposedHelpers.setStaticObjectField(Build.class,"");
            XposedHelpers.findAndHookMethod(Application.class , "attach",Context.class, new XC_MethodHook() {
                //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                   /* ClassLoader cl = ((Context) param.args[0]).getClassLoader();
                    Class<?> hookClass = null;*/

                    XposedHelpers.findAndHookMethod("org.json.JSONObject", loadPackageParam.classLoader, "toString", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("--------openLog action --------");
                            mmsdkBridge.log("==hook==[JSONObject_toString]" + param.getResult());
                        }
                    });
                    XposedHelpers.findAndHookMethod("org.json.JSONArray", loadPackageParam.classLoader, "toString", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("==hook==[JSONArray_toString]" + param.getResult());

                        }
                    });
                   //旧音乐
                 /*  XposedHelpers.findAndHookMethod("cmccwm.mobilemusic.renascence.data.entity.UICard",loadPackageParam.classLoader,"getImageUrl",new XC_MethodHook(){
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[org_getImageUrl]"+param.getResult());
                            if (param.getResult()!=null && !"".equals(param.getResult())){
                                param.setResult("http://172.17.3.11:8080/automate/download/default.jpg");
                                mmsdkBridge.log("[hook_getImageUrl]"+param.getResult());
                            }
                        }
                    });*/

                    /*XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.playdetail.PlayDetailActivity$16",loadPackageParam.classLoader,"playVideo", new XC_MethodHook(){
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[org_playVideo]"+param.getResult());
                        }
                    });
*/

                    //新音乐
                    /*XposedHelpers.findAndHookMethod("cmccwm.mobilemusic.bean.RadioStationBean.Item",loadPackageParam.classLoader,"getImageUrl", new XC_MethodHook(){
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[org_Item_getImageUrl]"+param.getResult());
                            if (param.getResult()!=null && !"".equals(param.getResult())){
                                param.setResult("http://172.17.3.11:8080/automate/download/default.jpg");
                                mmsdkBridge.log("[org_Item_getImageUrl]"+param.getResult());
                            }
                        }
                    });
                    XposedHelpers.findAndHookMethod("cmccwm.mobilemusic.bean.ImgItem",loadPackageParam.classLoader,"getImg", new XC_MethodHook(){
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[org_getImg]"+param.getResult());
                            if (param.getResult()!=null && !"".equals(param.getResult())){
                                if (param.getResult().toString().endsWith(".jpg")){
                                    param.setResult("http://172.17.3.11:8080/automate/download/default.jpg");
                                }else{
                                    param.setResult("http://172.17.3.11:8080/automate/download/default.png");
                                }
                                mmsdkBridge.log("[hook_getImg]"+param.getResult());
                            }
                        }
                    });
                    XposedHelpers.findAndHookMethod("com.migu.bizz_v2.uicard.entity.UICard",loadPackageParam.classLoader,"getImageUrl",new XC_MethodHook(){
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[org_getImageUrl]"+param.getResult());
                            if (param.getResult()!=null && !"".equals(param.getResult())){
                                param.setResult("http://172.17.3.11:8080/automate/download/default.jpg");
                                mmsdkBridge.log("[hook_getImageUrl]"+param.getResult());
                            }
                        }
                    });

                    //hook音乐活动里的图片
                    XposedHelpers.findAndHookMethod("cmccwm.mobilemusic.bean.musiclibgson.GsonColumnInfo", loadPackageParam.classLoader, "getColumnPicUrl",new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_GsonColumnInfo_getColumnPicUrl]" + param.getResult());
                            if (param.getResult()!=null && !"".equals(param.getResult())){
                                param.setResult("http://172.17.3.11:8080/automate/download/default.jpg");
                                mmsdkBridge.log("[hook_GsonColumnInfo_getColumnPicUrl]"+param.getResult());
                            }
                        }
                    });
                    XposedHelpers.findAndHookMethod("cmccwm.mobilemusic.ui.h5.H5WebInFragment", loadPackageParam.classLoader, "showRangeTips",String.class,new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_H5WebInFragment_showRangeTips]" + param.getResult());
                            if (param.getResult()!=null && !"".equals(param.getResult())){
                                param.setResult("http://172.17.3.11:8080/automate/download/default.jpg");
                                mmsdkBridge.log("[hook_H5WebInFragment_showRangeTips]"+param.getResult() +",[arg[0]]"+param.args[0]);
                            }
                        }
                    });*/
                    XposedHelpers.findAndHookMethod("android.webkit.WebView", loadPackageParam.classLoader, "loadUrl",String.class,
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    String pay_content_id = SystemProperties.get("hook_pay_content_id");
                                    mmsdkBridge.log("[org_WebView]"+param.args[0]);
                                    if (param.args[0]!=null && !"".equals(param.args[0])){ //http://a.mll.migu.cn/app/v2/zt/2017/random-listen/location.html?ua=Android_Sst&version=4.3110
                                        if (param.args[0].toString().contains("http://h5.nf.migu.cn/app/v2/zt/2017/random-listen/location.html?ua=Android_Sst&version")){
                                            param.args[0] = "https://h5.nf.migu.cn/app/v3/p/digital-album/list-pwa/index.html";
                                        }
                                        if (pay_content_id!=null && param.args[0].toString().contains("https://h5.nf.migu.cn/app/v3/p/digital-album/detail/index.html")){
                                            param.args[0] = "https://h5.nf.migu.cn/app/v3/p/digital-album/detail/index.html?id="+SystemProperties.get("hook_pay_content_id")+"&ua=Android_Sst&version=4.3110";
                                            mmsdkBridge.log("[hook_WebView] 成功 "+param.args[0]);
                                        }
                                    }
                                }
                            }
                    );
                   /* XposedHelpers.findAndHookMethod("com.migu.bizanalytics.LogFileManager", loadPackageParam.classLoader, "writeLog", String.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            mmsdkBridge.log("[org_LogFileManager]params:"+param.args[0]+"\t"+param.getResult()+"\t");
                            JSONObject json = new JSONObject((String) param.args[0]);
                            JSONObject params=json.getJSONObject("params");
                            String url = (String) json.getJSONArray("stack").getJSONObject(1).get("params");
                            json.put("params",params);
                            mmsdkBridge.log("[_LogFileManager]params:"+param.args[0]+"\t"+param.getResult()+"\t"+"url: "+url);
                            *//*String url ="https://h5.nf.migu.cn/app/v3/p/digital-album/list-pwa/index.html";
                            json.put("url",url);
                            mmsdkBridge.log("[hook]params: "+param.args[0]+"\t"+param.getResult()+"\t"+"url: "+url);*//*
                        }
                    });*/
                    //处理WebView的
                   /* XposedHelpers.findAndHookMethod("android.webkit.WebView", loadPackageParam.classLoader, "loadUrl",String.class, Map.class,
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    //mmsdkBridge.log("[before_WebView]"+param.args[0]+" \t"+param.getResult());//
                                    try{
                                        throw new Exception();
                                    }catch (Exception e){
                                        mmsdkBridge.log("[before_WebView2]"+param.args[0]+" \t"+param.args[1]+" \t"+param.getResult());}
                                }
                            }
                    );

                    XposedHelpers.findAndHookMethod("android.webkit.WebView", loadPackageParam.classLoader, "loadUrl",String.class,
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    //mmsdkBridge.log("[before_WebView]"+param.args[0]+" \t"+param.getResult());
                                    try{
                                        throw new Exception();
                                    }catch (Exception e){
                                        mmsdkBridge.log("[before_WebView]"+param.args[0]+" \t"+param.getResult());
                                    }
                                }
                            }
                    );*/

                }
            });
        }
    }
}
