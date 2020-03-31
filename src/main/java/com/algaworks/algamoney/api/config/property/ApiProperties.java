package com.algaworks.algamoney.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties("api")
public class ApiProperties {
	
	private Security security = new Security();
	
	@Getter
	@Setter
	public static class Security {
		private String allowedOrigin;
		private AuthStrategy authStrategy = AuthStrategy.OAUTH2;
	}
}
