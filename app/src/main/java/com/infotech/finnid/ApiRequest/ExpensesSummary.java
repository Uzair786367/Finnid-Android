package com.infotech.finnid.ApiRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpensesSummary {


    @SerializedName("sn")
    @Expose
    private Integer sn;
    @SerializedName("ledgerId")
    @Expose
    private Integer ledgerId;
    @SerializedName("accountHeadId")
    @Expose
    private Integer accountHeadId;
    @SerializedName("accountHeadText")
    @Expose
    private String accountHeadText;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    public ExpensesSummary(Integer sn, Integer ledgerId, Integer accountHeadId, String accountHeadText, Integer amount) {
        this.sn = sn;
        this.ledgerId = ledgerId;
        this.accountHeadId = accountHeadId;
        this.accountHeadText = accountHeadText;
        this.amount = amount;
    }
}
