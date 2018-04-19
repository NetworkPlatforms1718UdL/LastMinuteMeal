package com.example.jaume.lastminutemeal.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Reserva {

    private ArrayList<Menu> menu;
    private String lugar, hora;

    public Reserva(String lugar, String hora, ArrayList<Menu> menu){
        this.lugar=lugar;
        this.hora=hora;
        this.menu=menu;
    }

    public String getLugar(){
        return lugar;
    }

    public String getHora(){
        return hora;
    }

    public ArrayList<Menu> getMenu(){
        return menu;
    }

    public int getPersonas(){
        return menu.size();
    }

    public Map<String, Object> uploadToDataBase(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("restaurant_id",1);
        result.put("time",hora);
        HashMap<String, Object> menus = new HashMap<>();
        for (int x=0; x<menu.size(); x++){
            menus.put(String.valueOf(x),menu.get(x).uploadToDataBase());
        }
        result.put("menu",menus);
        return result;
    }
}
