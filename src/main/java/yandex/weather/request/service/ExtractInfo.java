package yandex.weather.request.service;

import com.google.gson.JsonObject;
import yandex.weather.request.common.WeatherUtils;

public class ExtractInfo {

    public static String extractCurrentTemperature(JsonObject params) {
        params = params.get("fact").getAsJsonObject();

        String preparedMessage = "Текущая температура: " + params.get("temp") + " градусов " +
                "\nОщущается как: " + params.get("feels_like") + " градусов" +
                "\nСостояние погоды: " + WeatherUtils.translate(params.get("condition").toString());

        return preparedMessage;

    }
}
