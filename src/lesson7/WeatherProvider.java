package lesson7;


import lesson7.enums.Periods;

import java.io.IOException;

public interface WeatherProvider {
    public default void getWeather(Periods period) throws IOException {

    }
}
