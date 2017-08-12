package jp.thotta.myapi.api;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

/**
 * Created by thotta on 2017/08/12.
 */
@LineMessageHandler
public class LineBotApi {
    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        System.out.println("event: " + event);
        return new TextMessage(event.getMessage().getText() + " でんがな");
    }

    @EventMapping
    public TextMessage handleLocationMessageEvent(MessageEvent<LocationMessageContent> event) {
        System.out.println("event: " + event);
        String lat = String.format("緯度: %.3f", event.getMessage().getLatitude());
        String lon = String.format("経度: %.3f", event.getMessage().getLongitude());
        return new TextMessage(lat + "\n" + lon);
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
