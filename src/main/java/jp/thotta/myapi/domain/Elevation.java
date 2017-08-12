package jp.thotta.myapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by thotta on 2017/08/12.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Elevation {
    private Double elevation;
    private String hsrc;
}
