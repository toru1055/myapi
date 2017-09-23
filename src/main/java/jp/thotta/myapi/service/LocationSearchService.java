package jp.thotta.myapi.service;

import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import jp.thotta.myapi.domain.LocationSearchResult;
import jp.thotta.myapi.repository.LocationSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.TextAction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thotta on 2017/09/23..
 */
@Service
public class LocationSearchService {
    @Autowired
    LocationSearchRepository locationSearchRepository;

    public Message getNearbyHospitalsMessage(double lat, double lon) {
        List<LocationSearchResult> hospitals = locationSearchRepository.findNearbyHospitals(lat, lon);
        List<CarouselColumn> carouselColumns = new ArrayList<>();
        for (LocationSearchResult hospital : hospitals) {
            List<Action> actions = new ArrayList<>();
            actions.add(new URIAction("地図へ", hospital.getMapUrl()));
            actions.add(new MessageAction("住所表示", hospital.getAddress()));
            CarouselColumn carouselColumn = new CarouselColumn(
                    hospital.getImageUrl(),
                    hospital.getName(),
                    hospital.getAddress(),
                    actions
            );
            carouselColumns.add(carouselColumn);
        }
        CarouselTemplate carouselTemplate = new CarouselTemplate(carouselColumns);
        return new TemplateMessage("病院検索結果", carouselTemplate);
    }
}
