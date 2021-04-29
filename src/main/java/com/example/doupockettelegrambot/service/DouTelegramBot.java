package com.example.doupockettelegrambot.service;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DouTelegramBot extends TelegramLongPollingBot {

    DouParcer douParcer = new DouParcer();

    @Override
    public String getBotUsername() {
        return "doupocketbot";
    }

    @Override
    public String getBotToken() {
        return "1667329763:AAGbxoalMpGl-RV2UDGctgw8qsWdPaQcfts";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        SendMessage message = new SendMessage();
        long chat_id = update.getMessage().getChatId();
        message.setChatId(String.valueOf(chat_id));
        String textFromUser = update.getMessage().getText();
        String result = douParcer.getResult(textFromUser);

        if(result.isEmpty()) {
            message.setText("We didn't find anything by your tags. \nHave a nice day!");
        } else {
            message.setText(result);
        }
        execute(message);
    }
}
