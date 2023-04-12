import java.util.Scanner;
public class InvoiceAddress
{
    private String street;
    private String city;
    private String state;
    private String zip;
    Scanner in;

    public InvoiceAddress()
    {
        in = new Scanner(System.in);
    }

    public InvoiceAddress(String street, String city, String state, String zip)
    {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }


    public String getStreet()
    {
        return street;
    }
    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }
    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getZip()
    {
        return zip;
    }
    public void setZip(String zip)
    {
        this.zip = zip;
    }


    public void getAddress()
    {
        setStreet(SafeInput.getNonZeroLenString(in, "Provide your Street name: "));
        setCity(SafeInput.getNonZeroLenString(in, "Provide City name: "));
        setState(SafeInput.getNonZeroLenString(in,"Provide State: "));
        setZip(SafeInput.getRegExString(in, "Provide Your Zip Code: ",  "\\d{5}"));
    }

    @Override
    public String toString()
    {
        return "Address{" +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", in=" + in +
                '}';
    }
}