package io.chatbot.controller;

import io.chatbot.model.IncomingMessageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

@RestController
public final class FacebookMessengerController {

    private static final Logger LOG = LoggerFactory.getLogger(FacebookMessengerController.class);

    private final String fbVerifyToken;
    private final String fbAccesstoken;

    public FacebookMessengerController(@Value("${fb.verifytoken}") final String fbVerifyToken,
                                       @Value("${fb.accesstoken}") final String fbAccesstoken) {
        this.fbVerifyToken = fbVerifyToken;
        this.fbAccesstoken = fbAccesstoken;
    }

    @RequestMapping(value = "/hook", method = RequestMethod.GET)
    public String fbMessengerHook(@RequestParam(name = "hub.verify_token") final String verifyToken,
                                  @RequestParam(name = "hub.challenge") final String challenge) {
        LOG.info("Received Facebook verification request, hub.verify_token={}, hub.challenge={}", verifyToken, challenge);
        if(!fbVerifyToken.equals(verifyToken)) {
            throw new RuntimeException("Invalid token");
        }
        return challenge;
    }

    @RequestMapping(value = "/hook", method = RequestMethod.POST)
    public ResponseEntity<Void> handleMessage(@RequestBody final IncomingMessageData incomingMessageData) {
        LOG.info("Received message data: {}", incomingMessageData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void sendMessage(final String recipientId, final String msg) {
        RestTemplate restTemplate = new RestTemplate();
        final String url = format("https://graph.facebook.com/v2.6/me/messages?access_token=%s", fbAccesstoken);
        final String body = format("{\"recipient\": { \"id\": %s }, \"message\": { \"text\":\"hello, world!\" }}", recipientId);
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        final HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        final ResponseEntity<Object> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
        LOG.info("Send Facebook message: {}", exchange.getStatusCode());
    }


}
