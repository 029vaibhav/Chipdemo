package com.vaibhav.chipdemo.interfaces;

/**
 * Created by vaibhav on 19/3/17.
 */

public class NetworkDataFactory {

    public static ProcessNetworkData getImpl() {
        return new ProcessNetworkDataImpl();
    }
}
