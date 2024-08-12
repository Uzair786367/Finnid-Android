package com.infotech.finnid.Utils;

import com.google.gson.JsonObject;
import com.infotech.finnid.ApiRequest.ChangePassReq;

import com.infotech.finnid.ApiRequest.InventoryPurchaseRequest;
import com.infotech.finnid.ApiRequest.LoginRequest;
import com.infotech.finnid.ApiRequest.MemberListRequest;
import com.infotech.finnid.ApiRequest.MemberRegistrationReq;
import com.infotech.finnid.ApiResponse.AddressProofResponse;
import com.infotech.finnid.ApiResponse.CityListResponse;
import com.infotech.finnid.ApiResponse.CountryListResponse;
import com.infotech.finnid.ApiResponse.FieldUOMResponse;
import com.infotech.finnid.ApiResponse.GetSalesVoucherResponse;
import com.infotech.finnid.ApiResponse.IdentityProofResponse;
import com.infotech.finnid.ApiResponse.LoginResponse;
import com.infotech.finnid.ApiResponse.MemberListResponse;
import com.infotech.finnid.ApiResponse.ShareTypeResponse;
import com.infotech.finnid.ApiResponse.StateListResponse;
import com.infotech.finnid.ApiResponse.InventorySaleRequest.InventorySaleRequest;
import com.infotech.finnid.Recharge.ApiRespose.OperatorRespose;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EndPoint {


    @Headers("Content-Type: application/json")
    @POST("api/Account/CompanySignIn")
    Call<LoginResponse> secureLogin(@Body LoginRequest appInfo);


    @Headers("Content-Type: application/json")
    @POST("api/Account/ChangePassword")
    Call<LoginResponse>changePassword(@Body ChangePassReq appInfo);

    @Headers("Content-Type: application/json")
    @POST("api/Company/MemberRegistration")
    Call<LoginResponse>MemberRegistration(@Header("Authorization") String token,@Body MemberRegistrationReq appInfo);

    @Headers("Content-Type: application/json")
    @POST("api/Company/RegisterNominee")
    Call<LoginResponse>RegisterNominee(@Header("Authorization") String token,@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("api/Company/GetMemberList")
    Call<MemberListResponse>GetMemberList(@Header("Authorization") String token, @Body MemberListRequest appInfo);




    @Headers("Content-Type: application/json")
    @POST("/api/Inventory/Purchase")
    Call<MemberListResponse>GetInventoryPurchase(@Header("Authorization") String token, @Body InventoryPurchaseRequest appInfo);

    @Headers("Content-Type: application/json")
    @POST("/api/Inventory/Sale")
    Call<MemberListResponse>InventorySale(@Header("Authorization") String token, @Body InventorySaleRequest appInfo);

    @Headers("Content-Type: application/json")
    @GET("api/Master/GetFieldUOM")
    Call<FieldUOMResponse>GetFieldUOM(@Header("Authorization") String token);
    @Headers("Content-Type: application/json")
    @GET("api/Master/CountryList")
    Call<CountryListResponse>CountryList(@Header("Authorization") String token);



 @Headers("Content-Type: application/json")
    @POST("/api/Inventory/GetMasterItemDetails")
    Call<CountryListResponse>GetMasterItemDetails(@Header("Authorization") String token ,  @Body JsonObject jsonObject);




 @Headers("Content-Type: application/json")
    @POST("/api/Inventory/CustomerDataById")
    Call<CountryListResponse>CustomerDataById(@Header("Authorization") String token ,  @Body JsonObject jsonObject);



 @Headers("Content-Type: application/json")
    @POST("/api/Inventory/GetSalesVoucherItemDetails")
    Call<GetSalesVoucherResponse>GetSalesVoucherItemDetails(@Header("Authorization") String token , @Body JsonObject jsonObject);



    @Headers("Content-Type: application/json")
    @GET("/api/Master/GetPaymentMode")
    Call<CountryListResponse>PAymentMode(@Header("Authorization") String token  );


    @Headers("Content-Type: application/json")
    @GET("/api/Inventory/GetUOM/{countryId}")
    Call<CountryListResponse>GetUOM(@Header("Authorization") String token, @Path("countryId") Integer id);

 @Headers("Content-Type: application/json")
    @GET("/api/Master/GetSubPaymentMode")
    Call<CountryListResponse>GetSubPaymentMode(@Header("Authorization") String token );



    @Headers("Content-Type: application/json")
    @POST("/api/SaleCart/Add")
    Call<CountryListResponse>SaleCartAdd(@Header("Authorization") String token, @Body JsonObject jsonObject);


@Headers("Content-Type: application/json")
    @POST("/api/NeoBanking/GetOperator")
    Call<OperatorRespose>GetOperator(@Header("Authorization") String token, @Body JsonObject jsonObject);

@Headers("Content-Type: application/json")
    @POST("/api/NeoBanking/DoRecharge")
    Call<OperatorRespose>DoRechage(@Header("Authorization") String token, @Body JsonObject jsonObject);

@Headers("Content-Type: application/json")
    @POST("/api/Company/UploadMemberDocuments")
    Call<OperatorRespose>UploadDocumentAdd(@Header("Authorization") String token, @Body JsonObject jsonObject);

@Headers("Content-Type: application/json")
    @GET("/api/NeoBanking/GetCircleCode")
    Call<OperatorRespose>GetCircleCode(@Header("Authorization") String token );



     @Headers("Content-Type: application/json")
    @POST("/api/SaleCart/Delete")
    Call<CountryListResponse>Delete_Cart(@Header("Authorization") String token, @Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST("/api/SaleCart/GetCartList")
    Call<GetSalesVoucherResponse>GetCartList(@Header("Authorization") String token, @Body JsonObject jsonObject);



     @Headers("Content-Type: application/json")
    @GET("api/Master/StateList/{countryId}")
    Call<StateListResponse>StateList(@Header("Authorization") String token, @Path("countryId") Integer id);

    @Headers("Content-Type: application/json")
    @POST("api/Company/GetSupplierList")
    Call<StateListResponse>GetSupplierList(@Header("Authorization") String token, @Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @GET("api/Master/GetCity/{stateId}")
    Call<CityListResponse>GetCity(@Header("Authorization") String token, @Path("stateId") Integer id);
    @Headers("Content-Type: application/json")
    @GET("api/Master/GetShareType")
    Call<ShareTypeResponse>GetShareType(@Header("Authorization") String token);
    @Headers("Content-Type: application/json")
    @GET("api/Master/GetProofOfIdentity")
    Call<IdentityProofResponse>GetProofOfIdentity(@Header("Authorization") String token);
    @Headers("Content-Type: application/json")
    @GET("api/Master/GetProofOfAddress")
    Call<AddressProofResponse>GetProofOfAddress(@Header("Authorization") String token);
    @Headers("Content-Type: application/json")
    @GET("api/Master/GetCompanyDesignation")
    Call<LoginResponse>GetCompanyDesignation(@Header("Authorization") String token);


}
