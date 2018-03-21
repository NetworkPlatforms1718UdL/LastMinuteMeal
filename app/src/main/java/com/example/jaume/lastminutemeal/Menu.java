package com.example.jaume.lastminutemeal;

import android.os.Parcel;
import android.os.Parcelable;

class Menu implements Parcelable{
    private String firstDish;
    private String secondDish;
    private String drink;
    private String desert;
    private boolean coffee;
    private int person;

    Menu(int person) {
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

    int getPerson(){
        return person;
    }

    String getFirstDish(){
        return firstDish;
    }

    String getSecondDish() {
        return secondDish;
    }

    String getDesert() {
        return desert;
    }

    String getDrink() {
        return drink;
    }

    Boolean getCoffee() {
        return coffee;
    }

    void setFirstDish(String firstDish) {
        this.firstDish = firstDish;
    }

    void setSecondDish(String secondDish) {
        this.secondDish = secondDish;
    }

    void setDrink(String drink) {
        this.drink = drink;
    }

    void setDesert(String desert) {
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
}
