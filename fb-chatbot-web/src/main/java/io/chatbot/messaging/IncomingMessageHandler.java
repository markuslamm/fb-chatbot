package io.chatbot.messaging;

import io.chatbot.model.IncomingMessage;
import io.chatbot.model.OutgoingMessage;
import io.chatbot.model.json.IncomingMessageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

@Component
public final class IncomingMessageHandler {

    private static final Logger LOG = LoggerFactory.getLogger(IncomingMessageHandler.class);

    private final RestTemplate restTemplate;
    private final String fbAccessToken;

    @Autowired
    public IncomingMessageHandler(final RestTemplate restTemplate,
                                  @Value("${fb.accesstoken}") final String fbAccessToken) {
        this.restTemplate = requireNonNull(restTemplate);
        this.fbAccessToken = requireNonNull(fbAccessToken);
    }

    public ResponseEntity<?> handleMessage(final IncomingMessageData incomingMessageData) {
        LOG.info("Handle incoming message data: {}", incomingMessageData.toString());
        final IncomingMessage incomingMessage = mapIncomingMessage(incomingMessageData);
        LOG.info("Mapped incoming message: {}", incomingMessage.toString());
        final OutgoingMessage outgoingMessage = createOutgoingMessage(incomingMessage.getSenderId(), incomingMessage.getText());
        return sendTextMessage(outgoingMessage);
    }

    private static IncomingMessage mapIncomingMessage(final IncomingMessageData incomingMessageData) {
        return new IncomingMessage(incomingMessageData);
    }

    private static OutgoingMessage createOutgoingMessage(final Long recipientId, final String text) {
        final String outgoingMessageText = createOutgoingMessageText(text);
        return new OutgoingMessage(recipientId, outgoingMessageText);
    }

    private static String createOutgoingMessageText(final String text) {
        return format("Thank you!\n\nI received your message:\n\n%s", text);
    }

    private ResponseEntity<?> sendTextMessage(final OutgoingMessage outgoingMessage) {
        LOG.info("Trying to send message: {}", outgoingMessage);
        final String url = format("https://graph.facebook.com/v2.6/me/messages?access_token=%s", fbAccessToken);
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        final HttpEntity<OutgoingMessage> requestEntity = new HttpEntity<>(outgoingMessage, headers);
        final ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
        LOG.info("Response: body={}, status={}", response.getBody(), response.getStatusCode());
        return response;
    }
}
