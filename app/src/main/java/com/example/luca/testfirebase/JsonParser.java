package com.example.luca.testfirebase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Luca on 05/02/2018.
 */

public class JsonParser {



    public static ArrayList<User> getAllUsers(JSONObject result){
        ArrayList<User> users= new ArrayList<>() ;
        try {
            Iterator i = result.keys();
            while(i.hasNext()){
                Object key = i.next();
                String name="";
                String progetto="";
                JSONObject value = result.getJSONObject((String)key);
                if(value.has("Id")) {
                    name = value.getString("Id");
                }
                if(value.has("progetto")) {
                    progetto = value.getString("progetto");
                }
                User us= new User(name,progetto);
                users.add(us);
                //System.out.print(listU);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }
}
