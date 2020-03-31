package fun.android.andrevicente.androidfun;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import fun.android.andrevicente.androidfun.models.City;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class ExampleUnitTest {

    private TreeSet<City> mCitiesTree;

    @Before
    public void testSetUp() {
        mCitiesTree = new TreeSet<>();

        try {
            JSONArray testing = loadJsonData();
            for (int i = 0; i < testing.length(); i++) {
                City tempCity = new Gson().fromJson(testing.get(i).toString(), City.class);
                mCitiesTree.add(tempCity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONArray loadJsonData() {
        String json;
        try {
            InputStream in = getClass().getResourceAsStream("/raw/citydata.json");
            int size = in.available();
            byte[] buffer = new byte[size];

            in.read(buffer);
            in.close();

            json = new String(buffer, StandardCharsets.UTF_8);
            return new JSONArray(json);
        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Test
    public void testSearchCityData() {
        // test for valid input Rastnik
        List<City> cityList = new ArrayList<>();
        for (City city : mCitiesTree) {
            if (city.mName.toLowerCase().startsWith("Rastnik".toLowerCase()))
                cityList.add(city);
        }
        // verify data results
        assertEquals("Search for cities with prefix: Rastnik", 1, cityList.size(), 0);

        // test for valid input Murava
        cityList.clear();
        for (City city : mCitiesTree) {
            if (city.mName.toLowerCase().startsWith("Murava".toLowerCase()))
                cityList.add(city);
        }
        // verify data results
        assertEquals("Search for cities with prefix: Murava", 1, cityList.size(), 0);

        // test for valid input al
        cityList.clear();
        for (City city : mCitiesTree) {
            if (city.mName.toLowerCase().startsWith("!."))
                cityList.add(city);
        }
        // verify data results
        assertEquals("Search for cities with prefix: !.", 0, cityList.size(), 0);
    }

    @Test
    public void testCoordinateData() {
        for (City c : mCitiesTree) {
            Assert.assertNotNull("Cities will have Coordinates associated to them", c.mCoordinates);
        }

        for (City c : mCitiesTree) {
            Assert.assertTrue("Coordinates for City will contain lat/longs that are not empty",
                    c.mCoordinates.mLatitude != 0 && c.mCoordinates.mLongitude != 0);
        }
    }

}