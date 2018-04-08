package com.nwt2.review.nwt2_ms_review.Controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.nwt2.review.nwt2_ms_review.Model.Location;
import com.nwt2.review.nwt2_ms_review.Model.User;
import com.nwt2.review.nwt2_ms_review.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ohrinator on 4/3/18.
 */
@RefreshScope
@RestController
public class LocationInfoController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${service.locationsService.serviceId}")
    private String locationServiceServiceId;

    /* return user info */
    @RequestMapping("/location/{myself}")
    public Location findme(@PathVariable Long myself) {
        Application application = eurekaClient.getApplication(locationServiceServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        System.out.println(instanceInfo);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "locations/" + myself;
        System.out.println("URL" + url);

        Location emp = restTemplate.getForObject(url, Location.class);
        System.out.println("ID: " + emp.getId());
        System.out.println("Name: " + emp.getName());
        System.out.println("RESPONSE " + emp);
        return emp;
    }
}
