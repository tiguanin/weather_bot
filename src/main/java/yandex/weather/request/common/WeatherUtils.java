package yandex.weather.request.common;

public class WeatherUtils {

    /**
     * Перевод состояний погоды на русский язык.
     * @param condition параметр из запроса к api.yandex.weather
     */
    public static String translate(String condition) {
        condition = condition.replaceAll("[\\\"]", "");
        String result = "";
        switch (condition) {
            case ("clear"):
                result = "ясно";
                break;
            case ("partly-cloudy"):
                result = "малооблачно";
                break;
            case ("cloudy"):
                result = "облачно с прояснениями";
                break;
            case ("overcast"):
                result = "пасмурно";
                System.out.println("123");
                break;
            case ("partly-cloudy-and-light-rain"):
                result = "небольшой дождь";
                break;
            case ("partly-cloudy-and-rain"):
                result = "дождь";
                break;
            case ("overcast-and-rain"):
                result = "сильный дождь";
                break;
            case ("overcast-thunderstorms-with-rain"):
                result = "сильный дождь, гроза";
                break;
            case ("cloudy-and-light-rain"):
                result = "небольшой дождь";
                break;
            case ("overcast-and-light-rain"):
                result = "небольшой дождь";
                break;
            case ("cloudy-and-rain"):
                result = "дождь";
                break;
        }
        System.out.println(result);
        return result;
    }
}
