package com.nwt2.identity.nwt2_ms_identity;

import com.nwt2.identity.nwt2_ms_identity.Model.User;
import com.nwt2.identity.nwt2_ms_identity.Model.Role;
import com.nwt2.identity.nwt2_ms_identity.Repository.RoleRepository;
import com.nwt2.identity.nwt2_ms_identity.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Optional;

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

			};
		}
	}


