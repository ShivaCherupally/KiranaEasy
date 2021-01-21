package com.goodeggs.android.repository

import androidx.lifecycle.MutableLiveData
import com.goodeggs.android.api.MyRetrofitBuilder
import com.goodeggs.android.model.*
import com.goodeggs.android.ui.paymentmode.OrderData
import com.goodeggs.android.utils.AppPreferences
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import org.json.JSONObject
import retrofit2.http.Field

object Repository {

    var job: CompletableJob? = null

    fun registerRepo(registerData: JSONObject): MutableLiveData<RegisterResponsedata> {
        job = Job()
        return object : MutableLiveData<RegisterResponsedata>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.registerAppData(registerData.getString("fullname"),
                                registerData.getString("mobile"),
                                registerData.getString("email"),
                                registerData.getString("password"),
                                registerData.getString("cityId")
                        )
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun loginUser(username: String?, password: String?): MutableLiveData<UserData> {
        job = Job()
        return object : MutableLiveData<UserData>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getUser(username, password)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }
                }

            }
        }
    }

    fun forgotpassword(emailid: String?): MutableLiveData<ForgotData> {
        job = Job()
        return object : MutableLiveData<ForgotData>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.forgotPassword(emailid)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun productInfoRepo(productid: String?): MutableLiveData<List<ProductCompleteInfo>> {
        job = Job()
        return object : MutableLiveData<List<ProductCompleteInfo>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getProductInfoReq(productid, AppPreferences.userId)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun orderInfoRepo(orderId: String?): MutableLiveData<List<OrderCompleteInfo>> {
        job = Job()
        return object : MutableLiveData<List<OrderCompleteInfo>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getOrderInfo(orderId)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }


    fun searchCategoryRepo(entry: String?): MutableLiveData<List<ProductInfo>> {
        job = Job()
        return object : MutableLiveData<List<ProductInfo>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.searchCategory(AppPreferences.userId, entry)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }


    fun getCategoeryList(): MutableLiveData<List<ProductTypesData>> {
        job = Job()
        return object : MutableLiveData<List<ProductTypesData>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getCategories(AppPreferences.userId)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun getDealsList(): MutableLiveData<List<DealData>> {
        job = Job()
        return object : MutableLiveData<List<DealData>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getDeals(AppPreferences.userId)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun getMyOrderListRepo(): MutableLiveData<List<MyOrderData>> {
        job = Job()
        return object : MutableLiveData<List<MyOrderData>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getMyOrderReq(AppPreferences.userId)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun getProductsRepo(): MutableLiveData<List<ProductTypesData>> {
        job = Job()
        return object : MutableLiveData<List<ProductTypesData>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getProducts(AppPreferences.userId)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun getCityListRepo(): MutableLiveData<List<CityData>> {
        job = Job()
        return object : MutableLiveData<List<CityData>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getCites()
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun getProductListRepo(category_id: String?, subcategory_id: String?): MutableLiveData<List<ProductData>> {
        job = Job()
        return object : MutableLiveData<List<ProductData>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getProducList(AppPreferences.userId, category_id, subcategory_id)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun getSubCategorylist(subcategory_id: String?): MutableLiveData<List<SubCategoryInfo>> {
        job = Job()
        return object : MutableLiveData<List<SubCategoryInfo>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getSubCategoryData(AppPreferences.userId, subcategory_id)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun getReturnReason(): MutableLiveData<List<ReturnReasonData>> {
        job = Job()
        return object : MutableLiveData<List<ReturnReasonData>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getReturnProduct()
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }


    fun addToCartRepo(user_id: String?, product_id: String?,
                      product_name: String?, quantity: Int?,
                      sale_price: Int?): MutableLiveData<CartResponse> {
        job = Job()
        return object : MutableLiveData<CartResponse>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.addCartdata(user_id, product_id, product_name, quantity, sale_price)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }


    fun getCartList(subcategory_id: String?): MutableLiveData<List<CartData>> {
        job = Job()
        return object : MutableLiveData<List<CartData>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getCartList(subcategory_id)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun changepasswordRepo(password: String?): MutableLiveData<ForgotData> {
        job = Job()
        return object : MutableLiveData<ForgotData>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.changePassword(AppPreferences.userId, password)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun returnReson(orderId: String?, product_id: String?, product_qty: String?, pack_qty: String?, return_type: String?): MutableLiveData<ForgotData> {
        job = Job()
        return object : MutableLiveData<ForgotData>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.returnProduct(orderId, product_id, product_qty, pack_qty, return_type)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun removeCartRepo(cart_id: String?): MutableLiveData<ForgotData> {
        job = Job()
        return object : MutableLiveData<ForgotData>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.removeCart(cart_id)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun cancelOrderRepo(order_id: String?): MutableLiveData<List<CancelOrderData>> {
        job = Job()
        return object : MutableLiveData<List<CancelOrderData>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.cancelorder(order_id)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun forceupdateRepo(versionno: String?): MutableLiveData<List<ForceUpdateData>> {
        job = Job()
        return object : MutableLiveData<List<ForceUpdateData>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.forceUpdateApp(versionno, "android")
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }


                }

            }
        }
    }

    fun orderCartRepo(user_id: String?, orders: String?): MutableLiveData<OrderData> {
        job = Job()
        return object : MutableLiveData<OrderData>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.ordertheCart(user_id, orders)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun addToCartRepo(addressJson: JSONObject?): MutableLiveData<AddressUpdate> {
        job = Job()
        return object : MutableLiveData<AddressUpdate>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.updateAddress(addressJson?.getString("address"),
                                addressJson?.getString("location"), addressJson?.getString("city"),
                                addressJson?.getString("pincode"), addressJson?.getString("state"), addressJson?.getString("landmark"),
                                addressJson?.getString("user_id"))
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun offerDataRepo(): MutableLiveData<List<OfferData>> {
        job = Job()
        return object : MutableLiveData<List<OfferData>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.offerdata()
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun editOrderRepo(op_id: String?, product_qty: Int?,
                      pack_qty: Int?): MutableLiveData<EditOrder> {
        job = Job()
        return object : MutableLiveData<EditOrder>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.editOrder(op_id, product_qty, pack_qty)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }

}
















