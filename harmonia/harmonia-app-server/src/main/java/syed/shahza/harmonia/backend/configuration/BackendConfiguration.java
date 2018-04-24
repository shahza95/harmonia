package syed.shahza.harmonia.backend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import syed.shahza.harmonia.backend.core.configuration.CoreConfiguration;
import syed.shahza.harmonia.backend.core.configuration.CoreJpaRepositoryConfiguration;
import syed.shahza.harmonia.backend.endpoint.configuration.EndpointConfiguration;

// Enables specified configuration files to access each others defined beans
@Configuration
@Import({ CoreConfiguration.class, CoreJpaRepositoryConfiguration.class, EndpointConfiguration.class })
public class BackendConfiguration {

}