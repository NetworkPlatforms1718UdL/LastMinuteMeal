package com.example.jaume.lastminutemeal;

class Menu
{
    private String fistDish;
    private String secondDish;
    private String drink;
    private String desert;
    private boolean coffe;
    private String person;
    private String type;
    private String content;

    Menu(String person, String type, String fistDish, String secondDish, String desert, String drink, Boolean coffe){
        this.person = person;
        this.type = type;
        this.fistDish = fistDish;
        this.secondDish = secondDish;
        this.desert = desert;
        this.drink = drink;
        this.coffe = coffe;
    }

    Menu(String person) {this.person = person;}

    String getPerson(){
    return person;
}

    String getType(){ return type;}

    void setType(String type){ this.type = type; }

    String getContent(){
        return content;
    }

    String getFistDish(){
        return fistDish;
    }

    String getSecondDish(){ return secondDish; }

    String getDesert(){ return desert; }

    String getDrink(){
        return drink;
    }

    Boolean getCoffe(){
        return coffe;
    }
}
