package com.infotech.finnid.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSalesVoucherDatum {




    String itemId;
    String itemName;

    Integer qty;
    Double amount;
    Integer id;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @SerializedName("userRoleId")
    @Expose
    private Double userRoleId;
    @SerializedName("ssmId")
    @Expose
    private Double ssmId;
    @SerializedName("itemcode")
    @Expose
    private String itemcode;
    @SerializedName("stockInGroupDetail")
    @Expose
    private String stockInGroupDetail;
    @SerializedName("barCode")
    @Expose
    private String barCode;
    @SerializedName("saletype")
    @Expose
    private String saletype;
    @SerializedName("qas")
    @Expose
    private String qas;
    @SerializedName("className")
    @Expose
    private String className;
    @SerializedName("saleprice")
    @Expose
    private Double saleprice;
    @SerializedName("mrpPerUnit")
    @Expose
    private Double mrpPerUnit;
    @SerializedName("looseSaleUOM")
    @Expose
    private Double looseSaleUOM;
    @SerializedName("taq")
    @Expose
    private Double taq;
    @SerializedName("mindisc")
    @Expose
    private Double mindisc;
    @SerializedName("maxdisc")
    @Expose
    private Double maxdisc;
    @SerializedName("gstrate")
    @Expose
    private Double gstrate;
    @SerializedName("cessrate")
    @Expose
    private Double cessrate;
    @SerializedName("exmrate")
    @Expose
    private Double exmrate;
    @SerializedName("uomid")
    @Expose
    private Double uomid;
    @SerializedName("ismultibatch")
    @Expose
    private Boolean ismultibatch;
    @SerializedName("sstId")
    @Expose
    private Double sstId;
    @SerializedName("productImage")
    @Expose
    private String productImage;
    @SerializedName("pImage")
    @Expose
    private String pImage;
    @SerializedName("pImageName")
    @Expose
    private String pImageName;
    @SerializedName("itemname")
    @Expose
    private String itemname;
    @SerializedName("uomdetails")
    @Expose
    private String uomdetails;

    public Double getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Double userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Double getSsmId() {
        return ssmId;
    }

    public void setSsmId(Double ssmId) {
        this.ssmId = ssmId;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getStockInGroupDetail() {
        return stockInGroupDetail;
    }

    public void setStockInGroupDetail(String stockInGroupDetail) {
        this.stockInGroupDetail = stockInGroupDetail;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getSaletype() {
        return saletype;
    }

    public void setSaletype(String saletype) {
        this.saletype = saletype;
    }

    public String getQas() {
        return qas;
    }

    public void setQas(String qas) {
        this.qas = qas;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Double getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(Double saleprice) {
        this.saleprice = saleprice;
    }

    public Double getMrpPerUnit() {
        return mrpPerUnit;
    }

    public void setMrpPerUnit(Double mrpPerUnit) {
        this.mrpPerUnit = mrpPerUnit;
    }

    public Double getLooseSaleUOM() {
        return looseSaleUOM;
    }

    public void setLooseSaleUOM(Double looseSaleUOM) {
        this.looseSaleUOM = looseSaleUOM;
    }

    public Double getTaq() {
        return taq;
    }

    public void setTaq(Double taq) {
        this.taq = taq;
    }

    public Double getMindisc() {
        return mindisc;
    }

    public void setMindisc(Double mindisc) {
        this.mindisc = mindisc;
    }

    public Double getMaxdisc() {
        return maxdisc;
    }

    public void setMaxdisc(Double maxdisc) {
        this.maxdisc = maxdisc;
    }

    public Double getGstrate() {
        return gstrate;
    }

    public void setGstrate(Double gstrate) {
        this.gstrate = gstrate;
    }

    public Double getCessrate() {
        return cessrate;
    }

    public void setCessrate(Double cessrate) {
        this.cessrate = cessrate;
    }

    public Double getExmrate() {
        return exmrate;
    }

    public void setExmrate(Double exmrate) {
        this.exmrate = exmrate;
    }

    public Double getUomid() {
        return uomid;
    }

    public void setUomid(Double uomid) {
        this.uomid = uomid;
    }

    public Boolean getIsmultibatch() {
        return ismultibatch;
    }

    public void setIsmultibatch(Boolean ismultibatch) {
        this.ismultibatch = ismultibatch;
    }

    public Double getSstId() {
        return sstId;
    }

    public void setSstId(Double sstId) {
        this.sstId = sstId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }

    public String getpImageName() {
        return pImageName;
    }

    public void setpImageName(String pImageName) {
        this.pImageName = pImageName;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getUomdetails() {
        return uomdetails;
    }

    public void setUomdetails(String uomdetails) {
        this.uomdetails = uomdetails;
    }


}
