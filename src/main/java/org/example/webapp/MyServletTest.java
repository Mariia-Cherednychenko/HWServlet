package org.example.webapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.webapp.dto.User;
import org.example.webapp.dto.UserResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// простая программа для вывода hello World
/*public class MyServlet extends HttpServlet {
    @Override
    // HttpServletRequest req - данные которые к нам пришли ,
    // HttpServletResponse resp -  то куда пишем ответ
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //пример - неважно что нам пислали, дадим простой ответ
        *//*resp.setHeader("Content-Type", "text/plain"); // нужно писать тип ответа
        resp.getWriter().println("hello world");
        resp.getWriter().flush(); // очень важно вызывать,
        // если не вызовем - то он может не успеть заисать ответ, до того как вызов закончится
        // и будет кусок ответа
        // смывка после себя*//*

    }*/

// получение ответа
public class MyServletTest extends JsonServlet{

    int i=2;

    // создание анонимного класса
    Map<String, User> users = new HashMap<String, User> (){{
        put ("1", new User().setName("VasiaTest").setRole("admin"));
        put("2", new User().setName("PetiaTest").setRole("user"));
    }};


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // cчитываем юзера
        User user = readJson(User.class,  req);
        // юзера можн добавить
        String id = String.valueOf(++i);
        users.put(id, user);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // http://127.0.0.1:8080/?id=7
        // все параметрі достатся в виде строки
        String id = req.getParameter("id");

        if (id != null && users.containsKey(id)) {
            User user = users.get(id);
            UserResponse userResponse = new UserResponse()
                    .setUser(user)
                    .setStatus("ok");

            writeJson(userResponse, resp);
        }
        else {
            writeJson(
                    new UserResponse()
                    .setError("User not found")
                    .setStatus("ok"),
                    resp);
        }
    }
}
