package com.gilbertopapa.coletorinterno.api;

import com.gilbertopapa.coletorinterno.model.WorkOrder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Gilbertopapa on 26/08/2017.
 */

public interface ColetorAPIWorkOrder {

    @GET("api/list/order/{order}")
    Call<List<WorkOrder>> getWorkOrder(@Header("Content-Type") String content, @Path("order") String order);

}
