import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EmployeeTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void getMethod() {
        given().when().get("/posts/2").then().statusCode(200);
    }

    @Test
    public void testGetRequestAndVerifyResponse() {
        // Base URI setup
        // RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Perform the GET request and extract the response
        Response response = given()
                .when()
                .get("/comments?postId=1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Convert response to List of Maps
        List<Map<String, Object>> jsonResponse = response.jsonPath().getList("");

        // Expected data for validation
        Object[][] expectedData = new Object[][] {
                { 1, "id labore ex et quam laborum", "Eliseo@gardner.biz" },
                { 2, "quo vero reiciendis velit similique earum", "Jayne_Kuhic@sydney.com" },
                { 3, "odio adipisci rerum aut animi", "Nikita@garfield.biz" },
                { 4, "alias odio sit", "Lew@alysha.tv" },
                { 5, "vero eaque aliquid doloribus et culpa", "Hayden@althea.biz" }
        };

        // Iterate through both expected and actual data
        for (int i = 0; i < expectedData.length; i++) {
            assertResponse(jsonResponse.get(i), expectedData[i][0], expectedData[i][1], expectedData[i][2]);
        }
    }

    // Custom assertion method for reusability
    private void assertResponse(Map<String, Object> actualResponse, Object expectedId, Object expectedName,
            Object expectedEmail) {
        assertEquals(expectedId, actualResponse.get("id"));
        assertEquals(expectedName, actualResponse.get("name"));
        assertEquals(expectedEmail, actualResponse.get("email"));
    }

}
