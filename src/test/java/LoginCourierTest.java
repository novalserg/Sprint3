import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginCourierTest {

    @Before
    public void createCourierforLogin() {
        CourierClient.createNewCourier();
    }

    @Test
    @Description("Login courier. Positive test")
    @Step("Compare ER and FR: status code and response body")
    public static void loginCourierPositiveTest(){
        Response response = LoginCourier.loginCourierPositive();
        response.then().assertThat().statusCode(200);
        System.out.println(response.body().asString());
    }

    @Test
    @Description("Login courier with not all data in request")
    @Step("Compare ER and FR: status code and response body")
    public static void loginCourierNotFullDataTest(){
        Response response = LoginCourier.loginCourierNotFullData();
        response.then().assertThat().statusCode(400).body("message", equalTo("Недостаточно данных для входа"));
        System.out.println(response.body().asString());
    }

    @Test
    @Description("Login courier with bot existing")
    @Step("Compare ER and FR")
    public static void loginCourierNotExistingDataTest(){
        Response response = LoginCourier.loginCourierNotExistingData();
        response.then().assertThat().statusCode(404).body("message", equalTo("Учетная запись не найдена"));
        System.out.println(response.body().asString());
    }



}
