 package com.infotech.finnid.ApiRequest;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MemberRegistrationReq {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("landOwnershipType")
    @Expose
    public String landOwnershipType;
    @SerializedName("relationWithFamilyMember")
    @Expose
    public String relationWithFamilyMember;
    @SerializedName("relativeName")
    @Expose
    public String relativeName;
    @SerializedName("khatauniNo")
    @Expose
    public String khatauniNo;
    @SerializedName("uomId")
    @Expose
    public Integer uomId;
    @SerializedName("area")
    @Expose
    public String area;
    @SerializedName("category")
    @Expose
    public Integer category;
    @SerializedName("companyId")
    @Expose
    public String companyId;
    @SerializedName("officeId")
    @Expose
    public String officeId;
    @SerializedName("memberCategory")
    @Expose
    public String memberCategory;
    @SerializedName("mem_FirstName")
    @Expose
    public String memFirstName;
    @SerializedName("mem_MiddileName")
    @Expose
    public String memMiddileName;
    @SerializedName("mem_LastName")
    @Expose
    public String memLastName;
    @SerializedName("f_FirstName")
    @Expose
    public String fFirstName;
    @SerializedName("f_MiddileName")
    @Expose
    public String fMiddileName;
    @SerializedName("f_LastName")
    @Expose
    public String fLastName;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("dob")
    @Expose
    public String dob;
    @SerializedName("nationality")
    @Expose
    public String nationality;
    @SerializedName("birthPlace")
    @Expose
    public String birthPlace;
    @SerializedName("occupationType")
    @Expose
    public String occupationType;
    @SerializedName("pan")
    @Expose
    public String pan;
    @SerializedName("adhar")
    @Expose
    public String adhar;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("landline")
    @Expose
    public String landline;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("proofOfIdentity")
    @Expose
    public Integer proofOfIdentity;
    @SerializedName("proofOfAddress")
    @Expose
    public Integer proofOfAddress;
    @SerializedName("permanentAddress")
    @Expose
    public String permanentAddress;
    @SerializedName("permCountryId")
    @Expose
    public Integer permCountryId;
    @SerializedName("permStateId")
    @Expose
    public Integer permStateId;
    @SerializedName("permCityId")
    @Expose
    public Integer permCityId;
    @SerializedName("permPinCode")
    @Expose
    public String permPinCode;
    @SerializedName("permLandmark")
    @Expose
    public String permLandmark;
    @SerializedName("presentAddress")
    @Expose
    public String presentAddress;
    @SerializedName("presCountryId")
    @Expose
    public Integer presCountryId;
    @SerializedName("presStateId")
    @Expose
    public Integer presStateId;
    @SerializedName("presCityId")
    @Expose
    public Integer presCityId;
    @SerializedName("presPinCode")
    @Expose
    public String presPinCode;
    @SerializedName("presLandmark")
    @Expose
    public String presLandmark;
    @SerializedName("durationOfStayPAddress")
    @Expose
    public Integer durationOfStayPAddress;
    @SerializedName("educationalQualification")
    @Expose
    public String educationalQualification;
    @SerializedName("appliedForNoOfShare")
    @Expose
    public Integer appliedForNoOfShare;
    @SerializedName("shareTypeId")
    @Expose
    public Integer shareTypeId;
    @SerializedName("membershipType")
    @Expose
    public Integer membershipType;

    public MemberRegistrationReq(Integer id, String landOwnershipType, String relationWithFamilyMember, String relativeName, String khatauniNo,
                                 Integer uomId, String area, Integer category, String companyId, String officeId, String memberCategory,
                                 String memFirstName, String memMiddileName, String memLastName, String fFirstName, String fMiddileName,
                                 String fLastName, String gender, String dob, String nationality, String birthPlace, String occupationType,
                                 String pan, String adhar, String mobile, String landline, String email, Integer proofOfIdentity,
                                 Integer proofOfAddress, String permanentAddress, Integer permCountryId, Integer permStateId,
                                 Integer permCityId, String permPinCode, String permLandmark, String presentAddress, Integer presCountryId,
                                 Integer presStateId, Integer presCityId, String presPinCode, String presLandmark, Integer durationOfStayPAddress,
                                 String educationalQualification, Integer appliedForNoOfShare, Integer shareTypeId, Integer membershipType) {
        super();
        this.id = id;
        this.landOwnershipType = landOwnershipType;
        this.relationWithFamilyMember = relationWithFamilyMember;
        this.relativeName = relativeName;
        this.khatauniNo = khatauniNo;
        this.uomId = uomId;
        this.area = area;
        this.category = category;
        this.companyId = companyId;
        this.officeId = officeId;
        this.memberCategory = memberCategory;
        this.memFirstName = memFirstName;
        this.memMiddileName = memMiddileName;
        this.memLastName = memLastName;
        this.fFirstName = fFirstName;
        this.fMiddileName = fMiddileName;
        this.fLastName = fLastName;
        this.gender = gender;
        this.dob = dob;
        this.nationality = nationality;
        this.birthPlace = birthPlace;
        this.occupationType = occupationType;
        this.pan = pan;
        this.adhar = adhar;
        this.mobile = mobile;
        this.landline = landline;
        this.email = email;
        this.proofOfIdentity = proofOfIdentity;
        this.proofOfAddress = proofOfAddress;
        this.permanentAddress = permanentAddress;
        this.permCountryId = permCountryId;
        this.permStateId = permStateId;
        this.permCityId = permCityId;
        this.permPinCode = permPinCode;
        this.permLandmark = permLandmark;
        this.presentAddress = presentAddress;
        this.presCountryId = presCountryId;
        this.presStateId = presStateId;
        this.presCityId = presCityId;
        this.presPinCode = presPinCode;
        this.presLandmark = presLandmark;
        this.durationOfStayPAddress = durationOfStayPAddress;
        this.educationalQualification = educationalQualification;
        this.appliedForNoOfShare = appliedForNoOfShare;
        this.shareTypeId = shareTypeId;
        this.membershipType = membershipType;
    }

}