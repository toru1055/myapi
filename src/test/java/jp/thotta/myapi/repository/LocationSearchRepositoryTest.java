package jp.thotta.myapi.repository;

import jp.thotta.myapi.domain.LocationSearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by thotta on 2017/09/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LocationSearchRepositoryTest {
    @Autowired
    LocationSearchRepository locationSearchRepository;

    @Test
    public void findNearbyRankedByDistance() throws Exception {
        //List<LocationSearchResult> results = locationSearchRepository.findNearbyHospitals(35.732796f, 140.071236f);
        //System.out.println(results);
    }

}