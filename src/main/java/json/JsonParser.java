package json;

import annonation.TopJSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.model.ec2.ec2data.EC2DataJson;
import sun.jvm.hotspot.ui.Annotation;

import java.util.ArrayList;

public class JsonParser{

    public JsonParser() {
    }

    public <T> T ParseJsonString(String jsonResponse, T type) {


        ObjectMapper objectMapper = new ObjectMapper();

        JavaType javaType = objectMapper.getTypeFactory().constructType(type.getClass());
        try {
            return objectMapper.readValue(jsonResponse, javaType);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public EC2DataJson ParseEC2DataString(String jsonResponse){

        return null;
    }


}
