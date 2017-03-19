package com.vaibhav.chipdemo.home;

import android.content.Context;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.vaibhav.chipdemo.interfaces.NetworkDataFactory;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vaibhav on 19/3/17.
 */

public class MainPresenter {

    public static void destroy() {
        mainPresenter = null;
    }


    private static MainPresenter mainPresenter;
    Context context;

    public MainPresenter(Context context) {
        this.context = context;
    }

    static MainPresenter getInstance(Context context) {
        if (mainPresenter == null) {
            mainPresenter = new MainPresenter(context);
        }
        return mainPresenter;
    }

    public void getAndAddData(MultiAutoCompleteTextView chipsView) {

        MainModel.getInstance().getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> onPostExecute(results, chipsView),
                        throwable -> Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show());
    }


    private void onPostExecute(List<String> result, MultiAutoCompleteTextView chipsView) {
        String s1 = "";
        for (String s : result) {
            s1 = s1 + "," + s;
        }
        NetworkDataFactory.getImpl().ProcessData(s1, context, chipsView);

    }

}
