package com.example.demo;
import java.io.Serializable;

public class RestaurantDTO implements Serializable {

   private int id ;
   private String name ;
   private String address ;
   private String foodtype ;
   private int mealprice ;

    public RestaurantDTO(int id) {
        super();
    }

    public RestaurantDTO(int id, String name, String address, String foodtype, int mealprice) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.foodtype = foodtype;
        this.mealprice = mealprice;
    }

    public String getName() {
            return name;
        }

        public void setName (String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    public String getAddress() {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype (String foodtype) {
        this.foodtype = foodtype;
    }
    public int getMealprice() {
        return mealprice;
    }

    public void setMealprice (int mealprice) {
        this.mealprice = mealprice;
    }


        @Override
        public String toString() {
            return "MyMessage [id=" + id + ", name =" + name + ",address =" + address +",foodtype ="+ foodtype + "mealprice ="+ mealprice+"]";
        }

    }

