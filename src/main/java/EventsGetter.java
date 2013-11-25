import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.JSONObject;

// Use the Bandsintown API to get concert/event information
public class EventsGetter {
    // The API requires you to supply a short APP_ID to identify yourself
    // This is a short URL to a Git repository containing this program
    private static String APP_ID = "http://git.io/uEW7HA";

    public static JSONObject get(String artist) {
        // Create a Jersey Client object
        Client c = Client.create();

        // Compose the final URL we will be using
        // Split up for clarity
        String baseUrl =  "http://api.bandsintown.com/artists/%s/events.json?api_version=%s&app_id=%s";
        // API version to use
        String ver = "2.0";
        // The final composed URL
        String finalUrl = String.format(baseUrl, artist, ver, APP_ID);

        // Create a Jersey WebResource object with the final URL
        WebResource wr = c.resource(finalUrl);

        // Create a ClientResponse object that accepts responses of the MIME type application/json
        // Read as: "Get a ClientResponse from wr of the type application/json"
        ClientResponse r = wr.accept("application/json").get(ClientResponse.class);

        // Get a string of the response
        String out = r.getEntity(String.class);

        // Serialise the response string into a JSONObject and return it
        return new JSONObject(out);
    }
}
