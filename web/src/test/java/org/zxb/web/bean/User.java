package org.zxb.web.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class User {

    @NotBlank(message = "name不能为空")
    private String name;

    private int age;

    @NotBlank(message = "time不能为空")
    private String time;

//    @NotNull(message = "person不能为空")
//    @Valid
    private Person person;
}
