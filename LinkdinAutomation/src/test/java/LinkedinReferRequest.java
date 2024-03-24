import org.testng.annotations.Test;


public class LinkedinReferRequest {
    public String companyName = "Phone Pe";
    public String jobLink = "https://boards.greenhouse.io/embed/job_app?token=5890043003&source=LinkedIn";
    public String defaultMessage = "I recently came across an opening for the SDE-1 role at " + companyName+". \n" +
            "It would be really helpful if you could assist me with the referral.\n" +
            "Thank You :)";
    public String resume = "https://drive.google.com/file/d/1ERlHiFgtrfeT1DOro56j1hzDdIIxW2mY/view";


    @Test
    public void linkdinReferRequeatSend() {
        LinkdinActions linkdinActions = new LinkdinActions();
        linkdinActions
                .navigateToUrl("https://www.linkedin.com/feed/")
                .searchKeywords(companyName)
                .selectConnections()
                .closeAllActiveTabsIfAny()
                .sendMessageToAllConnections(jobLink, defaultMessage, resume);

        linkdinActions.quitBrowser();
    }



}
