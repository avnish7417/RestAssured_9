package api.endpoints;

/*
Swagger URL -> https://petstore.swagger.io

Create user(Post) :  https://petstore.swagger.io/v2/user
Get user (Get) : https://petstore.swagger.io/v2/user/string --username
Update user (Put) :https://petstore.swagger.io/v2/user/string -- ''
 delete user (Delete) : https://petstore.swagger.io/v2/user/string -- ''

 */
public class Routes {
    public static  String base_url ="https://petstore.swagger.io/v2"; // use till v2 because it is url after that it vary according to the api we are using for example pet , store , bank etc.

    //User Model
    public static String post_url = base_url+"/user";
    public  static  String get_url = base_url+"/user/{username}";
    public  static  String put_url = base_url+"/user/{username}";
    public  static  String delete_url= base_url+"/user/{username}";

}
