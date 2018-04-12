package com.nwt2.review.nwt2_ms_review.Controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Collection;

@RefreshScope
@RestController
public class PhotoInfoController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${service.photosService.serviceId}")
    private String photoServiceServiceId;

    /* return photo info */
    @RequestMapping("/review/{myself}/photos")
    public Collection<String> findPhotos(@PathVariable Long myself) {
        Application application = eurekaClient.getApplication(photoServiceServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        System.out.println(instanceInfo);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "reviewphotos/review/" + myself + "/urls";
        System.out.println("URL" + url);

        Collection<String> result = restTemplate.getForObject(url, Collection.class);
        return result;
    }
}
