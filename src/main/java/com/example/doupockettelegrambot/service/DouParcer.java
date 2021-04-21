package com.example.doupockettelegrambot.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class DouParcer {
    public String getPositions() throws IOException {
        String url = "https://jobs.dou.ua/vacancies/?search=%D0%9B%D1%8C%D0%B2%D1%96%D0%B2+java";
        Document doc = Jsoup.connect(url).get();
        ArrayList<String> positions = new ArrayList<>();

        Elements elements =  doc.select("div.vacancy");
        for(Element element : elements) {
            positions.add(elements.text());
        }
        return elements.first().text();
    }
}


