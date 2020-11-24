package com.jwj.WebCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    public static void main(String [] args) throws Exception{

        // Find top 100 movies out of IMDB TOP 250
        final Integer maxNumberOfMovies = 100;
        final String baseUrl = "https://www.imdb.com";
        final Document document = Jsoup.connect("http://www.imdb.com/chart/top").timeout(100000000).get();
        MoviesDao md = new MoviesDao(); //Used to insert the acquired data into the database

        Elements body = document.select("tbody.lister-list").select("tr");
        Elements rows = document.select("table.chart.full-width tr");
        int idx = 0;
        while (idx < maxNumberOfMovies) {
            // Get movie's title and year
            Element e = body.get(idx);
            String title = e.select("td.posterColumn img").attr("alt");
            String year = e.select("td.titleColumn span.secondaryInfo").text().replaceAll("[^\\d]", "");

            // Get movie's description. The first row is null, needs to start from 1
            Element row = rows.get(idx+1);
            String append = row.select(".titleColumn >a").first().attr("href");
            //Stitch the complete connection
            String full = baseUrl + append;
            final Document doc = Jsoup.connect(full).get();
            Elements metaTags = doc.getElementsByTag("meta");
            String description = "";
            for (Element metaTag : metaTags) {
                String name = metaTag.attr("name");
                if (name.equalsIgnoreCase("description")) {
                    description = metaTag.attr("content");
                }
            }

            // Insert entry into database
            md.insert(title, Integer.parseInt(year), description, 0, 0.0);
            ++idx;
        }

    }
}
