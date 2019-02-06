/*
 * Javautveckling 2018
 */

package bank_emp_model;

import java.sql.Date;
import java.util.List;


public class HandleLoan {

/*
    CREATE TABLE hanteraLan (
idhanteraLan int(11) NOT NULL AUTO_INCREMENT,
lanId int(11) NOT NULL,
bevilja tinyint(4) DEFAULT NULL,
lanrantesats decimal(3,1) DEFAULT NULL,
betalplan date DEFAULT NULL,
anstalldId int(11) NOT NULL,
date datetime DEFAULT NULL,
    */
    
    private int id;
    private int loanId;
    private boolean isGranted;
    private double rate;
    private Date loanEndDate;
    private int employeeId;
    private Date dateForChange;
    private static List<HandleLoan> handleLoans;
    
    public HandleLoan(int id, int loanId, boolean isGranted, double rate, 
            Date loanEndDate, int employeeId, Date dateForChange){
        this.id = id;
        this.loanId = loanId;
        this.isGranted = isGranted;
        this.rate = rate;
        this.loanEndDate = loanEndDate;
        this.employeeId = employeeId;
        this.dateForChange = dateForChange;
    }
    
    public HandleLoan(){}

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
     * @return the loanId
     */
    public int getLoanId() {
        return loanId;
    }

    /**
     * @param loanId the loanId to set
     */
    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    /**
     * @return the isGranted
     */
    public boolean isIsGranted() {
        return isGranted;
    }

    /**
     * @param isGranted the isGranted to set
     */
    public void setIsGranted(boolean isGranted) {
        this.isGranted = isGranted;
    }

    /**
     * @return the rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * @return the loanEndDate
     */
    public Date getLoanEndDate() {
        return loanEndDate;
    }

    /**
     * @param loanEndDate the loanEndDate to set
     */
    public void setLoanEndDate(Date loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the dateForChange
     */
    public Date getDateForChange() {
        return dateForChange;
    }

    /**
     * @param dateForChange the dateForChange to set
     */
    public void setDateForChange(Date dateForChange) {
        this.dateForChange = dateForChange;
    }

    /**
     * @return the handleLoans
     */
    public List<HandleLoan> getHandleLoans() {
        return handleLoans;
    }

    /**
     * @param handleLoans the handleLoans to set
     */
    public void setHandleLoans(List<HandleLoan> handleLoans) {
        this.handleLoans = handleLoans;
    }
    
    public void printHandleLoans(){
        for (HandleLoan h : handleLoans){
            System.out.println(h.getId() + ", LoanId: " + h.getLoanId());
        }
    }
    
    
}
