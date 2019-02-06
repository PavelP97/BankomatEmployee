/*
 *  
Java18-OOJ
 */
package banksystem.model;

/**
 *
 * @author xingao
 */
public class AccountHistory {
    
    private int accountID;

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }


    public String accountHistoryInfo() {
        return "accountID=" + accountID ;
    }

    
}
