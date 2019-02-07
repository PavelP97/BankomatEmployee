/*
 *  
Java18-OOJ
 */

package banksystem;

import banksystem.model.Account;
import banksystem.model.Client;
import banksystem.model.Employee;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class BankSystem {

  
    public static void main(String[] args) throws ParseException, SQLException {
        
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Controller con=new Controller();
//        System.out.println(con.loadAccountsforClient(new Client(1)).size());
//        System.out.println(con.periodAccountHistory(sdf.parse("2019-02-01"), sdf.parse("2019-02-08")).size());
        
       Repository rep= new Repository();
//        System.out.println(con.EmployeeLogin(2019002).getId())  ;
        
//        rep.callCreateNewClient(4, 222345);
//        rep.callDeleteClient(4, 11);
//        System.out.println(rep.getAllClients().size());
//        System.out.println(rep.getAllEmployees().size());
//        System.out.println(rep.getAllHandleLoans().size());
//        System.out.println(rep.getAllLoans().size());
//        System.out.println(rep.getAllHandleAccounts());
////      
//         Repository_Employee re= new Repository_Employee();
//         System.out.println(re.callPayOffMonth(2));
//           System.out.println(con.loadAccountsforClient(new Client(1)).size());
////           System.out.println(rep.getAllAccounts().size());
//           System.out.println(con.loadLoansforClient(new Client(1)).size());
//        rep.callEndAccount(2, 1, 15);
        
//          con.endAccount(new Employee(2019002), new Client(1), new Account(14));
//          con.deleteClient(new Employee(2019002) , new Client(10));
//            System.out.println(new Employee(2019002).getId());
//            System.out.println(new Client(10).getPersonnumber());
//            System.out.println(new Client(1).getPersonnumber());

//            System.out.println(con.showVinstOfLoan(1));
            System.out.println(rep.callVinstOfLoan(2));
            System.out.println(rep.callPayOffMonth(1));
                    
//        
    }

}
