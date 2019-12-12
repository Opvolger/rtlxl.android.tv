package net.opvolger.rtlx;

import android.content.Context;
import android.os.Handler;
import android.support.v17.leanback.widget.ArrayObjectAdapter;

import net.opvolger.rtlresource.api.BaseCallResponse;
import net.opvolger.rtlresource.api.request.abstractitem;
import net.opvolger.rtlresource.api.request.itemRequest;
import net.opvolger.rtlresource.api.request.itemsResponse;
import net.opvolger.rtlresource.api.request.itemsRequest;
import net.opvolger.rtlresource.api.request.material;
import net.opvolger.rtlresource.api.request.meta;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MainListRowAdapter extends ArrayObjectAdapter {

    private abstractitem abstractitem;
    private List<Movie> movies = new ArrayList<>();
    private boolean loaded = false;
    private meta meta;

    public MainListRowAdapter(CardPresenter cardPresenter)
    {
        super(cardPresenter);
    }

    public void setItem(meta meta, abstractitem abstractitem)
    {
        this.meta = meta;
        this.abstractitem = abstractitem;
    }

    public void getmovies(final Context context) {
        if (!loaded)
        {
            loaded = true;
            // ga hier de filmen ophalen
            itemsRequest itemsRequest = new itemsRequest(abstractitem.itemsurl);
            itemsRequest.executeCall(new BaseCallResponse<itemsResponse>() {
                @Override
                public void onSuccess(itemsResponse result) {
                    if (result != null)
                    {
                        for (material item:result.material) {
                            if (abstractitem.key.equals("132237") || item.classname == "uitzending")
                            {
                                // http://www.rtl.nl/system/s4m/vfd/version=2/fun=catchup/output=json/
                                //String itemurl = "http://www.rtl.nl/system/s4m/vfd/version=2/fun=abstract/uuid="+item.uuid+"/fmt=adaptive/output=json/";
                                // http://www.rtl.nl/system/s4m/vfd/version=2/uuid
                                String itemurl = "http://www.rtl.nl/system/s4m/vfd/version=2/output=json/fun=abstract/uuid="+item.uuid+"/d=a3t/fmt=progressive/context=rtl/";
                                //itemRequest itemRequest = new itemRequest(itemurl);
                                movies.add(MovieList.buildMovieInfo(abstractitem.name, item.title,
                                        item.synopsis, abstractitem.station, itemurl, checkURLExists(meta.thumb_base_url + abstractitem.key),  checkURLExists(meta.poster_base_url + abstractitem.key)));

                            }
                        }
                        Handler mainHandler = new Handler(context.getMainLooper());
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                addAll(0, movies);
                            }});
                    }
                }
            });
        }
    }

    public String checkURLExists(String URLName) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();

            HttpURLConnection.setFollowRedirects(false);
            con.setRequestMethod("HEAD");

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return URLName;
            } else {
                return "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/card.jpg";
            }
        }
        catch(UnknownHostException unknownHostException){
            return "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/card.jpg";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/card.jpg";
        }
    }
}
