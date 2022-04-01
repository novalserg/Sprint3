import io.qameta.allure.Description;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class APICourierTests {

    CourierData courierDataCreate = new CourierData("Doston51", "as34df546", "Достон Быстров");
    CourierData courierDataNotFull = new CourierData(null, "4543ndlkjfsd", null);
    CourierData courierNotExistingData = new CourierData("11", "bvnmsdlkgnewegedr", "Нет Меня");
    CourierData courierDataWrong = new CourierData("Doston4", null, "Достон БЫстров");


    @Test
    @Description ("Create new courier")
    public void checkCreateCourier() {
        Response response = given().header("Content-type", "application/json")
                .and()
                .body(courierDataCreate)
                .when()
                .post("http://qa-scooter.praktikum-services.ru/api/v1/courier");
        response.then().assertThat().statusCode(201).body("ok", equalTo(true));
        System.out.println(response.body().asString());
    }
  //  @Step("Create the same courier")

    @Test
    public void checkCreateSameCourier() {
        Response response = given().header("Content-type", "application/json")
                .and()
                .body(courierDataCreate)
                .when()
                .post("http://qa-scooter.praktikum-services.ru/api/v1/courier");
        response.then().assertThat().statusCode(409).body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
        System.out.println(response.body().asString());
    }

    @Test
    public void checkCreateCourierWOFirstName(){
        Response response = given().header("Content-type", "application/json")
                .and()
                .body(courierDataWrong)
                .when()
                .post("http://qa-scooter.praktikum-services.ru/api/v1/courier");
        response.then().assertThat().statusCode(400).body("message", equalTo("Недостаточно данных для создания учетной записи"));
        System.out.println(response.body().asString());
    }

    @Test
    public void loginCourierPositiveTest() {
        Response response = given()
                .header("Content-type", "application/json")
                .body(courierDataCreate)
                .when()
                .post("http://qa-scooter.praktikum-services.ru/api/v1/courier/login");
        response.then().assertThat().statusCode(200);
        System.out.println(response.body().asString());
    }

        CourierID courierID = given()
                .header("Content-type", "application/json")
                .body(courierDataCreate)
                .post("http://qa-scooter.praktikum-services.ru/api/v1/courier/login")
                .getBody()
// попросили представить результат как объект
                .as(CourierID.class);

    @Test
    public void loginCourierWrongDataTest(){
        Response response = given()
                .header("Content-type", "application/json")
                .body(courierDataNotFull)
                .when()
                .post("http://qa-scooter.praktikum-services.ru/api/v1/courier/login");
        response.then().assertThat().statusCode(400).body("message", equalTo("Недостаточно данных для входа"));
        System.out.println(response.body().asString());
    }

    @Test
    public void loginCourierNotExistLoginTest(){
        Response response = given()
                .header("Content-type", "application/json")
                .body(courierNotExistingData)
                .when()
                .post("http://qa-scooter.praktikum-services.ru/api/v1/courier/login");
        response.then().assertThat().statusCode(404).body("message", equalTo("Учетная запись не найдена"));
        System.out.println(response.body().asString());
    }

    @Test
    public void deleteCourierTest() {
        Response response = given()
                .header("Content-type", "application/json")
                .body(courierID.getId())
                .delete("http://qa-scooter.praktikum-services.ru/api/v1/courier/:id");
        response.then().assertThat().statusCode(200).body("ok", equalTo(true));
        System.out.println(response.body().asString());


    }
}
