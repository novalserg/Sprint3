import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private String scooterColor;


/*    public CreateOrderTest(String scooterColor) {
        this.scooterColor = scooterColor;
    }*/

    @Parameterized.Parameters
    public static Object[][] getColor() {
        return new Object[][]{
                {"BLACK"},
                {"GREY"},
                {"BLACK" , "GREY"},
                {""}
        };
    }

    OrderClient.OrderData orderData = new OrderClient.OrderData("Александра", "Самокатова", "Мссква, Ленина, 19, 22", 5, "+79874562154", 4, "2022-05-05", "бла-бла-бла", getColor());

    @Test
    @Description("Create order test")
    @Step("Compare ER and FR: status code and response body")
    public void createOrderTest(){
        Response response = OrderClient.requestForCreateOrder();
        response.then().assertThat().body("track", notNullValue())
                .and()
                .statusCode(201);
        System.out.println(response.body().asString());

    }
}