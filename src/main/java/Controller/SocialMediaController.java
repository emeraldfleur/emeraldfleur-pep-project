package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.Account;

public class SocialMediaController {

    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("/register", this::registrationHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::postMessageHandler);

        app.get("/messages", this::getAllMessageHandler);
        app.get("/messages/{message_id}", this::getMessageIDHandler);
        app.get("/accounts/{account_id}/messages", this::getUserMessageID);

        app.delete("/messages/{message_id}", this::deleteMessageIDHandler);
    
        app.patch("/messages/{message_id}", this::patchMessageIDHandler);



        return app; //For testing.
    }

    // post Handlers
    private void registrationHandler(Context context) 
    {
        
    }

    private void loginHandler(Context context) 
    {
        
    }
    private void postMessageHandler(Context context)
    {
        
    }

    //get Handlers
    private void getAllMessageHandler(Context context)
    {
        
    }
    private void getMessageIDHandler(Context context)
    {

    }
    private void getUserMessageID(Context context)
    {

    }
    //delete Handler
    private void deleteMessageIDHandler(Context context)
    {

    }
    //patch Handler
    private void patchMessageIDHandler(Context context)
    {

    }

}