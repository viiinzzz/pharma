package org.viiinzzz.pharma;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("jpa")
// Custom JPA configuration here
// @EnableJpaRepositories("org.viiinzzz.pharma")
// @EnableJpaAuditing
public class JpaConfiguration {
}
