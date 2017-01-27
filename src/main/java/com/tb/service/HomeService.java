package com.tb.service;

import org.springframework.stereotype.Service;

/**
 * Created by Tivadar Bocz on 2017.01.27..
 */
@Service
public class HomeService {

    public String getWelcomeMessgae() {
        return "Hello";
    }

    public String getSecuredWelcomeMessgae() {
        return "Hello secured";
    }
}
