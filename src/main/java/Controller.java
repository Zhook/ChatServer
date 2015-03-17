import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@RequestMapping("/chat/**")
@RestController
public class Controller {

    private Users users = new Users();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public
    @ResponseBody
    String isLive() {
        System.out.println("myout: " + "isLive");
        return "live";
    }

    @RequestMapping("/start")
    public void generate(HttpSession session) {
        System.out.println("myout: " + "start");
        users = new Users();
        int id;
        for (int i = 0; i < 10; i++) {
            id = users.newUser(new User("Name" + String.valueOf(i)));
            System.out.println("myout: " + "new user id=" + id);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public
    @ResponseBody
    String login(@RequestParam(value = "name", required = true) String name, HttpSession session) {
        int id = users.newUser(new User(name));
        System.out.println(
                "myout: " + "loged"
                        + " name=" + name
                        + " id=" + id
                        + " session=" + session.getId());
        return String.valueOf(id);
    }

    @RequestMapping(value = "/{id}/logout", method = RequestMethod.GET)
    public
    @ResponseBody
    String logout(@PathVariable int id, HttpSession session) {
        users.delUser(id);
        System.out.println(
                "myout: " + "deleted"
                        + " id=" + id
                        + " session=" + session.getId());
        return "deleted";
    }

//    @RequestMapping(value = "getUsers", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ArrayList<User> getUser() {
//        System.out.println("myout: " + "getid");
//        return users.getUsers();
//    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<User> getUsers() {
        System.out.println("myout: " + "getid");
        return users.getUsers();
    }
}
