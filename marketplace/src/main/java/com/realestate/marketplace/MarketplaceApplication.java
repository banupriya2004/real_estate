package com.realestate.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketplaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceApplication.class, args);
	}
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow ALL URLs
                        .allowedOrigins("*") // Allow ALL websites (including Vercel)
                        .allowedMethods("*") // Allow GET, POST, PUT, DELETE
                        .allowedHeaders("*"); // Allow ALL headers
            }
        };
    }

    // Keep your test endpoint
    @GetMapping("/test")
    public String test() {
        return "Server is updated and CORS is enabled!";
    }
}
