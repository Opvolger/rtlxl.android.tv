package net.opvolger.rtlx;


import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ListRowPresenter;

/**
 * Created by magre on 10-11-2017.
 */

public class MainRowAdapter extends ArrayObjectAdapter {
    public MainRowAdapter()
    {
        super(new ListRowPresenter());
    }

}
