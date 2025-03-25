package com.inpad.spring.inpadspringboot.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Converter(autoApply = true)
public class JsonToMapConverter implements AttributeConverter<JSONObject, String> {

    Logger log = Logger.getLogger(JsonToMapConverter.class.getName());

    @Override
    public String convertToDatabaseColumn(JSONObject obj)
    {
        String data = null;
        try
        {
            data = obj.toString();
        }
        catch (final Exception e)
        {
            log.info("JSON writing error");
        }

        return data;
    }

    @Override
    public JSONObject convertToEntityAttribute(String data)
    {
        JSONObject obj = null;

        try
        {
            Object temp = JSONValue.parse(data);
            System.out.println(data);
            System.out.println(temp);
            obj = (JSONObject) temp ;
        }
        catch (final Exception e)
        {
//            log.info("JSON reading error" + e);
        }

        return obj;
    }

}
