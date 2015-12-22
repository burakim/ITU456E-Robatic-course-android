package pathfinder.itu_cs.com.ituroboticpathfinder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by BurakMac on 21/12/15.
 */
public class SettingsScreen extends Activity {
    private EditText editText;
    private Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);
        editText = (EditText) findViewById(R.id.ip_address_field);
        saveButton = (Button) findViewById(R.id.save_bttn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();
            if(str.compareTo("") != 0)
            {
                Constants.ipAddress = str;
            }
            }
        });
    }
}
