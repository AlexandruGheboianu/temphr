package com.ghb.temphr;

import com.ghb.temphr.config.AuditingDateTimeProvider;
import com.ghb.temphr.config.ModelAuditor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by agheboianu on 02.03.2017.
 */
@SpringBootApplication
@EnableJpaAuditing(dateTimeProviderRef = "", auditorAwareRef = "")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurerAdapter() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
      }
    };
  }

  @Bean
  DateTimeProvider dateTimeProvider() {
    return new AuditingDateTimeProvider();
  }

  @Bean
  AuditorAware<String> auditorAware() {
    return new ModelAuditor();
  }
}