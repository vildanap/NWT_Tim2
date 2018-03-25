package com.nwt2.location.nwt2_ms_location.Repository;

import com.nwt2.location.nwt2_ms_location.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Dragnic on 3/20/2018.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsByName(String name);
}
