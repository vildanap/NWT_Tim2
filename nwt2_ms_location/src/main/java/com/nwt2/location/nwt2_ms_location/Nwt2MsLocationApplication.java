package com.nwt2.location.nwt2_ms_location;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.nwt2.location.nwt2_ms_location.Model.Country;
import com.nwt2.location.nwt2_ms_location.Model.Location;
import com.nwt2.location.nwt2_ms_location.Repository.CountryRepository;
import com.nwt2.location.nwt2_ms_location.Repository.LocationRepository;
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

import java.util.List;

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