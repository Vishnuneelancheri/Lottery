package com.mainproject.vishnu_neelancheri.lottery.settings;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 12/30/2017.
 */

public class SettingsDetails {
    private int id, first_price, second_price, third_price, isActivated;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirst_price() {
        return first_price;
    }

    public void setFirst_price(int first_price) {
        this.first_price = first_price;
    }

    public int getSecond_price() {
        return second_price;
    }

    public void setSecond_price(int second_price) {
        this.second_price = second_price;
    }

    public int getThird_price() {
        return third_price;
    }

    public void setThird_price(int third_price) {
        this.third_price = third_price;
    }

    public int isActivated() {
        return isActivated;
    }

    public void setActivated(int activated) {
        isActivated = activated;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
