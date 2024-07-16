package com.fraisdirect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.paydunya.neptune.*;


@Configuration
public class AppConfig {


    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
