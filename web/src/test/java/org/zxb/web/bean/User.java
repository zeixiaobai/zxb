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

    @NotBlank(message="not.null")
    private String name;

    private int age;

    @NotBlank(message="not.null")
    private String time;

    @NotNull(message = "not.null")
    @Valid
    private Person person;
}
