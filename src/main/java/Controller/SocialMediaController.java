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

    /*
     *  As a user, I should be able to create a new Account on the endpoint POST localhost:8080/register. 
     *  The body will contain a representation of a JSON Account, but will not contain an account_id. 
     * 
     *  Registration - we'll receive an account as a JSON, but it will be lacking user ID.
     *  Service needs to assign it a userID, or we just check in service, and all of this is built on top of a DAO.
     * 
     *  The registration will be successful if and only if the username is not blank, the password is at least 4 characters long, 
     *  and an Account with that username does not already exist. If all these conditions are met, the response body should contain 
     *  a JSON of the Account, including its account_id. The response status should be 200 OK, which is the default. 
     *  The new account should be persisted to the database.
     * 
     *  If the registration is not successful, the response status should be 400. (Client error)
     */
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
    /*
    2: Our API should be able to process User logins.
    As a user, I should be able to verify my login on the endpoint POST localhost:8080/login. 
    The request body will contain a JSON representation of an Account, not containing an account_id. 
    In the future, this action may generate a Session token to allow the user to securely use the site. 
    We will not worry about this for now.

    The login will be successful if and only if the username and password provided in the request body 
    JSON match a real account existing on the database. 
    If successful, the response body should contain a JSON of the account in the response body, including its account_id. 
    The response status should be 200 OK, which is the default.
    If the login is not successful, the response status should be 401. (Unauthorized)
    */
    private void loginHandler(Context context) 
    {
        try
        {
            Account receivedAccount = context.bodyAsClass(Account.class);
            Account returnedAccount = bookie.tryLogin(receivedAccount);
            context.json(returnedAccount);
        }
        catch (Exception e)
        {
            context.status(400);
        }
    }
    /*
    3: Our API should be able to process the creation of new messages.

    As a user, I should be able to submit a new post on the endpoint POST localhost:8080/messages. 
    The request body will contain a JSON representation of a message, which should be persisted to the database, 
    but will not contain a message_id.

    The creation of the message will be successful if and only if the message_text is not blank, 
    is not over 255 characters, and posted_by refers to a real, existing user. 
    If successful, the response body should contain a JSON of the message, including its message_id. 
    The response status should be 200, which is the default. The new message should be persisted to the database.
    If the creation of the message is not successful, the response status should be 400. (Client error)
     */
    private void postMessageHandler(Context context)
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