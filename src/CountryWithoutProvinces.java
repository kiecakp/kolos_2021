import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CountryWithoutProvinces extends Country{
    public List<DailyStatistic> dailyStatistics;
    public CountryWithoutProvinces(String name) {
        super(name);
        this.dailyStatistics = new ArrayList<>();
    }

    @Override
    public Integer getConfirmedCases(LocalDate date) {
        Integer result = null;
        for(DailyStatistic statistic : dailyStatistics){
            if(statistic.date.equals(date)){
                result = statistic.getCases();
            }
        }
        return result;
    }

    @Override
    public Integer getDeaths(LocalDate date) {
        Integer result = null;
        for(DailyStatistic statistic : dailyStatistics){
            if(statistic.date.equals(date)){
                result = statistic.getDeaths();
            }
        }
        return result;
    }

    public void addDailyStatistic(LocalDate date, int cases, int deaths){
        DailyStatistic newStatistic = new DailyStatistic(date, cases, deaths);
        dailyStatistics.add(newStatistic);
    }

    public static class DailyStatistic{
        private LocalDate date;
        private int cases;
        private int deaths;

        public DailyStatistic(LocalDate date, int cases, int deaths) {
            this.date = date;
            this.cases = cases;
            this.deaths = deaths;
        }
        public LocalDate getDate() {
            return date;
        }
        public int getCases() {
            return cases;
        }
        public int getDeaths() {
            return deaths;
        }
    }
}
