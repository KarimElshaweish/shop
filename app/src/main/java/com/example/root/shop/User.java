package com.example.root.shop;

/**
 * Created by root on 1/19/18.
 */

public class User {
    private String FName,SName,City,State,Street;
    private int PhoneNumber,AltPhoneNumber,Post;

    public User(String FName, String SName, String city, String state, String street, int phoneNumber, int altPhoneNumber, int post) {
        this.FName = FName;
        this.SName = SName;
        City = city;
        State = state;
        Street = street;
        PhoneNumber = phoneNumber;
        AltPhoneNumber = altPhoneNumber;
        Post = post;
        check();
    }

    public User() {

    }

    private void check() {
        if(FName.isEmpty())
            FName="";
        if(SName.isEmpty())
            SName="";
        if(City.isEmpty())
            City="";
        if(State.isEmpty())
            State="";
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getAltPhoneNumber() {
        return AltPhoneNumber;
    }

    public void setAltPhoneNumber(int altPhoneNumber) {
        AltPhoneNumber = altPhoneNumber;
    }

    public int getPost() {
        return Post;
    }

    public void setPost(int post) {
        Post = post;
    }
}
