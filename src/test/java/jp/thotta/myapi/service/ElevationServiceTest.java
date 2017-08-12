package jp.thotta.myapi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by thotta on 2017/08/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ElevationServiceTest {
    @Autowired
    private ElevationService elevationService;

    @Test
    public void getElevation() throws Exception {
        assertEquals(elevationService.getElevation(35.360556, 138.727778), 3751.9, 1.0);
        assertEquals(elevationService.getElevation(58.105575324447756, 102.72617552429438), 0.0, 1.0);
    }

}