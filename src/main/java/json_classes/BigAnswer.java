package json_classes;

import pack.Controller;

import java.util.ArrayList;

public class BigAnswer {

    private Controller.RESPONSE status= Controller.RESPONSE.ERROR_UNKNOWN;
    private ArrayList<User> newUsers = new ArrayList<>();
    private ArrayList<User> deletedUsers = new ArrayList<>();
    private ArrayList<Message> newMessages = new ArrayList<>();

    public BigAnswer() {
    }

    public BigAnswer(Controller.RESPONSE status) {
        this.status = status;
    }


    public Controller.RESPONSE getStatus() {
        return status;
    }

    public void setStatus(Controller.RESPONSE status) {
        this.status = status;
    }

    public ArrayList<User> getDeletedUsers() {
        return deletedUsers;
    }

    public void setDeletedUsers(ArrayList<User> deletedUsers) {
        this.deletedUsers = deletedUsers;
    }

    public ArrayList<Message> getNewMessages() {
        return newMessages;
    }

    public void setNewMessages(ArrayList<Message> newMessages) {
        this.newMessages = newMessages;
    }

    public ArrayList<User> getNewUsers() {
        return newUsers;
    }

    public void setNewUsers(ArrayList<User> newUsers) {
        this.newUsers = newUsers;
    }


}
