package com.kiranam.android;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kiranam.android.models.Message;
import com.kiranam.android.models.NetworkCall;
import com.kiranam.android.models.NetworkStatus;
import com.kiranam.android.models.Student;
import com.kiranam.android.models.StudentListResponse;
import com.kiranam.android.retrofit2.AppRetrofitAdapter;
import com.kiranam.android.services.IStudentService;
import com.kiranam.android.utils.Utility;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class StudentViewModel extends ViewModel {

    private MutableLiveData<NetworkCall> networkCall;
    private MutableLiveData<Message> message;
    private Disposable disposable;
    private List<Student> allStudents;
    private MutableLiveData<List<Student>> studentLiveData, loadMoreStudents;
    private int items = 0;
    private MutableLiveData<Boolean> loadMore;

    public StudentViewModel() {
        networkCall = new MutableLiveData<>();
        message = new MutableLiveData<>();
        studentLiveData = new MutableLiveData<>();
    }

    public class NetworkTags {
        public static final int GET_ALL_STUDENTS = 1;
    }

    public LiveData<Message> getMessage() {
        return message;
    }

    public LiveData<NetworkCall> getNetworkCall() {
        return networkCall;
    }

    public LiveData<List<Student>> getAllStudents() {
        return studentLiveData;
    }

    public void fetchAllStudents() {
        if (!Utility.isNetworkAvailable(Utility.getContext())) {
            networkCall.postValue(new NetworkCall(NetworkTags.GET_ALL_STUDENTS, NetworkStatus.NO_INTERNET));
            return;
        }
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        networkCall.postValue(new NetworkCall(NetworkTags.GET_ALL_STUDENTS, NetworkStatus.IN_PROCESS));

        Observer<Response<StudentListResponse>> observer = new Observer<Response<StudentListResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Response<StudentListResponse> response) {
                if (response.code() == 200 && response.body() != null) {
                    if (response.body().getStudentAllInfoList() != null && response.body().getStudentAllInfoList().size() > 0) {
                        allStudents = new ArrayList<>();
                        allStudents.addAll(response.body().getStudentAllInfoList());
                        items = allStudents.size() > Constants.PAGING_LIMIT ? Constants.PAGING_LIMIT : allStudents.size();
                        List<Student> currentList = new ArrayList<>();
                        currentList.addAll(allStudents.subList(0, allStudents.size() > Constants.PAGING_LIMIT ? Constants.PAGING_LIMIT : allStudents.size()));
                        studentLiveData.postValue(currentList);
                        networkCall.postValue(new NetworkCall(NetworkTags.GET_ALL_STUDENTS, NetworkStatus.SUCCESS));
                    } else {
                        message.postValue(new Message(Constants.NO_DATA_AVAILABLE, 2));
                        networkCall.postValue(new NetworkCall(NetworkTags.GET_ALL_STUDENTS, NetworkStatus.NO_DATA));
                    }
                } else {
                    message.postValue(new Message(Constants.SOMETHING_WENT_WRONG, 0));
                    networkCall.postValue(new NetworkCall(NetworkTags.GET_ALL_STUDENTS, NetworkStatus.FAIL));
                }
            }

            @Override
            public void onError(Throwable e) {
                message.postValue(new Message(e.getMessage(), 0));
                networkCall.postValue(new NetworkCall(NetworkTags.GET_ALL_STUDENTS, NetworkStatus.ERROR));
            }

            @Override
            public void onComplete() {

            }
        };


        AppRetrofitAdapter.getRetrofit().create(IStudentService.class).getAllStudentsBySchoolId(Constants.SCHOOL_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }


    public List<Student> getLoadMore() {
        if (items >= allStudents.size())
            return null;

        int previous = items;
        if (allStudents.size() > items + Constants.PAGING_LIMIT) {
            items += Constants.PAGING_LIMIT;
        } else {
            items = allStudents.size();
        }

        return allStudents.subList(previous - 1, items);
    }

}
