package net.opvolger.rtlresource.api.request;

import com.google.gson.reflect.TypeToken;

import net.opvolger.rtlresource.api.RequestAbstract;

/**
 * Created by magre on 10-11-2017.
 */

public class itemsRequest extends RequestAbstract<itemsResponse> {

    public itemsRequest(String url) {
        super(new TypeToken<itemsResponse>(){
        }.getType(), url);
    }
}
