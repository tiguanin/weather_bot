package yandex.weather.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class WeatherApiRequest {
    final static String TOKEN = "4e32d33d-35f7-4f27-8c1b-8d6f4f2dc3ac";
    String url = "https://api.weather.yandex.ru/v1/";
    String rate = "forecast";
    String latitude = "lat=";
    String longitude = "lon=";
    String language = "lang=";
    String limit = "limit=";
    String hours = "hours=";
    String extra = "extra=true";


    public WeatherApiRequest() {
        url += rate + "?" + latitude + "55.75396" + "&" + longitude + "37.620393" + "&" + extra;
    }

    public JsonObject execute() {
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        JsonObject result = null;

        // TODO: доделать закрытие подключений
        try {
            httpClient = HttpClients.createDefault();
            httpGet = new HttpGet(this.url);
            httpGet.setHeader("X-Yandex-API-Key", this.TOKEN);
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String s = EntityUtils.toString(entity);
                JsonParser parser = new JsonParser();
                result = (JsonObject) parser.parse(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

}
