package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UserTests {
    Faker faker_data;
    User userPayload ;
    public Logger logger;
    @BeforeClass
    public void setupData()
    {

        faker_data= new Faker();
        userPayload= new User();

        userPayload.setId(faker_data.idNumber().hashCode());
        userPayload.setUsername(faker_data.name().username());
        userPayload.setFirstname(faker_data.name().firstName());
        userPayload.setLastname(faker_data.name().lastName());
        userPayload.setEmail(faker_data.internet().safeEmailAddress());
        userPayload.setPassword(faker_data.internet().password(5,10));
        userPayload.setPhone(faker_data.phoneNumber().cellPhone());

   //logs
        logger = LogManager.getLogger(this.getClass());

    }


    @Test(priority = 1)
    public void testPostUser()
    {
        logger.info("********************Creating user****************************");
        Response response =UserEndPoints.createUser(userPayload);
        response.then().log().all();
       JSONObject a1= new JSONObject(userPayload);
        System.out.println(a1.toString(2));
//        System.out.println(a1.get("email"));
//        System.out.println(userPayload.getUsername());

        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("********************User is created ****************************");
    }

    @Test(priority =2)
    public void testGetUserByName()
    {
        logger.info("********************reading user Info****************************");
        Response response =UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().statusCode(200);
        response.then().log().all();
        response.then().log().body();

        logger.info("********************User info is displayed****************************");

        //Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void testUpdateUserByName()
    {
        logger.info("********************Updating user****************************");

        userPayload.setLastname(faker_data.name().lastName());
        userPayload.setEmail(faker_data.internet().safeEmailAddress());
        userPayload.setPassword(faker_data.internet().safeEmailAddress());
        Response response= UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);

        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);


        logger.info("********************User is Updated****************************");
        //checking data after update
//        Response response_after_update = UserEndPoints.readUser(this.userPayload.getUsername());
//
//        response.then().log().all();
//        Assert.assertEquals(response_after_update.statusCode(),200);
    }

    @Test(priority = 4)
     public void testDeleteUserBYName()
    {
        logger.info("********************Deleting user****************************");

        Response response  =UserEndPoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.statusCode(),200);

        logger.info("********************User Deleted****************************");
    }
}
