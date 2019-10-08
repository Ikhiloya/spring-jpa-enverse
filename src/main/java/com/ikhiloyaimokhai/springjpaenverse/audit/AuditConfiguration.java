package com.ikhiloyaimokhai.springjpaenverse.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class AuditConfiguration {

//    @Bean
//    AuditorAware<String> auditorProvider() {
//        return new AuditorAwareImpl();
//    }

}
