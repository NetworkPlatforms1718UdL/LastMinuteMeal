package com.example.jaume.lastminutemeal.Utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Reserva implements Parcelable {

    public static final Creator<Reserva> CREATOR = new Creator<Reserva>() {
        @Override
        public Reserva createFromParcel(Parcel in) {
            return new Reserva(in);
        }

        @Override
        public Reserva[] newArray(int size) {
            return new Reserva[size];
        }
    };
    private ArrayList<Menu> menu;
    private String lugar, hora, uid, id;

    public Reserva(String id, String lugar, String hora, String uid, ArrayList<Menu> menu) {
        this.id = id;
        this.lugar = lugar;
        this.hora = hora;
        this.menu = menu;
        this.uid = uid;
    }

    protected Reserva(Parcel in) {
        menu = in.createTypedArrayList(Menu.CREATOR);
        lugar = in.readString();
        hora = in.readString();
        uid = in.readString();
        id = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(menu);
        parcel.writeString(lugar);
        parcel.writeString(hora);
        parcel.writeString(uid);
        parcel.writeString(id);
    }
}
