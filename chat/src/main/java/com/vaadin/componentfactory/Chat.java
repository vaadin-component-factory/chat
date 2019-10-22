package com.vaadin.componentfactory;

/*
 * #%L
 * Vaadin Chat for Vaadin 10
 * %%
 * Copyright (C) 2017 - 2018 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * See the file license.html distributed with this software for more
 * information about licensing.
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <http://vaadin.com/license/cval-3>.
 * #L%
 */

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.componentfactory.model.Message;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.List;
import java.util.Objects;

/**
 * Server-side component for the <code>vcf-chat</code> element.
 * It is a Web Component providing an easy way to display chat on web pages
 *
 * @author Vaadin Ltd
 */
@Tag("vcf-chat")
@HtmlImport("frontend://bower_components/vcf-chat/src/vcf-chat.html")
@NpmPackage(value = "@vaadin-component-factory/vcf-chat", version = "1.2.0")
@JsModule("@vaadin-component-factory/vcf-chat/src/vcf-chat.js")
public class Chat extends PolymerTemplate<Chat.ChatModel> {
    /**
     * `vcf-chat-trigger-lazy-load` is send when user scrolled almost to the top of message list
     *  It fires all the time when user scroll list and scrollTop value is less or equals lazyLoadTriggerOffset,
     *  with debounce time set in parameter debouncePeriod
     *
     *  @see #setDebouncePeriod(int)
     *  @see #setLazyLoadTriggerOffset(int)
     */
    @DomEvent("vcf-chat-trigger-lazy-load")
    public static class LazyLoadTriggerEvent extends ComponentEvent<Chat> {
        public LazyLoadTriggerEvent(Chat source, boolean fromClient) {
            super(source, fromClient);
        }
    }

    /**
     * `vcf-chat-new-message` is sent when the user clicks Send button.
     * Event contains message that user typed in input form
     */
    @DomEvent("vcf-chat-new-message")
    public static class ChatNewMessageEvent extends ComponentEvent<Chat> {
        private final String message;

        public ChatNewMessageEvent(Chat source, boolean fromClient, @EventData("event.detail.body") String message) {
            super(source, fromClient);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * Overriding default loading indicator component by custom component defined by user
     *
     * @param component
     *                     component that indicates loading in progress state
     */
    public void setLoadingIndicator(Component component) {
        Objects.requireNonNull(component, "Loading Indication Component cannot be null");

        component.getElement().setAttribute("slot", "loading-indicator");
        this.getElement().getChildren().forEach(child -> {
            if ("loading-indicator".equals(child.getAttribute("slot"))) {
                child.removeFromParent();
            }
        });
        this.getElement().appendChild(new Element[]{ component.getElement() });
    }

    /**
     * Adds `vcf-chat-trigger-lazy-load` event listener
     */
    public Registration addLazyLoadTriggerEvent(
            ComponentEventListener<LazyLoadTriggerEvent> listener) {
        return ComponentUtil.addListener(this, LazyLoadTriggerEvent.class, listener);
    }

    /**
     * Adds `vcf-chat-new-message` event listener
     */
    public Registration addChatNewMessageListener(
            ComponentEventListener<ChatNewMessageEvent> listener) {
        return ComponentUtil.addListener(this, ChatNewMessageEvent.class, listener);
    }

    /**
     * Clears new message input field from text
     */
    public void clearInput() {
        getElement().callFunction("clearInput");
    }

    /**
     * Scrolling list of messages to the bottom, where newest messages are
     */
    public void scrollToBottom() {
        getElement().callFunction("scrollToBottom");
    }

    /**
     * Setting list of messages to chat
     *
     * @param messages
     */
    public void setMessages(List<Message> messages){
        getModel().setMessages(messages);
    }

    /**
     * Returns list of messages
     *
     * @return list of messages
     */
    public List<Message> getMessages(){
        return getModel().getMessages();
    }

    /**
     * Adding message to the end message list.
     * Message will appear in end of conversation
     *
     * @param message to added to end of conversation
     */
    public void addNewMessage(Message message){
        getMessages().add(message);
    }

    /**
     * Adding list of messages to the top of chat
     *
     * @param messages
     *                  list of messages
     */
    public void addMessagesToTop(List<Message> messages){
        getMessages().addAll(0, messages);
    }

    /**
     * Sets distance from top of chat, as threshold for raising 'vcf-chat-trigger-lazy-load' events
     *
     * @param number
     *                  distance in pixels
     */
    public void setLazyLoadTriggerOffset(int number){
        getModel().setLazyLoadTriggerOffset(number);
    }

    /**
     * Returns parameter lazyLoadTriggerOffset which is threshold for raising 'vcf-chat-trigger-lazy-load' events
     *
     * @return lazyLoadTriggerOffset
     *                                  distance in pixels
     */
    public int getLazyLoadTriggerOffset(){
        return getModel().getLazyLoadTriggerOffset();
    }

    /**
     *  Setting debounce period for triggering 'vcf-chat-trigger-lazy-load' event
     *
     * @param number
     *                  debounce time in milliseconds
     */
    public void setDebouncePeriod(int number){
        getModel().setDebouncePeriod(number);
    }

    /**
     * Getting debounce period for triggering 'vcf-chat-trigger-lazy-load' event
     * @return debounce time in miliseconds
     */
    public int getDebouncePeriod(){
        return getModel().getDebouncePeriod();
    }

    /**
     * Set loading parameter. Which controls whether loading indicator is shown in chat
     *
     * @param loading
     *                  is loading indicator shown
     */
    public void setLoading(boolean loading){
        getModel().setLoading(loading);
    }

    /**
     * Is loading indicator visible
     *
     * @return loading parameter
     */
    public boolean isLoading(){
        return getModel().isLoading();
    }

    /**
     * This model binds properties between java(Chat) and polymer(vcf-chat.html)
     */
    public interface ChatModel extends TemplateModel {
        void setMessages(List<Message> messages);
        List<Message> getMessages();

        void setLazyLoadTriggerOffset(int number);
        int getLazyLoadTriggerOffset();

        void setDebouncePeriod(int number);
        int getDebouncePeriod();

        void setLoading(boolean loading);
        boolean isLoading();
    }
}
