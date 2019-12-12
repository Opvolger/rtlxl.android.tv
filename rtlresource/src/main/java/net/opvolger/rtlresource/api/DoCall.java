package net.opvolger.rtlresource.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;

public class DoCall<TResponseData> implements Callback {

    protected IRequest<TResponseData> request;
    private Type type;
    private CallbackInterface<TResponseData> responseDataBaseCallResponse;


    DoCall(IRequest<TResponseData> request, Type type, CallbackInterface<TResponseData> responseAfhandeling)
    {
        this.request = request;
        this.type = type;
        this.responseDataBaseCallResponse = responseAfhandeling;
    }

    @Override
    public void onFailure(final Call call, IOException e) {
        Log.d("CALLAPI", "Er is iets misgegaan" + e);
        this.onFailure();
        this.always();
    }

    @Override
    public void onResponse(Call call, okhttp3.Response response) throws IOException {
        String rawJsonData = response.body().string();
        Log.d("CALLAPI", "Ik heb antwoord " + this.type);
        this.always();
        try {
            Gson gSon = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            final TResponseData result = gSon.fromJson(rawJsonData, this.type);

            request.SetResponse(result);
            this.onSuccess(result);
        }
        catch (Exception e)
        {
            request.SetResponse(null);
            this.onFailure();
        }
    }

    private void always()
    {
        this.responseDataBaseCallResponse.always();
    }

    public void onSuccess(TResponseData result){
        this.responseDataBaseCallResponse.onSuccess(result);
    }

    public void onFailure(){
        this.responseDataBaseCallResponse.onFailure();
    }
}
