package com.example.jnc.testDemo;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;

import java.util.List;
import java.util.Random;

import de.nik.android.mmsdk.IXposedHookLoadPackage;
import de.nik.android.mmsdk.XC_MethodHook;
import de.nik.android.mmsdk.callbacks.XC_LoadPackage;
import de.nik.android.mmsdk.mmsdkBridge;
import de.nik.android.mmsdk.XposedHelpers;

/**
 * Created by JNC on 2018/5/27.
 * com.example.jnc.testxposed.hookMain
 */

public class hookMain implements IXposedHookLoadPackage{

    static String[] appArray = new String[]{"com.kugou.android","com.tencent.tmgp.sgame","com.zhihu.android","com.duowan.kiwi"
            ,"com.sankuai.meituan.takeoutnew","com.ss.android.article.news"," com.youku.phone","com.eg.android.AlipayGphone",
            "com.taobao.taobao","com.autonavi.minimap","com.tencent.mm","com.yixia.videoeditor"};
    private static String packageName =appArray[(int) (Math.random()*appArray.length)];
    private static String myPkg = "de.nik.android.mmsdk.installer";
    private static String AN = packageName.substring(packageName.lastIndexOf(".")+1);
    private static String  name=null;
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        if (loadPackageParam.packageName.equals("com.imohoo.shanpao")){
            mmsdkBridge.log("loadPackageName:"+loadPackageParam.packageName);
            XposedHelpers.findAndHookMethod("org.json.JSONObject", loadPackageParam.classLoader, "toString", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    mmsdkBridge.log("--------openLog action --------");
                    /*JSONObject jsonObject = new JSONObject(param.getResult().toString());
                    jsonObject.put("name",AN);*/
                    mmsdkBridge.log("==hook==[JSONObject_toString]" + param.getResult());
                }
            });
            XposedHelpers.findAndHookMethod("org.json.JSONArray", loadPackageParam.classLoader, "toString", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    mmsdkBridge.log("==hook==[JSONArray_toString]" + param.getResult());

                }
            });
            XposedHelpers.findAndHookMethod(Application.class , "attach",Context.class, new XC_MethodHook() {

                protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                    final String className = "cn.migu.library.base.util.SLog";
                    ClassLoader classLoader = ((Context)param.args[0]).getClassLoader();
                    Class<?> hookClass = null;
                    try{
                        hookClass = classLoader.loadClass(className);
                    }catch (Exception e){
                        mmsdkBridge.log("[Failed!]Can not find "+className);
                        return;
                    }
                    mmsdkBridge.log("[success!]Find class " +className);
                    XposedHelpers.findAndHookMethod(hookClass,"i",String.class,String.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            if (param!=null && !"".equals(param))
                                mmsdkBridge.log("======param_notnull====="+param.getResult());
                            mmsdkBridge.log("======hook_after=====[param1]"+param.args[0]+"[param2]"+param.args[1]);
                        }
                    });
                };
            });
/*            XposedHelpers.findAndHookMethod(Application.class , "attach",Context.class, new XC_MethodHook() {
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                    final String className = "android.app.ApplicationPackageManager"; //cn.migu.library.base.util.SystemUtils
                    ClassLoader classLoader = ((Context)param.args[0]).getClassLoader();
                    Class<?> hookClass = null;
                    try{
                        hookClass = classLoader.loadClass(className);
                    }catch (Exception e){
                        mmsdkBridge.log("[Failed!]Can not find "+className);
                        return;
                    }
                    mmsdkBridge.log("[success!]Find class " +className);
                    XposedHelpers.findAndHookMethod(hookClass,"getInstalledPackages",int.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("==org==[ApplicationPackageManager_getInstalledPackages]"+param.getResult());
                            List<PackageInfo> packageInfoList = (List<PackageInfo>) param.getResult();
                            for (PackageInfo packageInfo:packageInfoList ) {
                                if ("com.example.jnc.testxposed".equals(packageInfo.packageName))
                                    packageInfo.packageName=myPkg;
                            }
                            param.setResult(packageInfoList);
                            mmsdkBridge.log("==hook==[ApplicationPackageManager_getInstalledPackages]"+param.getResult());
                        }
                    });
                };
            });*/
            XposedHelpers.findAndHookMethod(Application.class , "attach",Context.class, new XC_MethodHook() {

                protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                    final String className = "com.mob.tools.utils.DeviceHelper";
                    ClassLoader classLoader = ((Context)param.args[0]).getClassLoader();
                    Class<?> hookClass = null;
                    try{
                        hookClass = classLoader.loadClass(className);
                    }catch (Exception e){
                        mmsdkBridge.log("[Failed!]Can not find "+className);
                        return;
                    }
                    mmsdkBridge.log("[success!]Find class " +className);

                    XposedHelpers.findAndHookMethod(hookClass,"getInstalledApp",boolean.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[hook after][DeviceHelper_getInstalledApp] getResult():"+param.getResult());
                        }
                    });

                    XposedHelpers.findAndHookMethod(classLoader.loadClass("android.app.ApplicationPackageManager"),"getPackageInfo",String.class,int.class,new XC_MethodHook(){
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            if (param.args[0]!=null && !"".equals(param.args[0])){
                                mmsdkBridge.log("==org==[getPackageInfo_before]"+param.args[0]);
                                if ("com.example.jnc.testxposed".equals(param.args[0])){
                                    mmsdkBridge.log("compare:b[getPackageInfo_before]:"+param.args[0]);
                                    param.args[0] = myPkg;
                                    mmsdkBridge.log("compare:a[getPackageInfo_before]:"+param.args[0]);
                                }else
                                    mmsdkBridge.log("不匹配");
                            }else
                                mmsdkBridge.log("[getPackageInfo_before]_param is null");
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            PackageInfo packageInfo = (PackageInfo) param.getResult();
                            mmsdkBridge.log("==org==[getPackageInfo_after]"+param.getResult());
                            if (packageInfo!=null && !"".equals(packageInfo)){
                                if ("de.nik.android.mmsdk.installer".equals(packageInfo.packageName)){
                                    String v6_1 = packageInfo.applicationInfo.name;
                                    mmsdkBridge.log("==org==packageInfolist_v6_1:"+v6_1+",label"+packageInfo.applicationInfo.labelRes);
                                    mmsdkBridge.log("==org==packageInfolist_versionCode:"+packageInfo.versionCode);
                                    if (packageName!=null && !"".equals(packageName)){
                                        packageInfo.packageName=packageName;
                                        packageInfo.versionCode=113;
                                        packageInfo.versionName="2.2";
                                        mmsdkBridge.log("packageInfolist_packageName"+packageInfo.packageName);
                                        param.setResult(packageInfo);
                                        mmsdkBridge.log("==hook==[getPackageInfo_after]"+param.getResult()+",versionCode:"+packageInfo.versionName+",versionName:"+packageInfo.versionCode);
                                    }else {
                                        mmsdkBridge.log("packageName is null");
                                    }
                                }
                            }
                        }
                    });
                    XposedHelpers.findAndHookMethod(classLoader.loadClass("android.app.ApplicationPackageManager"), "getApplicationInfo",String.class,int.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            if (param.args[0]!=null && !"".equals(param.args[0])){
                                mmsdkBridge.log("==org==[getApplicationInfo_before]"+param.args[0]);
                                if ("com.imohoo.shanpao".equals(param.args[0])){
                                    mmsdkBridge.log("匹配");
                                    param.args[0] =myPkg;
                                    mmsdkBridge.log("==hook==[getApplicationInfo_before]"+param.args[0]);
                                }else
                                    mmsdkBridge.log("不匹配");
                            }
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            ApplicationInfo applicationInfo = (ApplicationInfo) param.getResult();
                            mmsdkBridge.log("==org==[getApplicationInfo_after]"+param.args[0]);
                            if (applicationInfo!=null && !"".equals(applicationInfo)){
                                if ("de.nik.android.mmsdk.installer".equals(applicationInfo.packageName)){
                                    applicationInfo.name=AN;
                                    applicationInfo.packageName=packageName;
                                    param.setResult(applicationInfo);
                                    mmsdkBridge.log("==hook==[getApplicationInfo_after]name:"+applicationInfo.name+",packageName:"+applicationInfo.packageName);
                                }
                            }

                        }
                    });
                    XposedHelpers.findAndHookMethod(classLoader.loadClass("android.app.ApplicationPackageManager"),"getInstalledPackages",int.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            List<PackageInfo> packageInfoList = (List<PackageInfo>) param.getResult();
                            mmsdkBridge.log("==org==[ApplicationPackageManager_getInstalledPackages]"+param.getResult());
                            for (PackageInfo packageInfo:packageInfoList ) {
                                if ("com.example.jnc.testxposed".equals(packageInfo.packageName)){
                                    packageInfo.packageName=packageName;
                                    //packageInfo.
                                    packageInfo.versionCode= (int) (Math.random()*2000)+1;
                                    mmsdkBridge.log("==hook==[ApplicationPackageManager_getInstalledPackages]"+param.getResult());
                                }
                            }
                            param.setResult(packageInfoList);
                        }
                    });
                    XposedHelpers.findAndHookMethod("android.content.pm.PackageItemInfo",loadPackageParam.classLoader,"loadLabel",PackageManager.class, new XC_MethodHook() {

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            PackageItemInfo packageItemInfo = (PackageItemInfo) param.thisObject;
                            if (packageItemInfo!=null && !"".equals(packageItemInfo)){
                                if (packageName.equals(packageItemInfo.packageName)){
                                    packageItemInfo.packageName=myPkg;
                                    mmsdkBridge.log("[org getApplicationLabel_before]"+param.getResult()+",pkgName:"+packageItemInfo.packageName+",name:"+packageItemInfo.name);
                                }
                            }
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            PackageItemInfo packageItemInfo = (PackageItemInfo) param.thisObject;
                            mmsdkBridge.log("==org==[getApplicationLabel_after]"+param.getResult()+",name:"+packageItemInfo.name+",packageItemInfo.packageName:"+packageItemInfo.packageName+",load_name:"+packageItemInfo.name);
                            if (packageName.equals(packageItemInfo.packageName)){
                                param.setResult(AN);
                                mmsdkBridge.log("==hook==[getApplicationLabel_after]"+param.getResult()+",name:"+packageItemInfo.name+",packageItemInfo.packageName:"+packageItemInfo.packageName);
                            }
                        }
                    });
                    XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager",loadPackageParam.classLoader,"getText",String.class,int.class,ApplicationInfo.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            mmsdkBridge.log("[before][ApplicationPackageManager_getText]getResult:"+param.getResult()+",pkgName:"+param.args[0]+",int:"+param.args[1]+",apInfo:"+param.args[2]);
                            ApplicationInfo applicationInfo= (ApplicationInfo) param.args[2];
                           /* if ("com.example.jnc.testxposed".equals(param.args[0])){
                                mmsdkBridge.log("[org before][ApplicationPackageManager_getText]getResult:"+param.getResult()+"name:"+applicationInfo.name+",pkgName:"+applicationInfo.packageName);
                                applicationInfo.packageName=myPkg;
                                param.setResult(applicationInfo);
                                mmsdkBridge.log("[hook before][ApplicationPackageManager_getText]getResult:"+param.getResult()+"name:"+applicationInfo.name+",int:"+param.args[1]+",apInfo:"+param.args[2]+",pkgName:"+applicationInfo.packageName);
                            }*/
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            ApplicationInfo applicationInfo= (ApplicationInfo) param.args[2];
                            mmsdkBridge.log("[after][ApplicationPackageManager_getText]getResult:"+param.getResult()+",name:"+applicationInfo.name+",pkgName:"+applicationInfo.packageName);
                            if ("com.example.jnc.testxposed".equals(param.args[0])){
                                mmsdkBridge.log("[org after][ApplicationPackageManager_getText]getResult:"+param.getResult()+",name:"+applicationInfo.name+",pkgName:"+applicationInfo.packageName);
                                applicationInfo.packageName=packageName;
                                param.setResult(AN);
                                //param.setResult(applicationInfo);
                                mmsdkBridge.log("[hook after][ApplicationPackageManager_getText]getResult:"+param.getResult()+",name:"+applicationInfo.name+",pkgName:"+applicationInfo.packageName);
                            }

                        }
                    });
                    XposedHelpers.findAndHookMethod(classLoader.loadClass("java.lang.Runtime"), "exec",String.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            if (param.args[0].equals("pm list packages"))
                                param.args[0] ="cat /data/local/tmp/packages.txt";
                            mmsdkBridge.log("[Runtime_exec]"+param.args[0]);
                            //throw new Exception("myException");
                        }
                    });

                    //XposedHelpers.setStaticObjectField(android.content.pm.PackageInfo.class, "applicationInfo",AN);


                };
            });


            XposedHelpers.findAndHookMethod(Application.class , "attach",Context.class, new XC_MethodHook() {
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                    final String className = "com.migu.uem.crash.f";
                    ClassLoader classLoader = ((Context)param.args[0]).getClassLoader();
                    Class<?> hookClass = null;
                    try{
                        hookClass = classLoader.loadClass(className);
                    }catch (Exception e){
                        mmsdkBridge.log("[Failed!]Can not find "+className);
                        return;
                    }
                    mmsdkBridge.log("[success!]Find class " +className);
                    XposedHelpers.findAndHookMethod(hookClass,"a",Context.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            if (param!=null && !"".equals(param))
                                mmsdkBridge.log("======param_notnull====="+param.getResult().toString());
                            mmsdkBridge.log("[f_a]"+param.getResult());
                        }
                    });
                };
            });



            /*XposedHelpers.findAndHookMethod("cn.migu.library.base.util.SystemUtils", loadPackageParam.classLoader, "getInstalledPackages", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    mmsdkBridge.log("[SystemUtils_getInstalledPackages_before]"+param.getResult());
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    String[] appArray = new String[]{"com.kugou.android","com.tencent.tmgp.sgame","com.zhihu.android","com.duowan.kiwi"
                            ,"com.sankuai.meituan.takeoutnew","com.ss.android.article.news"," com.youku.phone","com.eg.android.AlipayGphone",
                            "com.taobao.taobao","com.autonavi.minimap","com.tencent.mm","com.yixia.videoeditor"};
                    param.setResult(new Random().nextInt(appArray.length));
                    mmsdkBridge.log("===hook====[SystemUtils_getInstalledPackages_after]"+param.getResult());
                }
            });
            XposedHelpers.findAndHookMethod("com.mob.tools.utils.DeviceHelper", loadPackageParam.classLoader, "getInstalledApp", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    mmsdkBridge.log("[DeviceHelper_getInstalledApp]"+param.getResult());
                }
            });*/

        }

        //cn.migu.library.base.util.getInstalledPackages()

    }

    protected void innetMethod(final XC_LoadPackage.LoadPackageParam loadPackageParam){
        XposedHelpers.findAndHookMethod("cn.migu.library.base.util.SLog", loadPackageParam.classLoader, "d",String.class,String.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        mmsdkBridge.log("====hook_before===="+param.getResult());
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        mmsdkBridge.log("====hook_after====");
                        param.args[0] =new Random().nextInt(appArray.length);
                        mmsdkBridge.log("修改后:"+param.args[0].toString());
                    }
                });
    }


}
