package com.learn.vertx.util;

import com.learn.vertx.VertxApp;
import com.learn.vertx.annotation.Action;
import com.learn.vertx.annotation.Url;

import java.io.File;
import java.lang.reflect.Method;

/**
 * @Auther: xuepengbo
 * @Date: 2018/9/19 15:23
 * @Description:
 */
public class Load {

    /**
     *
     * 功能描述:
     * @methodName: load
     * @params:
     * @param packget
     * @return: void
     * @auther: xuepengbo
     * @date: 2018/9/19 15:29
     * @description:
     *      启动时扫面全部的controller
     */
    public static void loadClass(String packget){
        String filePath = VertxApp.class.getResource("/").getPath() + packget.replace(".", "\\");
        File dir = new File(filePath);
        File[] files = dir.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            Class clazz = null;
            try {
                clazz = Class.forName(packget + "." + fileName.substring(0, fileName.lastIndexOf(".")));
            } catch (ClassNotFoundException e) {
                System.out.println(packget + "." + fileName.substring(0, fileName.lastIndexOf(".")) + "找不到！");
            }
            if(clazz.isAnnotationPresent(Action.class)){
                Action action = (Action) clazz.getAnnotation(Action.class);
                loadMethod(action.name(), clazz);
            }
        }
    }

    /**
     *
     * 功能描述:
     * @methodName: loadMethod
     * @params:
     * @param clazz
     * @return: void
     * @auther: xuepengbo
     * @date: 2018/9/19 16:34
     * @description:
     *      启动时扫面全部的controller的method
     */
    private static void loadMethod(String typePath, Class clazz){
        if(!typePath.startsWith("/")){
            typePath = "/" + typePath;
        }
        if(!typePath.endsWith("/")){
            typePath = typePath + "/";
        }
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if(method.isAnnotationPresent(Url.class)){
                Url url = method.getAnnotation(Url.class);
                String methodPath = url.path();
                if(methodPath.startsWith("/")){
                    methodPath = methodPath.substring(1);
                }
                String path = typePath + methodPath;
                VertxApp.methodMap.put(new Object[]{path, url.method()}, method);
            }
        }
    }
}
