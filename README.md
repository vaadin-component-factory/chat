# Chat

Chat is server-side component of [vcf-chat](https://github.com/vaadin-component-factory/vcf-chat) web component for Vaadin 10. 
It is a Web Component providing an easy way to display chat on web pages.

[<img src="https://raw.githubusercontent.com/vaadin/vcf-chat/master/screenshot.png" width="400" alt="Screenshot of vcf-chat">](https://vaadin.com/directory/components/vaadinvcf-chat)
## Usage

Create instance of `Chat`. You can set messages using `setMessages(List<Messages>)` method.
Messages in list should be sorted from older to newer, as last messages in list will be 
shown in bottom of chat and would represent newer messages. Message object contains  message text, 
sender name, sender avatar(url to avatar image) and indicator whether it's incoming or outgoing 
message(currentUser parameter). Avatar url should not be null but can be empty string. If it's set to 
empty string, image from first letters of  first and last names will be create created.
If message have parameter `currentUser` set to `true` it means that message is outgoing and 
will appear on right sight of chat, else it will be incoming message and appear on left side of chat.

After adding list of messages you, more likely, would like to scroll chat to the bottom, 
where newer messages are. You can do that by calling method `scrollToBottom()`.

Chat have text input and Send button. Clicking Send button or clicking `Ctrl+Enter` will dispatch 
`vcf-chat-new-message` event with users message as parameter.

Use `addChatNewMessageListener(ComponentEventListener)` to handle event. You can add message to bottom of chat 
using method `addNewMessage(Message)`. After message is added you can clear input by calling `clearInput`
on chat instance.

When user scrolls messages towards top and `scrollTop` value is smaller then `lazyLoadTriggerOffset`
parameter of Chat instance, event `vcf-chat-trigger-lazy-load` will be raised(and will be raising on every
scroll event with debounce time set in `debouncePeriod` parameter of chat instance). 

Use `addLazyLoadTriggerEvent(ComponentEventListener)` to handle event. You can add older messages
to top of chat using method `addMessagesToTop(List<Message>)`. When `vcf-chat-trigger-lazy-load` event 
is triggered, loading indication is shown in chat and can be disabled by calling `setLoading(false)` method 
on chat instance. Loading also can be enabled manually by calling same method with `true` value.

```
    Chat chat = new Chat();
    chat.setMessages(loadMessages());
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
        List<Message> list = loadMessages();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {}
        chat.setLoading(false);
        chat.addMessagesToTop(list);
    });
```

By default chat uses [<vaadin-progress-bar indeterminate>](https://github.com/vaadin/vaadin-progress-bar) 
loading indicator, but it can be overriden by custom component using method `setLoadingIndicator(Component)`

```
    Div loading = new Div();
    loading.setText("Loading...");
    loading.getElement().setAttribute("style", "text-align: center;");
    chat.setLoadingIndicator(loading);
```


## Demo
To run demo go to `chat-demo/` subfolder and run `mbn jetty:run`.
After server startup, you'll be able find demo at [http://localhost:8080/chat](http://localhost:8080/chat)

## Setting up for development:

Clone the project in GitHub (or fork it if you plan on contributing)

```
https://github.com/vaadin-component-factory/chat
```

To build and install the project into the local repository run 

```mvn install ```

## License & Author

This Add-on is distributed under [Commercial Vaadin Add-on License version 3](http://vaadin.com/license/cval-3) (CVALv3). For license terms, see LICENSE.txt.

Chat is written by Vaadin Ltd.

