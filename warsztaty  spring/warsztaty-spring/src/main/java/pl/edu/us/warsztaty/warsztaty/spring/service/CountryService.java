package pl.edu.us.warsztaty.warsztaty.spring.service;



import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.us.warsztaty.warsztaty.spring.model.RestResponse;
import pl.edu.us.warsztaty.warsztaty.spring.model.RootObject;

import javax.swing.*;
import java.util.Locale;

@Service
public class CountryService {

    private static final String COUNTRY_URL = "http://www.groupkt.com/country/get/iso2code/";
    private RestTemplate restTemplate;
    private MessageSource messageSource;

    public CountryService(RestTemplate restTemplate,MessageSource messageSource) {
        this.restTemplate = restTemplate;
        this.messageSource = messageSource;
    }

    public RestResponse getCountryFromRestApi(String countryCode){
        ResponseEntity<RootObject> responseEntity = getDataFromApi(countryCode);

        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            RestResponse restResponse = responseEntity.getBody().getRestResponse();
            restResponse.setSayHello(
                    messageSource.getMessage("hello.message",null, Locale.forLanguageTag(countryCode))
            );
            return restResponse;
        }
        throw new RuntimeException("Somthing went wrong");
    }

    private ResponseEntity<RootObject> getDataFromApi (String countryCode){
        return restTemplate.getForEntity(UriComponentsBuilder.fromHttpUrl(COUNTRY_URL+countryCode).build().toUri(),
                RootObject.class);
    }

}
