package io.chatbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class FacebookMessengerController {

    private static final Logger LOG = LoggerFactory.getLogger(FacebookMessengerController.class);

    @RequestMapping(value = "/hook", method = RequestMethod.GET)
    public String fbMessengerHook(@RequestParam(name = "hub.verify_token") final String verifyToken,
                                  @RequestParam(name = "hub.challenge") final String challenge) {
        LOG.info("Received Facebook request, hub.verify_token={}, hub.challenge={}", verifyToken, challenge);
        if(!"1234567890".equals(verifyToken)) {
            throw new RuntimeException("invalid token");
        }
        return challenge;
    }
}
