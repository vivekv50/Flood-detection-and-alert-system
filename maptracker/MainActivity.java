Package com.e.maptracker;
import androidx.appcompat.app.AppCompatActivity;
importandroid.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    //Initialize variable
    EditText etSource,etDestination;
    Button btTrack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        24
        //Assign variable
        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        btTrack = findViewById(R.id.bt_track);
        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get value from edit text
                String sSource = etSource.getText().toString().trim();
                String sDestination= etDestination.getText().toString().trim();
                // Check condition
                if (sSource.equals("") && sDestination.equals("")){
                    //when both value blank
                    Toast.makeText(getApplicationContext()
                            , "Search the location", Toast.LENGTH_SHORT).show();
                }
                else{
                    //when both value fill
                    //Dislay track
                    DisplayTrack(sSource,sDestination);
                }
            }
        });
    }
    private void DisplayTrack(String sSource, String sDestination) {
        //If the device does not have a map installed, then redirect it to play store
        try{
            //when google map is installed
            //Initialize uri
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+ sSource +
                    "/" + sDestination);
            //Initialize intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //Set package
            intent.setPackage("com.google.android.apps.maps");
            //set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // start activity
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            // when google map is not installed
            25
            //Initialize uri
            Uri uri =
                    Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.a
                            pps.maps");
                            //Initialize intent with action view
                            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity
            startActivity(intent);
        }
    }
}