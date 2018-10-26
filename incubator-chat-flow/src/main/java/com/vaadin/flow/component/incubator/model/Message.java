package com.vaadin.flow.component.incubator.model;

/*
 * #%L
 * Vaadin Chat for Vaadin 10
 * %%
 * Copyright (C) 2017 - 2018 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 *
 * See the file license.html distributed with this software for more
 * information about licensing.
 *
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <http://vaadin.com/license/cval-3>.
 * #L%
 */

/**
 * Message object for chat. Contains information about sender(name and avatar) and message content
 *
 * @author Vaadin Ltd
 */
public class Message {
    private String body;
    private String avatar;
    private String senderName;
    private boolean currentUser;

    /**
     * Create instance of Message with all parameters set
     *
     * @param body
     *                  message text
     * @param avatar
     *                  url to sender avatar image
     * @param senderName
     *                  sender name
     * @param currentUser
     *                  true if current user(message is outgoing) else message is incoming
     */
    public Message(String body, String avatar, String senderName, boolean currentUser) {
        this.body = body;
        this.avatar = avatar;
        this.senderName = senderName;
        this.currentUser = currentUser;
    }

    /**
     * Returns message content
     *
     * @return message content
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets message content
     *
     * @param body
     *                  message content
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Gets url to avatar image
     *
     * @return url to avatar image
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     *  Setting link to avatar image. Shouldn't ne null. If empty string will create image from first letters of
     *  first and last names
     *
     * @param avatar
     *                  url to avatar image
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Getting sender name
     *
     * @return sender name
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * Setting sender name
     *
     * @param senderName
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * Returns whether message belongs to current user or it's incoming message
     *
     * @return currentUser
     */
    public boolean isCurrentUser() {
        return currentUser;
    }

    /**
     *  Setting currentUser parameter. If true, message will appear on right side of the chat,
     *  else on left side
     *
     * @param currentUser
     *                      true if current user(message is outgoing)
     */
    public void setCurrentUser(boolean currentUser) {
        this.currentUser = currentUser;
    }
}
