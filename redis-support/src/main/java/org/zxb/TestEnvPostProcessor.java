package org.zxb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

import java.util.Iterator;
import java.util.Map;

/**
 * 覆盖配置文件
 *
 * @author zjx
 * @date 2020/10/19 0019 16:17
 */
public class TestEnvPostProcessor implements EnvironmentPostProcessor, Ordered {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        Iterator<PropertySource<?>> iterator = environment.getPropertySources().iterator();
        while (iterator.hasNext()) {
            PropertySource<?> ps = iterator.next();
            if (ps.getName().startsWith("applicationConfig")) {
                Map<String, Object> originalSource = (Map<String, Object>) ps.getSource();
                if (ps.getProperty("test.a") != null) {
                    originalSource.put("test.a", "update");
                    originalSource.put("test.b", "updateb");
                }
            }
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
