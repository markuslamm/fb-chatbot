package io.chatbot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public final class FacebookMessengerController {

    @RequestMapping(value = "/hook", method = RequestMethod.GET)
    public String fbMessengerHook() {
        return "facebook message received";
    }
}
