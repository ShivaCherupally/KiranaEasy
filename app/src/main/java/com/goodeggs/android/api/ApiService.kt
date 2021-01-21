package com.goodeggs.android.api

import com.goodeggs.android.model.*
import com.goodeggs.android.ui.paymentmode.OrderData
import com.goodeggs.android.utils.Constants
import retrofit2.http.*


interface ApiService {

    @POST(Constants.LOGIN_URL)
    @FormUrlEncoded
    suspend fun getUser(
            @Field("vendor_email") username: String?,
            @Field("password") password: String?
    ): UserData


    @POST(Constants.PRODUCTS_URL)
    @FormUrlEncoded
    suspend fun getProducts(@Field("user_id") userid: String?
    ): List<ProductTypesData>


    @POST(Constants.FORGOT_PWD_URL)
    @FormUrlEncoded
    suspend fun forgotPassword(
            @Field("email") username: String?
    ): ForgotData

    @POST(Constants.PRODUCT_INFO_URL)
    @FormUrlEncoded
    suspend fun getProductInfoReq(
            @Field("product_id") productid: String?,
            @Field("user_id") userid: String?
    ): List<ProductCompleteInfo>


    @POST(Constants.VIEW_ORDER)
    @FormUrlEncoded
    suspend fun getOrderInfo(@Field("order_id") order_id: String?): List<OrderCompleteInfo>


    @POST(Constants.MYORDERS_LIST_URL)
    @FormUrlEncoded
    suspend fun getMyOrderReq(
            @Field("user_id") user_id: String?
    ): List<MyOrderData>


    @POST(Constants.PRODUCTS_URL)
    @FormUrlEncoded
    suspend fun getCategories(@Field("user_id") user_id: String?
    ): List<ProductTypesData>

    @POST(Constants.BANNERS_LIST_URL)
    @FormUrlEncoded
    suspend fun getDeals(@Field("user_id") user_id: String?
    ): List<DealData>


    @GET("citylist")
    suspend fun getCites(
    ): List<CityData>


    @POST(Constants.CATEGORY_BASED_PRODUCTS_URL)
    @FormUrlEncoded
    suspend fun getProducList(
            @Field("user_id") user_id: String?,
            @Field("category_id") category_id: String?,
            @Field("subcategory_id") subcategory_id: String?
    ): List<ProductData>

    @POST(Constants.SUB_CATEGORIES_URL)
    @FormUrlEncoded
    suspend fun getSubCategoryData(
            @Field("user_id") user_id: String?,
            @Field("category_id") subcategory_id: String?
    ): List<SubCategoryInfo>


    @POST(Constants.PRODUCT_RETURN)
    suspend fun getReturnProduct(
    ): List<ReturnReasonData>


    @POST(Constants.ADD_TO_CART_URL)
    @FormUrlEncoded
    suspend fun addCartdata(@Field("user_id") user_id: String?,
                            @Field("product_id") product_id: String?,
                            @Field("product_name") product_name: String?,
                            @Field("pack_qty") quantity: Int?,
                            @Field("product_qty") sale_price: Int?): CartResponse

    @POST(Constants.CART_LIST_URL)
    @FormUrlEncoded
    suspend fun getCartList(@Field("user_id") subcategory_id: String?
    ): List<CartData>


    @POST(Constants.REGISTER_URL)
    @FormUrlEncoded
    suspend fun registerAppData(@Field("fullname") fullname: String?,
                                @Field("mobile") mobile: String?,
                                @Field("email") email: String?,
                                @Field("password") password: String?,
                                @Field("cityId") cityId: String?
    ): RegisterResponsedata


    @POST(Constants.SEARCH_URL)
    @FormUrlEncoded
    suspend fun searchCategory(
            @Field("user_id") userid: String?,
            @Field("keyword") entry: String?
    ): List<ProductInfo>

    @POST(Constants.CHANGE_PWD_URL)
    @FormUrlEncoded
    suspend fun changePassword(
            @Field("user_id") userid: String?,
            @Field("password") password: String?
    ): ForgotData


    @POST(Constants.RETURN_PRODUCT)
    @FormUrlEncoded
    suspend fun returnProduct(
            @Field("order_id") userid: String?,
            @Field("product_id") product_id: String?,
            @Field("product_qty") product_qty: String?,
            @Field("pack_qty") pack_qty: String?,
            @Field("return_type") return_type: String?
    ): ForgotData

    @POST(Constants.REMOVE_CART_URL)
    @FormUrlEncoded
    suspend fun removeCart(@Field("cart_id") password: String?): ForgotData

    @POST(Constants.CANCEL_ORDER_URL)
    @FormUrlEncoded
    suspend fun cancelorder(@Field("order_id") password: String?): List<CancelOrderData>


    @POST(Constants.FORCE_UPDATE_URL)
    @FormUrlEncoded
    suspend fun forceUpdateApp(@Field("version_no") version_no: String?, @Field("os_type") os_type: String?): List<ForceUpdateData>


    @POST(Constants.OFFER_URL)
    suspend fun offerdata(): List<OfferData>


    @POST(Constants.EDIT_ORDER)
    @FormUrlEncoded
    suspend fun editOrder(@Field("op_id") op_id: String?, @Field("product_qty") product_qty: Int?,
                          @Field("pack_qty") pack_qty: Int?): EditOrder

    @POST(Constants.NEW_ORDER_URL)
    @FormUrlEncoded
    suspend fun ordertheCart(
            @Field("user_id") userid: String?,
            @Field("orders") password: String?
    ): OrderData

    @POST(Constants.UPDATE_ADDRESS_URL)
    @FormUrlEncoded
    suspend fun updateAddress(
            @Field("address") address: String?,
            @Field("location") location: String?,
            @Field("city") city: String?,
            @Field("pincode") pincode: String?,
            @Field("state") state: String?,
            @Field("landmark") landmark: String?,
            @Field("user_id") user_id: String?
    ): AddressUpdate

}