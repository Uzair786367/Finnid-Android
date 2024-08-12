package com.infotech.finnid.ApiResponse.InventorySaleRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaleVoucherItemDetail {

    @SerializedName("itemcode")
    @Expose
    private String itemcode;
    @SerializedName("itemname")
    @Expose
    private String itemname;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("taq")
    @Expose
    private String taq;
    @SerializedName("qas")
    @Expose
    private String qas;
    @SerializedName("saleprice")
    @Expose
    private Integer saleprice;
    @SerializedName("gstrate")
    @Expose
    private Integer gstrate;
    @SerializedName("cessrate")
    @Expose
    private Integer cessrate;
    @SerializedName("exmrate")
    @Expose
    private Integer exmrate;
    @SerializedName("mindisc")
    @Expose
    private Integer mindisc;
    @SerializedName("maxdisc")
    @Expose
    private Integer maxdisc;
    @SerializedName("uomdetails")
    @Expose
    private String uomdetails;
    @SerializedName("uomid")
    @Expose
    private Integer uomid;
    @SerializedName("saletype")
    @Expose
    private String saletype;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("taxablevalue")
    @Expose
    private Integer taxablevalue;
    @SerializedName("gstvalue")
    @Expose
    private Integer gstvalue;
    @SerializedName("cessvalue")
    @Expose
    private Integer cessvalue;
    @SerializedName("totalamount")
    @Expose
    private Integer totalamount;
    @SerializedName("itemtype")
    @Expose
    private String itemtype;
    @SerializedName("keyword")
    @Expose
    private String keyword;
    @SerializedName("sstId")
    @Expose
    private String sstId;
    @SerializedName("ssmId")
    @Expose
    private String ssmId;
    @SerializedName("looseSaleUOM")
    @Expose
    private String looseSaleUOM;
    @SerializedName("hsn")
    @Expose
    private String hsn;
    @SerializedName("saleissue")
    @Expose
    private String saleissue;
    @SerializedName("sellingprice")
    @Expose
    private String sellingprice;

    public SaleVoucherItemDetail(String itemcode, String itemname, Integer qty, String taq, String qas, Integer saleprice, Integer gstrate, Integer cessrate, Integer exmrate, Integer mindisc, Integer maxdisc, String uomdetails, Integer uomid, String saletype, Integer amount, Integer taxablevalue, Integer gstvalue, Integer cessvalue, Integer totalamount, String itemtype, String keyword, String sstId, String ssmId, String looseSaleUOM, String hsn, String saleissue, String sellingprice) {
        this.itemcode = itemcode;
        this.itemname = itemname;
        this.qty = qty;
        this.taq = taq;
        this.qas = qas;
        this.saleprice = saleprice;
        this.gstrate = gstrate;
        this.cessrate = cessrate;
        this.exmrate = exmrate;
        this.mindisc = mindisc;
        this.maxdisc = maxdisc;
        this.uomdetails = uomdetails;
        this.uomid = uomid;
        this.saletype = saletype;
        this.amount = amount;
        this.taxablevalue = taxablevalue;
        this.gstvalue = gstvalue;
        this.cessvalue = cessvalue;
        this.totalamount = totalamount;
        this.itemtype = itemtype;
        this.keyword = keyword;
        this.sstId = sstId;
        this.ssmId = ssmId;
        this.looseSaleUOM = looseSaleUOM;
        this.hsn = hsn;
        this.saleissue = saleissue;
        this.sellingprice = sellingprice;
    }
}
