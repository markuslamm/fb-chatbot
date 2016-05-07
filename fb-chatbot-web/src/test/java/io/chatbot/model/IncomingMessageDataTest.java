package io.chatbot.model;

import io.chatbot.json.JsonUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class IncomingMessageDataTest {

    private static final String RAW_DATA = "incomming-message-data.json";

    @Test
    public void testMapJson() {
        final IncomingMessageData result = JsonUtils.readObjectFromResource(RAW_DATA, IncomingMessageData.class);
        assertThat(result).isNotNull();
        assertThat(result.getObject()).isNotNull();
        assertThat(result.getEntry()).isNotNull();
        result.getEntry().forEach(entry -> {
            assertThat(entry.getId()).isNotNull();
            assertThat(entry.getTime()).isNotNull();
            assertThat(entry.getMessaging()).isNotNull();
            entry.getMessaging().forEach(messaging -> {
                assertThat(messaging.getSender()).isNotNull();
                assertThat(messaging.getRecipient()).isNotNull();
                assertThat(messaging.getTimestamp()).isNotNull();
                assertThat(messaging.getMessage()).isNotNull();
                assertThat(messaging.getMessage().getMid()).isNotNull();
                assertThat(messaging.getMessage().getSeq()).isNotNull();
                assertThat(messaging.getMessage().getText()).isNotNull();
            });
        });
    }
}
