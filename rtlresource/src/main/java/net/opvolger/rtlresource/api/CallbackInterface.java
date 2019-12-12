package net.opvolger.rtlresource.api;

interface CallbackInterface<TResponseData>
{
    void always();

    void onSuccess(TResponseData result);

    void onFailure();
}
