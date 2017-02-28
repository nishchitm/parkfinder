package evolveres.in.parkfinder.interfaces;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by nishc on 27-06-2016.
 */
public interface set_details {
    @FormUrlEncoded
    @POST("/ParkingFinder/registerfinaltwo.php")
    public void insertdata(
            @Field("Fname") String Fname,
            @Field("Lname") String Lname,
            @Field("Email") String Email,
            @Field("Password") String Password,
            @Field("SecurityQuestion") String SecurityQuestion,
            @Field("Answer") String Answer,
            @Field("PhoneNumber") String PhoneNumber,
            Callback<Response> callback
    );

}
