package com.example.doupockettelegrambot.service;

import com.example.doupockettelegrambot.model.Position;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DouParcer {

    public String getResult(String textFromUser) {
        String url = "https://jobs.dou.ua/vacancies/?search=" + formatTags(textFromUser);
        List<Position> allPostitions = getPositionsFromWebPage(url);
        return getFormatedResult(allPostitions);
    }

    public String formatTags(String noFormatedTags) {
        StringBuilder formatedTags = new StringBuilder();
        if (!noFormatedTags.isEmpty()) {
            String[] text = noFormatedTags.split(" ");
            System.out.println(text.length);
            for (String s : text) {
                formatedTags.append(s.trim() + "+");
            }
        }
        return formatedTags.toString();
    }

    public List<Position> getPositionsFromWebPage(String url) {
        ArrayList<Position> allPositionsFromWebPage = new ArrayList<>();
        Elements elements = null;
        try {
            elements = Jsoup.connect(url).get().select("div.vacancy");
        } catch (IOException | NullPointerException  e) {
            e.printStackTrace();
        }

        for (int i = 0; i < elements.size(); i++) {
            Position position = new Position();
            String title = elements.get(i).select("div.title").select("a").text();
            String link = elements.get(i).select("div.title").select("a").attr("href");
            String description = elements.get(i).select("div.sh-info").text();
            position.setTitle(title);
            position.setDescription(description);
            position.setLink(link);
            allPositionsFromWebPage.add(position);
        }
        return allPositionsFromWebPage;
    }

    String getFormatedResult(List<Position> positions) {
        StringBuilder formatedResult = new StringBuilder();
        int positionCount = 0;
        for(Position position : positions) {
            formatedResult.append("\n"
                    + ++positionCount
                    + ". "
                    + position.getTitle()
                    + "\n"
                    + position.getLink()
                    + "\n"
            );
        }
        return formatedResult.toString();
    }
}

