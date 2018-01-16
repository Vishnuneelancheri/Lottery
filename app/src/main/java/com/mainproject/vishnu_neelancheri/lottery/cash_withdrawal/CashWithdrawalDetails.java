package com.mainproject.vishnu_neelancheri.lottery.cash_withdrawal;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 1/16/2018
 */

public class CashWithdrawalDetails {
    private String date;
    private int id, amount, customer_id;

    private void setId( int id ){
        this.id = id;
    }
    private int getId(){
        return id;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
