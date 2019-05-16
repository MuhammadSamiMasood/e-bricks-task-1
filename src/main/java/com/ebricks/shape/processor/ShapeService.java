package com.ebricks.shape.processor;

import com.ebricks.shape.config.ShapeConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;

public class ShapeService {

    public static Logger logger = LogManager.getRootLogger();
    ObjectMapper objectMapper = new ObjectMapper();
    ShapeConfig shapesConfig = ShapeConfig.getInstance();

    public Object get() throws IOException {

        shapesConfig = (ShapeConfig) objectMapper.readValue(new File("resources/config.json"), ShapeConfig.class);

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(shapesConfig.shapeServiceUrl);
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);

        InputStream inputStream = closeableHttpResponse.getEntity().getContent();
        String shapesJson = IOUtils.toString(inputStream);
        return shapesJson;
    }

    public Object post(String shapeJson) throws IOException{

        shapesConfig = (ShapeConfig) objectMapper.readValue(new File("resources/config.json"), ShapeConfig.class);

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(shapesConfig.shapeServiceUrl);

        StringEntity entity = new StringEntity(shapeJson);
        httpPost.setEntity(entity);
        httpPost.setHeader("content-type", "application/json");

        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
        return IOUtils.toString(closeableHttpResponse.getEntity().getContent());
    }
}
