package ee.sinchukov.foodslistfromxml;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class FoodsListActivity extends ListActivity {
    private State[] states ;
    private static final String TAG = "MainActivity";
    ArrayList<String> foodsList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate started...");
        try {
            XmlPullParser foodsParser = getResources().getXml(R.xml.foods);
            int eventType = foodsParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType==XmlPullParser.START_TAG && foodsParser.getName().equals("name")) {
                    foodsParser.next();
                    foodsList.add(foodsParser.getText());
                }
                eventType = foodsParser.next();
            }
        }
        catch (Throwable t) {
            Log.v(TAG, "Error XML-file loading: " + t.toString());
            Toast.makeText(this, "Error XML-file loading: " + t.toString(), Toast.LENGTH_LONG)
                    .show();
        }
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                foodsList));

        // set states
        /*countries = getResources().getStringArray(R.array.countries);
        capitals = getResources().getStringArray(R.array.capitals);
        flags = getResources().getStringArray(R.array.flags);*/

        states= new State[foodsList.size()];
        int flagsResource;
        for(int i=0; i<foodsList.size();i++){
          /*  flagsResource = getResources().getIdentifier(flags[i],"drawable",getPackageName());
            states[i]=new State(countries[i],capitals[i],flagsResource);*/
        }

        setListAdapter(new StateAdapter(states));


    }

    // State adapter

    private State getModel(int position) {
        return(((StateAdapter)getListAdapter()).getItem(position));
    }
    class StateAdapter extends ArrayAdapter<State> {

        private LayoutInflater mInflater;

        StateAdapter(State[] list) {
            super(FoodsListActivity.this,R.layout.activity_foods_list,  list);
            mInflater = LayoutInflater.from(FoodsListActivity.this);
        }
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            ViewHolder holder;
            View row=convertView;
            if(row==null){

                row = mInflater.inflate(R.layout.activity_foods_list, parent, false);
                holder = new ViewHolder();
/*                holder.imageView = (ImageView) row.findViewById(R.id.flag);
                holder.nameView = (TextView) row.findViewById(R.id.name);
                holder.capitalView = (TextView) row.findViewById(R.id.capital);*/
                row.setTag(holder);
            }
            else{

                holder = (ViewHolder)row.getTag();
            }

            State state = getModel(position);

/*            holder.imageView.setImageResource((state.getFlagResource()));
            holder.nameView.setText(state.getName());
            holder.capitalView.setText(state.getCapital());*/

            return row;
        }

        class ViewHolder {
           // public ImageView imageView;
            public TextView nameView, capitalView;
        }
    }
}
