package io.chatbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public final class FacebookMessengerController {

    private static final Logger LOG = LoggerFactory.getLogger(FacebookMessengerController.class);

    private final String fbVerifyToken;

    public FacebookMessengerController(@Value("${fb.verifytoken}") String fbVerifyToken) {
        this.fbVerifyToken = fbVerifyToken;
    }

    @RequestMapping(value = "/hook", method = RequestMethod.GET)
    public String fbMessengerHook(@RequestParam(name = "hub.verify_token") final String verifyToken,
                                  @RequestParam(name = "hub.challenge") final String challenge) {
        LOG.info("Received Facebook request, hub.verify_token={}, hub.challenge={}", verifyToken, challenge);
        if(!fbVerifyToken.equals(verifyToken)) {
            throw new RuntimeException("invalid token");
        }
        return challenge;
    }

    @RequestMapping(value = "/hook", method = RequestMethod.POST)
    public ResponseEntity<Void> handleMessage(@RequestBody final Object msgs) {
        LOG.info("Received Facebook message event: {}", msgs);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
