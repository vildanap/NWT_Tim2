package com.nwt2.review.nwt2_ms_review.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.nwt2.review.nwt2_ms_review.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
public class UserInfoController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${service.usersService.serviceId}")
    private String identityServiceServiceId;

    /* return user info */
    @RequestMapping("/user/{myself}")
    public User findme(@PathVariable Long myself) {
        Application application = eurekaClient.getApplication(identityServiceServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        System.out.println(instanceInfo);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "users/find/" + myself;
        System.out.println("URL" + url);

        User emp = restTemplate.getForObject(url, User.class);
        System.out.println("RESPONSE " + emp);
        return emp;
    }

}
