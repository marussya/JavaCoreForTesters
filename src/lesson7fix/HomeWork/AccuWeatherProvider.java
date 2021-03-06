package HomeWork;

import HomeWork.–°onfiguration.ApplicationGlobalState;
import HomeWork.dto.WeatherResponse;
import HomeWork.entity.WeatherData;
import HomeWork.enums.Periods;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AccuWeatherProvider implements WeatherProvider {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String CURRENT_CONDITIONS_ENDPOINT = "currentconditions";
    private static final String API_VERSION = "v1";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();
    private static final String FORECAST_PERIOD = "5day";
    private static final String FORECAST_TYPE = "daily";
    private static final String FORECAST = "forecasts";
    private static final ObjectMapper responseMapper = new ObjectMapper();

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void getWeather(Periods periods) throws IOException, SQLException {

        if (periods.equals(Periods.NOW)) {
            String cityKey = detectCityKey();
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(CURRENT_CONDITIONS_ENDPOINT)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("language", "ru-ru")
                    .addQueryParameter("metric", "true")
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            String responseList = client.newCall(request).execute().body().string();

            List<WeatherResponse> weatherResponseList = responseMapper.readValue(responseList, new TypeReference<List<WeatherResponse>>() {});

            WeatherResponse weather = weatherResponseList.get(0);

            System.out.println("–°–Ķ–Ļ—á–į—Ā –≤ –≥–ĺ—Ä–ĺ–ī–Ķ " + ApplicationGlobalState.getInstance().getSelectedCity() +
                    " —ā–Ķ–ľ–Ņ–Ķ—Ä–į—ā—É—Ä–į "  + weather.getTemperature().getMetric().getValue() + "¬į–°, –ł " + weather.getWeatherText() + ".");
        }

        if (periods.equals(Periods.FIVE_DAYS)) {
            String cityKey = detectCityKey();
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(FORECAST)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(FORECAST_TYPE)
                    .addPathSegment(FORECAST_PERIOD)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("language", "ru-ru")
                    .addQueryParameter("metric", "true")
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            String responseList = client.newCall(request).execute().body().string();

            int firstIndexBody = responseList.indexOf("[{\"Date\"");
            int lastIndexBody = responseList.lastIndexOf("}");
            responseList = responseList.substring(firstIndexBody, lastIndexBody);

            List<WeatherResponse> weatherResponseList = responseMapper.readValue(responseList, new TypeReference<List<WeatherResponse>>() {});

            for (WeatherResponse weather: weatherResponseList) {
                System.out.println("–í –≥–ĺ—Ä–ĺ–ī–Ķ " + ApplicationGlobalState.getInstance().getSelectedCity() + " –Ĺ–į —Ā–Ľ–Ķ–ī—É—é—Č—É—é –ī–į—ā—É " + weather.getDate().substring(0,10) +
                        " –ĺ–∂–ł–ī–į–Ķ—ā—Ā—Ź –Ņ–ĺ–≥–ĺ–ī–į: –ú–ł–Ĺ–ł–ľ–į–Ľ—Ć–Ĺ–į—Ź —ā–Ķ–ľ–Ņ–Ķ—Ä–į—ā—É—Ä–į "  + weather.getTemperature().getMinimum().getValue() + "¬į–°. –ú–į–ļ—Ā–ł–ľ–į–Ľ—Ć–Ĺ–į—Ź —ā–Ķ–ľ–Ņ–Ķ—Ä–į—ā—É—Ä–į " +
                        weather.getTemperature().getMaximum().getValue() + "¬į–°. –Ē–Ĺ—Ď–ľ - " + weather.getDay().getIconPhrase() + ". –Ě–ĺ—á—Ć—é - " + weather.getNight().getIconPhrase() + ".");

                WeatherData weatherData = new WeatherData(ApplicationGlobalState.getInstance().getSelectedCity(),
                        weather.getDate().substring(0,10), weather.getDay().getIconPhrase(), weather.getNight().getIconPhrase(),
                        castFloatToDouble(weather.getTemperature().getMinimum().getValue()),  castFloatToDouble(weather.getTemperature().getMaximum().getValue())
                );

            }
        }


        if (periods.equals(Periods.ZERO)) {
            exitApp();
        }
    }

    public void exitApp() {
        System.out.println("–ó–į–≤–Ķ—Ä—ą–į—é —Ä–į–Ī–ĺ—ā—É");
        System.exit(0);
    }

    private Double castFloatToDouble(float value) {
        return (double) value;
    }


    public String detectCityKey() throws IOException {
        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();

        HttpUrl detectLocationURL = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment("locations")
                .addPathSegment(API_VERSION)
                .addPathSegment("cities")
                .addPathSegment("autocomplete")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", selectedCity)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(detectLocationURL)
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("–Ě–Ķ–≤–ĺ–∑–ľ–ĺ–∂–Ĺ–ĺ –Ņ—Ä–ĺ—á–Ķ—Ā—ā—Ć –ł–Ĺ—Ą–ĺ—Ä–ľ–į—Ü–ł—é –ĺ –≥–ĺ—Ä–ĺ–ī–Ķ. " +
                    "–ö–ĺ–ī –ĺ—ā–≤–Ķ—ā–į —Ā–Ķ—Ä–≤–Ķ—Ä–į = " + response.code() + " —ā–Ķ–Ľ–ĺ –ĺ—ā–≤–Ķ—ā–į = " + response.body().string());
        }
        String jsonResponse = response.body().string();
        System.out.println("–ü—Ä–ĺ–ł–∑–≤–ĺ–∂—É –Ņ–ĺ–ł—Ā–ļ –≥–ĺ—Ä–ĺ–ī–į " + selectedCity);

        if (objectMapper.readTree(jsonResponse).size() > 0) {
            String cityName = objectMapper.readTree(jsonResponse).get(0).at("/LocalizedName").asText();
            String countryName = objectMapper.readTree(jsonResponse).get(0).at("/Country/LocalizedName").asText();
            System.out.println("–Ě–į–Ļ–ī–Ķ–Ĺ –≥–ĺ—Ä–ĺ–ī " + cityName + " –≤ —Ā—ā—Ä–į–Ĺ–Ķ " + countryName);
        } else throw new IOException("Server returns 0 cities");

        return objectMapper.readTree(jsonResponse).get(0).at("/Key").asText();
    }

    public String trimBrackets(JsonNode string){
        int lastIndexBody = string.toString().lastIndexOf("\"");
        String newString = string.toString().substring(1, lastIndexBody);
        return newString;
    }
}