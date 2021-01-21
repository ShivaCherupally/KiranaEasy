package com.goodeggs.android.ui.paymentmode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Oreder {
    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("total_amount")
    @Expose
    private String total_amount;

    @SerializedName("orders")
    @Expose
    private List<String> orders = null;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }
}
