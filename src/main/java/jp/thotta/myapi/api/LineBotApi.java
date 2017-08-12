package jp.thotta.myapi.api;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import jp.thotta.myapi.service.ElevationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by thotta on 2017/08/12.
 */
@LineMessageHandler
public class LineBotApi {
    @Autowired
    private ElevationService elevationService;

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        System.out.println("event: " + event);
        return new TextMessage(event.getMessage().getText() + " でんがな");
    }

    @EventMapping
    public TextMessage handleLocationMessageEvent(MessageEvent<LocationMessageContent> event) {
        System.out.println("event: " + event);
        double lat = event.getMessage().getLatitude();
        double lon = event.getMessage().getLongitude();
        Double elevation = elevationService.getElevation(lat, lon);
        if (elevation != null) {
            return new TextMessage("その場所の標高は、 \n" + elevation + " m です。");
        } else {
            return new TextMessage("その場所の標高は、 \n不明 です。");
        }
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
