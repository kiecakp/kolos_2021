import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

abstract class Country {
    private final String name;
    static private String deathsPath;
    static private String casesPath;

    public Country(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public static void setFiles(String deathsPath, String casesPath) throws FileNotFoundException {
        try {
            FileReader readerDeaths = new FileReader(deathsPath);
            FileReader readerCases = new FileReader(casesPath);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File cannot be read: " + e.getMessage());
        }
        Country.deathsPath = deathsPath;
        Country.casesPath = casesPath;
    }
//    public static Country fromCsv(String countryName){
//        try {
//            FileReader fileDeaths = new FileReader(deathsPath);
//            FileReader fileCases = new FileReader(casesPath);
//            BufferedReader readerDeaths = new BufferedReader(fileDeaths);
//            BufferedReader readerCases = new BufferedReader(fileCases);
//
//            try{
//                String lineDeaths = readerDeaths.readLine();
//                String lineCases = readerCases.readLine();
//                Country.checkCountry(lineDeaths, countryName);
//
//                CountryColumns deaths = getCountryColumns(lineDeaths, countryName);
//                CountryColumns cases = getCountryColumns(lineCases, countryName);
//                List<Country> countries = new ArrayList<>();
//
//                if(deaths.columnCount != 1 || cases.columnCount != 1){
//                    CountryWithProvinces countryWith = new CountryWithProvinces(countryName, countries);
//                    while (lineDeaths != null){
//                        // ???????????????????????????????
//                    }
//                } else {
//                    CountryWithoutProvinces countryWithout = new CountryWithoutProvinces(countryName);
//                    while (lineDeaths != null){
//                        String[] splited = lineDeaths.split(";");
//
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
//                        LocalDate date = LocalDate.parse(splited[0], formatter);
//
//                        countryWithout.addDailyStatistic(date, Integer.parseInt(splited[cases.firstColumnIndex]),
//                                Integer.parseInt(splited[deaths.firstColumnIndex]));
//                    }
//                }
//
//            } catch (CountryNotFoundException e){
//                System.out.println(e.getMessage());
//            }
//
//
//            fileDeaths.close();
//            fileCases.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return 0/* .... */;
//    }

    public static Country fromCsv(String name) {
        try (BufferedReader bf = new BufferedReader(new FileReader(deathsPath.toFile()))) {
            // Check if province and find columns
            String line;
            boolean hasProvincess = true;
            line = bf.readLine();
            CountryColumns cc = getCountryColumns(line, name); // <-- tutaj zacząc try
            if(cc.columnCount == 0) {
                hasProvincess = false;
            }
            // For provinces
            line = bf.readLine();
            if(hasProvincess) {
                List<Country> provinces = new ArrayList<>();
                String[] split = line.split(";");
                for(int i = cc.firstColumnIndex; i < cc.firstColumnIndex + cc.columnCount; i++) {
                    CountryWithoutProvinces temp = CountryWithoutProvinces.loadInfoFromFile(deathsPath.toString(), i, split[i]);
                    provinces.add(temp);
                }
                return new CountryWithProvinces(name, provinces.toArray(new Country[0]));
            } else {
                CountryWithoutProvinces result = CountryWithoutProvinces.loadInfoFromFile(deathsPath.toString(), cc.firstColumnIndex, name);
                return result;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CountryNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Country[] fromCsv(String[] country){
        List<Country> countries = new ArrayList<>();
        for(String countryName : country){
            try{
                Country country1 = fromCsv(countryName);
                countries.add(country1);
            } catch (CountryNotFoundException e){
                System.out.println(e.getMessage());
            }
        }

        return countries.toArray(new Country[0]);
    }

    private static class CountryColumns{
        public final int firstColumnIndex;
        private final int  columnCount;

        private CountryColumns(int firstColumnIndex, int columnCount) {
            this.firstColumnIndex = firstColumnIndex;
            this.columnCount = columnCount;
        }
    }

    private static CountryColumns getCountryColumns(String line, String country){
        Integer firstColumIndex = null;
        int columnCount = 0, index = 0;
        String[] splited = line.split(";");
        for(String str : splited){
            if(str.equals(country)){
                columnCount++;

                if(firstColumIndex == null){
                    firstColumIndex = index;
                }
            }
            index++;
        }
        return new CountryColumns(firstColumIndex, columnCount);
    }
    public static void checkCountry(String line, String country) throws CountryNotFoundException {
        CountryColumns countryColumns = getCountryColumns(line, country);
        if(countryColumns.columnCount == 0){
            throw new CountryNotFoundException(country);
        }
    }
    // czysto wirtualne metody (zaimplementowane w klasach podrzednych)
    public abstract Integer getConfirmedCases(LocalDate date);
    public abstract Integer getDeaths(LocalDate date);

    public static List<Country> sortByDeaths(List<Country> list, LocalDate start, LocalDate end){
        List<Country> changed = list.stream()
                .filter( -> )

        return changed;
    }
}