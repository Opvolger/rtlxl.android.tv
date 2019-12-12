package net.opvolger.rtlresource.api.request;

import com.google.gson.reflect.TypeToken;

import net.opvolger.rtlresource.api.RequestAbstract;

/**
 * Created by magre on 10-11-2017.
 */

public class itemRequest extends RequestAbstract<itemResponse> {

    public itemRequest(String url) {
        super(new TypeToken<itemResponse>(){
        }.getType(), url);
    }
}
