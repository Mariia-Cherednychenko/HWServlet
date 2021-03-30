package org.example.webapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // включаем только не нулевые поля
// т.к. возвращать пароль не будем - то его и не будет в ответе
// также есть аннотация ниже - чтоб когда  вызываем сеттеры - они возвращали тот же самый ответ
// чтоб можно было вызывать цепочкой
// есть еще похожая аннотация - билдер - но у него нет конструктора по умолчанию,
// а если пишем так - то конструторк по умолчанию остается

@Accessors (chain = true)
public class User {
    private String name;
    private String password;
    private String role;
}
