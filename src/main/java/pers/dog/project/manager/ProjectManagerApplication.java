package pers.dog.project.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pers.dog.project.manager.configuration.GitlabProperties;

@SpringBootApplication
@EnableConfigurationProperties(GitlabProperties.class)
public class ProjectManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagerApplication.class, args);
    }

}
