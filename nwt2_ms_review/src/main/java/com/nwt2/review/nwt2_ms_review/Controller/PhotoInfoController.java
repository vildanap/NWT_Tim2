package com.nwt2.review.nwt2_ms_review.Controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.nwt2.review.nwt2_ms_review.Model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
public class PhotoInfoController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${service.photosService.serviceId}")
    private String pohotoServiceServiceId;

    @RequestMapping("/photo/{myself}")
    public Photo findme(@PathVariable Long myself) {
        Application application = eurekaClient.getApplication(pohotoServiceServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        System.out.println(instanceInfo);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "photo/find/" + myself;
        System.out.println("URL" + url);

        Photo photo = restTemplate.getForObject(url, Photo.class);
        System.out.println("RESPONSE " + photo);
        return photo;
    }
}
