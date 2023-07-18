package com.dupenghao.ssh.util;

import com.alibaba.fastjson.JSONObject;
import kong.unirest.*;

import java.util.Map;

public class HttpUtil {

    static {
        Unirest.config().verifySsl(false);
        Unirest.config().connectTimeout(30000);
    }

    static String Cookie = "";

    public JSONObject doGet(String url) {
        HttpResponse jsonResponse = null;
        try {
            jsonResponse = Unirest.get(url)
                    .header("content-type", "application/json")
                    .header("Cookie", Cookie)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public JSONObject doGet(String url, Map<String, Object> map) {
        HttpResponse jsonResponse = null;
        try {
            jsonResponse = Unirest.get(url)
                    .header("content-type", "application/json")
                    .header("Cookie", Cookie)
                    .queryString(map)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public JSONObject doPut(String url) {
        kong.unirest.HttpResponse jsonResponse = null;
        try {
            jsonResponse = Unirest.put(url)
                    .header("content-type", "application/json")
                    .header("Cookie", Cookie)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public JSONObject doPut(String url, String json) {
        HttpResponse jsonResponse = null;
        try {
            jsonResponse = Unirest.put(url)
                    .body(json)
                    .header("content-type", "application/json")
                    .header("Cookie", Cookie)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public JSONObject doDelete(String url) {
        HttpResponse jsonResponse = null;
        try {
            jsonResponse = Unirest.delete(url)
                    .header("content-type", "application/json")
                    .header("Cookie", Cookie)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public JSONObject doPost(String url, String json) {
        HttpResponse jsonResponse = null;
        try {
            JsonNode jsonNode = new JsonNode(json);
            jsonResponse = Unirest.post(url)
                    .body(jsonNode)
                    .header("Cookie", Cookie)
                    .header("content-type", "application/json")
                    .asJson();
            //TODO 这里cookie的获取逻辑需要自己解析来写
            /*if (Cookie.equals("")) {
                Headers headers = jsonResponse.getHeaders();
                List<String> h = headers.get("Set-Cookie");
                for (String s1 : h
                ) {
                    String[] ss = s1.split(";");
                    for (String s2 : ss
                    ) {
                        if (s2.contains("Cookie")) {
                            Cookie = s2;
                            break;
                        }
                    }
                }
            }*/
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public JSONObject doPostBytxt(String url, String txt) {
        HttpResponse jsonResponse = null;
        try {
            jsonResponse = Unirest.post(url)
                    .body(txt)
                    .header("Cookie", Cookie)
                    .header("content-type", "application/json")
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

    public JSONObject doPostByForm(String url, Map<String, Object> map) {
        HttpResponse jsonResponse = null;

        try {
            jsonResponse = Unirest.post(url)
                    .fields(map)
                    .header("Cookie", Cookie)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return JSONObject.parseObject(jsonResponse.getBody().toString());
    }

}
