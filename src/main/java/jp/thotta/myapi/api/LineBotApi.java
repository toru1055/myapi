package jp.thotta.myapi.api;

import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import jp.thotta.myapi.service.ElevationService;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thotta on 2017/08/12.
 */
@LineMessageHandler
public class LineBotApi {
    @Autowired
    private ElevationService elevationService;

    @Autowired
    private LineMessagingService lineMessagingService;

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        System.out.println("event: " + event);
        return new TextMessage(event.getMessage().getText() + " でんがな");
    }

    @EventMapping
    public void handleLocationMessageEvent(MessageEvent<LocationMessageContent> event) throws IOException {
        System.out.println("event: " + event);
        double lat = event.getMessage().getLatitude();
        double lon = event.getMessage().getLongitude();
        List<Message> messages = new ArrayList<>();
        messages.add(elevationService.getElevationMessage(lat, lon));
        Response<BotApiResponse> response = lineMessagingService.replyMessage(
                new ReplyMessage(event.getReplyToken(), messages)
        ).execute();
        System.out.println("Sent messages: " + response.message());
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
