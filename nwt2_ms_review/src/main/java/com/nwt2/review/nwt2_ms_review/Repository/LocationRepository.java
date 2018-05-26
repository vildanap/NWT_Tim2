package com.nwt2.review.nwt2_ms_review.Repository;

import com.nwt2.review.nwt2_ms_review.Model.Location;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ohrinator on 4/3/18.
 */
public interface LocationRepository extends CrudRepository<Location, Long> {
    Iterable<Location> findFirst6ByOrderByIdDesc();
}
