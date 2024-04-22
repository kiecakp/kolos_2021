import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class zad8_strumien {
    public LocalDate date;
    public int deaths;
    public int cases;

    public int sumDeaths(List<zad8_strumien> list){
        int sum = 0;
        for(zad8_strumien statistic : list){
                sum += statistic.deaths;
        }
        return sum;
    }
    public static List<zad8_strumien> sortByDeaths(List<zad8_strumien> list){
        List<zad8_strumien> changed = list.stream()
                .sorted(Comparator.comparingInt((zad8_strumien obj) -> obj.deaths).reversed())
                .collect(Collectors.toList());

        return changed;
    }
}
