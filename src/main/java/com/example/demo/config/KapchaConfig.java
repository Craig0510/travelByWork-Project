package com.example.demo.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KapchaConfig {
    @Bean
    public DefaultKaptcha kaptchaLogin(){
        com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha=new com.google.code.kaptcha.impl.DefaultKaptcha();
        Properties properties=new Properties();
        properties.put("kapcha.border","no");
        properties.put("kapcha.textproducer.font.color","black");
        properties.put("kapcha.image.width","150");
        properties.put("kapcha.image.height","40");
        properties.put("kapcha.textproducer.font.size","30");
        properties.put("kapcha.session.key","verufyCode");
        properties.put("kapcha.textproducer.char.space","5");
        Config config=new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}

