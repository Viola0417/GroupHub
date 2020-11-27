package com.jwj.WebCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    public static void main(String [] args) throws Exception{

//        // Find top 100 movies out of IMDB TOP 250
//        final Integer maxNumberOfMovies = 100;
//        final String baseUrl = "https://www.imdb.com";
//        final Document document = Jsoup.connect("http://www.imdb.com/chart/top").timeout(100000000).get();
//        MoviesDao md = new MoviesDao(); //Used to insert the acquired data into the database
//
//        Elements body = document.select("tbody.lister-list").select("tr");
//        Elements rows = document.select("table.chart.full-width tr");
//        int idx = 0;
//        while (idx < maxNumberOfMovies) {
//            // Get movie's title and year
//            Element e = body.get(idx);
//            String title = e.select("td.posterColumn img").attr("alt");
//            String year = e.select("td.titleColumn span.secondaryInfo").text().replaceAll("[^\\d]", "");
//
//            // Get movie's description. The first row is null, needs to start from 1
//            Element row = rows.get(idx+1);
//            String append = row.select(".titleColumn >a").first().attr("href");
//            //Stitch the complete connection
//            String full = baseUrl + append;
//            final Document doc = Jsoup.connect(full).get();
//            Elements metaTags = doc.getElementsByTag("meta");
//            String description = "";
//            for (Element metaTag : metaTags) {
//                String name = metaTag.attr("name");
//                if (name.equalsIgnoreCase("description")) {
//                    description = metaTag.attr("content");
//                }
//            }
//
//            // Insert entry into database
//            md.insertMovie(title, Integer.parseInt(year), description, 0, 0.0);
//            ++idx;
//        }


        // Find top books
        final Document document = Jsoup.connect("https://www.theguardian.com/books/2019/sep/21/best-books-of-the-21st-century").timeout(100000000).get();
        MoviesDao md = new MoviesDao(); //Used to insert the acquired data into the database

        Elements titleElements = document.select("h2");
        Elements descElements = document.select("p");

        int titleIdx = 1, descIdx = 2;
        while (titleIdx <= 297) {
            Element titleElement = titleElements.get(titleIdx);
            String name = titleElement.text();
            String author = titleElements.get(titleIdx + 1).text();
            if (author.substring(0, 2).equalsIgnoreCase("by")) {
                author = author.substring(3);
            }
            if (author.contains("translated by")) {
                int translatorIdx = author.indexOf("translated by");
                author = author.substring(0, translatorIdx - 2);
            }
            int length = author.length();
            author = author.substring(0, length - 7);
            titleIdx += 3;

            Element descElement = descElements.get(descIdx);
            String desc = descElement.text();
            if (desc.contains("Read the review")) {
                int extraIdx = desc.indexOf("Read the review");
                if (extraIdx != -1) {
                    desc = desc.substring(0, extraIdx - 1);
                }
            }
            descIdx++;

            md.insertBook(name, author, desc, 0, 0.0);
        }


//        // Find top travel destinations from tripadvisor
//        final String baseUrl = "https://www.tripadvisor.com";
//        final Document document = Jsoup.connect("https://www.tripadvisor.com/TravelersChoice-Destinations").timeout(100000000).get();
//        MoviesDao md = new MoviesDao(); //Used to insert the acquired data into the database
//
//        Elements titles = document.select("div.mainName");
//        for (Element title : titles) {
//            String travelSite = title.select("a").first().text();
//            String city = "";
//            String country = "";
//            int commaIdx = travelSite.indexOf(',');
//            if (commaIdx == -1) {
//                city = travelSite;
//                country = travelSite + " ";
//            } else {
//                city = travelSite.substring(0, commaIdx);
//                country = travelSite.substring(commaIdx + 2);
//            }
//            //System.out.println(city + " ======= " + country);
//            String append = title.select("a").first().attr("href");
//            String nextUrl = baseUrl + append;
//            Document doc = Jsoup.connect(nextUrl).timeout(100000000).get();
//            String description = doc.select("div._3y4w8kK3._1Eip5_6m").first().text();
//            //System.out.println(description);
//            md.insertTravel(city, country, description, 0, 0.0);
//        }

    }
}
