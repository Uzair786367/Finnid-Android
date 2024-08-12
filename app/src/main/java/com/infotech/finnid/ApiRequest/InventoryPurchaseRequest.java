package com.infotech.finnid.ApiRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InventoryPurchaseRequest {


    @SerializedName("IPAddress")
    @Expose
    private String IPAddress;

    @SerializedName("isSupplier")
    @Expose
    private String isSupplier;
    @SerializedName("supplierId")
    @Expose
    private String supplierId;
    @SerializedName("supplierStateId")
    @Expose
    private String supplierStateId;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("poNo")
    @Expose
    private String poNo;
    @SerializedName("billNo")
    @Expose
    private String billNo;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("stateId")
    @Expose
    private String stateId;
    @SerializedName("ewayBill")
    @Expose
    private String ewayBill;
    @SerializedName("billDate")
    @Expose
    private String billDate;
    @SerializedName("isRCM")
    @Expose
    private String isRCM;
    @SerializedName("taxable")
    @Expose
    private String taxable;
    @SerializedName("cess")
    @Expose
    private String cess;
    @SerializedName("roundOff")
    @Expose
    private String roundOff;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("postingDate")
    @Expose
    private String postingDate;
    @SerializedName("documentDate")
    @Expose
    private String documentDate;
    @SerializedName("sgst")
    @Expose
    private String sgst;
    @SerializedName("cgst")
    @Expose
    private String cgst;
    @SerializedName("igst")
    @Expose
    private String igst;
    @SerializedName("itemDetails")
    @Expose
    private ArrayList<ItemDetail> itemDetails;
    @SerializedName("paymentDetails")
    @Expose
    private ArrayList<PaymentDetail> paymentDetails;
    @SerializedName("itemOfferDetails")
    @Expose
    private ArrayList<ItemOfferDetail> itemOfferDetails;
    @SerializedName("denominationList")
    @Expose
    private String denominationList;
    @SerializedName("returndenominationList")
    @Expose
    private String returndenominationList;
    @SerializedName("documentBase64")
    @Expose
    private String documentBase64;
    @SerializedName("ext")
    @Expose
    private String ext;
    @SerializedName("isInterstateFunction")
    @Expose
    private String isInterstateFunction;
    @SerializedName("isDedicatedPurchase")
    @Expose
    private Boolean isDedicatedPurchase;
    @SerializedName("isUnRegisteredVendor")
    @Expose
    private Boolean isUnRegisteredVendor;
    @SerializedName("dedicatedPurchase")
    @Expose
    private Integer dedicatedPurchase;
    @SerializedName("purchasedPrice")
    @Expose
    private ArrayList<PurchasedPrice> purchasedPrice;
    @SerializedName("expensesSummaryList")
    @Expose
    private ArrayList<ExpensesSummary> expensesSummaryList;

    public InventoryPurchaseRequest(String IPAddress,String isSupplier, String supplierId, String supplierStateId, String mobileNo, String poNo, String billNo, String flag, String stateId, String ewayBill, String billDate, String isRCM, String taxable, String cess, String roundOff, String total, String postingDate, String documentDate, String sgst, String cgst, String igst, ArrayList<ItemDetail> itemDetails, ArrayList<PaymentDetail> paymentDetails, ArrayList<ItemOfferDetail> itemOfferDetails, String denominationList, String returndenominationList, String documentBase64, String ext, String isInterstateFunction, Boolean isDedicatedPurchase, Boolean isUnRegisteredVendor, Integer dedicatedPurchase, ArrayList<PurchasedPrice> purchasedPrice, ArrayList<ExpensesSummary> expensesSummaryList) {
        this.IPAddress = IPAddress;
        this.isSupplier = isSupplier;
        this.supplierId = supplierId;
        this.supplierStateId = supplierStateId;
        this.mobileNo = mobileNo;
        this.poNo = poNo;
        this.billNo = billNo;
        this.flag = flag;
        this.stateId = stateId;
        this.ewayBill = ewayBill;
        this.billDate = billDate;
        this.isRCM = isRCM;
        this.taxable = taxable;
        this.cess = cess;
        this.roundOff = roundOff;
        this.total = total;
        this.postingDate = postingDate;
        this.documentDate = documentDate;
        this.sgst = sgst;
        this.cgst = cgst;
        this.igst = igst;
        this.itemDetails = itemDetails;
        this.paymentDetails = paymentDetails;
        this.itemOfferDetails = itemOfferDetails;
        this.denominationList = denominationList;
        this.returndenominationList = returndenominationList;
        this.documentBase64 = documentBase64;
        this.ext = ext;
        this.isInterstateFunction = isInterstateFunction;
        this.isDedicatedPurchase = isDedicatedPurchase;
        this.isUnRegisteredVendor = isUnRegisteredVendor;
        this.dedicatedPurchase = dedicatedPurchase;
        this.purchasedPrice = purchasedPrice;
        this.expensesSummaryList = expensesSummaryList;

    }
}
