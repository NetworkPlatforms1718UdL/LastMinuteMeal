package com.example.jaume.lastminutemeal.Utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Menu implements Parcelable{
    private String firstDish;
    private String secondDish;
    private String drink;
    private String desert;
    private boolean coffee;
    private int person;

    public Menu(int person) {
        this.person = person;
        firstDish = null;
        secondDish = null;
        drink = null;
        desert = null;
        coffee = false;
    }

    private Menu(Parcel in) {
        firstDish = in.readString();
        secondDish = in.readString();
        drink = in.readString();
        desert = in.readString();
        coffee = in.readByte() != 0;
        person = in.readInt();
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

    public Boolean getCoffee() {
        return coffee;
    }

    public void setFirstDish(String firstDish) {
        this.firstDish = firstDish;
    }

    public void setSecondDish(String secondDish) {
        this.secondDish = secondDish;
    }

    void setDrink(String drink) {
        this.drink = drink;
    }

    public void setDesert(String desert) {
        this.desert = desert;
    }

    void setCoffee(boolean coffee) {
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
        dest.writeByte((byte) (coffee ? 1 : 0));
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
