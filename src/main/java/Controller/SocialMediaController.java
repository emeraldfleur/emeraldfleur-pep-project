package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.Account;
import Service.AccountService;
import Service.AccountServiceImplementation;

public class SocialMediaController {
    private AccountService bookie;
    public SocialMediaController()
    {
        bookie = new AccountServiceImplementation();
    }

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
        try
        {
            Account receivedAccount = context.bodyAsClass(Account.class);
            Account returnedAccount = bookie.registerNewAccount(receivedAccount);
            context.json(returnedAccount);
        }
        catch (Exception e)
        {
            context.status(400);
        }
        
    }

    private void loginHandler(Context context) 
    {
        Account login = 
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