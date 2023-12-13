package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;


public class DDTests extends UserTests{
//public Logger logger = (Logger) LogManager.getLogger(this.getClass());

    @Test(priority = 5,dataProvider  ="Data",dataProviderClass= DataProviders.class)
    public  void testPostUser1(String userID , String userName , String firstName ,String LastName ,String Email , String Password , String Phone )
    {
        //logger = LogManager.getLogger();
//        logger = (Logger) LogManager.getLogger(this.getClass());
        logger.info("********************Creating user for DD****************************");
        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstname(firstName);
        userPayload.setLastname(LastName);
        userPayload.setEmail(Email);
        userPayload.setPassword(Password);
        userPayload.setPhone(Phone);

        Response response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("********************created user for DD****************************");
    }

    @Test(priority = 6,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testdeleteuserByName(String userName)
    {
        Response response = UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
