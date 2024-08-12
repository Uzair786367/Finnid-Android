package com.infotech.finnid.ApiResponse.InventorySaleRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DenominationOut {



    @SerializedName("currency")
    @Expose
    private Integer currency;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    public DenominationOut(Integer currency, Integer quantity) {
        this.currency = currency;
        this.quantity = quantity;
    }
}
