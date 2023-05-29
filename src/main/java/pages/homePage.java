package pages;

import objects.homeObjects;
import utils.generalUtils;

public class homePage{
    homeObjects homeObjects = new homeObjects();
    public void isHomePage(){
        generalUtils.isVisible(homeObjects.hello, "Hello praveen", 10);
    }
}
