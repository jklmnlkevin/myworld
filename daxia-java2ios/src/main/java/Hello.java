import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Hello {
    public static void main(String[] args) throws Exception {
        String url = "http://www.dianping.com/search/category/344/10/g104r299p{page}";
        for (int i = 1; i <= 50; i++) {
            Thread.sleep(100);
            Connection conn = Jsoup.connect(url.replace("{page}", i + ""));
            conn = conn.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36");
            Document doc = conn.get();
            Elements elements = doc.select("div.info");
            for (int j = 0; j < elements.size(); j++) {
                Element element = elements.get(j);
                String name = element.select("a.shopname").text();
                String average = element.select("span.average").text();
                String comment = element.select("span.comment-list").text();
                System.out.println(name + "###" + average + "###" + comment);
            }
        }
    }
}
