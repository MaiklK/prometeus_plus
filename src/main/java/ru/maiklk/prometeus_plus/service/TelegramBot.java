package ru.maiklk.prometeus_plus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.maiklk.prometeus_plus.configuration.BotConfig;
import ru.maiklk.prometeus_plus.entity.TextMessage;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final MessageService messageService;

    @Autowired
    public TelegramBot(BotConfig botConfig, MessageService messageService) {
        super(botConfig.getTOKEN());
        this.botConfig = botConfig;
        this.messageService = messageService;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBOT_NAME();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            int messageId = update.getMessage().getMessageId();
            messageService.saveTextMessage(TextMessage.builder()
                    .chatId(chatId)
                    .text(messageText)
                    .build());

            if (update.getMessage().getText().equals("/start")) {
                sendMessage(chatId);
            } else {
                replyMessage(chatId, messageId);
            }
            update.getMessage().setReplyToMessage(update.getMessage());

        }
    }
    private void replyMessage(long chatId, int messageId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setReplyToMessageId(messageId);
        sendMessage.setText("ага");

        executeSendMessage(sendMessage);
    }

    private void sendMessage(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Работаем");

        executeSendMessage(sendMessage);
    }

    private void executeSendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Ошибка API телеграмма: {}", e.getMessage());
        }
    }
}
