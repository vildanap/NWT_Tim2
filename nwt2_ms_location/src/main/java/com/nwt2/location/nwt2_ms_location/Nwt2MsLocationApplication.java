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
            Country g = new Country("Germany");
            country.save(g);
            country.save(new Country("Spain"));
            Country f = new Country("France");
            country.save(f);
            Country c = new Country("Bosnia and Herzegovina");
            country.save(c);
            Country usa = new Country("United States of America");
            country.save(usa);

            location.save(new Location("https://www.telegraph.co.uk/content/dam/Travel/hotels/europe/france/paris/eiffel-tower-paris-p.jpg", "Paris", "Description.", (float) 48.864716, (float) 2.349014, f));
            location.save(new Location("https://www.visitberlin.de/system/files/styles/visitberlin_bleed_header_visitberlin_mobile_1x/private/image/iStock_000074120341_Double_DL_PPT_0.jpg?h=a66ba266&itok=2YXS5_33", "Berlin", "Description.", (float) 52.51, (float) 13.349014, g));
            location.save(new Location("https://media.cntraveler.com/photos/5a9d825ad363c34048b3639a/3:2/w_385,c_limit/GettyImages-640006562.jpg", "New York", "Description.", (float) 73.9667, (float) 40.783, usa));
            location.save(new Location("https://sarajevo.travel/assets/photos/tours/original/eat-pray-love-1407767838.jpg", "Sarajevo", "Description.", (float) 43.83, (float) 18.37, c));
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