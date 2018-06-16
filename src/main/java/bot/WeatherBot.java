package bot;

import com.google.gson.JsonObject;
import constants.BotInfo;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import yandex.weather.request.WeatherApiRequest;
import yandex.weather.request.service.ExtractInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

public class WeatherBot extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = null;
            if (update.getMessage().getText().equals("/current")) {
                message = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Привет! Я бот, который будет рассказывать тебе два раза в день о погоде.");
                sendKeyboard(update.getMessage().getChatId().toString());
                Timer timer = new Timer();
                Calendar date = Calendar.getInstance();
                date.set(2018, Calendar.JUNE, Calendar.SATURDAY, 06, 30);
                timer.schedule(new SendNotice(this, update), date.getTime(), 86400000);
            } else if (update.getMessage().getText().equals("During the day")) {
                message = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("От пользователя пришёл запрос прогноза погоды на весь текущий день.");
            }
            else {
                message = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Пока я умею принимать только стартовую команду. Сори.");

            }
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public String getBotUsername() {
        return BotInfo.NAME;
    }

    @Override
    public String getBotToken() {
        return BotInfo.TOKEN;
    }


    public void notice(Update update) {
        System.out.println("notice worked");
        WeatherApiRequest request = new WeatherApiRequest();
        JsonObject result = request.execute();
        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(ExtractInfo.extractCurrentTemperature(result));
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void sendKeyboard(String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Custom message text");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add("Current");
        row.add("During the day");

        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

}
