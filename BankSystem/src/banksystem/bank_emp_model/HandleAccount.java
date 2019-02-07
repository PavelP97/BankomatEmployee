/*
 * Javautveckling 2018
 */

package bank_emp_model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


public class HandleAccount {
    
    // ! skapa metoder för att ta ut rätt account, anställd, kund från id:na
    
    private int id;
    private int accountId;
    private int depositAmount;
    private int withdrawalAmount;
    private double rate;
    private boolean created;
    private Date dateForChange;
    private boolean closedAccount;  // true = kontot avslutat
    private int employeeId;
    private int clientId;
    private static List<HandleAccount> historyOfAccounts;
    
    
    public HandleAccount(int id, int accountId, int depositAmount, int withdrawalAmount, 
            double rate, boolean created, Date dateForChange, boolean closedAccount,
            int employeeId, int clientId){
        this.id = id;
        this.accountId = accountId;
        this.depositAmount = depositAmount;
        this.withdrawalAmount = withdrawalAmount;
        this.rate = rate;
        this.dateForChange = dateForChange;
        this.closedAccount = closedAccount;
        this.employeeId = employeeId;
        this.clientId = clientId;
    }
    
    public HandleAccount(){}

    
    
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    public int getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(int depositAmount) {
        this.depositAmount = depositAmount;
    }

    public int getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(int withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Date getCreationDate() {
        return dateForChange;
    }

    public void setCreationDate(Date creationDate) {
        this.dateForChange = creationDate;
    }

    public boolean isClosedAccount() {
        return closedAccount;
    }

    public void setClosedAccount(boolean closedAccount) {
        this.closedAccount = closedAccount;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the historyOfAccounts
     */
    public List<HandleAccount> getHistoryOfAccounts() {
        return historyOfAccounts;
    }

    /**
     * @param aHistoryOfAccounts the historyOfAccounts to set
     */
    public void setHistoryOfAccounts(List<HandleAccount> aHistoryOfAccounts) {
        historyOfAccounts = aHistoryOfAccounts;
    }
    
    public void printHandleAccount (){
        for (HandleAccount h : historyOfAccounts){
            System.out.println(h.getId() + ", " + h.getAccountId());
        }
    }

   public Account getAccountByAccountID(int accountId){
       Account account = new Account();
       return account.getAccounts().get(accountId);
   }
   
   //     private int employeeId;
   //    private int clientId;
   public Client getClientByClientID(int clientId){
       Client client = new Client();
       return client.getClients().get(clientId);
   }
    
}
