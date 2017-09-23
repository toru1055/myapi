package jp.thotta.myapi.repository;

import jp.thotta.myapi.domain.LocationSearchResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import se.walkercrou.places.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by thotta on 2017/09/22.
 */
@Repository
public class LocationSearchRepository {
    private static final String GOOGLE_PLACES_API_URL = "https://maps.googleapis.com/maps/api/place/";
    private static final String GOOGLE_MAP_URL_WITH_PLACE_ID = "https://www.google.com/maps/place/?q=place_id:";

    @Value("${google.places.api.key}")
    private String API_KEY;

    private GooglePlaces client;

    @PostConstruct
    private void initialize() {
//        client = new GooglePlaces(API_KEY);
    }

    private String toImageUrlFromPhotoReference(String photoReference) {
        String uri = String.format(
                "%sphoto?photoreference=%s&key=%s&maxwidth=400",
                GOOGLE_PLACES_API_URL,
                photoReference,
                API_KEY
        );
        return uri;
    }

    private String toImageUrl(Place place) {
        if (place.getJson().has("photos")) {
            JSONArray photos = place.getJson().getJSONArray("photos");
            if (photos.length() > 0) {
                JSONObject photo = photos.getJSONObject(0);
                if (photo.has("photo_reference")) {
                    String photoReference = photo.getString("photo_reference");
                    return toImageUrlFromPhotoReference(photoReference);
                }
            }
        }
        return place.getIconUrl();
    }

    public List<LocationSearchResult> findNearbyHospitals(double lat, double lon) {
        return findNearbyRankedByDistance(
                lat, lon,
                Param.name("language").value("ja"),
                Param.name("type").value(Types.TYPE_HOSPITAL)
        );
    }

    public List<LocationSearchResult> findNearbyRankedByDistance(double lat, double lon, Param... params) {
        client = new GooglePlaces(API_KEY);
        List<LocationSearchResult> locationResults = new ArrayList<>();
        System.out.println("findNearbyRankedByDistance: lat=" + lat + ", lon=" + lon);
        List<Place> places = client.getNearbyPlacesRankedByDistance(lat, lon, 5, params);
        System.out.println("findNearbyRankedByDistance is done");
        for (Place place : places) {
            LocationSearchResult result = new LocationSearchResult();
            result.setName(place.getName());
            result.setAddress(place.getVicinity());
            result.setLatitude(place.getLatitude());
            result.setLongitude(place.getLongitude());
            result.setMapUrl(GOOGLE_MAP_URL_WITH_PLACE_ID + place.getPlaceId());
            result.setImageUrl(toImageUrl(place));
            locationResults.add(result);
        }
        System.out.println("LocationSearchResult is created");
        return locationResults;
    }
}
