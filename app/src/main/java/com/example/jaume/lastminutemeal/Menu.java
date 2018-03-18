package com.example.jaume.lastminutemeal;

class Menu
{
    private String person;
    private String type;
    private String content;

    Menu(String person, String type, String content){
        this.person = person;
        this.type = type;
        this.content = content;
    }

    String getPerson(){
        return person;
    }

    String getType(){
        return type;
    }

    String getContent(){
        return content;
    }
}
