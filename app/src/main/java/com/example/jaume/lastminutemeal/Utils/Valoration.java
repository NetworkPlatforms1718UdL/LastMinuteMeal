package com.example.jaume.lastminutemeal.Utils;

import java.util.HashMap;
import java.util.Map;

public class Valoration {

    private String id;
    private String restaurant_id;
    private String rating;
    private String comment;
    private String userId;

    public Valoration(String id, String restaurant_id, String rating, String comment, String userId){
        this.id=id;
        this.restaurant_id=restaurant_id;
        this.rating=rating;
        this.comment=comment;
        this.userId=userId;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String,String> uploadToDatabase(){
        HashMap<String, String> result = new HashMap<>();
        result.put("id", id);
        result.put("restaurant_id", restaurant_id);
        result.put("userid", userId);
        result.put("comment", comment);
        result.put("rating", rating);
        return result;
    }
}
