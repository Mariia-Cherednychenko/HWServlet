package org.example.webapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;

public class JsonServlet extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();

    // помимо конструктора есть блоки инициализации
    // это блок кода, который запускаетс после конструктора автоматчески
    // есть статик блок - запускается при первом обращении к классу, конструктор можно вообще не вызывать,
    // но он работает только 1 раз - > static {} , используется для инициалищации статических полей
    // но по возможности его лучше вообще не использовать
    // обычный блок  -  запускается после конструктора -> {}
    // большинство блоков пишется без этих  блоков
    /*{
       mapper = new ObjectMapper();
    }*/

protected void writeJson(Object object, HttpServletResponse response) throws IOException {
    try {
        response.setHeader("Content-Type", "application/json");
        String  strResponse = mapper.writeValueAsString(object);
        response.getWriter().print(strResponse); // исключение добавили в метод сигнатур, т.к. сервлеті могут вібрасівать исклчения
    response.getWriter().flush(); // обязательно
    } catch (JsonProcessingException e) {
        e.printStackTrace(); // исключения віваливатся в логе томката - файл каталина аут
    }

}

protected  <T> T readJson (Class <T> clazz, HttpServletRequest request) throws IOException {
    // чтоб читать реквесты
    // делаем строку - 2 варианта
    /*Scanner scanner = new Scanner(request.getInputStream());
    StringBuilder stringBuilder = new StringBuilder();
    while (scanner.hasNextLine()){
       stringBuilder.append(scanner.nextLine());
    }
    String requestString = stringBuilder.toString();*/

   // String requestString = new String(request.getInputStream().readAllBytes());

    // маппер умеет принимать стрим, поєтому строка не нужна
    return mapper.readValue(request.getInputStream(), clazz);


}

}
