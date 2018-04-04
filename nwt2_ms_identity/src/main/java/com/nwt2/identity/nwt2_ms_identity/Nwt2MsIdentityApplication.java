package com.nwt2.identity.nwt2_ms_identity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.nwt2.identity.nwt2_ms_identity.Model.User;
import com.nwt2.identity.nwt2_ms_identity.Model.Role;
import com.nwt2.identity.nwt2_ms_identity.Repository.RoleRepository;
import com.nwt2.identity.nwt2_ms_identity.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@EnableDiscoveryClient
@SpringBootApplication

public class Nwt2MsIdentityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Nwt2MsIdentityApplication.class, args);
	}

		@Bean
		public CommandLineRunner demo(RoleRepository role, UserRepository user){
			return (args) -> {

				Role r1= new Role("admin");
				Role r2= new Role("registeredUser");
				role.save(r1);
				role.save(r2);

				user.save(new User("Vildana","Panjeta","vildanapanjeta","pass12A","vildanapanjeta@gmail.com",(long) 2));
				user.save(new User("Zerina","Dragnic","zerinadragnic","pass12A","zerinadragnic@gmail.com",(long)2));
				user.save(new User("Amina","Puce","aminapuce","pass21A","aminapuce@gmail.com",(long)2));
				user.save(new User("Mirza","Ohranovic","mirzaohranovic","pass21A","mirzaohranovic@gmail.com",(long)2));

				//TEST : validacija - firstename, username i password nisu u ispravnom formatu
				ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
				Validator validator = factory.getValidator();
				User u = new User("","","vil","pass","vildanapanjeta",(long)2);
				Set<ConstraintViolation<User>> violations = validator.validate(u);
				for (ConstraintViolation<User> violation : violations) {
					System.out.println(String.format(
                            "Error here! property: [%s], value: [%s], message: [%s]",
                            violation.getPropertyPath(), violation.getInvalidValue(), violation.getMessage()));
				}
			};
		}
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
				jsonConverter.setObjectMapper(new ObjectMapper());
				jsonConverter.setSupportedMediaTypes(ImmutableList.of(new MediaType("application", "json", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET), new MediaType("text", "javascript", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET)));
			}
		}
		return restTemplate;
	}
	}


