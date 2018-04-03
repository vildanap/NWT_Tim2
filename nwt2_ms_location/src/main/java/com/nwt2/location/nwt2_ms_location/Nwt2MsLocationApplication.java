package com.nwt2.location.nwt2_ms_location;

import com.nwt2.location.nwt2_ms_location.Model.Country;
import com.nwt2.location.nwt2_ms_location.Model.Location;
import com.nwt2.location.nwt2_ms_location.Repository.CountryRepository;
import com.nwt2.location.nwt2_ms_location.Repository.LocationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class Nwt2MsLocationApplication {

    public static void main(String[] args) {
        SpringApplication.run(Nwt2MsLocationApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CountryRepository country, LocationRepository location) {
        return (args) -> {
            country.save(new Country("Germany"));
            country.save(new Country("Spain"));
            country.save(new Country("France"));
            country.save(new Country("Bosnia and Herzegovina"));

            location.save(new Location("/resources/images/logo.jpg", "Berlin", "Description.", (float) 48.864716, (float) 2.349014, 1));


        };
    }
}