package com.company.util;

import com.company.program.AppProperties;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class AppPropertiesHelper {
    public void run() {
        {
            try {
                InputStream is = AppPropertiesHelper.class.getResourceAsStream("/app.properties");
                Properties properties = new Properties();
                properties.load(is);
                AppProperties appProperties = PropertyHandling.ManagingProperties(properties, AppProperties.class);
                Class<?> classOfInstance = appProperties.getClass();
                Field[] fields = classOfInstance.getFields();
                for (Field field : fields) {
                    System.out.println("The field name is " +
                            field.getName() + "; the type is " +
                            field.getType() + "; annotated type is" +
                            field.getAnnotatedType());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
