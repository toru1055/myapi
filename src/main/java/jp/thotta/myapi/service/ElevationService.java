package jp.thotta.myapi.service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jp.thotta.myapi.domain.Elevation;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by thotta on 2017/08/12.
 */
@Service
public class ElevationService {
    public Double getElevation(double lat, double lon) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            Elevation elevation = restTemplate.getForObject(
                    "http://cyberjapandata2.gsi.go.jp/general/dem/scripts/getelevation.php" +
                            "?lat=" + lat +
                            "&lon=" + lon +
                            "&outtype=json",
                    Elevation.class
            );
            return elevation.getElevation();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
