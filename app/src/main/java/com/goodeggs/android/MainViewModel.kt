package com.goodeggs.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goodeggs.android.model.*
import com.goodeggs.android.repository.Repository
import com.goodeggs.android.ui.paymentmode.OrderData
import org.json.JSONObject

class MainViewModel : ViewModel() {

    fun registerAppViewModel(registerData: JSONObject): MutableLiveData<RegisterResponsedata>? {
        return Repository.registerRepo(registerData)
    }

    fun getLoginResponse(email: String?, password: String?): LiveData<UserData?>? {
        return Repository.loginUser(email, password)
    }

    fun getHolidays(): LiveData<List<ProductTypesData>> {
        return Repository.getCategoeryList()
    }

    fun getDeals(): LiveData<List<DealData>> {
        return Repository.getDealsList()
    }

    fun getOrderList(): LiveData<List<MyOrderData>> {
        return Repository.getMyOrderListRepo()
    }

    fun getEggTypes(): LiveData<List<ProductTypesData>> {
        return Repository.getProductsRepo()
    }

    fun getCityList(): LiveData<List<CityData>> {
        return Repository.getCityListRepo()
    }

    fun fogotPasswordReq(email: String?): LiveData<ForgotData?>? {
        return Repository.forgotpassword(email)
    }

    fun getProductListVM(category_id: String?, subcategory_id: String?): LiveData<List<ProductData>> {
        return Repository.getProductListRepo(category_id, subcategory_id)
    }

    fun getSubCatogoryList(subcategory_id: String?): LiveData<List<SubCategoryInfo>> {
        return Repository.getSubCategorylist(subcategory_id)
    }

    fun getReturnReasons(): LiveData<List<ReturnReasonData>> {
        return Repository.getReturnReason()
    }


    fun addCartProduct(user_id: String?, product_id: String?,
                       product_name: String?, quantity: Int?,
                       sale_price: Int?): LiveData<CartResponse> {
        return Repository.addToCartRepo(user_id, product_id, product_name, quantity, sale_price)
    }

    fun getCartList(subcategory_id: String?): LiveData<List<CartData>> {
        return Repository.getCartList(subcategory_id)
    }


    fun searchCategoryViewmodel(entry: String?): LiveData<List<ProductInfo>>? {
        return Repository.searchCategoryRepo(entry)
    }

    fun productInfoReq(productId: String?): LiveData<List<ProductCompleteInfo>>? {
        return Repository.productInfoRepo(productId)
    }

    fun orderInfoReq(orderId: String?): LiveData<List<OrderCompleteInfo>>? {
        return Repository.orderInfoRepo(orderId)
    }

    fun changepasswordReq(password: String?): LiveData<ForgotData>? {
        return Repository.changepasswordRepo(password)
    }

    fun sendReturnReason(orderId: String?, product_id: String?, product_qty: String?, pack_qty: String?, return_type: String?
    ): LiveData<ForgotData>? {
        return Repository.returnReson(orderId, product_id, product_qty, pack_qty, return_type)
    }

    fun removeCartReq(cart_id: String?): LiveData<ForgotData>? {
        return Repository.removeCartRepo(cart_id)
    }

    fun orderCartReq(user_id: String?, orders: String?): LiveData<OrderData?>? {
        return Repository.orderCartRepo(user_id, orders)
    }

    fun updateAddressReq(addressjson: JSONObject?): LiveData<AddressUpdate> {
        return Repository.addToCartRepo(addressjson)
    }

    fun cancelOrder(order_id: String?): LiveData<List<CancelOrderData>>? {
        return Repository.cancelOrderRepo(order_id)
    }

    fun forceupdate(versionno: String?): LiveData<List<ForceUpdateData>>? {
        return Repository.forceupdateRepo(versionno)
    }

    fun getOfferData(): LiveData<List<OfferData>>? {
        return Repository.offerDataRepo()
    }

    fun editOrderInfo(op_id: String?, product_qty: Int?,
                      pack_qty: Int?): LiveData<EditOrder> {
        return Repository.editOrderRepo(op_id, product_qty, pack_qty)
    }

    fun cancelJobs() {
        Repository.cancelJobs()
    }

}















