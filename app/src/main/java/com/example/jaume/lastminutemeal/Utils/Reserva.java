package com.example.jaume.lastminutemeal.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Reserva {

    private ArrayList<Menu> menu;
    private String lugar, hora, uid, id;

    public Reserva(String id, String lugar, String hora, String uid, ArrayList<Menu> menu) {
        this.id = id;
        this.lugar = lugar;
        this.hora = hora;
        this.menu = menu;
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public String getLugar() {
        return lugar;
    }

    public String getHora() {
        return hora;
    }

    public String getUid() {
        return uid;
    }

    public ArrayList<Menu> getMenu() {
        return menu;
    }

    public int getPersonas() {
        return menu.size();
    }

    public Map<String, Object> uploadToDataBase() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("restaurant_id", lugar);
        result.put("userid", uid);
        result.put("time", hora);
        result.put("persons", menu.size());
        HashMap<String, Object> menus = new HashMap<>();
        for (int x = 0; x < menu.size(); x++) {
            menus.put(String.valueOf(x), menu.get(x).uploadToDataBase());
        }
        result.put("menu", menus);
        return result;
    }
}
