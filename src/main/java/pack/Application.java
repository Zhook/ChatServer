package pack;

import json_classes.User;
import json_classes.Users;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    @Bean
    public HttpSessionListener getHttpSessionListener() {
        return new HttpSessionListener() {

            @Override
            public void sessionCreated(HttpSessionEvent event) {
                HttpSession session = event.getSession();
                log("session " + session.getId() + " Created");
                session.setMaxInactiveInterval(30);
            }

            @Override
            public void sessionDestroyed(HttpSessionEvent event) {
                HttpSession session = event.getSession();
                ServletContext servletContext = session.getServletContext();

                log("session " + session.getId() + " Destroyed");

                Users users = (Users) servletContext.getAttribute(Controller.USERS_KEY);
                if (users != null) {
                    User user = (User) session.getAttribute(Controller.USER_KEY);
                    users.delUser(user);
                }
            }
        };
    }

    @Bean
    public ServletContextListener getServletContextListener() {
        return new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                sce.getServletContext().setAttribute(Controller.USERS_KEY, new Users());
                log("ServletContext " + sce.getServletContext().toString() + " Initialized");
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {
                log("ServletContext " + sce.getServletContext().toString() + " Destroyed");
            }
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public static void log(String text) {
        System.out.println("myout: " + text);
    }
}
