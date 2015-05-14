package ee.sinchukov.foodslistfromxml;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class InfoActivity extends ActionBarActivity {

    private String id;
    Bundle extras;
    TextView textView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Log.v(TAG, "info activity OnCreate started");

        // get extras from Main Screen
        extras = getIntent().getExtras();
        id = extras.getString(FoodsListActivity.EXTRA_FOOD_ID);
        Log.v(TAG, "received EXTRA_FOOD_ID:" +id);

                 String test = FoodsListActivity.getInfobyFoodId(id);
        // set info text
        textView = (TextView)findViewById(R.id.textView);

        textView.setText(test);
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
