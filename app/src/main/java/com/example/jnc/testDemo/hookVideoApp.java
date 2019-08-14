package com.example.jnc.testDemo;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import de.nik.android.mmsdk.IXposedHookLoadPackage;
import de.nik.android.mmsdk.XC_MethodHook;
import de.nik.android.mmsdk.XposedHelpers;
import de.nik.android.mmsdk.callbacks.XC_LoadPackage;
import de.nik.android.mmsdk.mmsdkBridge;

public class hookVideoApp implements IXposedHookLoadPackage {

    /*public int config(Context context){
        int screentConfig = context.getResources().getConfiguration().screenLayout;
        if (screentConfig<=0){
            System.out.println("获取屏幕失败");
        }
        return screentConfig;
    }*/
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        //int screentConfig =0;
        if ("com.cmcc.cmvideo".equals(loadPackageParam.packageName)){

            mmsdkBridge.log("loadPackageName:"+loadPackageParam.packageName);
            //mmsdkBridge.log("screentConfig: "+screenConfig);

 /*              String  activityName = "com.secneo.apkwrapper.ApplicationWrapper";

         XposedHelpers.findAndHookMethod(activityName, loadPackageParam.classLoader, "onCreate",new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam mhp) throws Throwable {

                    mmsdkBridge.log("findbeforeHookMethod success=");
                    //System.load("/system/lib64/libInlineHook.so");
                    //System.load("/data/app/"+lpp.packageName+"-2/lib/arm/libkis.so");
                    //System.load("/data/app/com.cmcc.cmvideo-1/lib/arm/libkis.so");
                    //System.load("/data/app/cmccwm.mobilemusic-1/lib/arm/libkis.so");
                    System.load("/data/app/"+loadPackageParam.packageName+"-1/lib/arm/libkis.so");
                    mmsdkBridge.log("load success=");
                    super.beforeHookedMethod(mhp);
                }
                @Override
                protected void afterHookedMethod(MethodHookParam mhp) throws Throwable {
                    mmsdkBridge.log("findafterHookMethod success");

                    super.afterHookedMethod(mhp);

                }
            });*/
            XposedHelpers.findAndHookMethod(Application.class , "attach",Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                   /* //int screentConfig =0;
                    //有用的
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

                   XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.foundation.fresco.MGSimpleDraweeView", loadPackageParam.classLoader, "setImageURI",Uri.class,int.class,int.class,Object.class,new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_MGSimpleDraweeView_setImageURI4]" + param.getResult()+",[arg[0]]"+param.args[0]+",[arg[1]] "+param.args[1]+",[arg[2]] "+param.args[2]+",[arg[3]] "+param.args[3]);
                            if(param.args[0]!=null && !"".equals(param.args[0])){
                                if (param.args[0].toString().endsWith(".jpg")){
                                    param.args[0]=Uri.parse("http://172.17.3.11:8080/automate/download/default.jpg");
                                }else{
                                    param.args[0]=Uri.parse("http://172.17.3.11:8080/automate/download/default.png");
                                }
                                mmsdkBridge.log("[hook_setImageURI4]"+param.getResult()+",[arg[0]]"+param.args[0]+",[arg[1]] "+param.args[1]+",[arg[2]] "+param.args[2]+",[arg[3]] "+param.args[3]);
                            }
                        }
                    });*/

                   /* XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.foundation.player.bean.VideoBean", loadPackageParam.classLoader, "getNodeId",new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_VideoBean_getNodeId]" + param.getResult());
                            if (param.getResult()==null || "".equals(param.getResult())){
                                param.setResult("123456");
                            }
                        }
                    });

                    //VOD
                    XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.foundation.player.bean.VideoBean", loadPackageParam.classLoader, "getType",new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_VideoBean_getType]" + param.getResult());
                        }
                    });*/
                    /*XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.foundation.player.model.VideoDetailsObject", loadPackageParam.classLoader, "updateVideoInfo",XposedHelpers.findClass("com.cmcc.cmvideo.foundation.player.bean.VideoBean",loadPackageParam.classLoader),new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_VideoDetailsObject_updateVideoInfo]" + param.getResult());
                        }
                    });
                    XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.player.PlayHelper", loadPackageParam.classLoader, "dataObjectChanged",XposedHelpers.findClass("com.cmcc.cmvideo.foundation.network.BaseObject",loadPackageParam.classLoader),int.class,new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_PlayHelper_dataObjectChanged]" + param.getResult()+",[arg[0]]"+param.args[0]+",[arg[1]]"+param.args[1]);
                        }
                    });

                    XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.foundation.network.ACCache", loadPackageParam.classLoader, "putResponse",String.class,long.class,String.class,String.class,new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_ACCache_putResponse]" + param.getResult() +",[arg[0]]"+param.args[0]+",[arg[1]]"+param.args[1]+",[arg[2]]"+param.args[2]+",[arg[3]]"+param.args[3]);
                        }
                    });*/

                    /*XposedHelpers.findAndHookConstructor("com.miguplayer.player.sqm.e.a", loadPackageParam.classLoader,String.class, int.class, String.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            if ("MG_MSG_MEDIA_INFO_VIDEO_RESOLUTION".equals(param.args[0])){
                                try{
                                    throw new Exception("分辨率错误");
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

                    XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.foundation.login.uesr.UserService", loadPackageParam.classLoader, "isVip",XposedHelpers.findClass("com.cmcc.cmvideo.foundation.login.bean.User",loadPackageParam.classLoader),new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_UserService_isVip]" + param.getResult() +",[arg[0]]"+param.args[0]);
                            if (param.getResult().toString().equals("false")){
                                param.setResult("true");
                                mmsdkBridge.log("[hook_UserService_isVip]" + param.getResult() +",[arg[0]]"+param.args[0]);
                            }

                        }
                    });*/
                    XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.foundation.player.bean.VideoBean", loadPackageParam.classLoader, "getUrlType",new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_VideoBean_getUrlType]" + param.getResult());
                            String[] randUser = new String[]{"normal","tourist"};
                            if(param.getResult()==null || "trial".equals(param.getResult())){
                                param.setResult(randUser[new Random().nextInt(randUser.length)]);
                                mmsdkBridge.log("[hook_VideoBean_getUrlType]" + param.getResult());
                            }
                        }
                    });
                    XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.layout.mainfragment.HotLineSection$3", loadPackageParam.classLoader, "dataObjectFailed",XposedHelpers.findClass("com.cmcc.cmvideo.foundation.network.BaseObject",loadPackageParam.classLoader),int.class,JSONObject.class,new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_HotLineSection$3_dataObjectFailed]" + param.getResult()+" arg[0]: "+param.args[0]);
                        }
                    });
                    /*XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.foundation.login.bean.User", loadPackageParam.classLoader, "getUserType",new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_User_getUserType]" + param.getResult());

                        }
                    });*/

                   /*
                   XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.player.presenter.impl.TvUniteControlPresenterImpl", loadPackageParam.classLoader, "startRefreshView",new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_TvUniteControlPresenterImpl_startRefreshView]" + param.getResult());
                        }
                    });
                    XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.player.presenter.impl.TvUniteControlPresenterImpl", loadPackageParam.classLoader, "startLoading",new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_TvUniteControlPresenterImpl_startLoading]" + param.getResult());
                        }
                    });
                   XposedHelpers.findAndHookMethod("com.migu.a.f", loadPackageParam.classLoader, "c",Context.class,new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_f_c]" + param.getResult() +",[arg[0]]"+param.args[0]);
                            if (!"0".equals(param.getResult())){
                                param.setResult("0");
                                mmsdkBridge.log("[hook_f_c]" + param.getResult() +",[arg[0]]"+param.args[0]);
                            }
                        }
                    });
                     XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.foundation.player.MiGuPlayer", loadPackageParam.classLoader, "setPlayerState",int.class,new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_MiGuPlayer_setPlayerState]" + param.getResult() +",[arg[0]]"+param.args[0]);
                        }
                    });
                    */

                    //setVideoAd(String str)
                    //skipAd()
                   /* XposedHelpers.findAndHookMethod("com.growingio.android.sdk.collection.VdsJsBridgeManager", loadPackageParam.classLoader, "hookWebViewIfNeeded",View.class,new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_VdsJsBridgeManager_hookWebViewIfNeeded]" + param.getResult()+",[arg[0]]"+param.args[0]);
                            *//*if(param.args[0]!=null && !"".equals(param.args[0])){
                                if (param.args[0].toString().endsWith(".jpg")){
                                    param.args[0]=Uri.parse("http://172.17.3.11:8080/automate/download/default.jpg");
                                }else{
                                    param.args[0]=Uri.parse("http://172.17.3.11:8080/automate/download/default.png");
                                }
                                mmsdkBridge.log("[hook_setImageURI4]"+param.getResult()+",[arg[0]]"+param.args[0]+",[arg[1]] "+param.args[1]+",[arg[2]] "+param.args[2]+",[arg[3]] "+param.args[3]);
                            }*//*
                        }
                    });*/
                    /*
                     XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.foundation.player.MiGuPlayer",loadPackageParam.classLoader,"setVideo",XposedHelpers.findClass("com.cmcc.cmvideo.foundation.player.bean.VideoBean",loadPackageParam.classLoader), new XC_MethodHook(){
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[org_MiGuPlayer_setVideo]"+param.getResult()+",[arg[0]]"+param.args[0]);
                        }
                    });
                    XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.foundation.player.MiGuPlayer",loadPackageParam.classLoader,"skipAd", new XC_MethodHook(){
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[org_MiGuPlayer_skipAd]"+param.getResult());
                        }
                    });*/
                /* XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.playdetail.PlayDetailActivity$16",loadPackageParam.classLoader,"playVideo", new XC_MethodHook(){
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            super.afterHookedMethod(param);
                                            mmsdkBridge.log("[org_playVideo]"+param.getResult());
                                        }
                   });*/


                    /*XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.foundation.fresco.MGSimpleDraweeView", loadPackageParam.classLoader, "setImageURI",String.class,new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_MGSimpleDraweeView_setImageURI]" + param.getResult()+",[arg[0]]"+param.args[0]);
                            if(param.args[0]!=null && !"".equals(param.args[0])){
                                if (param.args[0].toString().endsWith(".jpg")){
                                    param.args[0]="http://172.17.3.11:8080/automate/download/default.jpg";
                                }else{
                                    param.args[0]="http://172.17.3.11:8080/automate/download/default.png";
                                }
                                param.setResult(param);
                                mmsdkBridge.log("[hook_setImageURI]"+param.getResult()+",[arg[0]]"+param.args[0]);
                            }
                        }
                    });





                    XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.widget.CommenTopBarView", loadPackageParam.classLoader, "setBackgrounImageUri",String.class,new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_CommenTopBarView_setBackgrounImageUri]" + param.getResult()+",[arg[0]]"+param.args[0]);
                            if(param.args[0]!=null && !"".equals(param.args[0])){
                                if (param.args[0].toString().endsWith(".jpg")){
                                    param.args[0]="http://172.17.3.11:8080/automate/download/default.jpg";
                                }else{
                                    param.args[0]="http://172.17.3.11:8080/automate/download/default.png";
                                }
                                param.setResult(param);
                                mmsdkBridge.log("[hook_setBackgrounImageUri]"+param.getResult()+",[arg[0]]"+param.args[0]);
                            }
                        }
                    });*/
                  /*  XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.foundation.fresco.MGControllerListener", loadPackageParam.classLoader, "onFinalImageSet",String.class,XposedHelpers.findClass("com.facebook.imagepipeline.image.ImageInfo",loadPackageParam.classLoader),Animatable.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("==org==[MGControllerListener_onFinalImageSet]" + param.getResult()+",[arg[0]]"+param.args[0]+",[arg[1]] "+param.args[1]+",[arg[2]] "+param.args[2]);
                        }
                    });

                    XposedHelpers.findAndHookMethod("com.google.common.net.MediaType", loadPackageParam.classLoader, "create",String.class,String.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("==org==[MediaType_create]" + param.getResult()+",[arg[0]]"+param.args[0]+",[arg[1]] "+param.args[1]);

                        }
                    });
*/
                  /*  XposedHelpers.findAndHookMethod("com.cmcc.cmvideo.foundation.fresco.MGSimpleDraweeView", loadPackageParam.classLoader, "setImageURI",String.class,int.class,int.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[org_MGSimpleDraweeView_setImageURI3]" + param.getResult()+",[arg[0]]"+param.args[0]+",[arg[1]] "+param.args[1]+",[arg[2]] "+param.args[2]);
                            if(param.args[0]!=null && !"".equals(param.args[0])){
                                if (param.args[0].toString().endsWith(".jpg")){
                                    param.args[0]="http://172.17.3.11:8080/automate/download/default.jpg";
                                }else{
                                    param.args[0]="http://172.17.3.11:8080/automate/download/default.png";
                                }
                                param.setResult(param);
                                mmsdkBridge.log("[hook_setImageURI3]"+param.getResult()+",[arg[0]]"+param.args[0]+",[arg[1]] "+param.args[1]+",[arg[2]] "+param.args[2]);
                            }
                        }
                    });
*/

                }
            });
        }
    }
}

