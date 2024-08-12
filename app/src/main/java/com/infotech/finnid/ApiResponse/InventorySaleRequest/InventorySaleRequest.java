package com.infotech.finnid.ApiResponse.InventorySaleRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InventorySaleRequest {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ipAddress")
    @Expose
    private String ipAddress;
    @SerializedName("custDetail")
    @Expose
    private String custDetail;
    @SerializedName("isBill2Ship2")
    @Expose
    private Boolean isBill2Ship2;
    @SerializedName("bill2Ship2Detail")
    @Expose
    private String bill2Ship2Detail;
    @SerializedName("remarkDisclosed")
    @Expose
    private String remarkDisclosed;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("roundOff")
    @Expose
    private Integer roundOff;
    @SerializedName("commitmentDate")
    @Expose
    private String commitmentDate;
    @SerializedName("postingDate")
    @Expose
    private String postingDate;
    @SerializedName("documentDate")
    @Expose
    private String documentDate;
    @SerializedName("documentName")
    @Expose
    private String documentName;
    @SerializedName("companyVoucherPaymentDetails")
    @Expose
    private ArrayList<CompanyVoucherPaymentDetail> companyVoucherPaymentDetails;
    @SerializedName("saleVoucherItemDetails")
    @Expose
    private ArrayList<SaleVoucherItemDetail> saleVoucherItemDetails;
    @SerializedName("denominationIn")
    @Expose
    private ArrayList<DenominationIn> denominationIn;
    @SerializedName("denominationOut")
    @Expose
    private ArrayList<DenominationOut> denominationOut;


    public InventorySaleRequest(Integer id, String ipAddress, String custDetail, Boolean isBill2Ship2,
                                String bill2Ship2Detail, String remarkDisclosed, String remark, Integer roundOff,
                                String commitmentDate, String postingDate, String documentDate,
                                String documentName,
                                ArrayList<CompanyVoucherPaymentDetail> companyVoucherPaymentDetails,
                                ArrayList<SaleVoucherItemDetail> saleVoucherItemDetails,
                                ArrayList<DenominationIn> denominationIn,
                                ArrayList<DenominationOut> denominationOut) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.custDetail = custDetail;
        this.isBill2Ship2 = isBill2Ship2;
        this.bill2Ship2Detail = bill2Ship2Detail;
        this.remarkDisclosed = remarkDisclosed;
        this.remark = remark;
        this.roundOff = roundOff;
        this.commitmentDate = commitmentDate;
        this.postingDate = postingDate;
        this.documentDate = documentDate;
        this.documentName = documentName;
        this.companyVoucherPaymentDetails = companyVoucherPaymentDetails;
        this.saleVoucherItemDetails = saleVoucherItemDetails;
        this.denominationIn = denominationIn;
        this.denominationOut = denominationOut;
    }
}
