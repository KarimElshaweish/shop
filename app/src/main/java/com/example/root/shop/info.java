package com.example.root.shop;

import android.net.Uri;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 1/20/18.
 */

public  class info {
    public  static String Fname="", Sname="",City="",State="",Street="",AltPhone="",Phone="";
    public static int img;
    public static Uri prof_imag=null;
   private static ArrayList<String>ListDec=new ArrayList<>();
   private static ArrayList<Integer>ListDraw=new ArrayList<>();
   private static ArrayList<String>ListName=new ArrayList<>();
   private static ArrayList<String>ListPrice=new ArrayList<>();
   void addDec(String text){
       ListDec.add(text);
   }
   void addDraw(int draw){
       ListDraw.add(draw);
   }
   void addName(String Name){
       ListName.add(Name);
   }
   void addPrice(String Price){
       ListPrice.add(Price);
   }
  public ArrayList<String>Dec(){
       return ListDec;
   }
   public ArrayList<Integer>Draw(){
       return ListDraw;
   }
   public ArrayList<String>Name(){
       return ListName;
   }
  public ArrayList<String>Price(){
       return ListPrice;
   }
}
