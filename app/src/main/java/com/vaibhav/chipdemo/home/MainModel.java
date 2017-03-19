package com.vaibhav.chipdemo.home;

import com.vaibhav.chipdemo.network.ApiClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by vaibhav on 19/3/17.
 */

public class MainModel {
    private static MainModel mainModel;
    private ApiClient apiClient;

    static MainModel getInstance() {
        if (mainModel == null)
            mainModel = new MainModel();
        return mainModel;
    }

    Observable<List<String>> getData() {

        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                Call<List<String>> data = ApiClient.getInstance().getService().getData();
                Response<List<String>> execute = data.execute();
                if (execute.isSuccessful())
                    e.onNext(execute.body());
                else
                    e.onError(new RuntimeException(execute.errorBody().string()));
            }
        });


    }


}
