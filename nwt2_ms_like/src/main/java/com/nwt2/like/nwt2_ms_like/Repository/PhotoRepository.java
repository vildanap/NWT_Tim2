package com.nwt2.like.nwt2_ms_like.Repository;

import com.nwt2.like.nwt2_ms_like.Model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by amina on 3/20/2018.
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    boolean existsByUrl(String url);
}
