package json_classes;

import java.util.ArrayList;

public class Users {


    private ArrayList<User> mUsers = new ArrayList<>();

    public void addUser(User user) {
        mUsers.add(user);
        for (User u : mUsers) {
            u.addNewUser(user);
        }
    }

    public boolean delUser(User user) {
        boolean deleted = mUsers.remove(user);
        if (deleted)
            for (User u : mUsers) {
                u.addDeletedUsers(user);
            }
        return deleted;
    }

    public ArrayList<User> getUsers() {
        return mUsers;
    }

    public void post(Message msg) {
        for (User u : mUsers) {
            if (u != null)
                u.addNewMessage(msg);
        }
    }
}
