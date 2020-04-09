package com.ziran.meiliao.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ziran.meiliao.entry.MusicEntry;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */
public class ListDataSave {
    private static List<MusicEntry> list;


    /**
     * 保存List
     *
     * @param datalist
     */
    public static void setDataList(Context mcontext, List<MusicEntry> datalist) {
        SharedPreferences sp = mcontext.getSharedPreferences("SP_PEOPLE_List", Activity.MODE_PRIVATE);//创建sp对象
        Gson gson = new Gson();
        String jsonStr = gson.toJson(datalist); //将List转换成Json
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("KEY_PEOPLE_LIST_DATA", jsonStr); //存入json串
        editor.commit();  //提交
    }


    /**
     * 获取List
     *
     * @return
     */
    public static List<MusicEntry> getDataList(Context mcontext) {
        SharedPreferences sp = mcontext.getSharedPreferences("SP_PEOPLE_List", Activity.MODE_PRIVATE);//创建sp对象,如果有key为"SP_PEOPLE"的sp就取出
        String ListJson = sp.getString("KEY_PEOPLE_LIST_DATA", "");  //取出key为"KEY_PEOPLE_DATA"的值，如果值为空，则将第二个参数作为默认值赋值
        if (ListJson != "")  //防空判断
        {
            Gson gson = new Gson();
             list = gson.fromJson(ListJson, new TypeToken<List<MusicEntry>>() {
            }.getType());
        }
        return list;
    }


    public static void deleteItem(Context mcontext,int position){
        SharedPreferences sp = mcontext.getSharedPreferences("SP_PEOPLE_LIST",Activity.MODE_PRIVATE);
       String ListJson = sp.getString("KEY_PEOPLE_LIST_DATA","");
        if(ListJson!="")  //防空判断
        {
            Gson gson = new Gson();
            List<MusicEntry> peopleList = gson.fromJson(ListJson, new TypeToken<List<MusicEntry>>() {
            }.getType()); //1.2. 取出并转换成List
            peopleList.remove(position) ; //3.移除第position个的javabean
            String jsonStr=gson.toJson(peopleList); //4.将删除完的List转换成Json
            SharedPreferences.Editor editor = sp.edit() ;
            editor.putString("KEY_PEOPLE_LIST_DATA", jsonStr) ; //存入json串
            editor.commit() ;  //提交
        }

    }
}