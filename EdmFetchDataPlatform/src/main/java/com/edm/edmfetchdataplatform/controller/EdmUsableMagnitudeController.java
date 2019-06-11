package com.edm.edmfetchdataplatform.controller;

import com.edm.edmfetchdataplatform.domain.EdmUsableMagnitude;
import com.edm.edmfetchdataplatform.service.EdmUsableMagnitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Date 2019-05-16
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/edmUsableMagnitudeController")
public class EdmUsableMagnitudeController {

    @Autowired
    private EdmUsableMagnitudeService edmUsableMagnitudeService;


    @RequestMapping(value = "/showCurrentDayEdmUsableMagnitudes", method = RequestMethod.GET)
    public String showCurrentDayEdmUsableMagnitudes(Model model){
        List<EdmUsableMagnitude> edmUsableMagnitudes = edmUsableMagnitudeService.findTodayEdmUsableMagnitudesAndDescription();
        model.addAttribute(edmUsableMagnitudes);
        return "usableMagnitude";
    }
}
