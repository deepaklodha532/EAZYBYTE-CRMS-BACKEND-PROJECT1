package com.crms.CustomerRelationalManagement.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "CRMS app backend " ,
                description = "Created by Deepak,  ",
                version = "1.0v",
                contact = @Contact(
                        name = "deepak lodha",
                        email = "deepaklodha532@gmail.com",
                        url="https://linkedin.com/in/deepak-lodha-79254724b"
                )
        ),
        security = @SecurityRequirement(name = "jwtScheme")
)

@SecurityScheme(
        name = "jwtScheme",
        type= SecuritySchemeType.HTTP,
        scheme = "bearer" ,
        bearerFormat = "JWT"
)
public class ProjectConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper() ;
    }


}
