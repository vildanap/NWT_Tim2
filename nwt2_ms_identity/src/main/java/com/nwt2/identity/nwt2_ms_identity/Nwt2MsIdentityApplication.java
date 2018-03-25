package com.nwt2.identity.nwt2_ms_identity;

import com.nwt2.identity.nwt2_ms_identity.Model.User;
import com.nwt2.identity.nwt2_ms_identity.Model.Role;
import com.nwt2.identity.nwt2_ms_identity.Repository.RoleRepository;
import com.nwt2.identity.nwt2_ms_identity.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication

public class Nwt2MsIdentityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Nwt2MsIdentityApplication.class, args);
	}

		@Bean
		public CommandLineRunner demo(RoleRepository role, UserRepository user){
			return (args) -> {

				role.save(new Role("admin"));
				role.save(new Role("registeredUser"));

				user.save(new User("Vildana","Panjeta","vildanapanjeta","pass","vildanapanjeta@gmail.com",2));
				user.save(new User("Zerina","Dragnic","zerinadragnic","pass","zerinadragnic@gmail.com",2));
				user.save(new User("Amina","Puce","aminapuce","pass","aminapuce@gmail.com",2));
				user.save(new User("Mirza","Ohranovic","mirzaohranovic","pass","mirzaohranovic@gmail.com",2));

				//TEST : validacija - firstename, username i password nisu u ispravnom formatu
				ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
				Validator validator = factory.getValidator();
				User u = new User("","Panjeta","vil","pass","vildanapanjeta",2);
				Set<ConstraintViolation<User>> violations = validator.validate(u);
				for (ConstraintViolation<User> violation : violations) {
					System.out.println(violation.getMessage());
				}
			};
		}
	}


