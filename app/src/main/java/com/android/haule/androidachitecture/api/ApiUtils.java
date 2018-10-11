package com.android.haule.androidachitecture.api;

/**
 * Created by Hau Le on 2018-10-09.
 */
public class ApiUtils {
    private static final String DEV = "https://api.stackexchange.com/";

    public static ApiServices getApiService() {
        return RetrofitClient.getClient(DEV).create(ApiServices.class);
    }
}
