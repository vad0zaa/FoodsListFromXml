package ee.sinchukov.foodslistfromxml;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class FoodsListActivity extends ListActivity {
    public static final String EXTRA_FOOD_ID = "ee.sinchukov.customarrayadapter.FOOD_ID";

    protected Food[] foods;
    private static final String TAG = "MainActivity";
    ArrayList<String> foodsList = new ArrayList<String>();
    ArrayList<String> priceList = new ArrayList<String>();
    ArrayList<String> descriptionList = new ArrayList<String>();
    ArrayList<String> caloriesList = new ArrayList<String>();
    ArrayList<String> idList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate started...");
        try {
            XmlPullParser foodsParser = getResources().getXml(R.xml.foods);
            int eventType = foodsParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType==XmlPullParser.START_TAG && foodsParser.getName().equals("name")) {

                    if(foodsParser.getAttributeName(0).equals("food_id")) {
                        idList.add(foodsParser.getAttributeValue(0));
                        Log.v(TAG, "food_id:"+foodsParser.getAttributeValue(0));
                    }

                    foodsParser.next();
                    foodsList.add(foodsParser.getText());
                    Log.v(TAG, "added name:"+foodsParser.getText());
                    eventType = foodsParser.next();
                }

                if (eventType==XmlPullParser.START_TAG && foodsParser.getName().equals("price")) {
                    foodsParser.next();
                    priceList.add(foodsParser.getText());
                    Log.v(TAG, "added price:"+foodsParser.getText());
                    eventType = foodsParser.next();
                }

                if (eventType==XmlPullParser.START_TAG && foodsParser.getName().equals("description")) {
                    foodsParser.next();
                    descriptionList.add(foodsParser.getText());
                    Log.v(TAG, "added description:"+foodsParser.getText());
                    eventType = foodsParser.next();
                }

                if (eventType==XmlPullParser.START_TAG && foodsParser.getName().equals("calories")) {
                    foodsParser.next();
                    caloriesList.add(foodsParser.getText());
                    Log.v(TAG, "added calories:"+foodsParser.getText());
                    eventType = foodsParser.next();
                }


                eventType = foodsParser.next();
            }
        }
        catch (Throwable t) {
            Log.v(TAG, "Error XML-file loading: " + t.toString());
            Toast.makeText(this, "Error XML-file loading: " + t.toString(), Toast.LENGTH_LONG)
                    .show();
        }

        // set foods
        Log.v(TAG, "set foods started, need to fill foods arrays...");

        foods = new Food[foodsList.size()];

        for(int i=0; i<foodsList.size();i++){
            foods[i]=new Food(foodsList.get(i),priceList.get(i),descriptionList.get(i),caloriesList.get(i),idList.get(i));
        }
        // set foods end
        Log.v(TAG, "set foods finished, foods arrays are ready...");

        Log.v(TAG, "set foodAdapter...");
        setListAdapter(new FoodAdapter(foods));

        // click
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                Food selectedFood = (Food)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Был выбран пункт id " + selectedFood.getId(),
                        Toast.LENGTH_SHORT).show();

                // show info screen
                Log.v(TAG, "start show info");
                showInfo(selectedFood.getId());
            }
        };
        getListView().setOnItemClickListener(itemListener);
    }

    protected void showInfo(String foodId){
        Intent intent = new Intent(this,InfoActivity.class);
        // pass item position to info screen
        intent.putExtra(FoodsListActivity.EXTRA_FOOD_ID, foodId);
        Log.v(TAG, "put EXTRA_FOOD_ID:"+ foodId);
        //show info screen
        startActivity(intent);
    }

    public static String getInfobyFoodId(String id){
        return "info about id "+id;
    }

/*    public String getInfobyFoodId(String id){
    for (int i=0;i<foods.length;i++){
      if(foods[i].getId().equals(id)){
          return "information: "+ foods[i].getName()+ " Descripion: "+ foods[i].getDescription();
      }
        else return "unknown id "+id;
    }
     return  "no info found";
    }*/

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

            holder.nameView.setText(food.getName());
            holder.priceView.setText(food.getPrice());
            return row;
        }


        private Food getFoodPosition(int position) {
            return(((FoodAdapter)getListAdapter()).getItem(position));
        }

        class ViewHolder {
            public TextView nameView, priceView;
        }
    }
}
