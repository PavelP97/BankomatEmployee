

package bank_emp_model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Account {
   
    private int id;
    private int clientID;
    
    private int number;
    
    private int balance;
    
    private double rate;
    
    private boolean avsluta;  // true = avslutat konto
    
    private static List<Account> accounts;
            
    
    public Account (int id, int clientID, int number, int balance, double rate, boolean avsluta){
        this.id = id;
        this.clientID = clientID;
        this.number = number;
        this.balance = balance;
        this.rate = rate;
        this.avsluta = avsluta;
    }
    
    public Account (){}
    
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String AccountInfo() {
        return  "number=" + number + ", balance=" + balance + ", rate=" + rate ;
    }

    /**
     * @return the avsluta
     */
    public boolean getAvsluta() {
        return avsluta;
    }

    /**
     * @param avsluta the avsluta to set
     */
    public void setAvsluta(boolean avsluta) {
        this.avsluta = avsluta;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the accounts
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * @param aAccounts the accounts to set
     */
    public void setAccounts(List<Account> aAccounts) {
        accounts = aAccounts;
    }
    
    public void printAccounts (){
        for (Account a : accounts){
            System.out.println(a.getId() + ", Balance: " + a.getBalance());
        }
    }

    
    
}
