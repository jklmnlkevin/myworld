import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Request;
import org.jsoup.nodes.Document;


public class Hello2 {
    public static void main(String[] args) throws Exception {
        Connection connection = Jsoup.connect("http://www.kmart.com/tvs-electronics-gaming/b-1347163426?rdwna=y");
        
        connection = connection.userAgent("Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_2_1 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148 Safari/6533.18.5");
        Document doc = connection.get();
        Request request = connection.request();
        Response response = connection.response();
        Map<String, String> headers = response.headers();
        for (String key : headers.keySet()) {
            System.out.println(key + ": " + headers.get(key));
        }
        System.out.println(response.statusCode());
    }
}
