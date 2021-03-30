package org.example.webapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // включаем только не нулевые поля
// если ошибки нет, то ее не будет в ответе

// также есть аннотация ниже - чтоб когда  вызываем сеттеры - они возвращали тот же самый ответ
// чтоб можно было вызывать цепочкой
@Accessors(chain = true)
public class UserResponse {
    private String status;
    private User user;
    private String error;
}
