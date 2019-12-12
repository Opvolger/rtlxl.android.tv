package net.opvolger.rtlresource.api;

/**
 * Created by magre on 10-11-2017.
 */

public interface IRequest<TResponseData>   {
    String GetApiUrl();
    void SetResponse(TResponseData response);
}