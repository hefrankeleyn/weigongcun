package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmUsableMagnitude;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Date 2019-05-16
 * @Author lifei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EdmUsableMagnitudeServiceTest {

    @Autowired
    private EdmUsableMagnitudeService edmUsableMagnitudeService;

    @Test
    public void shouldNotNull(){
        Assert.assertNotNull(edmUsableMagnitudeService);
    }

    @Test
    public void findCurrentDayEdmUsableMagnitudes(){
        List<EdmUsableMagnitude> edmUsableMagnitudes = edmUsableMagnitudeService.findCurrentDayEdmUsableMagnitudes();
        System.out.println(edmUsableMagnitudes);

        for (EdmUsableMagnitude magnitude :
                edmUsableMagnitudes) {
            System.out.println(magnitude);
        }
    }
    @Test
    public void findTodayEdmMagnitudes(){
        List<EdmUsableMagnitude> edmUsableMagnitudes = edmUsableMagnitudeService.findTodayEdmUsableMagnitudesAndDescription();
        for (EdmUsableMagnitude ed :
                edmUsableMagnitudes) {
            System.out.println(ed);
        }
    }
}
