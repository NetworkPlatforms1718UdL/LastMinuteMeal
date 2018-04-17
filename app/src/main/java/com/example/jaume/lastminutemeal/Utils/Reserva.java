package com.example.jaume.lastminutemeal.Utils;

import java.util.ArrayList;

public class Reserva {

    private ArrayList<Menu> menu;
    private String lugar, hora;

    public Reserva(String lugar, String hora, ArrayList<Menu> menu){
        this.lugar=lugar;
        this.hora=hora;
        this.menu=menu;
    }
}
