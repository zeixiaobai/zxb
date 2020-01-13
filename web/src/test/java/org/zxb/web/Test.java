package org.zxb.web;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;
import org.zxb.web.bean.User;

import java.util.Map;

public class Test {

    public static void main(String[] args) throws BindException {
        User user = new User();
        DataBinder binder = new DataBinder(user, "user");
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.add("name", "fsx");
        pvs.add("age", 18);
        pvs.add("address","xxxxx");

        binder.bind(pvs);

        binder.validate();

        Map<?, ?> close = binder.close();

        System.out.println(user);
        System.out.println(close);

    }
}
