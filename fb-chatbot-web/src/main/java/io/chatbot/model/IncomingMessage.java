package io.chatbot.model;

import io.chatbot.model.json.Entry;
import io.chatbot.model.json.IncomingMessageData;
import io.chatbot.model.json.Message;
import io.chatbot.model.json.Messaging;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.stream.Collectors;

public final class IncomingMessage {

    private final String object;
    private final Long id;
    private final Long time;
    private final Long senderId;
    private final Long recipientId;
    private final Long timestamp;
    private final String mid;
    private final Integer seq;
    private final String text;
    private final List<Attachment> attachments;

    public IncomingMessage(final IncomingMessageData incomingMessageData) {
        this.object = incomingMessageData.getObject();

        final Entry entry = incomingMessageData.getEntry().get(0);
        this.id = entry.getId();
        this.time = entry.getTime();

        final Messaging messaging = entry.getMessaging().get(0);
        this.senderId = messaging.getSender().getId();
        this.recipientId = messaging.getRecipient().getId();
        this.timestamp = messaging.getTimestamp();

        final Message message = messaging.getMessage();
        this.mid = message.getMid();
        this.seq = message.getSeq();
        this.text = message.getText();
        this.attachments = message.getAttachments().stream()
                .map(attachment -> new Attachment(attachment.getType(), attachment.getPayload()))
                .collect(Collectors.toList());
    }

    public String getObject() {
        return object;
    }

    public Long getId() {
        return id;
    }

    public Long getTime() {
        return time;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getMid() {
        return mid;
    }

    public Integer getSeq() {
        return seq;
    }

    public String getText() {
        return text;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
