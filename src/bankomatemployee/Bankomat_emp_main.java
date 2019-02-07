

package bankomatemployee;

import bank_emp_model.Account;
import bank_emp_model.Client;
import bank_emp_model.HandleAccount;
import bank_emp_model.Loan;
import java.util.ArrayList;
import java.util.List;


public class Bankomat_emp_main {
    
    public static void main(String[] args) {
        
        Repository r = new Repository();
        Account ac = new Account();
        Controller con = new Controller();
        Client client = new Client();
        Loan loan = new Loan();
        HandleAccount handleAccount = new HandleAccount();        
        
        
        r.getAllAccounts();
        System.out.println("\nAccounts:");
        ac.printAccounts();
        
        r.getAllLoans();
        System.out.println("\nLoans:");
        loan.printLoans();
        
        r.getAllLoans();
        System.out.println("\nLoans:");
        loan.printLoans();
        
        r.getAllHandleAccounts();
        System.out.println("\nHandleAccounts:");
        handleAccount.printHandleAccount();
        
        
        // System.out.println(r.getAllEmployees().size());
        
        
        
        // Lägga in nya kunder
        // (int employeeID, int clientNumber, int clientPIN))
        // r.callCreateNewClient(1, 1006305050, 1234);
        
        // Uppdatera kunduppgifter
        // `UpdateInfo`(in clientID int, in oPIN int, in clientName varchar(45), 
        // in clientAddress varchar(45), in clientTelephone varchar(45))
        // r.callUpdateInfo(3, 4321, "Oskar", "Blomvägen 5", "0736444555");
        
        // Radera kunder
        // `Delete Client`(in employeeID int, in clientID int)
        // r.callDeleteClient(1, 6);
        
        // Skapa konton åt en kund
        // `AssignAccount`(in employeeID int, in clientID int, in AccountNumber int)
        // r.callAssignAccount(1, 4, 200200);
        
        // Avsluta konto åt en kund
        // `EndAccount`(in employeeID int, in clientID int, in AccountID int)
        // r.callEndAccount(1, 4, 4);
        
        // Sätta in pengar på ett kundkonto
        // `Deposit`(in employeeID int, in clientID int, in accountID int, in amount int)
        // r.callDeposit(1, 2, 3, 5000);
        
        // Ta ut pengar från ett kundkonto
        // `Withdraw`(in employeeID int, in clientID int, in accountID int, in amount int)
        // r.callWithdraw(1, 2, 3, 400);
        
        // Ändra räntesats på en kunds konto
        // `SetAccountRate`(in employeeID int, in clientID int, in accountID int, in rate decimal(3,1))
        // r.callSetAccountRate(1, 2, 3, 2.5);
        
        // Bevilja lån till en kund
        // `GrantLoan`(in employeeID int, in clientID int, in loanID int)
        // r.callGrantLoan(1, 3, 2);
                
        // Ändra räntesats på en kunds lån
        // `SetLoanRate`(in employeeID int, in clientID int, in loanID int, in rate decimal(3,1))
        // r.callSetLoanRate(1, 3, 2, 3.3);
   
        // Ändra betalplan på en kunds lån (den tid som det kommer att ta för kunden att betala lånet)
        // `ChangeLanPlan`(in employeeID int, in clientID int, in loanID int, in newPlan date)
        // FUNKAR INTE      r.callChangeLanPlan(1, 3, 2,'2023-09-09');
        
        System.out.println("\ndatumPeriodEmployee-prog:");
        Account testAccount = ac.getAccounts().get(0);
        List<HandleAccount> selectedDatesOfHandleAccount = new ArrayList();
        selectedDatesOfHandleAccount = con.loadHistorysforAccount(testAccount, "2019-01-09", "2019-02-03");
        System.out.println("Antal transaktioner: " + selectedDatesOfHandleAccount.size());
        
        
    }

}
