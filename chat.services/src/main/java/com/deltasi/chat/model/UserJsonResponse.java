package com.deltasi.chat.model;

import java.util.Map;

/**
 *
 * @author Nick
 */
public class UserJsonResponse {


    private User user;
    private boolean validated;
    private Map<String, String> errorMessages;

    // Getter and Setter methods

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the validated
     */
    public boolean isValidated() {
        return validated;
    }

    /**
     * @param validated the validated to set
     */
    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    /**
     * @return the errorMessages
     */
    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * @param errorMessages the errorMessages to set
     */
    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

}
