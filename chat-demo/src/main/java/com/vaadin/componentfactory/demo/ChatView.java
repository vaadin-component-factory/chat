package com.vaadin.componentfactory.demo;

import com.vaadin.componentfactory.Chat;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.componentfactory.model.Message;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Route("chat")
@RouteAlias("")
public class ChatView extends DemoView {
    private static int MESSAGE_LOAD_NUMBER = 100;
    private int messageStartNum1 = 0;
    private int messageStartNum2 = 0;

    @Override
    protected void initView() {
        addBasicChat();
        addChatWithCustomLoading();
    }

    private void addBasicChat() {
        Chat chat = new Chat();
        chat.setMessages(generateMessages(messageStartNum1));
        chat.setLazyLoadTriggerOffset(0);
        chat.scrollToBottom();

        chat.addChatNewMessageListener(event -> {
            chat.addNewMessage(new Message(event.getMessage(),
                    "https://mir-s3-cdn-cf.behance.net/project_modules/disp/ce54bf11889067.562541ef7cde4.png",
                    "Ben Smith", true));
            chat.clearInput();
            chat.scrollToBottom();
        });

        chat.addLazyLoadTriggerEvent(e -> {
            messageStartNum1 += MESSAGE_LOAD_NUMBER;
            List<Message> list = generateMessages(messageStartNum1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {}
            chat.setLoading(false);
            chat.addMessagesToTop(list);
        });

        Div description = new Div();
        description.setText("In this example LazyLoadTriggerOffset is set to 0 so loading of new messages " +
                "will be started only after fully scrolling to top.");

        addCard("Basic Chat usage", chat, description);
    }

    private void addChatWithCustomLoading() {
        Chat chat = new Chat();
        chat.setMessages(generateMessages(messageStartNum2));
        chat.setDebouncePeriod(200);
        chat.setLazyLoadTriggerOffset(2500);
        chat.scrollToBottom();

        chat.addChatNewMessageListener(event -> {
            event.getSource().addNewMessage(new Message(event.getMessage(),
                    "https://mir-s3-cdn-cf.behance.net/project_modules/disp/ce54bf11889067.562541ef7cde4.png",
                    "Ben Smith", true));
            event.getSource().clearInput();
            event.getSource().scrollToBottom();
        });

        chat.addLazyLoadTriggerEvent(e -> {
            messageStartNum2 += MESSAGE_LOAD_NUMBER;
            List<Message> list = generateMessages(messageStartNum2);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {}
            chat.setLoading(false);
            chat.addMessagesToTop(list);
        });

        chat.setLoadingIndicator(createLoadingComponent());

        Div description = new Div();
        description.setText("In this example LazyLoadTriggerOffset is set to 2500 so loading of new messages " +
                "will be started when user scroll 2500px close to top.\n" +
                "Also custom loading indicator is used in this example");

        addCard("Chat with custom loading usage", chat, description);
    }

    private Component createLoadingComponent() {
        Div loading = new Div();
        loading.setText("Loading...");
        loading.getElement().setAttribute("style", "text-align: center;");
        return loading;
    }


    private List<Message> generateMessages(int start) {
        List<Message> list = new ArrayList<>();

        for(int i = start; i < start + MESSAGE_LOAD_NUMBER; i++) {
            String body = i +  " Lorem Ipsum on yksinkertaisesti testausteksti, jota tulostus- ja ladontateollisuudet käyttävät. Lorem Ipsum on ollut teollisuuden normaali testausteksti jo 1500-luvulta asti, jolloin tuntematon tulostaja otti kaljuunan ja sekoitti sen tehdäkseen esimerkkikirjan. ";
            if (i % 2 == 0) {
                list.add(new Message(body, "", "Johana Livingstone", false));
            } else {
                list.add(new Message(body, "https://mir-s3-cdn-cf.behance.net/project_modules/disp/ce54bf11889067.562541ef7cde4.png", "Ben Smith", true));
            }
        }

        Collections.reverse(list);
        return list;
    }

}