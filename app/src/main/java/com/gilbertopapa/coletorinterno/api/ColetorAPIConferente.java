package com.gilbertopapa.coletorinterno.api;

import com.gilbertopapa.coletorinterno.model.Conferente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Gilbertopapa on 26/08/2017.
 */

public interface ColetorAPIConferente {

    @GET("api/user/{title}")
    Call<List<Conferente>> getConferente(@Header("Content-Type") String content , @Path("title") String title);

}
