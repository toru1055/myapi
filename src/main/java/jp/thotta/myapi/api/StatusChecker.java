package jp.thotta.myapi.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by thotta on 2017/08/11.
 */
@RestController
public class StatusChecker {
    @RequestMapping("/status")
    public Boolean status() {
        return true;
    }
}
