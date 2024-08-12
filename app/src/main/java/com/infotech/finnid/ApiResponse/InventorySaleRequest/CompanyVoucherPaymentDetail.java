package com.infotech.finnid.ApiResponse.InventorySaleRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyVoucherPaymentDetail {



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
    private Integer balanceAmount;
    @SerializedName("utr")
    @Expose
    private String utr;

    public CompanyVoucherPaymentDetail(Integer srNo, Integer paymentModeId, Integer subPaymentModeId, Integer paidAmount, Integer balanceAmount, String utr) {
        this.srNo = srNo;
        this.paymentModeId = paymentModeId;
        this.subPaymentModeId = subPaymentModeId;
        this.paidAmount = paidAmount;
        this.balanceAmount = balanceAmount;
        this.utr = utr;
    }
}
