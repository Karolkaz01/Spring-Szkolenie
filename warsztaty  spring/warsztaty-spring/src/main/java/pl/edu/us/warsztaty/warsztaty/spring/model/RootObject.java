package pl.edu.us.warsztaty.warsztaty.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RootObject {

    @JsonProperty("RestResponse")
    private RestResponse restResponse;

    public RestResponse getRestResponse() {
        return restResponse;
    }

    public void setRestResponse(RestResponse restResponse) {
        this.restResponse = restResponse;
    }
}
