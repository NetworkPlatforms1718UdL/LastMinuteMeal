package com.example.jaume.lastminutemeal.Utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class Menu implements Parcelable{
    private String firstDish;
    private String secondDish;
    private String drink;
    private String desert;
    private String coffee;
    private int person;

    public Menu(int person) {
        this.person = person;
        firstDish = null;
        secondDish = null;
        drink = null;
        desert = null;
        coffee = null;
    }

    private Menu(Parcel in) {
        firstDish = in.readString();
        secondDish = in.readString();
        drink = in.readString();
        desert = in.readString();
        coffee = in.readString();
        person = in.readInt();
    }

    public Menu (HashMap<String,Object> map) {
        this.person = 1;
        this.firstDish =  (String) map.get("first");
        this.secondDish = (String) map.get("second");
        this.desert = (String) map.get("desert");
        this.drink = (String) map.get("drink");
        this.coffee = (String) map.get("coffee");
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    public int getPerson(){
        return person;
    }

    public String getFirstDish(){
        return firstDish;
    }

    public String getSecondDish() {
        return secondDish;
    }

    public String getDesert() {
        return desert;
    }

    public String getDrink() {
        return drink;
    }

    public String getCoffee() {
        return coffee;
    }

    public void setFirstDish(String firstDish) {
        this.firstDish = firstDish;
    }

    public void setSecondDish(String secondDish) {
        this.secondDish = secondDish;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public void setDesert(String desert) {
        this.desert = desert;
    }

    public void setCoffee(String coffee) {
        this.coffee = coffee;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstDish);
        dest.writeString(secondDish);
        dest.writeString(drink);
        dest.writeString(desert);
        dest.writeString(coffee);
        dest.writeInt(person);
    }

    public Map<String, Object> uploadToDataBase(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("first",firstDish);
        result.put("second",secondDish);
        result.put("desert",desert);
        result.put("drink",drink);
        result.put("coffe",coffee);
        return result;
    }
}
