package json_classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    private String session;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @JsonIgnore
    private ArrayList<User> newUsers = new ArrayList<>();

    @JsonIgnore
    private ArrayList<User> deletedUsers = new ArrayList<>();

    @JsonIgnore
    private ArrayList<Message> newMessage = new ArrayList<>();

    public synchronized void addNewUser(User user) {
        newUsers.add(user);
    }

    public synchronized ArrayList<User> getNewUsers() {
        ArrayList<User> users = newUsers;
        newUsers = new ArrayList<>();
        return users;
    }

    public synchronized void addDeletedUsers(User deletedUser) {
        this.deletedUsers.add(deletedUser);
    }

    public synchronized ArrayList<User> getDeletedUsers() {
        ArrayList<User> users = deletedUsers;
        deletedUsers = new ArrayList<>();
        return users;
    }

    public synchronized void addNewMessage(Message msg) {
        newMessage.add(msg);
    }

    public synchronized ArrayList<Message> getNewMessage() {
        ArrayList<Message> msgs = newMessage;
        newMessage = new ArrayList<>();
        return msgs;
    }
}
