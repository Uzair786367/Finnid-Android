package com.infotech.finnid.ApiRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentDetail {

    @SerializedName("srNo")
    @Expose
    private Integer srNo;
    @SerializedName("paymentModeId")
    @Expose
    private Integer paymentModeId;
    @SerializedName("subPaymentModeId")
    @Expose
    private Integer subPaymentModeId;
    @SerializedName("paidAmount")
    @Expose
    private Integer paidAmount;
    @SerializedName("balanceAmount")
    @Expose
    private Double balanceAmount;
    @SerializedName("utr")
    @Expose
    private String utr;


    public PaymentDetail(Integer srNo, Integer paymentModeId, Integer subPaymentModeId, Integer paidAmount,
                         Double balanceAmount, String utr) {
        this.srNo = srNo;
        this.paymentModeId = paymentModeId;
        this.subPaymentModeId = subPaymentModeId;
        this.paidAmount = paidAmount;
        this.balanceAmount = balanceAmount;
        this.utr = utr;
    }
}
