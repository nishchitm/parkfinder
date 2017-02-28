package evolveres.in.parkfinder.interfaces;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by nishc on 27-06-2016.
 */
public interface setlogin {
    @FormUrlEncoded
    @POST("/ParkingFinder/login.php")
    public void insertdata(
            @Field("Email") String Email,
            @Field("Password") String Password,
            Callback<Response> callback
    );

}