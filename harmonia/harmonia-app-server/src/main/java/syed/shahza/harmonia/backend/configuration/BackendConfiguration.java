package syed.shahza.harmonia.backend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import syed.shahza.harmonia.backend.core.configuration.CoreConfiguration;
import syed.shahza.harmonia.backend.endpoint.configuration.EndpointConfiguration;


@Configuration
@Import({ CoreConfiguration.class, EndpointConfiguration.class })
public class BackendConfiguration {

}