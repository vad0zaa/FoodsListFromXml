package ee.sinchukov.foodslistfromxml;

/**
 * Created by user_39 on 13.05.2015.
 */

/**
 * Created by user_39 on 07.05.2015.
 */
public class Food {

    private String name;
    private String price;
    private String description;
    private String calories;
    private String id;


    public Food(String name, String price, String description, String calories, String id){

        this.name=name;
        this.price = price;
        this.description = description;
        this.calories = calories;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public String getCalories() {
        return calories;
    }
    public String getId() {
        return id;
    }




    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(String price) {
        this.price = price;
    }

}


