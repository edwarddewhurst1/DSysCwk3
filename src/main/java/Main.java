import org.json.JSONObject;
import org.json.JSONArray;

public class Main {
    public static void main(String args[]) {
        JSONObject topArtistsjo = TopArtistsGetter.get("edwarddewhurst");

        // Extract the user's top (most played) artist...
        // See lastfmOutputExample.json for an example of the JSON returned by TopArtistsGetter

        // Extract the main "topartists" object from the JSON
        JSONObject topArtists = topArtistsjo.getJSONObject("topartists");

        // Extract the total number of artists contained in the JSON from the "@attr" object
        int numArtists = topArtists.getJSONObject("@attr").getInt("total");

        // Declare a String array to store the artist names
        String[] artistNames = new String[numArtists];

        // Loop and extract the artist names from the ith object in the "artist" object
        for (int i = 0; i < numArtists; i++ ) {
            artistNames[i] = topArtists.getJSONArray("artist").getJSONObject(i).getString("name");
        }

        // For each artist...
        for (String name : artistNames) {
            EventsGetter.get(name);
        }


    }
}
