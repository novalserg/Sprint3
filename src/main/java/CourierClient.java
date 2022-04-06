import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CourierClient {

        static CourierData courierDataCreate = new CourierData("Doston77", "as34df546", "Достон Быстров");
        static CourierData courierDataTwo = new CourierData("Doston87", "as34df544", "Достон Супербыстров");
        static CourierData courierDataWrong = new CourierData("Doston4", null, "Достон БЫстров");

        static final String URL = ("http://qa-scooter.praktikum-services.ru/api/v1/courier");

        @Step("Send POST request to create new courier")
        public static Response createNewCourier(){
                Response responseCreateCourier = given()
                        .header("Content-type", "application/json")
                        .log().all()
                        .and()
                        .body(courierDataCreate)
                        .when()
                        .post(URL);
                return responseCreateCourier;
        }
        @Step("Send two POST request with the same data")
        public static Response createSameCourier(){
                Response responseCreateSameCourier = given()
                        .header("Content-type", "application/json")
                        .log().all()
                        .body(courierDataTwo)
                        .when()
                        .post(URL);
                return responseCreateSameCourier;
        }

        public static Response CreateCourierWOPassword() {
                Response responseCreateCourierWOPassword = given()
                        .header("Content-type", "application/json")
                        .log().all()
                        .body(courierDataWrong)
                        .when()
                        .post(URL);
                return responseCreateCourierWOPassword;
        }


        public static void deleteCourier() {
                CourierID courierID = new CourierID();
                CourierID response =
                        given()
                                .header("Content-type", "application/json")
                                .body(courierID)
                                .when()
                                .post("/api/v1/courier/login")
                                .then().extract().body().as(CourierID.class);
                int id = response.getId();
                given()
                        .delete("/api/v1/courier/" + id);
        }
        }
