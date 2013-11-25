import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.JSONObject;

// Use the Last.fm API to get a users
public class TopArtistsGetter {
    public static JSONObject get(String lastfmusername) {
        Client c = Client.create();

        // Compose the final URL we will be using
        // This is split up mainly for clarity
        String baseUrl = "http://ws.audioscrobbler.com/2.0/?method=%s&user=%s&api_key=%s&format=json";
        // The API method to use
        String method = "user.getTopArtists";
        // The API key needed to make calls to the Last.fm API (registered to my account)
        String apikey = "a442d7f534631e0f51bade7ce5eb96e7";

        String finalUrl = String.format(baseUrl, method, lastfmusername, apikey);

        // Create a Jersey WebResource object with the composed URL
        WebResource wr = c.resource(finalUrl);

        // DEBUG
        //System.out.println(finalUrl);

        // Specify a ClientResponse object to accept the MIME type application/json
        ClientResponse r = wr.accept("application/json").get(ClientResponse.class);

        // Get the response string
        String out = r.getEntity(String.class);

        // Serialise into a JSONObject
        JSONObject jo = new JSONObject(out);

        // DEBUG
        //System.out.println(jo.toString(2));

        // Return that JSONObject
        return jo;
    }
}
