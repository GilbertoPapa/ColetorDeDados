package com.gilbertopapa.coletorinterno.api;

/**
 * Created by Gilbertopapa on 15/11/2017.
 */

import com.gilbertopapa.coletorinterno.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ColetorAPIItens {
    @GET("api/list/item/{id}")
    Call<List<Item>> getItens(@Header("Content-Type") String content, @Path("id") String id);

    @FormUrlEncoded
    @POST("api/item")
    @Headers("Accept: application/json")
    Call<Item> setItem(@Field("name") String name,
                       @Field("weight") String weight,
                       @Field("order_id") String order_id);
}
