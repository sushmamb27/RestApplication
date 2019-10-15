import static  io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;
import java.io.File;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Header;

import static com.jayway.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TestClass {
	
	@Test
	public void uploadFile(){
		
		File testUploadFile= new File("C:\\Users\\sushma.bharadwaj\\Desktop/Read.txt");
		RestAssured.baseURI="http://localhost:8080/testApplicationContext";
		Response response = given()
		.header("checksum-type","MD5")
				
		.multiPart(testUploadFile)
		.when().
		post("/uploadData/LbrZv9q4XMgbuAEusU5JvUSdgVRoHg8Knx");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.asString());
		
		Headers headers = response.getHeaders();
		for (io.restassured.http.Header header : headers) {
			System.out.println("Key : " + header.getName() 
	                           + " ,Value : " + header.getValue());
}
	}
	
	
	@Test
    public void downloadFileKey(){
		
		RestAssured.baseURI="http://localhost:8080";
    	 given()
    	 .header("checksum-type","MD5")
 	    .when ()
 	     .get("/testApplicationContext/downloadData/LbrZv9q4XMgbuAEusU5JvUSdgVRoHg8Knx?fileName=Read.txt")
 	    .then()
 	 .statusCode(200);
    	 
 	    	 
      
        }
   
	@Test
    public void downloadFileNoKey(){
		
		RestAssured.baseURI="http://localhost:8080";
    	 given()
 	    .when ()
 	     .get("/testApplicationContext/downloadData")
 	    .then()
 	 .statusCode(404);

       
        }
		
	@Test
    public void deleteFileKey(){
    	
    	RestAssured.baseURI="http://localhost:8080/testApplicationContext";
    	   	
    	 given()
    	    .when ()
 	     	    .get("/removeData/LbrZv9q4XMgbuAEusU5JvUSdgVRoHg8Knx?fileName=Read.txt")
    	    .then()
    	 .statusCode(200);
    }
	
	@Test
    public void deleteFileNoKey(){
    	
    	RestAssured.baseURI="http://localhost:8080/testApplicationContext";
    	   	
    	 given()
    	    .when ()
 	     	    .get("/removeData")
    	    .then()
    	 .statusCode(404);
    }
}
	