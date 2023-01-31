package org.viiinzzz.pharma;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mongodb")
// Custom Mongo configuration here
// @EnableMongoRepositories("org.viiinzzz.pharma")
// @EnableMongoAuditing
public class MongoConfiguration {
}
