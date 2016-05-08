package io.chatbot.model;

import io.chatbot.model.json.IncomingMessageData;
import org.junit.Test;

import static io.chatbot.util.json.JsonUtils.readObjectFromResource;
import static org.assertj.core.api.Assertions.assertThat;

public final class IncomingMessageTest {

    private static final String RAW_DATA_TEXT_MESSAGE = "incoming-message-data.json";
    private static final String RAW_DATA_IMAGE_MESSAGE = "incoming-message-data-with-image-attachment.json";

    @Test
    public void testMapTextMessage() {
        final IncomingMessageData incomingMessageData = readObjectFromResource(RAW_DATA_TEXT_MESSAGE, IncomingMessageData.class);
        final IncomingMessage result = new IncomingMessage(incomingMessageData);
        assertThat(result).isNotNull();
        assertThat(result.getObject()).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getTime()).isNotNull();
        assertThat(result.getSenderId()).isNotNull();
        assertThat(result.getRecipientId()).isNotNull();
        assertThat(result.getTimestamp()).isNotNull();
        assertThat(result.getMid()).isNotNull();
        assertThat(result.getSeq()).isNotNull();
        assertThat(result.getText()).isNotNull();
        assertThat(result.getAttachments()).isNotNull();
        assertThat(result.getAttachments().isEmpty()).isTrue();
    }

    @Test
    public void testMapImageMessage() {
        final IncomingMessageData incomingMessageData = readObjectFromResource(RAW_DATA_IMAGE_MESSAGE, IncomingMessageData.class);
        final IncomingMessage result = new IncomingMessage(incomingMessageData);
        assertThat(result).isNotNull();
        assertThat(result.getObject()).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getTime()).isNotNull();
        assertThat(result.getSenderId()).isNotNull();
        assertThat(result.getRecipientId()).isNotNull();
        assertThat(result.getTimestamp()).isNotNull();
        assertThat(result.getMid()).isNotNull();
        assertThat(result.getSeq()).isNotNull();
        assertThat(result.getText()).isNull();
        assertThat(result.getAttachments()).isNotNull();
        assertThat(result.getAttachments().isEmpty()).isFalse();
    }
}
