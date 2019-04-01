package com.example.jnc.testDemo;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

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
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    ClassLoader cl = ((Context) param.args[0]).getClassLoader();
                    Class<?> hookClass = null;

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
                    XposedHelpers.findAndHookMethod("cmccwm.mobilemusic.bean.RadioStationBean.Item",loadPackageParam.classLoader,"getImageUrl", new XC_MethodHook(){
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
                   /* XposedHelpers.findAndHookMethod("cmccwm.mobilemusic.renascence.data.entity.UICard",loadPackageParam.classLoader,"getImageUrl",new XC_MethodHook(){
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
                    /*XposedHelpers.findAndHookMethod("com.migu.imgloader.MiguImgLoader", loadPackageParam.classLoader, "with",Context.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[org_with]"+param.getResult()+",[arg[0]]"+param.args[0]);
                        }
                    });
                    //Class.forName("com.migu.rx.lifecycle.ILifeCycle")
                    XposedHelpers.findAndHookMethod("com.migu.bizz.loder.RecomentdationPageLoader", loadPackageParam.classLoader, "load",XposedHelpers.findClass("com.migu.rx.lifecycle.ILifeCycle",loadPackageParam.classLoader), new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[org_load]"+param.getResult()+",[arg[0]]"+param.args[0]);
                        }
                    });
                    XposedHelpers.findAndHookMethod("com.bumptech.glide.request.GenericRequest", loadPackageParam.classLoader, "a",String.class,Object.class,String.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[org_a]"+param.getResult()+",[arg[0]]"+param.args[0]+",[arg[1]] "+param.args[1]+",[arg[2]] "+param.args[2]);
                        }
                    });
                    XposedHelpers.findAndHookMethod("cmccwm.mobilemusic.ui.GlideImageLoader", loadPackageParam.classLoader, "displayImage",Context.class,Object.class,ImageView.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[org_displayImage] "+param.getResult()+",[arg[0]] "+param.args[0]+",[arg[1]] "+param.args[1]+",[arg[2]] "+param.args[2]);
                            if (param.args[1]!=null && !"".equals(param.args[1])){
                                param.args[1] = "http://172.17.3.11:8080/automate/download/default.jpg";
                                //param.args[1] = "/data/local/tmp/1.jpg";
                                param.setResult(param);
                            }
                            mmsdkBridge.log("[hook_displayImage] "+param.getResult()+",[arg[0]] "+param.args[0]+",[arg[1]] "+param.args[1]+",[arg[2]] "+param.args[2]);
                        }
                    });
                    XposedHelpers.findAndHookMethod("okhttp3.HttpUrl", loadPackageParam.classLoader, "f", String.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[org_HttpUrl]"+param.getResult()+",[arg[0]] "+param.args[0]);
                           if (param.args[0]!=null && !"".equals(param.args[0])){
                                if (param.args[0].toString().contains("fileID")){
                                    param.args[0] = "http://172.17.3.11:8080/automate/download/default.jpg";
                                    param.setResult(param);
                                    mmsdkBridge.log("[hook_HttpUrl]"+param.getResult()+",[arg[0]] "+param.args[0]);
                                }
                            }else
                                mmsdkBridge.log("[HttpUrl] not hook_success");
                        }
                    });
                    XposedHelpers.findAndHookMethod("com.migu.net.NetLoader", loadPackageParam.classLoader, "baseUrl", String.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[org_baseUrl]"+param.getResult()+",[arg[0]]"+param.args[0]);
                        }
                    });
                    XposedHelpers.findAndHookMethod("com.migu.cache.CacheLoader", loadPackageParam.classLoader, "buildRequest", String.class,String.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[org_buildRequest]"+param.getResult()+",[arg[0]]"+param.args[0]);
                        }
                    });
                    XposedHelpers.findAndHookMethod("com.bumptech.glide.request.GenericRequest",loadPackageParam.classLoader,"a",String.class,new XC_MethodHook(){
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[GenericRequest_a]"+param.getResult()+",[arg[0]]"+param.args[0]);
                        }
                    });
                    XposedHelpers.findAndHookMethod("com.bumptech.glide.request.GenericRequest",loadPackageParam.classLoader,"a",String.class,Object.class,String.class,new XC_MethodHook(){
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[GenericRequest_a3]"+param.getResult()+",[arg[0]]"+param.args[0]+",[arg[1]]"+param.args[1]+",[arg[2]]"+param.args[2]);
                        }
                    });
                    XposedHelpers.findAndHookConstructor("com.bumptech.glide.load.a.e",loadPackageParam.classLoader,new Object[]{Context.class,Uri.class,new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[constructor_e]"+param.getResult()+"[arg[0]]"+param.args[0]+",[arg[1]]"+param.args[1]);
                        }
                    }});
                    XposedHelpers.findAndHookMethod("com.bumptech.glide.load.a.e",loadPackageParam.classLoader,"a",Uri.class,ContentResolver.class,new XC_MethodHook(){
                        protected void after(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[imageload_setImageUrl]"+param.getResult()+"[arg[0]]"+param.args[0]+",[arg[1]]"+param.args[1]);

                        }
                    });
                    XposedHelpers.findAndHookMethod("com.bumptech.glide.load.resource.c.c", loadPackageParam.classLoader, "a",File.class,int.class,int.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("[reader_c]"+param.getResult()+",param1:"+param.args[0]+",param2:"+param.args[1]+",param3:"+param.args[2]);
                        }
                    });
                    XposedHelpers.findAndHookConstructor("com.bumptech.glide.load.b.g", loadPackageParam.classLoader, new Object[]{InputStream.class,ParcelFileDescriptor.class,new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    Object param1 = param.args[0];
                                    Object param2 = param.args[1];
                                    mmsdkBridge.log("[Constructor_g]"+param.getResult()+",p1:"+param1+",p2:"+param2);
                                }
                            }}
                    );
                    XposedHelpers.findAndHookMethod("com.bumptech.glide.request.d", loadPackageParam.classLoader, "a",Long.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            mmsdkBridge.log("param.args[0]:"+param.args[0]+",getresult:"+param.getResult());
                        }
                    });*/
                    /*XposedHelpers.findAndHookMethod("com.migu.bizz_v2.uicard.entity.GroupBean",loadPackageParam.classLoader,"setItemList",List.class,new XC_MethodHook(){
                        protected void after(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[MiguImgLoader_setItemList]"+param.getResult()+"[arg[0]]"+param.args[0]);

                        }
                    });*/
                    //shouldOverrideUrlLoading
                    /*XposedHelpers.findAndHookMethod("android.webkit.WebViewClient", loadPackageParam.classLoader, "shouldInterceptRequest", WebView.class,WebResourceRequest.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[WebViewClient_shouldInterceptRequest]"+param.getResult());
                            WebResourceRequest request= (WebResourceRequest) param.args[1];
                            String url = request.getUrl().getPath();
                            WebResourceResponse resourceResponse = null;
                            String insteadUrl = null;
                            String mime="image/jpeg";
                            if (url.toLowerCase().endsWith("jpg")){
                                insteadUrl = "http://172.17.3.11:8080/automate/download/default.jpg";
                            }else if (url.toLowerCase().endsWith("png")){
                                insteadUrl = "http://172.17.3.11:8080/automate/download/default.png";
                                mime = "image/png";
                            }
                            if (insteadUrl!=null){
                                URL http = new URL(insteadUrl);
                                resourceResponse = new WebResourceResponse(mime,"utf-8",http.openStream());
                                param.setResult(resourceResponse);
                                mmsdkBridge.log("替换前的url:"+url+"/n"+"替换后的insteadUrl"+ insteadUrl);
                            }
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                        }
                    });*/

                }
            });
        }
    }
}
