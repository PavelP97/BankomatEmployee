/*
 *  
Java18-OOJ
 */
package banksystem;


import banksystem.model.Account;
import banksystem.model.AccountHistory;
import banksystem.model.Client;
import banksystem.model.Employee;
import banksystem.model.HandleAccount;
import banksystem.model.Loan;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author xingao
 */
public class Repository_Employee {
    
    private Properties p= new Properties();
    
    public Repository_Employee(){
        try {
            p.load(new FileInputStream("src/bankomat/Settings.properties"));
//            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public List<Client> getAllClients(){
        Client client = new Client();
        List<Client> clients = new ArrayList();
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
            p.getProperty("name"), p.getProperty("password"));
            Statement stmt = con.createStatement();){
            
            ResultSet rs = stmt.executeQuery("select idKund, personnummer, "
                    + "pin, namn, address, telefon from Kund");
            
            while(rs.next()){
                client = new Client(rs.getInt("idKund"), rs.getInt("personnummer"), rs.getInt("pin"),
                        rs.getString("namn"), rs.getString("address"), rs.getString("telefon"));
                clients.add(client);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        client.setClients(clients);
        return clients;
    }
    
    
    public List<Account> getAllAccounts(){
        Account account = new Account();
        List<Account> accounts = new ArrayList();
        boolean avsluta;
        
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
            p.getProperty("name"), p.getProperty("password"));
            Statement stmt = con.createStatement();){
            
            ResultSet rs = stmt.executeQuery("select idKonto, number, "
                    + "kundId, saldo, sparaRantesats, avsluta from Konto");
            
            while(rs.next()){
                if (rs.getInt("avsluta") == 1){
                    avsluta = true;
                } else
                    avsluta = false;
                
                account = new Account(rs.getInt("idKonto"), rs.getInt("kundId"), rs.getInt("number"),rs.getInt("saldo"), 
                        rs.getDouble("sparaRantesats"), avsluta);
                accounts.add(account);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        account.setAccounts(accounts);
        return accounts;
    }
    
    
    public List<Loan> getAllLoans(){
        Loan loan = new Loan();
        List<Loan> loans = new ArrayList();
        boolean bevilja;
        //
        
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
            p.getProperty("name"), p.getProperty("password"));
            Statement stmt = con.createStatement();){
            
            ResultSet rs = stmt.executeQuery("select idLan, LanNumber, "
                    + "kundId, lanAntal, lanRantesats, betalplan, bevilja from Lan");
            
            while(rs.next()){

                if (rs.getInt("bevilja") == 1){
                    bevilja = true;
                } else
                    bevilja = false;
                
                loan = new Loan(rs.getInt("idLan"), rs.getInt("LanNumber"),
                        rs.getInt("kundId"), rs.getInt("lanAntal"), 
                        rs.getDouble("lanRantesats"), rs.getDate("betalplan"), bevilja);
                loans.add(loan);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        loan.setLoans(loans);
        return loans;
    }
    
    /*
    CREATE TABLE hanteraKonto (
idhantering int(11) NOT NULL AUTO_INCREMENT,
kontoId int(11) NOT NULL,
sattainsaldo int(11) DEFAULT NULL,
tautsaldo int(11) DEFAULT NULL,
rantesats decimal(3,1) DEFAULT NULL,
skapa tinyint(4) DEFAULT NULL,
avsluta tinyint(4) DEFAULT NULL,
anstalldId int(11) DEFAULT NULL,
kundId int(11) DEFAULT NULL,
date datetime NOT NULL,
    */
    
    public List<HandleAccount> getAllHandleAccounts(){
        HandleAccount handleAccount = new HandleAccount();
        List<HandleAccount> historyOfAccounts = new ArrayList();
        boolean avsluta;
        boolean skapa;
        
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
            p.getProperty("name"), p.getProperty("password"));
            Statement stmt = con.createStatement();){
            
            ResultSet rs = stmt.executeQuery("select idhantering, kontoId, "
                    + "sattainsaldo, tautsaldo, rantesats, skapa, avsluta, "
                    + "anstalldId, kundId, date from hanteraKonto");
            
            while(rs.next()){
                if (rs.getInt("avsluta") == 1){
                    avsluta = true;
                } else
                    avsluta = false;

                if (rs.getInt("skapa") == 1){
                    skapa = true;
                } else
                    skapa = false;
                
                // (int id, int accountId, int depositAmount, int withdrawalAmount, 
            // double rate, Date creationDate, boolean closedAccount,
            // int employeeId, int clientId)
                
                handleAccount = new HandleAccount(rs.getInt("idhantering"), 
                        rs.getInt("kontoId"), 
                        rs.getInt("sattainsaldo"),
                        rs.getInt("tautsaldo"),
                        rs.getDouble("rantesats"),
                        skapa,
                        rs.getDate("date"),
                        avsluta,
                        rs.getInt("anstalldId"),
                        rs.getInt("kundId"));
                
                historyOfAccounts.add(handleAccount);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        handleAccount.setHistoryOfAccounts(historyOfAccounts);
        return historyOfAccounts;
    }
    
     
     
     public List<Employee> getAllEmployees(){
         List<Employee> employees= new ArrayList<>();
         return employees;
     }
     
     public void callCreateClient(int employeeID, int clientID){
         
     }
     
     public void callUpdateInfo(int clientID,int PIN,String name,String address,String telephont){
         
     }
     
     public void callDeleteClient(int employeeID, int clientID){
         
     }
     
     public void callAssignAccount(int employeeID, int clientID, int AccountNumber){
         
     }
     
     public void callEndAccount(int employeeID, int clientID, int accountID){
         
     }
     
     public void callDeposit(int employeeID, int clientID,int accountID, int amount){
         
     }
     
     public void callWithdraw(int employeeID, int clientID,int accountID, int amount){
         
     }
     
     public void callSetAccountRate(int employeeID, int clientID,int accountID, double rate){
         
     }
     
     public void callGrantLoan(int employeeID, int clientID,int loanID){
         
     }
     
     public void callSetLoanRate(int employeeID, int clientID,int loanID, double rate){
         
     }
     
//     public double callPayOffMonth(int loanID){
//         
//     }
//     
//     public double callVinstOfLoan(int loanID){
//         
//     }
}
