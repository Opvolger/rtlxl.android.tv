package net.opvolger.rtlresource.api.request;

import com.google.gson.reflect.TypeToken;
import net.opvolger.rtlresource.api.RequestAbstract;

public class azRequest extends RequestAbstract<azResponse> {

    public azRequest() {
        super(new TypeToken<azResponse>(){
        }.getType(), "http://www.rtl.nl/system/s4m/vfd/version=2/d=a2t/fmt=progressive/fun=az");
    }
}
