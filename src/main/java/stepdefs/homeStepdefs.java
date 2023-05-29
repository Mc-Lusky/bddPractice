package stepdefs;

import cucumber.api.java.en.Given;
import pages.homePage;

public class homeStepdefs {

    @Given("user is on Homepage")
    public void userIsOnHomePage(){
        homePage home = new homePage();
        home.isHomePage();
    }
}
