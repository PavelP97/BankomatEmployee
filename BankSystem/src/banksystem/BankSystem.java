/*
 *  
Java18-OOJ
 */

package banksystem;

import banksystem.model.Account;
import banksystem.model.Client;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class BankSystem {

  
    public static void main(String[] args) throws ParseException, SQLException {
        
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Controller con=new Controller();
//        System.out.println(con.loadAccountsforClient(new Client(1)).size());
        System.out.println(con.periodAccountHistory(sdf.parse("2019-02-01"), sdf.parse("2019-02-08")).size());
        
//        System.out.println(con.EmployeeLogin(2019002).getId());
//      
//         Repository_Employee re= new Repository_Employee();
//         System.out.println(re.callPayOffMonth(2));
        
    }

}
