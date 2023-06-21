package com.msstudent.ie.carbonfree.Controller;

import com.msstudent.ie.carbonfree.business.DisplayCar;
import com.msstudent.ie.carbonfree.business.DisplayCity;
import com.msstudent.ie.carbonfree.business.DisplayEmissionService;
import com.msstudent.ie.carbonfree.data.Calculation;
import com.msstudent.ie.carbonfree.data.CalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Controller
@CrossOrigin
@RequestMapping("/emissions")
public class EmissionController {



    private DisplayEmissionService displayEmissionService;

    private CalculationRepository calculationRepository;
    @Autowired
    public EmissionController(DisplayEmissionService displayEmissionService, CalculationRepository calculationRepository) {
        this.displayEmissionService = displayEmissionService;
        this.calculationRepository = calculationRepository;
    }


    @GetMapping(path = "/allCityName")
    public @ResponseBody Iterable<String> getAllCityName () {

        return displayEmissionService.getAllCityName();
    }

    @GetMapping(path = "/getAllCityInfo")
    public @ResponseBody Iterable<DisplayCity> getAllCityInfo() {
        return displayEmissionService.getAllInfo();
    }



    @GetMapping("/cityInfo")
    public @ResponseBody Iterable<DisplayCity> getInfoByCityName (@RequestParam String cName) {
        return displayEmissionService.getInfoByCityName(cName);
    }

    @GetMapping(path = "/allCarBrand")
    public @ResponseBody Iterable<String> getAllCarBrand () {

        return displayEmissionService.getAllCarBrand();
    }

    @GetMapping(path = "/carModelByBrand")
    public @ResponseBody Iterable<String> getCarModelByBrand (@RequestParam String aBrand) {
        return displayEmissionService.getCarModelByBrand(aBrand);
    }

    @GetMapping(path = "/carSizeByBrandAndModel")
    public @ResponseBody Iterable<String> getCarSizeByBrandAndModel (@RequestParam String aBrand, @RequestParam String aModel) {

        return displayEmissionService.getCarSizeByBrandAndModel(aBrand,aModel);
    }

    @GetMapping(path = "/getTheCarInfo")
    public @ResponseBody DisplayCar getACarInfo(@RequestParam String aBrand, @RequestParam String aModel, @RequestParam String aSize) {
        System.out.println(displayEmissionService.getCarEmissionInfoByCar(aBrand,aModel,aSize));
        return displayEmissionService.getCarEmissionInfoByCar(aBrand,aModel,aSize);
    }

    @PostMapping(path = "/calculateResultAdd")
    public ResponseEntity<?> addCalculationRecord (@RequestBody Calculation calculation) {
        calculationRepository.save(calculation);
        return  ResponseEntity.ok().build();
    }
}
