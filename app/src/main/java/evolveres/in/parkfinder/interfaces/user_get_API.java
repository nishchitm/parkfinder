package evolveres.in.parkfinder.interfaces;

/**
 * Created by nishc on 27-06-2016.
 */
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


public interface user_get_API {
    @FormUrlEncoded
    @POST("/ParkingFinder/getParkingPlaces.php")
    public void getdata(
            @Field("UID") String UID,
            Callback<Response> callback
    );

}