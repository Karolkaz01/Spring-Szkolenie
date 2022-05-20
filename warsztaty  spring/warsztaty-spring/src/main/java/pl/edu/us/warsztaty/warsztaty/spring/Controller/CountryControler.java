package pl.edu.us.warsztaty.warsztaty.spring.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.us.warsztaty.warsztaty.spring.model.RestResponse;
import pl.edu.us.warsztaty.warsztaty.spring.service.CountryService;

@RestController
public class CountryControler {

    private CountryService countryService;

    public CountryControler(CountryService countryService) {
        this.countryService = countryService;
    }
    @GetMapping("/country/{countryName}")
    public RestResponse getCountryByCode(@PathVariable("countryName") String countryName){
        return countryService.getCountryFromRestApi(countryName);
    }

}
