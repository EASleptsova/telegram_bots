import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Prog06Bot extends TelegramLongPollingBot {

    public static ArrayList<User> users= new ArrayList<User>();

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            for(User u:users) {
                if (u.getChatID().equals(String.valueOf(chat_id))) {
                    //convert
                    if (u.getState().equals("binary_decimal")) {
                        ConverterBD c = new ConverterBD();
                        String output = c.output(message_text);
                        SendMessage msg = new SendMessage()
                                .setChatId(chat_id)
                                .setText(output);
                        try {
                            execute(msg);
                            users.remove(u);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    if (u.getState().equals("decimal_binary")) {
                        ConverterDB c = new ConverterDB();
                        String output = c.output(message_text);
                        SendMessage msg = new SendMessage()
                                .setChatId(chat_id)
                                .setText(output);
                        try {
                            execute(msg);
                            users.remove(u);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

                if (message_text.equals("/start")) {
                    // User send /start
                    String welcome= "Welcome to our nerdy cave! \nUse /markup to convert numbers ";
                    SendMessage message = new SendMessage() // Create a message object
                            .setChatId(chat_id)
                            .setText(welcome);
                    try {
                        execute(message); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (message_text.equals("/markup")) {
                    SendMessage message = new SendMessage() // Create a message object
                            .setChatId(chat_id)
                            .setText("Choose a converter(button)");
                    // Create ReplyKeyboardMarkup object
                    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                    // Create the keyboard (list of keyboard rows)
                    List<KeyboardRow> keyboard = new ArrayList();
                    // Create a keyboard row
                    KeyboardRow row = new KeyboardRow();
                    // Set each button, you can also use KeyboardButton objects if you need something else than text
                    row.add("Binary_to_Decimal");
                    // Add the first row to the keyboard
                    keyboard.add(row);
                    // Create another keyboard row
                    row = new KeyboardRow();
                    // Set each button for the second line
                    row.add("Decimal_to_Binary");
                    // Add the second row to the keyboard
                    keyboard.add(row);
                    // Set the keyboard to the markup
                    keyboardMarkup.setKeyboard(keyboard);
                    // Add it to the message
                    message.setReplyMarkup(keyboardMarkup);
                    try {
                        execute(message); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else if (message_text.equals("Binary_to_Decimal")) {
                    String input ="Input a binary number";
                    SendMessage msg = new SendMessage()
                            .setChatId(chat_id)
                            .setText(input);
                    ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
                    msg.setReplyMarkup(keyboardMarkup);
                    try {
                        execute(msg);
                        users.add(new User(String.valueOf(chat_id), "binary_decimal"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else if (message_text.equals("Decimal_to_Binary")) {
                    String input ="Input a decimal number";
                    SendMessage msg = new SendMessage()
                            .setChatId(chat_id)
                            .setText(input);
                    ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
                    msg.setReplyMarkup(keyboardMarkup);
                    try {
                        execute(msg);
                        users.add(new User(String.valueOf(chat_id), "decimal_binary"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else if (message_text.equals("/hide")) {
                    SendMessage msg = new SendMessage()
                            .setChatId(chat_id)
                            .setText("Keyboard hidden");
                    ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
                    msg.setReplyMarkup(keyboardMarkup);
                    try {
                        execute(msg); // Call method to send the photo
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    // Unknown command
                    SendMessage message = new SendMessage() // Create a message object
                            .setChatId(chat_id)
                            .setText("Unknown command");
                    try {
                        execute(message); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    public String getBotUsername() {
        return "Prog06Bot";
    }

    public String getBotToken() {
        return "someTokenHere";
    }
}
