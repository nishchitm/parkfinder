package evolveres.in.parkfinder.interfaces;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by nishc on 01-07-2016.
 */
public interface user_get2_API {
    @FormUrlEncoded
    @POST("/ParkingFinder/getDetails.php")
    public void getdata(
            @Field("Lat") double Lat,
            @Field("Lon") double Lon,
            Callback<Response> callback
    );

}