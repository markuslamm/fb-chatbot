package io.chatbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

@Component
public final class FBVerificationServiceImpl implements FBVerificationService {

    private static final Logger LOG = LoggerFactory.getLogger(FBVerificationServiceImpl.class);

    private final String fbVerifyToken;

    public FBVerificationServiceImpl(@Value("${fb.verifytoken}") final String fbVerifyToken) {
        this.fbVerifyToken = requireNonNull(fbVerifyToken);
    }

    @Override
    public String verifyWebhook(final String verifyToken, final String challenge) {
        LOG.info("Trying to verify token: {}", verifyToken);
        if (!fbVerifyToken.equals(verifyToken)) {
            throw new RuntimeException(format("Invalid verify token: %s", verifyToken));
        }
        LOG.info("Token verified: {}", verifyToken);
        return challenge;
    }
}
