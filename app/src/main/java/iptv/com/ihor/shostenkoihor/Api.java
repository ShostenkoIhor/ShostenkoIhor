package iptv.com.ihor.shostenkoihor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ihor on 12.03.17.
 */

public interface Api {
    @GET("api/v1/exchange")
   Call<List<ExchangeRate>> getExchangeRate();

}
