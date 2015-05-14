package ee.sinchukov.foodslistfromxml;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;


public class InfoActivity extends ActionBarActivity {

    private String id;
    private int position;

String name,price,description,calories;

    Bundle extras;
    TextView textView;
    TextView textViewInfo, textViewName, textViewDescription, textViewPrice, textViewCalories;
    private static final String TAG = "MainActivity";

    String currentFoodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Log.v(TAG, "info activity OnCreate started");

        textViewName = (TextView)findViewById(R.id.textViewName);
        textViewPrice = (TextView)findViewById(R.id.textViewPrice);
        textViewDescription = (TextView)findViewById(R.id.textViewDescription);
        textViewCalories = (TextView)findViewById(R.id.textViewCalories);

        // get extras from Main Screen
        extras = getIntent().getExtras();
        id = extras.getString(FoodsListActivity.EXTRA_FOOD_ID);
        position = extras.getInt(FoodsListActivity.EXTRA_FOOD_POSITION);

        Log.v(TAG, "received EXTRA_FOOD_ID:" +id);
        Log.v(TAG, "received EXTRA_FOOD_POSITION:" +position);

        String test = FoodsListActivity.getInfobyFoodId(id);

        // set info text
        textView = (TextView)findViewById(R.id.textView);
        textView.setText(test);

        //  parse again by id

        try {
            XmlPullParser foodsParser = getResources().getXml(R.xml.foods);
            int eventType = foodsParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType==XmlPullParser.START_TAG && foodsParser.getName().equals("name") ) {
                    currentFoodId = foodsParser.getAttributeValue(0);
                    if (currentFoodId.equals(id)) {
                        foodsParser.next();
                        textViewName.setText("name: " + foodsParser.getText());
                        foodsParser.next();
                        foodsParser.next();
                        foodsParser.next();
                        textViewPrice.setText("price: " + foodsParser.getText());
                        foodsParser.next();
                        foodsParser.next();
                        foodsParser.next();
                        textViewDescription.setText("Description: " + foodsParser.getText());
                        foodsParser.next();
                        foodsParser.next();
                        foodsParser.next();
                        textViewCalories.setText("Calories: " + foodsParser.getText());
                    }

                }
                eventType = foodsParser.next();
            }
        }
        catch (Throwable t) {
            Log.v(TAG, "Error XML-file loading: " + t.toString());
            Toast.makeText(this, "Error XML-file loading: " + t.toString(), Toast.LENGTH_LONG)
                    .show();
        }


        // set info text
        //textViewInfo = (TextView)findViewById(R.id.textViewInfo);
        //textViewInfo.setText("not ready yet");




    }


















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
