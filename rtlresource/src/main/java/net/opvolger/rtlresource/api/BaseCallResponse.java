package net.opvolger.rtlresource.api;

/**
 * Created by magre on 10-11-2017.
 */

public abstract class BaseCallResponse<TResponseData> implements CallbackInterface<TResponseData>
{
    public BaseCallResponse()
    {
    }

    @Override
    public void always() {

    }

    @Override
    public abstract void onSuccess(TResponseData result);

    @Override
    public void onFailure() {

    }
}
