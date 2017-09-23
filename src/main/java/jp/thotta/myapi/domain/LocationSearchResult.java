package jp.thotta.myapi.domain;

import lombok.Data;

/**
 * Created by thotta on 2017/09/22.
 */
@Data
public class LocationSearchResult {
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String imageUrl;
    private String mapUrl;
}
