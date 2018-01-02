package com.mainproject.vishnu_neelancheri.lottery.cash_in;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 1/1/2018.
 */

public class CashInDetails {
    private int id;
    private int settingsId;
    private int customerId;
    private int firstPrizeQty;
    private int secondPrizeQty;
    private int thirdPriceQty;
    private String date;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(int settingsId) {
        this.settingsId = settingsId;
    }

    public int getFirstPrizeQty() {
        return firstPrizeQty;
    }

    public void setFirstPrizeQty(int firstPrizeQty) {
        this.firstPrizeQty = firstPrizeQty;
    }

    public int getSecondPrizeQty() {
        return secondPrizeQty;
    }

    public void setSecondPrizeQty(int secondPrizeQty) {
        this.secondPrizeQty = secondPrizeQty;
    }

    public int getThirdPriceQty() {
        return thirdPriceQty;
    }

    public void setThirdPriceQty(int thirdPriceQty) {
        this.thirdPriceQty = thirdPriceQty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
