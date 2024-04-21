import java.time.LocalDate;
import java.util.List;

public class CountryWithProvinces extends Country{
    public CountryWithProvinces(String name, List<Country> list) {
        super(name);
        this.countryList = list;
    }

    private List<Country> countryList;

    @Override
    public Integer getConfirmedCases(LocalDate date) {
        return null;
    }

    @Override
    public Integer getDeaths(LocalDate date) {
        return null;
    }
}
