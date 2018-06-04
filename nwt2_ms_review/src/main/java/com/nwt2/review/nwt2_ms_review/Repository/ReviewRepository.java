package com.nwt2.review.nwt2_ms_review.Repository;

import com.nwt2.review.nwt2_ms_review.Model.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by ohrinator on 3/20/18.
 */
public interface ReviewRepository extends CrudRepository<Review, Long> {
    Iterable<Review> findByCityId(Integer cityId);
    Iterable<Review> findByUserId(Integer userId);
    Long countByCityId(Integer cityId);
    Iterable<Review> findTop10ByOrderByIdDesc();
    Boolean existsByCityId(Integer cityId);

    @Query("SELECT AVG(r.grade) from Review r where r.cityId = :cityId")
    Double getAverageGrade(@Param("cityId") Integer cityId);
}
