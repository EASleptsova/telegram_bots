import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.math.BigInteger;
import java.util.Random;
import java.util.regex.Pattern;

public class JavaBot extends TelegramLongPollingBot {

    private String [] javaAnswers={"Java has Rich API.", "It has powerful development tools.",
            "Java Rock!", "Java is Easy to learn.",
            "Java is an Object Oriented Programming Language",
            "Great collection of Open Source libraries.",
            "Wonderful Community Support.",
            "Java is FREE.","It has excellent documentation support - Javadocs.",
            "Java is Platform Independent.","Java is Everywhere!"};
    private String [] pythonAnswers ={"Python is Great for Beginners.", "Python has High Salaries.",
            "Python is the future of AI and Machine Learning.",
            "Python is portable & extensible.","Python is the leading language of many data scientist.",
            "Python has an array of frameworks for developing websites. ","Python can also be used as Scripting language."};

    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText().toLowerCase();
            long chat_id = update.getMessage().getChatId();

            if (message_text.equals("/start")) {
                // User send /start
                String welcome = "Welcome to our nerdy cave! \n YouÂ´d better be careful talking about java and python...";
                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText(welcome);
                try {
                    execute(message);
                    return;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (message_text.equals("/java")) {
                String javaWelcome = "Who said Java? You are my hero!";
                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText(javaWelcome);
                try {
                    execute(message);
                    return;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if(message_text.substring(0,4).toLowerCase().equals("/fib")){
                if(message_text.length()>=5 && message_text.length()<=11){
                    try {
                        BigInteger n = new BigInteger(message_text.substring(5));
                        BigInteger nMinusTwo = new BigInteger("0");
                        BigInteger nMinusOne = new BigInteger("1");
                        BigInteger one = new BigInteger("1");
                        BigInteger number = null;

                        if (n.compareTo(one) == 0){
                            String fibNumber= "For 1 fibonacci number is 1";
                            SendMessage message = new SendMessage()
                                    .setChatId(chat_id)
                                    .setText(fibNumber);
                            try {
                                execute(message);
                                return;
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        } else if((n.compareTo(one) > 0)){
                            while (n.compareTo(one) > 0) {
                                number = nMinusOne.add(nMinusTwo);
                                nMinusTwo = nMinusOne;
                                nMinusOne = number;
                                n = n.subtract(one);
                            }
                            String fibNumber= "And the fibonacci number is " + number.toString()+ " !!!";
                            SendMessage message = new SendMessage()
                                    .setChatId(chat_id)
                                    .setText(fibNumber);
                            try {
                                execute(message);
                                return;

                            }
                            catch (TelegramApiRequestException e){
                                e.printStackTrace();
                            }
                            catch (TelegramApiException e) {
                                e.printStackTrace();
                            }

                        }else {
                            String fibNumber= "I cant handle that :-( \nTry to type in the format:\n/fib XXXXX\nwhere X is a digit.\nYou can choose any positive number from 1 to 19447.";
                            SendMessage message = new SendMessage()
                                    .setChatId(chat_id)
                                    .setText(fibNumber);
                            try {
                                execute(message);
                                return;
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }
                    }catch(NumberFormatException ex)
                    {
                        String fibNumber= "I cant handle that :-( \nTry to type in the format:\n/fib XXXXX\nwhere X is a digit.\nYou can choose any positive number from 1 to 19447.";
                        SendMessage message = new SendMessage()
                                .setChatId(chat_id)
                                .setText(fibNumber);
                        try {
                            execute(message);
                            return;
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    String fibNumber= "I cant handle that :-( \nTry to type in the format:\n/fib XXXXX\nwhere X is a digit.\nYou can choose any positive number from 1 to 19447.";
                    SendMessage message = new SendMessage()
                            .setChatId(chat_id)
                            .setText(fibNumber);
                    try {
                        execute(message);
                        return;
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                String[] parts = message_text.split("([.,!?:;'\"-]|\\s)+");

                for(int i=0; i<parts.length; i++){
                    //System.out.println(parts[i]);
                    if (Pattern.matches("\\bjava\\b", parts[i])) {
                        //System.out.println(parts[i] +" matches");
                        int random = new Random().nextInt(javaAnswers.length);
                        String output = "Who said JAVA? " + javaAnswers[random];//matches "java"
                        SendMessage msg = new SendMessage()
                                .setChatId(chat_id)
                                .setText(output);
                        try {
                            execute(msg);
                            break;

                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                    }else
                    if (Pattern.matches("\\bpython\\b", parts[i])) {
                        //System.out.println(parts[i] +" matches");
                        int random = new Random().nextInt(pythonAnswers.length);
                        String output = "Who said PYTHON? " + pythonAnswers[random];//matches "python"
                        SendMessage msg = new SendMessage()
                                .setChatId(chat_id)
                                .setText(output);
                        try {
                            execute(msg);
                            break;

                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        //System.out.println(parts[i] + " doesnÂ´t match");
                    }
                }
            }
        }
    }

    public String getBotUsername() {
        return "name";
    }

    public String getBotToken() {
        return "some token here";
    }
}
