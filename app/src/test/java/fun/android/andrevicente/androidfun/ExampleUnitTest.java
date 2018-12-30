package fun.android.andrevicente.androidfun;

import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.impl.BinanceApiAsyncRestClientImpl;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private TreeSet<City> mCitiesMap = new TreeSet<>();

    @Test
    public void addition_isCorrect() {
        BinanceApiAsyncRestClientImpl client = new BinanceApiAsyncRestClientImpl("BLAH",
                "Hiding for screen sharing purposes");

        client.getPrice("BTCUSDT", new BinanceApiCallback<TickerPrice>() {
            @Override
            public void onResponse(TickerPrice tickerPrice) {
                System.out.println("BTC Price is: " + tickerPrice.getPrice());
            }
            @Override
            public void onFailure(Throwable cause) {
                cause.printStackTrace();
            }
        });

        JSONObject jsonObject = new JSONObject();
        String s = "[" +
                "{\"country\":\"UA\",\"name\":\"Hurzuf\",\"_id\":707860,\"coord\":{\"lon\":34.283333,\"lat\":44.549999}}," +
                "{\"country\":\"RU\",\"name\":\"Novinki\",\"_id\":519188,\"coord\":{\"lon\":37.666668,\"lat\":55.683334}}," +
                "{\"country\":\"NP\",\"name\":\"Gorkhā\",\"_id\":1283378,\"coord\":{\"lon\":84.633331,\"lat\":28}}," +
                "{\"country\":\"DK\",\"name\":\"Understed\",\"_id\":2610888,\"coord\":{\"lon\":10.51667,\"lat\":57.383331}}," +
                "{\"country\":\"DK\",\"name\":\"Skodsborg\",\"_id\":2613685,\"coord\":{\"lon\":12.57324,\"lat\":55.822498}}," +
                "{\"country\":\"DK\",\"name\":\"Smidstrup\",\"_id\":2613357,\"coord\":{\"lon\":12.55787,\"lat\":55.865688}}," +
                "{\"country\":\"BG\",\"name\":\"Rastnik\",\"_id\":6460975,\"coord\":{\"lon\":25.283331,\"lat\":41.400002}}," +
                "{\"country\":\"BG\",\"name\":\"Rastnik\",\"_id\":727762,\"coord\":{\"lon\":25.283331,\"lat\":41.400002}}," +
                "{\"country\":\"LT\",\"name\":\"Murava\",\"_id\":596826,\"coord\":{\"lon\":23.966669,\"lat\":54.916672}}" +
                "]";
        try {
            JSONArray testing = new JSONArray(s);

            for (int i = 0; i < testing.length(); i++) {
                City tempCity = new Gson().fromJson(testing.get(i).toString(), City.class);
                mCitiesMap.add(tempCity);
            }

            jsonObject.put("locations", testing);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        testSearchCityData();
    }

    private void testSearchCityData() {
        // test for valid input Rastnik
        List<City> cityList = new ArrayList<>();
        for (City city : mCitiesMap) {
            if (city.mName.toLowerCase().startsWith("Rastnik".toLowerCase()))
                cityList.add(city);
        }
        // verify data results
        assertEquals("Search for cities with prefix: Rastnik", 1, cityList.size(), 0);

        // test for valid input Murava
        cityList.clear();
        for (City city : mCitiesMap) {
            if (city.mName.toLowerCase().startsWith("Murava".toLowerCase()))
                cityList.add(city);
        }
        // verify data results
        assertEquals("Search for cities with prefix: Murava", 1, cityList.size(), 0);

        // test for valid input al
        cityList.clear();
        for (City city : mCitiesMap) {
            if (city.mName.toLowerCase().startsWith("!."))
                cityList.add(city);
        }
        // verify data results
        assertEquals("Search for cities with prefix: !.", 0, cityList.size(), 0);
    }

}