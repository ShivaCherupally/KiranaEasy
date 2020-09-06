//package com.kiranam.android.ui.data.model
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.kiranam.android.Constants
//import com.kiranam.android.StudentViewModel
//import com.kiranam.android.models.*
//import com.kiranam.android.retrofit2.AppRetrofitAdapter
//import com.kiranam.android.services.IServices
//import com.kiranam.android.utils.Utility.context
//import com.kiranam.android.utils.Utility.isNetworkAvailable
//import io.reactivex.Observer
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.disposables.Disposable
//import io.reactivex.schedulers.Schedulers
//import retrofit2.Response
//import java.util.*
//
//class SignupViewModel : ViewModel() {
//
//    private var networkCall: MutableLiveData<NetworkCall>? = null
//    private var message: MutableLiveData<Message>? = null
//    private var disposable: Disposable? = null
//    private var allStudents: List<Student>? = null
//    private var studentLiveData: MutableLiveData<SignupData>? = null
//
//    fun SignupViewModel() {
//        networkCall = MutableLiveData()
//        message = MutableLiveData()
//        studentLiveData = MutableLiveData()
//    }
//
//
//    object NetworkTags {
//        const val GET_ALL_STUDENTS = 1
//    }
//
//    fun getMessage(): LiveData<Message?>? {
//        return message
//    }
//
//    fun getNetworkCall(): LiveData<NetworkCall?>? {
//        return networkCall
//    }
//
//    fun getAllStudents(): MutableLiveData<List<Student>>? {
//        return studentLiveData
//    }
//
//    fun signupReqInfo() {
//        if (!isNetworkAvailable(context)) {
//            networkCall!!.postValue(NetworkCall(StudentViewModel.NetworkTags.GET_ALL_STUDENTS, NetworkStatus.NO_INTERNET))
//            return
//        }
////        SignupData SignupData = new SignupData();
//        if (disposable != null && !disposable!!.isDisposed()) {
//            disposable!!.dispose()
//        }
//        networkCall!!.postValue(NetworkCall(StudentViewModel.NetworkTags.GET_ALL_STUDENTS, NetworkStatus.IN_PROCESS))
//        val observer: Observer<Response<StudentListResponse?>> = object : Observer<Response<StudentListResponse?>> {
//            override fun onSubscribe(d: Disposable) {
//                disposable = d
//            }
//
//            override fun onNext(response: Response<StudentListResponse?>) {
//                if (response.code() == 200 && response.body() != null) {
//                    if (response.body()!!.studentAllInfoList != null && response.body()!!.studentAllInfoList.size > 0) {
//                        allStudents = ArrayList()
//                        Log.e("shiva", (allStudents as ArrayList<Student>).size.toString())
//                        (allStudents as ArrayList<Student>).addAll(response.body()!!.studentAllInfoList)
//                        items = if ((allStudents as ArrayList<Student>).size > Constants.PAGING_LIMIT) Constants.PAGING_LIMIT else (allStudents as ArrayList<Student>).size
//                        val currentList: MutableList<Student> = ArrayList()
//                        currentList.addAll((allStudents as ArrayList<Student>).subList(0, if ((allStudents as ArrayList<Student>).size >
//                                Constants.PAGING_LIMIT) Constants.PAGING_LIMIT else (allStudents as ArrayList<Student>).size))
//                        studentLiveData!!.postValue(currentList)
//                        networkCall!!.postValue(NetworkCall(StudentViewModel.NetworkTags.GET_ALL_STUDENTS, NetworkStatus.SUCCESS))
//                    } else {
//                        message!!.postValue(Message(Constants.NO_DATA_AVAILABLE, 2))
//                        networkCall!!.postValue(NetworkCall(StudentViewModel.NetworkTags.GET_ALL_STUDENTS, NetworkStatus.NO_DATA))
//                    }
//                } else {
//                    message!!.postValue(Message(Constants.SOMETHING_WENT_WRONG, 0))
//                    networkCall!!.postValue(NetworkCall(StudentViewModel.NetworkTags.GET_ALL_STUDENTS, NetworkStatus.FAIL))
//                }
//            }
//
//            override fun onError(e: Throwable) {
//                message!!.postValue(Message(e.message, 0))
//                networkCall!!.postValue(NetworkCall(StudentViewModel.NetworkTags.GET_ALL_STUDENTS, NetworkStatus.ERROR))
//            }
//
//            override fun onComplete() {}
//        }
//
//
//        AppRetrofitAdapter.getRetrofit().create(IServices::class.java).signupReq("Constants.SCHOOL_ID")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer)
//    }
//
//}