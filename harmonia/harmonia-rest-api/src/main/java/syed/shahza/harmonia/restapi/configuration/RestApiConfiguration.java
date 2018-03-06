package syed.shahza.harmonia.restapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.JoinLectureAction;
import syed.shahza.harmonia.restapi.action.LectureCreationAction;
import syed.shahza.harmonia.restapi.action.LoginAction;
import syed.shahza.harmonia.restapi.client.RestClient;

@Configuration
@PropertySource("classpath:client.properties")
public class RestApiConfiguration {

	@Value("${backendBaseUrl}")
	private String backendBaseUrl;

	@Bean
	public RestClient restClient() {
		return new RestClient(this.backendBaseUrl);
	}

	@Bean
	public LoginAction loginAction() {
		return new LoginAction(restClient());
	}
	
	@Bean
	public LectureCreationAction lectureCreationAction() {
		return new LectureCreationAction(restClient());
	}
	
	@Bean
	public JoinLectureAction joinLectureAction() {
		return new JoinLectureAction(restClient());
	}
	
	@Bean
	public GetAllCommentsAction getAllCommentsAction() {
		return new GetAllCommentsAction(restClient());
	}
}
