package com.gilbertopapa.coletorinterno.util;

import com.gilbertopapa.coletorinterno.model.Item;
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
 * Created by Gilbertopapa on 15/11/2017.
 */

public class ItensDeserializer implements JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            List<Item> itens = null;
            Type listType = new TypeToken<ArrayList<Item>>(){}.getType();
            itens = new Gson().fromJson(json.getAsString(),listType);

            return itens;
        }catch (Exception e){}

        try {
            Item item = null;
            item = new Gson().fromJson(json.getAsString(),Item.class);

            return item;
        }catch (Exception e){}
        return null;
    }
}
