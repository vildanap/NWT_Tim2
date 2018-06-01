package com.nwt2.review.nwt2_ms_review.Repository;

import com.nwt2.review.nwt2_ms_review.Model.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ohrinator on 3/20/18.
 */
public interface ReviewRepository extends CrudRepository<Review, Long> {
    Iterable<Review> findByCityId(Integer cityId);
    Long countByCityId(Long cityId);

    Iterable<Review> findTop10ByOrderByIdDesc();
}
