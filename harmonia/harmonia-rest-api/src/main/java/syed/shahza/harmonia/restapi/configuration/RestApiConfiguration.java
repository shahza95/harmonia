package syed.shahza.harmonia.restapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import syed.shahza.harmonia.restapi.action.LoginAction;
import syed.shahza.harmonia.restapi.client.RestClient;

@Configuration
public class RestApiConfiguration {

	@Value("${backendBaseUrl}")
	private String backendBaseUrl;

	@Bean
	public RestClient restClient() {
		return new RestClient(this.backendBaseUrl);
	}

	@Bean
	public LoginAction getJobberAction() {
		return new LoginAction(restClient());
	}
}
