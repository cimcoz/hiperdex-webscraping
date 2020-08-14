import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;

public class WebScrape {
    public static void main(String[] args) {
        try {
            File manga = new File("scrape.txt");
            if (manga.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("File already exists.");
                PrintWriter writer = new PrintWriter(manga);
                writer.print("");
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        int i = 1;
        String url;
        while ( i < 16){
            url = "https://hiperdex.com/manga-list/page/" + String.valueOf(i) + "/";
            try {
                final Document document = Jsoup.connect(url).get();
                BufferedWriter writer = new BufferedWriter(new FileWriter("scrape.txt", true));
                Elements a = document.select("body.page-template div.wrap div.body-wrap div.site-content div.c-page-content div.content-area div.container div.row div.main-col div.main-col-inner div.c-blog-listing div.c-blog__inner div.c-blog__content div#loop-content.page-content-listing div.page-listing-item div.row div.col-6 div.page-item-detail div.item-summary div.post-title h3.h5 a");
                for (Element element : a){
                    writer.append(element.text());
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
