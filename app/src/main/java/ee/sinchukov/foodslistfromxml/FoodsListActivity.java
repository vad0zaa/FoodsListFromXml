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
    private Food[] foods;
    private static final String TAG = "MainActivity";
    ArrayList<String> foodsList = new ArrayList<String>();
    ArrayList<String> priceList = new ArrayList<String>();
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

                if (eventType==XmlPullParser.START_TAG && foodsParser.getName().equals("price")) {
                    foodsParser.next();
                    priceList.add(foodsParser.getText());
                }

                eventType = foodsParser.next();
            }
        }
        catch (Throwable t) {
            Log.v(TAG, "Error XML-file loading: " + t.toString());
            Toast.makeText(this, "Error XML-file loading: " + t.toString(), Toast.LENGTH_LONG)
                    .show();
        }
        //setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foodsList));

        // set foods


        foods = new Food[foodsList.size()];
        int flagsResource;
        for(int i=0; i<foodsList.size();i++){
          /*  flagsResource = getResources().getIdentifier(flags[i],"drawable",getPackageName());
            foods[i]=new State(countries[i],capitals[i],flagsResource);*/
            foods[i]=new Food(foodsList.get(i),priceList.get(i));
        }

        setListAdapter(new FoodAdapter(foods));


    }

    // State adapter

    private Food getFoodPosition(int position) {
        return(((FoodAdapter)getListAdapter()).getItem(position));
    }
    class FoodAdapter extends ArrayAdapter<Food> {

        private LayoutInflater mInflater;

        FoodAdapter(Food[] list) {
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
                holder.nameView = (TextView) row.findViewById(R.id.name);
                holder.priceView = (TextView) row.findViewById(R.id.price);
                row.setTag(holder);
            }
            else{

                holder = (ViewHolder)row.getTag();
            }

            Food food = getFoodPosition(position);

         //   holder.imageView.setImageResource((state.getFlagResource()));
            holder.nameView.setText(food.getName());
            holder.priceView.setText(food.getPrice());

            return row;
        }

        class ViewHolder {
           // public ImageView imageView;
            public TextView nameView, priceView;
        }
    }
}
