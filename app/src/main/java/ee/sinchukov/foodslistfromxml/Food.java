package ee.sinchukov.foodslistfromxml;

/**
 * Created by user_39 on 13.05.2015.
 */

/**
 * Created by user_39 on 07.05.2015.
 */
public class Food {

    private String name; // название
    private String price;

    public Food(String name, String price){

        this.name=name;
        this.price = price;
      //  this.flagResource=flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}


