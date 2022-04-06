import io.qameta.allure.Step;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class LoginCourier {

    static CourierData courierDataNotFull = new CourierData(null, "4543ndlkjfsd", null);
    static CourierData courierNotExistingData = new CourierData("11", "bvnmsdlkgnewegedr", "Нет Меня");

    static final String URL = "http://qa-scooter.praktikum-services.ru/api/v1/courier/login";


    @Step("Send request with positive data")
    public static Response loginCourierPositive(){
        Response response = given()
                .header("Content-type", "application/json")
                .body(CourierClient.createNewCourier())
                .when()
                .post(URL);
        return response;
    }

    @Step("Send request with not all data (no login")
    public static Response loginCourierNotFullData(){
        Response response = given()
                .header("Content-type", "application/json")
                .body(courierDataNotFull)
                .when()
                .post(URL);
        return response;
    }

    @Step("Send request")
    public static Response  loginCourierNotExistingData(){
        Response response = given()
                .header("Content-type", "application/json")
                .body(courierNotExistingData)
                .when()
                .post(URL);
        return response;
    }
}
