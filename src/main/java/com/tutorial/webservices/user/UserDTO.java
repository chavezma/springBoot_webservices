package com.tutorial.webservices.user;

import java.time.LocalDate;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String name;
    private LocalDate bithDate;
}
