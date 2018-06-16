package bot;

import org.telegram.telegrambots.api.objects.Update;

import java.util.TimerTask;

public class SendNotice extends TimerTask {
    WeatherBot bot;
    Update update;

    public SendNotice(WeatherBot bot, Update update) {
        this.bot = bot;
        this.update = update;
        run();
    }

    @Override
    public void run() {
        bot.notice(update);
    }

}
