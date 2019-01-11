package com.example.utils;

import com.example.entity.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:lifuyi
 * @Date: 2019/1/11 11:49
 * @Description:
 */
public class JsonUtil {

    /**
     * json字符串转javaBean
     * @param json
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T getBeanFromJson(String json, Class c) {
        try {
            List<String> tags = new ArrayList<String>();
            Pattern pattern = Pattern.compile("\\\"([a-zA-Z0-9]*)\\\":");
            Matcher m = pattern.matcher(json);
            while (m.find()) {
                tags.add(m.group(1));
            }
            for (int i = 0; i < tags.size(); i++) {
                json = json
                        .replaceAll("\\\"" + tags.get(i) + "\\\"",
                                //toLowerCase解决无论redis中大小写问题
                                "^^" + tags.get(i).toLowerCase() + "^^")
                        .replaceAll(":\\\"", ":^^").replaceAll("\\\",", "^^,");
            }
            json = json.replaceAll("\\\"}", "^^}").replaceAll("\\\"]", "^^]")
                    .replaceAll("\"", "~~");
            json = json.replace("^^", "\"");
            System.out.println(json);
            ObjectMapper mapper = new ObjectMapper();

            Object object = mapper.readValue(json, c);

            Field[] declaredFields = object.getClass().getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                declaredFields[i].setAccessible(true);
                Object fieldValue = declaredFields[i].get(object);
                String str;
                if(fieldValue.getClass().getName().equals("java.lang.String")){
                    str= (String) fieldValue;
                    declaredFields[i].set(object,str.replace("~~", "\""));
                }
            }
            return (T) object;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

}
