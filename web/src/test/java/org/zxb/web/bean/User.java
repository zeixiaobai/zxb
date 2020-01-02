package org.zxb.web.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;

@Data
public class User {

    private String name;

    private int age;

    @NotBlank(message="validate.time")
    private String time;

}
