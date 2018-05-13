package com.nwt2.like.nwt2_ms_like.Repository;

import com.nwt2.like.nwt2_ms_like.Model.Photo;
import com.nwt2.like.nwt2_ms_like.Model.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by amina on 3/20/2018.
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    boolean existsByUrl(String url);

    @Query("SELECT p.url FROM Photo p where p.id = :id")
    String findUrlByPhotoId(@Param("id") Long id);

    default Collection<String> findAllUrls(Collection<ReviewPhoto> reviewPhotos){
        Collection<String> photoUrls = new ArrayList<String>();
        reviewPhotos.forEach((temp) -> {
            photoUrls.add(findUrlByPhotoId(temp.getPhotoId()));
        });
        return photoUrls;
    }
}
