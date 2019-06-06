import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Prog06Bot extends TelegramLongPollingBot {

    public static Queue<Integer> msgQueu = new LinkedList<Integer>();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            Integer user_id = update.getMessage().getFrom().getId();

            if (msgQueu.size() < 10) {
                msgQueu.add(user_id);
            } else {
                Set<Integer> set = new HashSet<>();
                for (Integer id : msgQueu) {
                    set.add(id);
                }
                if (set.size() == 1) {
                    String output = "Someone is talking too much!";
                    SendMessage msg = new SendMessage()
                            .setChatId(chat_id)
                            .setText(output);
                    try {
                        execute(msg);
                        msgQueu.clear();
                        msgQueu.add(user_id);

                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

                if (set.size() == 2) {
                    String output = "May be someone need a room?";
                    SendMessage msg = new SendMessage()
                            .setChatId(chat_id)
                            .setText(output);
                    try {
                        execute(msg);
                        msgQueu.clear();
                        msgQueu.add(user_id);

                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }

       if (message_text.equals("/start")) {
           // User send /start
           String welcome = "Welcome to our nerdy cave!";
            SendMessage message = new SendMessage()
                   .setChatId(chat_id)
                    .setText(welcome);
            try {
                execute(message);
            } catch (TelegramApiException e) {
               e.printStackTrace();
            }
       }
   }
}



    @Override
    public String getBotUsername() {
        return "Prog06Bot";
    }

    @Override
    public String getBotToken() {
        return "";
    }
}
