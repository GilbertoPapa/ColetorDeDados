package com.gilbertopapa.coletorinterno.util;

import com.gilbertopapa.coletorinterno.model.Item;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by GilbertoPapa on 18/11/2017.
 */

public class ItemDeserializer implements JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Item item = new Item();
        
        item.setId(new Gson().fromJson(json.getAsJsonObject().get("id"), Integer.class));
        item.setName(new Gson().fromJson(json.getAsJsonObject().get("name"), String.class));
        item.setWeight(new Gson().fromJson(json.getAsJsonObject().get("weight"), String.class));
        item.setOrderId(new Gson().fromJson(json.getAsJsonObject().get("order_id"), String.class));

        return item;
    }
}
