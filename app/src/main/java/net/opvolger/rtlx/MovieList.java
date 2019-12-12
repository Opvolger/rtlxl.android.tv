package net.opvolger.rtlx;

import android.content.Context;
import android.os.Handler;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;

import net.opvolger.rtlresource.api.BaseCallResponse;
import net.opvolger.rtlresource.api.request.abstractitem;
import net.opvolger.rtlresource.api.request.azRequest;
import net.opvolger.rtlresource.api.request.azResponse;

import java.util.ArrayList;
import java.util.List;

public final class MovieList {

    private azResponse azResponse;

    public void LoadList(final Context context, final MainRowAdapter mainRowAdapter)
    {
        azRequest az = new azRequest();
        az.executeCall(new BaseCallResponse<azResponse>() {

            @Override
            public void onSuccess(final azResponse result) {
                azResponse = result;
                final ArrayList<String> myCollection = new ArrayList<>();
                for (abstractitem item: result.abstracts) {
                    myCollection.add(item.name);
                }
                MOVIE_CATEGORY=myCollection.toArray(new String[myCollection.size()]);
                Handler mainHandler = new Handler(context.getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        CardPresenter cardPresenter = new CardPresenter();
                        int i = 0;
                        for (abstractitem item: result.abstracts) {
                            if (item.key.equals("132237")){
                                item.id = i;
                                MainListRowAdapter listRowAdapter = new MainListRowAdapter(cardPresenter);
                                listRowAdapter.setItem(result.meta, item);
                                HeaderItem header = new HeaderItem(i, item.name);
                                mainRowAdapter.add(new ListRow(header, listRowAdapter));
                                i++;
                            }
                        }
                    }
                });
            }
        });
    }


    public static String MOVIE_CATEGORY[];

    public static Movie buildMovieInfo(String category, String title,
                                        String description, String studio, String itemUrl, String cardImageUrl,
                                        String bgImageUrl) {
        Movie movie = new Movie();
        movie.setId(Movie.getCount());
        Movie.incCount();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setStudio(studio);
        movie.setCategory(category);
        movie.setCardImageUrl(cardImageUrl);
        movie.setBackgroundImageUrl(bgImageUrl);
        movie.setItemUrl(itemUrl);
        return movie;
    }
}
