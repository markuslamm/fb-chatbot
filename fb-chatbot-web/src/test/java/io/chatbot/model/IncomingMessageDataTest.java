package io.chatbot.model;

import io.chatbot.model.json.IncomingMessageData;
import org.junit.Test;

import static io.chatbot.util.json.JsonUtils.readObjectFromResource;
import static org.assertj.core.api.Assertions.assertThat;

public final class IncomingMessageDataTest {

    private static final String RAW_DATA_TEXT_MESSAGE = "incoming-message-data.json";
    private static final String RAW_DATA_IMAGE_MESSAGE = "incoming-message-data-with-image-attachment.json";

    @Test
    public void testMapTextMessage() {
        final IncomingMessageData result = readObjectFromResource(RAW_DATA_TEXT_MESSAGE, IncomingMessageData.class);
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

    @Test
    public void testMapImageMessage() {
        final IncomingMessageData result = readObjectFromResource(RAW_DATA_IMAGE_MESSAGE, IncomingMessageData.class);
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
                assertThat(messaging.getMessage().getAttachments()).isNotNull();
                assertThat(messaging.getMessage().getAttachments()).isNotEmpty();
            });
        });
    }
}
