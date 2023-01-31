package org.viiinzzz.pharma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.viiinzzz.pharma.entity.Upload;
import org.viiinzzz.pharma.help.AnsiHelper;
import org.viiinzzz.pharma.help.EnvHelper;
import org.viiinzzz.pharma.repository.MeasureRepository;
import org.viiinzzz.pharma.repository.UploadRepository;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.viiinzzz.pharma.property.FileStorageProperties;
import org.viiinzzz.pharma.entity.Measure;

import java.time.LocalDateTime;


@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class Application {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("org.viiinzzz")).build();
    }
   
    @Autowired
    private MeasureRepository measureRepository;

    @Autowired
    private UploadRepository uploadRepository;

    @Autowired
    private EnvHelper envUtil;


    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Profile("verify")
    public CommandLineRunner onApplicationStart() {
        return args -> {
            System.out.println("\n" + AnsiHelper.ANSI_CYAN + "API: http://localhost:"
                    + envUtil.getEnvValue("local.server.port")
                    + "/\n" + AnsiHelper.ANSI_RESET);

            measureRepository.deleteAll();
            uploadRepository.deleteAll();

            Upload upload = new Upload("test-probe", LocalDateTime.now());

            uploadRepository.save(upload);
            System.out.println(measureRepository.count() + " measure found:");

            measureRepository.findAll();
            measureRepository.save(new Measure(upload, "2022-02-01 08:00:00", "15.1"));
            measureRepository.save(new Measure(upload, "2022-02-01 09:00:00", "14.5"));

            measureRepository.findAll().forEach(measure -> System.out.println("demo " + measure));
            System.out.println("--------------------------------------------");
        };
    }
}
