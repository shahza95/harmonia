package syed.shahza.harmonia.frontend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import syed.shahza.harmonia.frontend.controller.LoginController;

@Configuration
public class WebAppConfiguration {

    @Bean
    public LoginController loginController() {
        return new LoginController();
    }
}
