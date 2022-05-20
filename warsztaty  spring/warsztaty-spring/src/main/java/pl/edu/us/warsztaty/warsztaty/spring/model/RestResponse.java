package pl.edu.us.warsztaty.warsztaty.spring.model;

import java.util.List;

public class RestResponse {

    private List<String> message;
    private Result result;
    private  String sayHello;

    public String getSayHello() {
        return sayHello;
    }

    public void setSayHello(String sayHello) {
        this.sayHello = sayHello;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
