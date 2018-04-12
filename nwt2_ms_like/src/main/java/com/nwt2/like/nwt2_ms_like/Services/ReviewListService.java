package com.nwt2.like.nwt2_ms_like.Services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import com.nwt2.like.nwt2_ms_like.Model.Photo;
import com.nwt2.like.nwt2_ms_like.Model.ReviewPhoto;
import com.nwt2.like.nwt2_ms_like.Repository.PhotoRepository;
import com.nwt2.like.nwt2_ms_like.Repository.ReviewPhotoRepository;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.tools.jstat.ParserException;

import java.io.IOException;

/**
 * Created by ohrinator on 4/12/18.
 */
@Service
public class ReviewListService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    ReviewPhotoRepository reviewPhotoRepository;

    @RabbitListener(queues = "#{photoReviewCreatedQueue.name}")
    public void createPhotoForReview(String message) {
        logger.info(message);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObj =  (JSONObject) parser.parse(message);

            String url = jsonObj.getAsString("image");

            Number reviewidint = jsonObj.getAsNumber("review_id");
            Long reviewid = new Long(reviewidint.longValue());

            logger.info("URL: " + url);

            Photo photo = new Photo(url);
            photoRepository.save(photo);

            Long photoid = photo.getId();

            ReviewPhoto rev = new ReviewPhoto(reviewid, photoid);
            reviewPhotoRepository.save(rev);
        } catch (ParseException e) {
            logger.info(String.valueOf(e.getMessage()));
        } catch (Exception e) {
            logger.info(String.valueOf(e.getMessage()));
        }
    }

}
