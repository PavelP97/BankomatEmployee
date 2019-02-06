/*
 * 
 */

package bankomatemployee;


public class BankomatEmployee {
    public double totalmoneyloaned;
    public double rantesats;
    public double tiden;
    public double pengarslutvarde;
    public double revenue;
    public double payperyear;
    public double paypermonth;
    
    public BankomatEmployee(double a,double b,double c){
        this.totalmoneyloaned = a;
        this.rantesats = b;
        this.tiden = c;
    }
    public void Calculatebenefit(){
        pengarslutvarde = (Math.pow((rantesats/100+1), tiden)*totalmoneyloaned); // calculates final amount of money from loan after x years
        revenue = pengarslutvarde - totalmoneyloaned;                      // calculates the bank's profit alltså hur mycket tjänar på ett lån totalt
        System.out.println(Math.round(pengarslutvarde)); //Pengar som finns hos banken till slutet
        System.out.println(Math.round(revenue)); //Pengar som räknas som vinnst hos banken
    }
    public void Calculatemanadsbetalning(){
        payperyear=(pengarslutvarde*(rantesats/100))/((Math.pow((rantesats/100+1), tiden))-1);
        paypermonth=payperyear/12;
        System.out.println(Math.round(payperyear)); //hur mycket en kund ska betala av på ett lån per ÅR med nuvarande betalplan och räntesats
        System.out.println(Math.round(paypermonth));//hur mycket en kund ska betala av på ett lån per MÅNAD med nuvarande betalplan och räntesats
    }
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BankomatEmployee account1 = new BankomatEmployee(1800000,3,20); // en example of customer who loaned 1800000 sek from bank with räntesats 3% over 20 years
        
        account1.Calculatebenefit();
        
        account1.Calculatemanadsbetalning();
        
        
        
    }

}