package com.gilbertopapa.coletorinterno.util;

import com.gilbertopapa.coletorinterno.model.WorkOrder;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gilbertopapa on 26/08/2017.
 */

public class WorkOrderDeserializer implements JsonDeserializer <Object> {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        System.out.println(json.getAsString());
        List<WorkOrder> workOrders = null;
            Type listType = new TypeToken<ArrayList<WorkOrder>>(){}.getType();
            workOrders = new Gson().fromJson(json.getAsString(),listType);

        return workOrders;
    }
}
