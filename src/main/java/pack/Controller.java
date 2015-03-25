package pack;

import json_classes.BigAnswer;
import json_classes.Message;
import json_classes.User;
import json_classes.Users;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;


@RequestMapping("/chat/**")
@RestController
public class Controller {

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String check(HttpSession session) {
        Application.log("check");
        return "im fine";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RESPONSE login(@RequestBody String name, HttpSession session) {
        Application.log("logging" + " name=" + name + " session=" + session.getId());

        if (name.length() < 3) return RESPONSE.ERROR_SHORT_LOGIN;

        Users users = (Users) (session.getServletContext()).getAttribute(Controller.USERS_KEY);
        User user = (User) session.getAttribute(Controller.USER_KEY);

        if (user == null) {
            user = new User(name);
            session.setAttribute(USER_KEY, user);
            users.addUser(user);
            Application.log(name + " logged");
        } else {
            Application.log(user.getName() + "now " + name);
            users.post(new Message(user.getName(), "now " + name));
            user.setName(name);
        }

        return RESPONSE.OK;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public void logout(HttpSession session) {

        Users users = (Users) (session.getServletContext()).getAttribute(Controller.USERS_KEY);
        User user = (User) session.getAttribute(Controller.USER_KEY);

        if (user == null) return;

        users.delUser(user);
        Application.log(user.getName() + " deleted," + " session=" + session.getId());
    }


    @RequestMapping(value = "/post", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RESPONSE post_(@RequestBody String post, HttpSession session) {
        Users users = (Users) (session.getServletContext()).getAttribute(Controller.USERS_KEY);
        User user = (User) session.getAttribute(Controller.USER_KEY);

        if (user == null) return RESPONSE.ERROR_NOT_LOGGED;

        long time = System.currentTimeMillis();
        users.post(new Message(time, user.getName(), post));

        Application.log(new Date(time).toString() + " " + user.getName() + " send: " + post);

        return RESPONSE.OK;
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<User> getUsers(HttpSession session) {
        Users users = (Users) (session.getServletContext()).getAttribute(Controller.USERS_KEY);
        return users.getUsers();
    }

    @RequestMapping(value = "/new/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BigAnswer getNew(HttpSession session) {

        User user = (User) session.getAttribute(Controller.USER_KEY);
        if (user != null) {
            BigAnswer big = new BigAnswer(RESPONSE.OK);
            big.setNewUsers(user.getNewUsers());
            big.setDeletedUsers(user.getDeletedUsers());
            big.setNewMessages(user.getNewMessage());
            return big;
        }

        return new BigAnswer(RESPONSE.ERROR_NOT_LOGGED);
    }


    public static final String USERS_KEY = "users";
    public static final String USER_KEY = "user";

    public static enum RESPONSE {OK, ERROR_SHORT_LOGIN, ERROR_NOT_LOGGED, ERROR_UNKNOWN, RESPONSE_EXC}
}
