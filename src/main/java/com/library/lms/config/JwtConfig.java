package com.library.lms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.jwt")
public class JwtConfig {
	private String secrete;
	private long expirationMs;
	
	// Getter and setters
	public String getSecrete() {
		return secrete;
	}
	public void setSecrete(String secrete) {
		this.secrete = secrete;
	}
	public long getExpirationMs() {
		return expirationMs;
	}
	public void setExpirationMs(long expirationMs) {
		this.expirationMs = expirationMs;
	}
	

}
