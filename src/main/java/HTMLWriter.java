import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintWriter;

// Extract upcoming tour dates from the JSONArray and print it to a HTML file
// Essentially this is just a lot of string concatenation...
public class HTMLWriter {
    public static void write(JSONArray eventsArray, String name) {
        String head = String.format("<head>\n<title>%s</title>\n</head>", name);

        // The number of events
        int numEvents = eventsArray.length();

        // The start of the body
        String body = "<body>\n";

        // e.g. <h2>Coldplay</h2>
        String heading = String.format("<h2>%s</h2>\n", name);

        // Start the table element
        String details = "<details>\n<table border=\"1\">\n";

        // Loop through each event and add it as a table row
        for (int i = 0; i < numEvents; i++) {
            JSONObject event = eventsArray.getJSONObject(i);

            String title = event.getString("title");

            String tickets = String.format("<a href=\"%s\">Tickets</a>", event.get("ticket_url"));

            String date = event.getString("formatted_datetime");

            String tmp = String.format("<tr> <td>%s</td> <td>%s</td> <td>%s</td> </tr>\n", title, date, tickets);

            details = details.concat(tmp);
        }

        details = details.concat("</table>\n</details>\n");

        // Concatenate the body and head
        body = body.concat(heading);
        body = body.concat(details);

        body = body.concat("</body>\n</html>\n");

        // Write this HTML to a file
        try {
            PrintWriter htmlFile = new PrintWriter(String.format("%s.html", name));

            htmlFile.print(head);
            htmlFile.print(body);

            htmlFile.flush();

            htmlFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
