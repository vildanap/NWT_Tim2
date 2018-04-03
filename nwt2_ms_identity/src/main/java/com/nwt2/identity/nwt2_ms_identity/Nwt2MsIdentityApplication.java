package com.nwt2.identity.nwt2_ms_identity;

import com.nwt2.identity.nwt2_ms_identity.Model.User;
import com.nwt2.identity.nwt2_ms_identity.Model.Role;
import com.nwt2.identity.nwt2_ms_identity.Repository.RoleRepository;
import com.nwt2.identity.nwt2_ms_identity.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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

				user.save(new User("Vildana","Panjeta","vildanapanjeta","pass12A","vildanapanjeta@gmail.com",r2));
				user.save(new User("Zerina","Dragnic","zerinadragnic","pass12A","zerinadragnic@gmail.com",r2));
				user.save(new User("Amina","Puce","aminapuce","pass21A","aminapuce@gmail.com",r2));
				user.save(new User("Mirza","Ohranovic","mirzaohranovic","pass21A","mirzaohranovic@gmail.com",r2));

				//TEST : validacija - firstename, username i password nisu u ispravnom formatu
				ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
				Validator validator = factory.getValidator();
				User u = new User("","","vil","pass","vildanapanjeta",r2);
				Set<ConstraintViolation<User>> violations = validator.validate(u);
				for (ConstraintViolation<User> violation : violations) {
					System.out.println(String.format(
                            "Error here! property: [%s], value: [%s], message: [%s]",
                            violation.getPropertyPath(), violation.getInvalidValue(), violation.getMessage()));
				}
			};
		}
	}


