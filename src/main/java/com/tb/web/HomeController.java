package com.tb.web;

import com.tb.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tivadar Bocz on 2017.01.27..
 */
@RestController
@RequestMapping(path = "home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @RequestMapping(method = RequestMethod.GET, path = "public")
    public String getWelcomeMessage() {
        return homeService.getWelcomeMessgae();
    }

    @RequestMapping(method = RequestMethod.GET, path = "secured")
    public String getSecuredWelcomeMessage() {
        return homeService.getSecuredWelcomeMessgae();
    }
}
