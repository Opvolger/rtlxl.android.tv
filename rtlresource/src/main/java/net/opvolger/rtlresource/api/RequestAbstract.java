package net.opvolger.rtlresource.api;


import java.lang.reflect.Type;

import okhttp3.Call;

public abstract class RequestAbstract<TResponseData> implements IRequest<TResponseData>{

    protected TResponseData response;
    private Type responseType;
    private String url;

    public RequestAbstract(Type responseType, String url)
    {
        this.url = url;
        this.responseType = responseType;
    }

    public String GetApiUrl()
    {
        return url;
    }

    public void SetResponse(TResponseData response) {
        this.response = response;
    }

    public Call executeCall(CallbackInterface<TResponseData> responsecallbackfunction) {
        DoCall<TResponseData> call = new DoCall<>(this, responseType, responsecallbackfunction);
        ApiReader apiReaderModule = ApiReader.getInstance();
        return apiReaderModule.get(this,call);
    }
}

