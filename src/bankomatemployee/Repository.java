package bankomatemployee;


import bank_emp_model.Account;
import bank_emp_model.Client;
import bank_emp_model.Employee;
import bank_emp_model.HandleAccount;
import bank_emp_model.Loan;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Repository {
    
    private Properties p= new Properties();
    
    public Repository(){
        try {
            p.load(new FileInputStream("src/bankomat/Settings.properties"));
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
    
    
    
    
    
}
