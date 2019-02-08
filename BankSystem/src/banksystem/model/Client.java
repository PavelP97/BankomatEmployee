

package banksystem.model;

import java.util.List;

public class Client {
    
    private int id;
    private int personnumber;
    private int PIN;
    private String name;
    private String address;
    private String telephone;
    private static List<Client> clients;
    
    
    public Client (int id, int personnumber, int PIN, String name, String address, String telephone){
        this.id = id;
        this.personnumber = personnumber;
        this.PIN = PIN;
        this.name = name;
        this.address = address;
        this.telephone = telephone;   
    }
    
    public Client(int personnumber, int PIN) {
        this.personnumber = personnumber;
        this.PIN = PIN;
    }
    
    public Client (){}
    
    public Client(int id){
        this.id=id;
    }
    
    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public int getPersonnumber() {
        return personnumber;
    }

    public void setPersonnumber(int personnumber) {
        this.personnumber = personnumber;
    }
   
    public String ClientInfo() {
        return  "personnumber=" + personnumber + "\n"+"PIN="+PIN+"\n"+ "name=" + name + "\n"+ "address=" + address + "\n" +"telephone=" + telephone ;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> aClients) {
        clients = aClients;
    }

    public void printClients (){
        for (Client c : clients){
            System.out.println(c.getId() + ", " + c.getName());
        }
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

    
}
