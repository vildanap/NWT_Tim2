package com.nwt2.like.nwt2_ms_like.Repository;

import com.nwt2.like.nwt2_ms_like.Model.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by amina on 3/20/2018.
 */
public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto, Long> {
  boolean existsByReviewIdAndPhotoId(Long reviewId, Long photoId);
  List<ReviewPhoto> findAllByReviewId(Long reviewId);
}