package task.smartwork.arbis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
public class TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	//swagger documentation and implementation
	@Configuration
	@EnableSwagger2
	public class SwaggerConfig {
		@Bean
		public Docket createPhoneBooks() {
			return new Docket(DocumentationType.SWAGGER_2)
					.groupName("Create")
					.select()
					.apis(RequestHandlerSelectors.basePackage("task.smartwork.arbis.controller"))
					.paths(regex("/phoneBook.*"))
					.build()
					.apiInfo(new ApiInfoBuilder().version("1.0").title("PHONE BOOK API CREATE/DELETE/EDIT").build());
		}
	}
}
