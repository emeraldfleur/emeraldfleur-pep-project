package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.Account;

public class SocialMediaController {

    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("/register", this::registrationHandler);
        app.post("/login", this::loginHandler);
        app.post("/message", this::postMessageHandler);

        app.get("/messages", this::getMessageHandler);

        return app;
    }


    private void registrationHandler(Context context) 
    {
        
    }

    private void loginHandler(Context context) 
    {
        
    }
    private void postMessageHandler(Context context)
    {
        
    }
    private void getMessageHandler(Context context)
    {
        
    }

}