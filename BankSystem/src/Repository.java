

import bank_emp_model.Account;
import banksystem.model.Client;
import bank_emp_model.Employee;
import bank_emp_model.HandleAccount;
import bank_emp_model.HandleLoan;
import bank_emp_model.Loan;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Repository {
    
    private Properties p= new Properties();
    
    public Repository(){
        try {
            p.load(new FileInputStream("src/bankomatemployee/Settings.properties"));
//            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    // Kunden ska kunna logga in med personnummer och PIN
    public int getClientIdByPersonnummerAndPIN(int personnummer, int pin){

        String query = "select id from Kund where personnummer = ? and pin = ?";
        ResultSet rs;
        int id = -1;
        
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
            p.getProperty("name"), p.getProperty("password"));
            PreparedStatement stmt = con.prepareStatement(query);
                ){
            stmt.setInt(1, personnummer);
            stmt.setInt(2, pin);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                id = rs.getInt("id");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }  
        return id;
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
    
    
    public List<Employee> getAllEmployees(){
        Employee employee = new Employee();
        List<Employee> employees = new ArrayList();
        
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
            p.getProperty("name"), p.getProperty("password"));
            Statement stmt = con.createStatement();){
            
            ResultSet rs = stmt.executeQuery("select idAnstalld, number, "
                    + "name from Anstalld");
            
            while(rs.next()){
                employee = new Employee(rs.getInt("idAnstalld"), rs.getInt("number"), rs.getString("name"));
                employees.add(employee);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        employee.setEmployees(employees);
        return employees;
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
                
                account = new Account(rs.getInt("idKonto"), rs.getInt("number"),
                        rs.getInt("kundId"), rs.getInt("saldo"), 
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
    
    
    public List<HandleLoan> getAllHandleLoans(){
        HandleLoan handleLoan = new HandleLoan();
        List<HandleLoan> handleLoans = new ArrayList();
        boolean isGranted;
        
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
            p.getProperty("name"), p.getProperty("password"));
            Statement stmt = con.createStatement();){
            
            ResultSet rs = stmt.executeQuery("select idhanteraLan, lanId, "
                    + "bevilja, lanrantesats, betalplan, anstalldId, date from hanteraLan");
            
            while(rs.next()){

                if (rs.getInt("bevilja") == 1){
                    isGranted = true;
                } else
                    isGranted = false;
                
                handleLoan = new HandleLoan(rs.getInt("idhanteraLan"), rs.getInt("lanId"), isGranted, 
                        rs.getDouble("lanRantesats"), rs.getDate("betalplan"), rs.getInt("anstalldId"), 
                        rs.getDate("date"));
                handleLoans.add(handleLoan);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        handleLoan.setHandleLoans(handleLoans);
        return handleLoans;
    }
    
    
    // Call Stored Procedures
    
    
    // Lägga in nya kunder
    public void callCreateNewClient(int employeeID, int clientNumber, int clientPIN){
                       
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
            p.getProperty("name"), p.getProperty("password"));
            CallableStatement cstmt = con.prepareCall("CALL CreateNewClient(?,?,?)");
                ){

            cstmt.setInt(1, employeeID);
            cstmt.setInt(2, clientNumber);
            cstmt.setInt(3, clientPIN);
            ResultSet rs = cstmt.executeQuery();
            }
            
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        } 
     }  
     
    // Uppdatera kunduppgifter
     public void callUpdateInfo(int clientID, int PIN, String name, String address, String telephone){
         
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
            p.getProperty("name"), p.getProperty("password"));
            CallableStatement cstmt = con.prepareCall("CALL UpdateInfo(?,?,?,?,?)");
                ){

            cstmt.setInt(1, clientID);
            cstmt.setInt(2, PIN);
            cstmt.setString(3, name);
            cstmt.setString(4, address);
            cstmt.setString(5, telephone);
            ResultSet rs = cstmt.executeQuery();
            }
            
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        } 
     }
     
    // Radera kunder
    public void callDeleteClient(int employeeID, int clientID){

       try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
           p.getProperty("name"), p.getProperty("password"));
           CallableStatement cstmt = con.prepareCall("CALL DeleteClient(?,?)");
               ){

           cstmt.setInt(1, employeeID);
           cstmt.setInt(2, clientID);
           ResultSet rs = cstmt.executeQuery();
           }

       catch (SQLException ex) {
           ex.printStackTrace();
       }
       catch (Exception e){
           e.printStackTrace();
       } 

    }
     
    // Skapa konton åt en kund
    public void callAssignAccount(int employeeID, int clientID, int AccountNumber){
       
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
           p.getProperty("name"), p.getProperty("password"));
           CallableStatement cstmt = con.prepareCall("CALL AssignAccount(?,?,?)");
               ){

           cstmt.setInt(1, employeeID);
           cstmt.setInt(2, clientID);
           cstmt.setInt(3, AccountNumber);
           ResultSet rs = cstmt.executeQuery();
           }

       catch (SQLException ex) {
           ex.printStackTrace();
       }
       catch (Exception e){
           e.printStackTrace();
       } 
    }
     
    // Avsluta konto åt en kund
    public void callEndAccount(int employeeID, int clientID, int accountID){
        
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
           p.getProperty("name"), p.getProperty("password"));
           CallableStatement cstmt = con.prepareCall("CALL EndAccount(?,?,?)");
               ){

           cstmt.setInt(1, employeeID);
           cstmt.setInt(2, clientID);
           cstmt.setInt(3, accountID);
           ResultSet rs = cstmt.executeQuery();
           }

       catch (SQLException ex) {
           ex.printStackTrace();
       }
       catch (Exception e){
           e.printStackTrace();
       } 
    }
     
    // Sätta in pengar på ett kundkonto
    public void callDeposit(int employeeID, int clientID,int accountID, int amount){

        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
           p.getProperty("name"), p.getProperty("password"));
           CallableStatement cstmt = con.prepareCall("CALL Deposit(?,?,?,?)");
               ){

           cstmt.setInt(1, employeeID);
           cstmt.setInt(2, clientID);
           cstmt.setInt(3, accountID);
           cstmt.setInt(4, amount);
           ResultSet rs = cstmt.executeQuery();
           }

       catch (SQLException ex) {
           ex.printStackTrace();
       }
       catch (Exception e){
           e.printStackTrace();
       } 
    }

    // Ta ut pengar från ett kundkonto
    public void callWithdraw(int employeeID, int clientID,int accountID, int amount){

        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
           p.getProperty("name"), p.getProperty("password"));
           CallableStatement cstmt = con.prepareCall("CALL Withdraw(?,?,?,?)");
               ){

           cstmt.setInt(1, employeeID);
           cstmt.setInt(2, clientID);
           cstmt.setInt(3, accountID);
           cstmt.setInt(4, amount);
           ResultSet rs = cstmt.executeQuery();
           }

       catch (SQLException ex) {
           ex.printStackTrace();
       }
       catch (Exception e){
           e.printStackTrace();
       } 
    }
        
       
    // Ändra räntesats på en kunds konto
    public void callSetAccountRate(int employeeID, int clientID, int accountID, double rate){
        
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
           p.getProperty("name"), p.getProperty("password"));
           CallableStatement cstmt = con.prepareCall("CALL SetAccountRate(?,?,?,?)");
               ){

           cstmt.setInt(1, employeeID);
           cstmt.setInt(2, clientID);
           cstmt.setInt(3, accountID);
           cstmt.setDouble(4, rate);
           ResultSet rs = cstmt.executeQuery();
           }

       catch (SQLException ex) {
           ex.printStackTrace();
       }
       catch (Exception e){
           e.printStackTrace();
       } 
    }
        
    // Bevilja lån till en kund
    public void callGrantLoan(int employeeID, int clientID, int loanID){
        
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
           p.getProperty("name"), p.getProperty("password"));
           CallableStatement cstmt = con.prepareCall("CALL GrantLoan(?,?,?)");
               ){

           cstmt.setInt(1, employeeID);
           cstmt.setInt(2, clientID);
           cstmt.setInt(3, loanID);
           ResultSet rs = cstmt.executeQuery();
           }

       catch (SQLException ex) {
           ex.printStackTrace();
       }
       catch (Exception e){
           e.printStackTrace();
       } 
    }

    // Ändra räntesats på en kunds lån
    // `SetLoanRate`(in employeeID int, in clientID int, in loanID int, in rate decimal(3,1))
    public void callSetLoanRate(int employeeID, int clientID,int loanID, double rate){
        
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
           p.getProperty("name"), p.getProperty("password"));
           CallableStatement cstmt = con.prepareCall("CALL SetLoanRate(?,?,?,?)");
               ){

           cstmt.setInt(1, employeeID);
           cstmt.setInt(2, clientID);
           cstmt.setInt(3, loanID);
           cstmt.setDouble(4, rate);
           ResultSet rs = cstmt.executeQuery();
           }

       catch (SQLException ex) {
           ex.printStackTrace();
       }
       catch (Exception e){
           e.printStackTrace();
       } 
    }
    
    // Ändra betalplan på en kunds lån (den tid som det kommer att ta för kunden att betala lånet)
    // `ChangeLanPlan`(in employeeID int, in clientID int, in loanID int, in newPlan date)
    public void callChangeLanPlan(int employeeID, int clientID,int loanID, Date newPlan){
        
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
           p.getProperty("name"), p.getProperty("password"));
           CallableStatement cstmt = con.prepareCall("CALL ChangeLanPlan(?,?,?,?)");
               ){

           cstmt.setInt(1, employeeID);
           cstmt.setInt(2, clientID);
           cstmt.setInt(3, loanID);
           cstmt.setDate(4, newPlan);
           ResultSet rs = cstmt.executeQuery();
           }

       catch (SQLException ex) {
           ex.printStackTrace();
       }
       catch (Exception e){
           e.printStackTrace();
       } 
    }
    
    

 public double callPayOffMonth(int loanID) throws SQLException{
       ResultSet rs =null;
        String query="select PayOffMonth( ? ) as result; ";
        double result=0;
                       
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                             p.getProperty("name"),
                             p.getProperty("password"));
            PreparedStatement stmt = con.prepareStatement(query);){
            stmt.setInt(1, loanID);
           
            rs = stmt.executeQuery();
            while(rs.next()){
            result=rs.getDouble("result");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }  
        return result;
     }
     
     public double callVinstOfLoan(int loanID) throws SQLException{
        ResultSet rs =null;
        String query="select VinstofLoan( ? ) as result; ";
        double result=0;              
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                             p.getProperty("name"),
                             p.getProperty("password"));
            PreparedStatement stmt = con.prepareStatement(query);){
            stmt.setInt(1, loanID);
           
            rs = stmt.executeQuery();
            while(rs.next()){
            result=rs.getDouble("result");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }  
        return result;
     }
    
    
    
    
    
    
}
