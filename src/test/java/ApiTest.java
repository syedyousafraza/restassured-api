import io.restassured.RestAssured;
import io.restassured.http.ContentType;
//import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void apiCall(){
        try {
            given().when().get("/posts/1").then().statusCode(200);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testGetRequest() {
        try {
            given()
                    .when()
                    .get("/posts/1")
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("id", equalTo(1))
                    .body("title", not(emptyString()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testGetRequest1() {
        given()
                .when()
                .get("/posts/2")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(2))
                .body("title", equalTo("qui est esse"))
                .body("userId", equalTo(1));

    }

    @Test
    public void testPostRequest() {
        String requestBody = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo("foo"));
    }

    // THIS FUNCTION IS A TRY TO MEASURE TIME FOR THE TEST. BUT I THINK ITS NOT
    // WORKING AS EXPECTED.

    @Test
    public void measureTime() {
        // Send the GET request and capture the response
        long startTime = System.currentTimeMillis(); // Record the start time

        Response response = given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1))
                .body("title", not(emptyString()))
                .extract()
                .response(); // Extract the response to measure time

        long endTime = System.currentTimeMillis(); // Record the end time

        // Calculate the actual response time in ms
        long actualResponseTime = endTime - startTime;
        System.out.println("Actual Response time: " + actualResponseTime + " ms");

        // You can also verify that the response time meets certain conditions
        response.then().time(lessThan(2000L)); // Assert that the response is under 2 seconds
    }

    @Test
    public static int sum(int a , int b){
        int result; 
        result = a + b; 
        return result;
    }
    
    public static void main(String[] args) {
        sum(3,4);
    }
}

