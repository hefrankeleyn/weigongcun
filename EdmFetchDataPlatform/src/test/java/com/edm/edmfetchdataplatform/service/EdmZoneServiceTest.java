package com.edm.edmfetchdataplatform.service;

import com.edm.edmfetchdataplatform.domain.EdmZone;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Date 2019-06-11
 * @Author lifei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EdmZoneServiceTest {

    @Autowired
    private EdmZoneService edmZoneService;

    @Test
    public void shouldNotNull(){
        System.out.println(edmZoneService);
        Assert.assertNotNull(edmZoneService);
    }

    @Test
    public void finCityByProvincecode(){
        List<EdmZone> cities = edmZoneService.findCitiesByProvinceCode("100");
        if (cities!=null && !cities.isEmpty()){
            for (EdmZone city :
                    cities) {
                System.out.println(city);
            }
        }
    }

    @Test
    public void findProvinceNamesByProvinceCodes(){
        String[] provinceCodes = new String[]{"100","200"};
        String[] provinceNames = edmZoneService.findProvinceNamesByProvinceCodes(provinceCodes);
        for (String provinceName :
                provinceNames) {
            System.out.println(provinceName);
        }
    }

    @Test
    public void findCityNamesByCityCodes(){
        String[] cityCodes = new String[]{"1011","1010"};
        String[] cityNames = edmZoneService.findCityNamesByCityCodes(cityCodes);
        for (String cityName :
                cityNames) {
            System.out.println(cityName);
        }
    }
}
