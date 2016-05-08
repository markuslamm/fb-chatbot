package io.chatbot.controller;

import io.chatbot.model.json.IncomingMessageData;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static io.chatbot.util.json.JsonUtils.readObjectFromResource;

public final class FacebookMessengerControllerTest {

    private static final String RAW_DATA = "incoming-message-data.json";

    private FacebookMessengerController controller;

    private IncomingMessageData incomingMessageData;

    @Before
    public void setUp() {
        incomingMessageData = readObjectFromResource(RAW_DATA, IncomingMessageData.class);
    }

    @Test
    public void testHandleMessage() {
        Assertions.assertThat(incomingMessageData).isNotNull();
    }
}
