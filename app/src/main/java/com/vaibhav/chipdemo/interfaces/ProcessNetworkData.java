package com.vaibhav.chipdemo.interfaces;

import android.content.Context;
import android.view.View;

/**
 * Created by vaibhav on 19/3/17.
 */

public interface ProcessNetworkData<T> {

    void ProcessData(T t, Context context, View view);
}
