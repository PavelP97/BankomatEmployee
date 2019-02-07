/*
 *  
Java18-OOJ
 */
package banksystem;


import banksystem.model.Account;
import banksystem.model.Client;
import banksystem.model.Employee;
import banksystem.model.HandleAccount;
import banksystem.model.Loan;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;



/**
 *
 * @author xingao
 */
public class Controller {
   Repository repo= new Repository(); 
   
   
   public Client checkLogin (int usernumber, int pin){
      List<Client> clients=repo.getAllClients();
       for(Client c:clients){
           if(usernumber==c.getPersonnumber()){
               if(pin==c.getPIN()){                                
                   return c;
               }              
           else
              JOptionPane.showMessageDialog(null,"Invalid Login.","Login Error",JOptionPane.ERROR_MESSAGE); 
           }
       }
       return null;
   } 
     
   
   public List<Account> loadAccountsforClient(Client c){
       List<Account> accountsofClient=repo.getAllAccounts().stream().
               filter(a->a.getClientID()==c.getId()&&a.getAvsluta()==false).
               collect(Collectors.toList());
       return accountsofClient;
   }
   
   
   
   public List<Loan> loadLoansforClient(Client c){
       List<Loan> loansofClient=repo.getAllLoans().stream().filter(a->a.getClientID()==c.getId()).
               collect(Collectors.toList());
       return loansofClient;
   }
   
   public List<HandleAccount> loadHistorysforAccount(Account a){
       List<HandleAccount> historysofAccount= repo.getAllHandleAccounts().stream().filter(s->s.getAccountId()==a.getId()).
               collect(Collectors.toList());
       return historysofAccount;
   }
   
   public Employee EmployeeLogin (int number){
      List<Employee> employees=repo.getAllEmployees();
       for(Employee e:employees){
           if(number==e.getNumber()){
              return e;  
           }
           else
               JOptionPane.showMessageDialog(null,"Invalid Number.");
           }          
       return null;
   } 
   
   
   public Client checkClientNumber(String personnumber){
       List<Client> clients=repo.getAllClients();
       for(Client c:clients){
           if(personnumber.equals(c.getPersonnumber())){
               return c;
           }
           
       }
       return null;  
   
   }
   
   public void createClient(Employee e,Client c){
       repo.callCreateNewClient(e.getId(), c.getPersonnumber());
   }
   
   public void deleteClient(Employee e,Client c){
       repo.callDeleteClient(e.getId(), c.getId());
   }
   
   public void updateInfo(Client c,int PIN,String name,String address,String telephont){
       repo.callUpdateInfo(c.getId(), PIN, name, address, telephont);
   }
   
   public void assignAccount(Employee e,Client c,int accountNumber){
       repo.callAssignAccount(e.getId(), c.getId(), accountNumber);
   }
   
   public void endAccount(Employee e,Client c,Account a){
       
   }
   
   public void depositAmount(Employee e,Client c,Account a,int amount){
       repo.callDeposit(e.getId(), c.getId(), a.getId(), amount);
   }
   
   public void withdrawAmount(Employee e,Client c,Account a,int amount){
       repo.callWithdraw(e.getId(), c.getId(), a.getId(), amount);
   }
   
   public void setAccountRate(Employee e,Client c,Account a,double rate){
       repo.callSetAccountRate(e.getId(), c.getId(), a.getId(), rate);
   }
   
   public void grantLoan(Employee e,Client c,Loan l){
       repo.callGrantLoan(e.getId(), c.getId(), l.getId());
       
   }
   
   public void setLoanRate(Employee e,Client c,Loan l,double rate){
       repo.callSetLoanRate(e.getId(), c.getId(), l.getId(), rate);
   }
   
   public double showVinstOfLoan(Loan l) throws SQLException{
       return repo.callVinstOfLoan(l.getId());
   }
   
   public double showPayOffMonth(Loan l) throws SQLException{
       return repo.callPayOffMonth(l.getId());
   }
   
   public List<HandleAccount> periodAccountHistory(Date fram,Date to){ 
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       List<HandleAccount> periodHistoryofAccounts=repo.getAllHandleAccounts().stream().
               filter(s->s.getCreationDate().after(fram)&&s.getCreationDate().before(to)).
               collect(Collectors.toList());
       return periodHistoryofAccounts;
   }
}
