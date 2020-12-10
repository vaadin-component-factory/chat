package com.vaadin.componentfactory.model;

/*
 * #%L
 * Vaadin Chat for Vaadin 10
 * %%
 * Copyright (C) 2017 - 2018 Vaadin Ltd
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
     * @param senderName sender name
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
