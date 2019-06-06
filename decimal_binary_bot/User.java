public class User {
    private String chatID;
    private String state;

    public User(String chatID, String state){
        this.chatID=chatID;
        this.state=state;
    }
    public String getChatID(){
        return chatID;
    }
    public String getState(){
        return state;
    }
}
