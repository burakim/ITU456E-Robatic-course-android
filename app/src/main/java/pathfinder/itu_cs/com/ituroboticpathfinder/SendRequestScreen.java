package pathfinder.itu_cs.com.ituroboticpathfinder;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import pathfinder.itu_cs.com.ituroboticpathfinder.webservice.WebServiceManager;
import pathfinder.itu_cs.com.ituroboticpathfinder.webservice.models.RouteGetResponse;
import pathfinder.itu_cs.com.ituroboticpathfinder.webservice.requests.RouteGetRequest;
import retrofit.Call;

/**
 * Created by BurakMac on 21/12/15.
 */
public class SendRequestScreen extends AppCompatActivity {
    private Spinner destinationSpinner;
    private Spinner departureSpinner;
    private Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_request);
        String[] departureArray = getResources().getStringArray(R.array.departure_array);
        String[] destinationArray = getResources().getStringArray(R.array.destination_array);

        destinationSpinner = (Spinner) findViewById(R.id.destination_spinner);
        departureSpinner = (Spinner) findViewById(R.id.departure_spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, departureArray);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> dataAdapterDestination = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, destinationArray);
        dataAdapterDestination.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationSpinner.setAdapter(dataAdapterDestination);
        departureSpinner.setAdapter(dataAdapter);
        goButton = (Button) findViewById(R.id.send_request_go_bttn);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RouteGetRequest routeGetRequest = new RouteGetRequest();
//                {"fromX":11, "fromY":28, "toX":13, "toY":3,"routeType":0}
                routeGetRequest.setFromX(11);
                routeGetRequest.setFromY(28);
                routeGetRequest.setToX(13);
                routeGetRequest.setToY(3);
                routeGetRequest.setRouteType(0);
                AsyncTask asyncTask = new AsyncTask() {
                    RouteGetResponse routeGetResponse;

                    @Override
                    protected void onPostExecute(Object o) {
                        AlertDialog alertDialog = new AlertDialog.Builder(SendRequestScreen.this).create();
                        if(routeGetResponse == null || routeGetResponse.getResultCode() != 58)
                        alertDialog.setMessage("Error occurred.");
                        else
                        alertDialog.setMessage("Result send.");

                        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                        super.onPostExecute(o);
                    }

                    @Override
                    protected Object doInBackground(Object[] params) {
                        try {
                            Call<RouteGetResponse> responseCall = WebServiceManager.getInstance().serviceMethods.findPath(routeGetRequest);
                             routeGetResponse = (RouteGetResponse) responseCall.execute().body();
                        }
                        catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                        return null;
                    }
                }.execute();
            }
        });
    }
}
