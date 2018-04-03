package com.nwt2.review.nwt2_ms_review;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.nwt2.review.nwt2_ms_review.Model.Review;
import com.nwt2.review.nwt2_ms_review.Model.ReviewType;
import com.nwt2.review.nwt2_ms_review.Repository.ReviewRepository;
import com.nwt2.review.nwt2_ms_review.Repository.ReviewTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@EnableDiscoveryClient
@SpringBootApplication
public class Nwt2MsReviewApplication {
	public static void main(String[] args) {
		SpringApplication.run(Nwt2MsReviewApplication.class, args);
	}
}
