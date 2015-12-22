package pathfinder.itu_cs.com.ituroboticpathfinder.webservice;

import pathfinder.itu_cs.com.ituroboticpathfinder.Constants;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by BurakMac on 21/12/15.
 */
public class WebServiceManager {
    private static WebServiceManager webServiceManager;
    public   ServiceMethods serviceMethods;
    public static Retrofit retrofit;

    public static WebServiceManager getInstance()
    {
        if(webServiceManager == null)
        {
            webServiceManager = new WebServiceManager();
        }
        return webServiceManager;
    }
    public WebServiceManager()
    {

        try{
            if(Constants.ipAddress == null | Constants.ipAddress.compareTo("") == 0)
                throw new IllegalAccessException("root Url cannot be null.");
            else
            {
                retrofit = new Retrofit.Builder().baseUrl("http://"+Constants.ipAddress).addConverterFactory(GsonConverterFactory.create()).build();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        serviceMethods = this.retrofit.create(ServiceMethods.class);

    }

}
