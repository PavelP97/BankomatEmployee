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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;




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
           
           } 
       
       return null;
   } 
   
   
   public Client checkClientNumber(String personnumber){
       List<Client> clients=repo.getAllClients();
       for(Client c:clients){
           if(personnumber.equals(String.valueOf(c.getPersonnumber()))){
               return c;
           }        
           
       }      
       
       return new Client();  
   
   }
   
   public void createClient(Employee e,String personnumber){
       repo.callCreateNewClient(e.getId(), Integer.valueOf(personnumber));
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
       repo.callEndAccount(e.getId(), c.getId(), a.getId());
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
   
   public void changeLanPlan(Employee e,Client c,Loan l,String newplan){
       repo.callChangeLanPlan(e.getId(), c.getId(), l.getId(), newplan);
   }
   
   public double showVinstOfLoan(Loan l) throws SQLException{
       return repo.callVinstOfLoan(l.getId());
   }
   
   public double showPayOffMonth(Loan l) throws SQLException {
       return repo.callPayOffMonth(l.getId());
   }
   
   
    public List<HandleAccount> loadHistorysforAccount(int aid, String stringStartDate, String stringEndDate){
       repo.getAllHandleAccounts();
       LocalDate startDate = LocalDate.parse(stringStartDate);
       LocalDate endDate = LocalDate.parse(stringEndDate);
       
//       int accountId = a.getId();
       HandleAccount ha = new HandleAccount();
       List<HandleAccount> selectedDatesOfHandleAccount = new ArrayList<>();
       
       for (int i = 0; i < ha.getHistoryOfAccounts().size(); i++) {
           // om kontoID stämmer
           if (ha.getHistoryOfAccounts().get(i).getAccountId() == aid){
                // Om datumet är mellan startDate och endDate, eller är lika med startDate eller endDate.
                if(((ha.getHistoryOfAccounts().get(i).getCreationDate()).toLocalDate().isAfter(startDate)) && 
                        ((ha.getHistoryOfAccounts().get(i).getCreationDate()).toLocalDate().isBefore(endDate)) ||
                        ((ha.getHistoryOfAccounts().get(i).getCreationDate()).toLocalDate().equals(startDate)) ||
                        ((ha.getHistoryOfAccounts().get(i).getCreationDate()).toLocalDate().equals(endDate))
                        ){
                    selectedDatesOfHandleAccount.add(ha.getHistoryOfAccounts().get(i));
                }
           }
       }
       return selectedDatesOfHandleAccount;
   }
}
