package io.chatbot.controller;

import io.chatbot.messaging.IncomingMessageHandler;
import io.chatbot.model.json.IncomingMessageData;
import io.chatbot.service.FBVerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

@RestController
public final class FacebookMessengerController {

    private static final Logger LOG = LoggerFactory.getLogger(FacebookMessengerController.class);

    private final FBVerificationService fbVerificationService;
    private final IncomingMessageHandler incomingMessageHandler;

    @Autowired
    public FacebookMessengerController(final FBVerificationService fbVerificationService,
                                       final IncomingMessageHandler incomingMessageHandler) {
        this.fbVerificationService = requireNonNull(fbVerificationService);
        this.incomingMessageHandler = requireNonNull(incomingMessageHandler);

    }

    @RequestMapping(value = "/hook", method = RequestMethod.GET)
    public String fbMessengerHook(@RequestParam(name = "hub.verify_token") final String verifyToken,
                                  @RequestParam(name = "hub.challenge") final String challenge) {
        LOG.info("Received Facebook verification request, hub.verify_token={}, hub.challenge={}", verifyToken, challenge);
        return fbVerificationService.verifyWebhook(verifyToken, challenge);
    }

    @RequestMapping(value = "/hook", method = RequestMethod.POST)
    public ResponseEntity<Void> handleMessage(@RequestBody final IncomingMessageData incomingMessageData) {
        LOG.info("Received message data: {}", incomingMessageData);
        final ResponseEntity<?> responseEntity = incomingMessageHandler.handleMessage(incomingMessageData);
        if(!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException(format("Cannot send message, status: %s", responseEntity.getStatusCode()));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
