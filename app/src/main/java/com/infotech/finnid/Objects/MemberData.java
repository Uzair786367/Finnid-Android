package com.infotech.finnid.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberData {

    @SerializedName("memberId")
    @Expose
    private String memberId;

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


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLandOwnershipType() {
        return landOwnershipType;
    }

    public void setLandOwnershipType(String landOwnershipType) {
        this.landOwnershipType = landOwnershipType;
    }

    public String getRelationWithFamilyMember() {
        return relationWithFamilyMember;
    }

    public void setRelationWithFamilyMember(String relationWithFamilyMember) {
        this.relationWithFamilyMember = relationWithFamilyMember;
    }

    public String getRelativeName() {
        return relativeName;
    }

    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }

    public String getKhatauniNo() {
        return khatauniNo;
    }

    public void setKhatauniNo(String khatauniNo) {
        this.khatauniNo = khatauniNo;
    }

    public Integer getUomId() {
        return uomId;
    }

    public void setUomId(Integer uomId) {
        this.uomId = uomId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getMemberCategory() {
        return memberCategory;
    }

    public void setMemberCategory(String memberCategory) {
        this.memberCategory = memberCategory;
    }

    public String getMemFirstName() {
        return memFirstName;
    }

    public void setMemFirstName(String memFirstName) {
        this.memFirstName = memFirstName;
    }

    public String getMemMiddileName() {
        return memMiddileName;
    }

    public void setMemMiddileName(String memMiddileName) {
        this.memMiddileName = memMiddileName;
    }

    public String getMemLastName() {
        return memLastName;
    }

    public void setMemLastName(String memLastName) {
        this.memLastName = memLastName;
    }

    public String getfFirstName() {
        return fFirstName;
    }

    public void setfFirstName(String fFirstName) {
        this.fFirstName = fFirstName;
    }

    public String getfMiddileName() {
        return fMiddileName;
    }

    public void setfMiddileName(String fMiddileName) {
        this.fMiddileName = fMiddileName;
    }

    public String getfLastName() {
        return fLastName;
    }

    public void setfLastName(String fLastName) {
        this.fLastName = fLastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getAdhar() {
        return adhar;
    }

    public void setAdhar(String adhar) {
        this.adhar = adhar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getProofOfIdentity() {
        return proofOfIdentity;
    }

    public void setProofOfIdentity(Integer proofOfIdentity) {
        this.proofOfIdentity = proofOfIdentity;
    }

    public Integer getProofOfAddress() {
        return proofOfAddress;
    }

    public void setProofOfAddress(Integer proofOfAddress) {
        this.proofOfAddress = proofOfAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Integer getPermCountryId() {
        return permCountryId;
    }

    public void setPermCountryId(Integer permCountryId) {
        this.permCountryId = permCountryId;
    }

    public Integer getPermStateId() {
        return permStateId;
    }

    public void setPermStateId(Integer permStateId) {
        this.permStateId = permStateId;
    }

    public Integer getPermCityId() {
        return permCityId;
    }

    public void setPermCityId(Integer permCityId) {
        this.permCityId = permCityId;
    }

    public String getPermPinCode() {
        return permPinCode;
    }

    public void setPermPinCode(String permPinCode) {
        this.permPinCode = permPinCode;
    }

    public String getPermLandmark() {
        return permLandmark;
    }

    public void setPermLandmark(String permLandmark) {
        this.permLandmark = permLandmark;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public Integer getPresCountryId() {
        return presCountryId;
    }

    public void setPresCountryId(Integer presCountryId) {
        this.presCountryId = presCountryId;
    }

    public Integer getPresStateId() {
        return presStateId;
    }

    public void setPresStateId(Integer presStateId) {
        this.presStateId = presStateId;
    }

    public Integer getPresCityId() {
        return presCityId;
    }

    public void setPresCityId(Integer presCityId) {
        this.presCityId = presCityId;
    }

    public String getPresPinCode() {
        return presPinCode;
    }

    public void setPresPinCode(String presPinCode) {
        this.presPinCode = presPinCode;
    }

    public String getPresLandmark() {
        return presLandmark;
    }

    public void setPresLandmark(String presLandmark) {
        this.presLandmark = presLandmark;
    }

    public Integer getDurationOfStayPAddress() {
        return durationOfStayPAddress;
    }

    public void setDurationOfStayPAddress(Integer durationOfStayPAddress) {
        this.durationOfStayPAddress = durationOfStayPAddress;
    }

    public String getEducationalQualification() {
        return educationalQualification;
    }

    public void setEducationalQualification(String educationalQualification) {
        this.educationalQualification = educationalQualification;
    }

    public Integer getAppliedForNoOfShare() {
        return appliedForNoOfShare;
    }

    public void setAppliedForNoOfShare(Integer appliedForNoOfShare) {
        this.appliedForNoOfShare = appliedForNoOfShare;
    }

    public Integer getShareTypeId() {
        return shareTypeId;
    }

    public void setShareTypeId(Integer shareTypeId) {
        this.shareTypeId = shareTypeId;
    }

    public Integer getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(Integer membershipType) {
        this.membershipType = membershipType;
    }




    /*
    @SerializedName("memberId")
    @Expose
    private String memberId;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("memberCategory")
    @Expose
    private String memberCategory;
    @SerializedName("mem_FirstName")
    @Expose
    private String memFirstName;
    @SerializedName("mem_MiddileName")
    @Expose
    private String memMiddileName;
    @SerializedName("mem_LastName")
    @Expose
    private String memLastName;
    @SerializedName("f_FirstName")
    @Expose
    private String fFirstName;
    @SerializedName("f_MiddileName")
    @Expose
    private String fMiddileName;
    @SerializedName("f_LastName")
    @Expose
    private String fLastName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("birthPlace")
    @Expose
    private String birthPlace;
    @SerializedName("occupationType")
    @Expose
    private String occupationType;
    @SerializedName("educationalQualification")
    @Expose
    private String educationalQualification;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("pan")
    @Expose
    private String pan;
    @SerializedName("form_60")
    @Expose
    private String form60;
    @SerializedName("adhar")
    @Expose
    private String adhar;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("isFormUpload")
    @Expose
    private Boolean isFormUpload;
    @SerializedName("isVerified")
    @Expose
    private Boolean isVerified;
    @SerializedName("permanentAddress")
    @Expose
    private String permanentAddress;
    @SerializedName("permCountryId")
    @Expose
    private Integer permCountryId;
    @SerializedName("permanentCountry")
    @Expose
    private String permanentCountry;
    @SerializedName("permStateId")
    @Expose
    private Integer permStateId;
    @SerializedName("permCityId")
    @Expose
    private Integer permCityId;
    @SerializedName("permanentState")
    @Expose
    private String permanentState;
    @SerializedName("permanentCity")
    @Expose
    private String permanentCity;
    @SerializedName("presentAddress")
    @Expose
    private String presentAddress;
    @SerializedName("permPinCode")
    @Expose
    private String permPinCode;
    @SerializedName("presCountryId")
    @Expose
    private Integer presCountryId;
    @SerializedName("presStateId")
    @Expose
    private Integer presStateId;
    @SerializedName("presentCountry")
    @Expose
    private String presentCountry;
    @SerializedName("presentState")
    @Expose
    private String presentState;
    @SerializedName("presCityId")
    @Expose
    private Integer presCityId;
    @SerializedName("presentCity")
    @Expose
    private String presentCity;
    @SerializedName("presPinCode")
    @Expose
    private String presPinCode;
    @SerializedName("proofOfAddress")
    @Expose
    private String proofOfAddress;
    @SerializedName("proofOfAddressDoc")
    @Expose
    private String proofOfAddressDoc;
    @SerializedName("addresProofName")
    @Expose
    private String addresProofName;
    @SerializedName("proofOfIdentity")
    @Expose
    private String proofOfIdentity;
    @SerializedName("identityName")
    @Expose
    private String identityName;
    @SerializedName("proofOfIdentityDoc")
    @Expose
    private String proofOfIdentityDoc;
    @SerializedName("durationOfStayPAddress")
    @Expose
    private String durationOfStayPAddress;
    @SerializedName("memberPhoto")
    @Expose
    private String memberPhoto;
    @SerializedName("signaturePhoto")
    @Expose
    private String signaturePhoto;
    @SerializedName("appliedForNoOfShare")
    @Expose
    private String appliedForNoOfShare;
    @SerializedName("shareApplicationForm")
    @Expose
    private String shareApplicationForm;
    @SerializedName("allotShareFormDoc")
    @Expose
    private Object allotShareFormDoc;
    @SerializedName("isShareApply")
    @Expose
    private Boolean isShareApply;
    @SerializedName("isShareCancel")
    @Expose
    private Boolean isShareCancel;
    @SerializedName("isShareAlloted")
    @Expose
    private Boolean isShareAlloted;
    @SerializedName("isPass3Filed")
    @Expose
    private Boolean isPass3Filed;
    @SerializedName("pass3FiledUploadId")
    @Expose
    private Integer pass3FiledUploadId;
    @SerializedName("companyId")
    @Expose
    private Integer companyId;
    @SerializedName("officeId")
    @Expose
    private Integer officeId;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getMemberCategory() {
        return memberCategory;
    }

    public void setMemberCategory(String memberCategory) {
        this.memberCategory = memberCategory;
    }

    public String getMemFirstName() {
        return memFirstName;
    }

    public void setMemFirstName(String memFirstName) {
        this.memFirstName = memFirstName;
    }

    public String getMemMiddileName() {
        return memMiddileName;
    }

    public void setMemMiddileName(String memMiddileName) {
        this.memMiddileName = memMiddileName;
    }

    public String getMemLastName() {
        return memLastName;
    }

    public void setMemLastName(String memLastName) {
        this.memLastName = memLastName;
    }

    public String getfFirstName() {
        return fFirstName;
    }

    public void setfFirstName(String fFirstName) {
        this.fFirstName = fFirstName;
    }

    public String getfMiddileName() {
        return fMiddileName;
    }

    public void setfMiddileName(String fMiddileName) {
        this.fMiddileName = fMiddileName;
    }

    public String getfLastName() {
        return fLastName;
    }

    public void setfLastName(String fLastName) {
        this.fLastName = fLastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }

    public String getEducationalQualification() {
        return educationalQualification;
    }

    public void setEducationalQualification(String educationalQualification) {
        this.educationalQualification = educationalQualification;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPan() {
        return pan;
    }

    public void String(String pan) {
        this.pan = pan;
    }

    public String getForm60() {
        return form60;
    }

    public void setForm60(String form60) {
        this.form60 = form60;
    }

    public String getAdhar() {
        return adhar;
    }

    public void setAdhar(String adhar) {
        this.adhar = adhar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsFormUpload() {
        return isFormUpload;
    }

    public void setIsFormUpload(Boolean isFormUpload) {
        this.isFormUpload = isFormUpload;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Integer getPermCountryId() {
        return permCountryId;
    }

    public void setPermCountryId(Integer permCountryId) {
        this.permCountryId = permCountryId;
    }

    public String getPermanentCountry() {
        return permanentCountry;
    }

    public void setPermanentCountry(String permanentCountry) {
        this.permanentCountry = permanentCountry;
    }

    public Integer getPermStateId() {
        return permStateId;
    }

    public void setPermStateId(Integer permStateId) {
        this.permStateId = permStateId;
    }

    public Integer getPermCityId() {
        return permCityId;
    }

    public void setPermCityId(Integer permCityId) {
        this.permCityId = permCityId;
    }

    public String getPermanentState() {
        return permanentState;
    }

    public void setPermanentState(String permanentState) {
        this.permanentState = permanentState;
    }

    public String getPermanentCity() {
        return permanentCity;
    }

    public void setPermanentCity(String permanentCity) {
        this.permanentCity = permanentCity;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getPermPinCode() {
        return permPinCode;
    }

    public void setPermPinCode(String permPinCode) {
        this.permPinCode = permPinCode;
    }

    public Integer getPresCountryId() {
        return presCountryId;
    }

    public void setPresCountryId(Integer presCountryId) {
        this.presCountryId = presCountryId;
    }

    public Integer getPresStateId() {
        return presStateId;
    }

    public void setPresStateId(Integer presStateId) {
        this.presStateId = presStateId;
    }

    public String getPresentCountry() {
        return presentCountry;
    }

    public void setPresentCountry(String presentCountry) {
        this.presentCountry = presentCountry;
    }

    public String getPresentState() {
        return presentState;
    }

    public void setPresentState(String presentState) {
        this.presentState = presentState;
    }

    public Integer getPresCityId() {
        return presCityId;
    }

    public void setPresCityId(Integer presCityId) {
        this.presCityId = presCityId;
    }

    public String getPresentCity() {
        return presentCity;
    }

    public void setPresentCity(String presentCity) {
        this.presentCity = presentCity;
    }

    public String getPresPinCode() {
        return presPinCode;
    }

    public void setPresPinCode(String presPinCode) {
        this.presPinCode = presPinCode;
    }

    public String getProofOfAddress() {
        return proofOfAddress;
    }

    public void setProofOfAddress(String proofOfAddress) {
        this.proofOfAddress = proofOfAddress;
    }

    public String getProofOfAddressDoc() {
        return proofOfAddressDoc;
    }

    public void setProofOfAddressDoc(String proofOfAddressDoc) {
        this.proofOfAddressDoc = proofOfAddressDoc;
    }

    public String getAddresProofName() {
        return addresProofName;
    }

    public void setAddresProofName(String addresProofName) {
        this.addresProofName = addresProofName;
    }

    public String getProofOfIdentity() {
        return proofOfIdentity;
    }

    public void setProofOfIdentity(String proofOfIdentity) {
        this.proofOfIdentity = proofOfIdentity;
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    public String getProofOfIdentityDoc() {
        return proofOfIdentityDoc;
    }

    public void setProofOfIdentityDoc(String proofOfIdentityDoc) {
        this.proofOfIdentityDoc = proofOfIdentityDoc;
    }

    public String getDurationOfStayPAddress() {
        return durationOfStayPAddress;
    }

    public void setDurationOfStayPAddress(String durationOfStayPAddress) {
        this.durationOfStayPAddress = durationOfStayPAddress;
    }

    public String getMemberPhoto() {
        return memberPhoto;
    }

    public void setMemberPhoto(String memberPhoto) {
        this.memberPhoto = memberPhoto;
    }

    public String getSignaturePhoto() {
        return signaturePhoto;
    }

    public void setSignaturePhoto(String signaturePhoto) {
        this.signaturePhoto = signaturePhoto;
    }

    public String getAppliedForNoOfShare() {
        return appliedForNoOfShare;
    }

    public void setAppliedForNoOfShare(String appliedForNoOfShare) {
        this.appliedForNoOfShare = appliedForNoOfShare;
    }

    public String getShareApplicationForm() {
        return shareApplicationForm;
    }

    public void setShareApplicationForm(String shareApplicationForm) {
        this.shareApplicationForm = shareApplicationForm;
    }

    public Object getAllotShareFormDoc() {
        return allotShareFormDoc;
    }

    public void setAllotShareFormDoc(Object allotShareFormDoc) {
        this.allotShareFormDoc = allotShareFormDoc;
    }

    public Boolean getIsShareApply() {
        return isShareApply;
    }

    public void setIsShareApply(Boolean isShareApply) {
        this.isShareApply = isShareApply;
    }

    public Boolean getIsShareCancel() {
        return isShareCancel;
    }

    public void setIsShareCancel(Boolean isShareCancel) {
        this.isShareCancel = isShareCancel;
    }

    public Boolean getIsShareAlloted() {
        return isShareAlloted;
    }

    public void setIsShareAlloted(Boolean isShareAlloted) {
        this.isShareAlloted = isShareAlloted;
    }

    public Boolean getIsPass3Filed() {
        return isPass3Filed;
    }

    public void setIsPass3Filed(Boolean isPass3Filed) {
        this.isPass3Filed = isPass3Filed;
    }

    public Integer getPass3FiledUploadId() {
        return pass3FiledUploadId;
    }

    public void setPass3FiledUploadId(Integer pass3FiledUploadId) {
        this.pass3FiledUploadId = pass3FiledUploadId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/
}
