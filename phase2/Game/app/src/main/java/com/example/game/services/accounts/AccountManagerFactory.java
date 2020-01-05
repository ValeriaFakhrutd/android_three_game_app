package com.example.game.services.accounts;

import android.content.Context;

/**
 * A class that exists solely to create AccountManager objects, in the form of instances of the
 * concrete implementation UserAccountManager
 */
public class AccountManagerFactory {
    /**
     * Creates and returns an AccountManager object
     * @param context - the Context wishing to create the object
     * @return an AccountManager object for the calling class
     */
    public AccountManager build(Context context){
        return new UserAccountManager(context);
    }
}
