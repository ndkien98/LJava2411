package com.t3h.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ApplicationPropertiesConfig {

    @Value("${file.img.path}")
    private String pathImg;



}
