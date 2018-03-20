package com.nwt2.review.nwt2_ms_review;

import com.nwt2.review.nwt2_ms_review.Model.Review;
import com.nwt2.review.nwt2_ms_review.Model.ReviewType;
import com.nwt2.review.nwt2_ms_review.Repository.ReviewRepository;
import com.nwt2.review.nwt2_ms_review.Repository.ReviewTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class Nwt2MsReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(Nwt2MsReviewApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ReviewRepository repository, ReviewTypeRepository typeRepo) {
		return (args) -> {
			repository.save(new Review(10, 10, "This is a comment", 2));
			repository.save(new Review(10, 10, "This is a comment", 2));
			repository.save(new Review(10, 10, "This is a comment", 2));
			repository.save(new Review(10, 10, "This is a comment", 2));

			typeRepo.save(new ReviewType("Food"));
			typeRepo.save(new ReviewType("Nightlife"));
			typeRepo.save(new ReviewType("Culture"));
			typeRepo.save(new ReviewType("Movies"));
		};
	}
}
