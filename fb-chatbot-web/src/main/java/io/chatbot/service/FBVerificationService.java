package io.chatbot.service;

public interface FBVerificationService {

    String verifyWebhook(final String verifyToken, final String challenge);
}
