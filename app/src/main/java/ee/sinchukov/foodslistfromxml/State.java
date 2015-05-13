package ee.sinchukov.foodslistfromxml;

/**
 * Created by user_39 on 13.05.2015.
 */

/**
 * Created by user_39 on 07.05.2015.
 */
public class State {

    private String name; // название
    private String capital;  // столица
    private int flagResource; // ресурс флага

    public State(String name, String capital, int flag){

        this.name=name;
        this.capital=capital;
        this.flagResource=flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getFlagResource() {
        return flagResource;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }
}


