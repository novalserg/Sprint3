import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTest {

    @Test
    @Description("Create new courier. Positive test")
    @Step("Compare ER and FR: status code and response body")
            public void CreateNewCourierTest() {
    Response response = CourierClient.createNewCourier();
        response.then().assertThat().statusCode(201).body("ok", equalTo(true));
        System.out.println(response.body().asString());
    }

    @Test
    @Description("Create the same courier two times")
    @Step("Compare ER and FR: status code and response massage")
    public void createSameCourier(){
        Response response = CourierClient.createSameCourier();
        response.then().assertThat().statusCode(409).body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
        System.out.println(response.body().asString());

    }

    @Test
    @Description()
    @Step("Compare ER and FR: status code and response massage")
    public void createCourierWOPasswordTest(){
        Response response = CourierClient.CreateCourierWOPassword();
        response.then().assertThat().statusCode(400).body("message", equalTo("Недостаточно данных для создания учетной записи"));
        System.out.println(response.body().asString());
    }

        @After
    public void deleteCourier(){
        CourierClient.deleteCourier();
        }


}
