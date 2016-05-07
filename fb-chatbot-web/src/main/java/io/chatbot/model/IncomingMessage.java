package io.chatbot.model;

import io.chatbot.model.json.IncomingMessageData;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    public IncomingMessage(final IncomingMessageData incomingMessageData) {
        this.object = incomingMessageData.getObject();
        this.id = incomingMessageData.getEntry().get(0).getId();
        this.time = incomingMessageData.getEntry().get(0).getTime();
        this.senderId = incomingMessageData.getEntry().get(0).getMessaging().get(0).getSender().getId();
        this.recipientId = incomingMessageData.getEntry().get(0).getMessaging().get(0).getRecipient().getId();
        this.timestamp = incomingMessageData.getEntry().get(0).getMessaging().get(0).getTimestamp();
        this.mid = incomingMessageData.getEntry().get(0).getMessaging().get(0).getMessage().getMid();
        this.seq = incomingMessageData.getEntry().get(0).getMessaging().get(0).getMessage().getSeq();
        this.text = incomingMessageData.getEntry().get(0).getMessaging().get(0).getMessage().getText();
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
