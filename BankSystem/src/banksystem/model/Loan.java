

package banksystem.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Loan {
   
    private int id;
    private int number;
    private int amount;
    private double rate;
    private int clientID;
    private Date paymentPlan;
    private boolean granted;
    private static List<Loan> loans;
    
    public Loan (int id, int number, int clientID, int amount, double rate , Date paymentPlan, boolean granted){
        this.id = id;
        this.number = number;
        this.clientID = clientID;
        this.amount = amount;
        this.rate = rate;
        this.paymentPlan = paymentPlan;
        this.granted = granted;
    }
    
    public Loan(){}
    
    
    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
   
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
   
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    private String payoffdate;

    public String getPayoffdate() {
        return payoffdate;
    }

    public void setPayoffdate(String payoffdate) {
        this.payoffdate = payoffdate;
    }

    public String loanInfo() {
        return "number=" + number + "\n"+"amount=" + amount +"\n"+ "rate=" + rate + 
                "\n"+"paymentPlan=" + paymentPlan+"\n"+"granted="+granted;
    }

    /**
     * @return the paymentPlan
     */
    public Date getPaymentPlan() {
        return paymentPlan;
    }

    /**
     * @param paymentPlan the paymentPlan to set
     */
    public void setPaymentPlan(Date paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    /**
     * @return the granted
     */
    public boolean isGranted() {
        return granted;
    }

    /**
     * @param granted the granted to set
     */
    public void setGranted(boolean granted) {
        this.granted = granted;
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
     * @return the loans
     */
    public List<Loan> getLoans() {
        return loans;
    }

    /**
     * @param aLoans the loans to set
     */
    public void setLoans(List<Loan> aLoans) {
        loans = aLoans;
    }

    public void printLoans (){
        for (Loan l : loans){
            System.out.println(l.getId() + ", Amount: " + l.getAmount());
        }
    }
    
    
}
