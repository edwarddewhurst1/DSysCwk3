import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.JSONObject;

public class EventsGetter {

    private static String APP_ID = "DisSysWebAPICoursework";

    public static void get(String artist) {
        String baseUrl =  "http://api.bandsintown.com/artists/%s/events.json?api_version=%s&app_id=%s";

        String ver = "2.0";

        String finalString = String.format(baseUrl, artist, ver, APP_ID);

        System.out.println(finalString);
    }
}
