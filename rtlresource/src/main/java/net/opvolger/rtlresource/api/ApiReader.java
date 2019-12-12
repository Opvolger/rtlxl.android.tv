package net.opvolger.rtlresource.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

class ApiReader {

    private OkHttpClient okHttpClient;
    private static ApiReader instance;

    private ApiReader()
    {
        this.okHttpClient = new OkHttpClient.Builder().build();
    }

    public static ApiReader getInstance()
    {
        if (instance == null)
        {
            instance = new ApiReader();
        }
        return instance;
    }

    public <DataResponse> DataResponse getsync(IRequest<DataResponse> requestdata, Type type) {

        Request request = new Request.Builder()
                .url(requestdata.GetApiUrl())
                .get()
                .header("User-Agent", "RTLXL Android TV")
                .addHeader("Accept", "application/json; charset=utf-8")
                .build();
        okhttp3.Response call;
        try {
            Log.d("CALLAPI", "Ik doe een post call " + requestdata.GetApiUrl());
            call = okHttpClient.newCall(request).execute();
            String rawJsonData;
            rawJsonData = call.body().string();
            Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            return gSon.fromJson(rawJsonData, type);
        } catch (IOException e) {
            Log.d("RTLXL", "Call mislukt " + requestdata.GetApiUrl());
        }
        return null;
    }

    public <DataResponse> Call get(IRequest<DataResponse> requestdata, Callback callback) {
        Request request = new Request.Builder()
                .url(requestdata.GetApiUrl())
                .get()
                .header("User-Agent", "RTLXL Android TV")
                .addHeader("Accept", "application/json; charset=utf-8")
                .build();
        Log.d("RTLXL", "Ik doe een callback call " + requestdata.GetApiUrl());
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
        return call;
    }
}
