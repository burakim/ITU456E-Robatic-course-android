package pathfinder.itu_cs.com.ituroboticpathfinder.webservice;

import pathfinder.itu_cs.com.ituroboticpathfinder.webservice.models.RouteGetResponse;
import pathfinder.itu_cs.com.ituroboticpathfinder.webservice.requests.RouteGetRequest;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by BurakMac on 21/12/15.
 */

public interface ServiceMethods {

    @POST("/route")
    public Call<RouteGetResponse> findPath(@Body RouteGetRequest routeGetRequest);
}
