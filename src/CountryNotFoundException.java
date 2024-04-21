public class CountryNotFoundException extends Exception{
    public CountryNotFoundException(String country){
        super("Nie znaleziono panstwa: " + country);
    }
}
