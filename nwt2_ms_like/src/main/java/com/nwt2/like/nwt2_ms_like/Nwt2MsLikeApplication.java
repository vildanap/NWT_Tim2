package com.nwt2.like.nwt2_ms_like;

import com.nwt2.like.nwt2_ms_like.Model.Photo;
import com.nwt2.like.nwt2_ms_like.Model.ReviewPhoto;
import com.nwt2.like.nwt2_ms_like.Repository.PhotoRepository;
import com.nwt2.like.nwt2_ms_like.Repository.ReviewPhotoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class Nwt2MsLikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Nwt2MsLikeApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PhotoRepository photoRepository, ReviewPhotoRepository reviewPhotoRepository) {
		return (args) -> {
			//photoRepository.save(new Photo("https://cdn.pixabay.com/photo/2016/01/19/18/00/city-1150026_960_720.jpg"));
			//photoRepository.save(new Photo("https://www.liveandinvestoverseas.com/wp-content/uploads/2015/03/urban-407693_1280.jpg"));
			//photoRepository.save(new Photo("https://s3-media3.fl.yelpcdn.com/bphoto/Js5QOEvLhCh6tRxNkkVdKw/348s.jpg"));

			//reviewPhotoRepository.save(new ReviewPhoto(1,1));
			//reviewPhotoRepository.save(new ReviewPhoto(1,2));
			//reviewPhotoRepository.save(new ReviewPhoto(2,3));
		};
	}
}
