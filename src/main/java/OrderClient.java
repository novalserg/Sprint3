import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class OrderClient {

    static final String URL = "http://qa-scooter.praktikum-services.ru/api/v1/orders";
    private static Object OrderData;

    public static class OrderData {
        private String firstName;
        private String lastname;
        private String address;
        private int metroStation;
        private String phone;
        private int rentTime;
        private String deliveryDate;
        private String comment;
        private Object color;

        public OrderData(String firstName, String lastname, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, Object color) {
            this.firstName = firstName;
            this.lastname = lastname;
            this.address = address;
            this.metroStation = metroStation;
            this.phone = phone;
            this.rentTime = rentTime;
            this.deliveryDate = deliveryDate;
            this.comment = comment;
            this.color = color;
        }
    }
    @Step("Send order's data")
    public static Response requestForCreateOrder(){
        Response responseCreateOrder = given()
                .header("Content-type", "application/json")
                .log().all()
                .and()
                .body(OrderData)
                .when()
                .post(URL);
        return responseCreateOrder;

    }
}

