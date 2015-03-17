import java.util.ArrayList;

public class Users {

    private User[] users = new User[100];

    public int newUser(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                return i;
            }
        }
        return -1;
    }

    public boolean hasUser(int id) {
        return users[id] != null;
    }

    public User getUser(int id) {
        if (id >= users.length) return null;
        if (id < 0) return null;

        return users[id];
    }

    public void delUser(int id) {
        if (id >= users.length) return;
        if (id < 0) return;

        users[id] = null;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users1 = new ArrayList<>();
        for (User user : users) {
            if (user != null)
                users1.add(user);
        }
        return users1;
    }
}
