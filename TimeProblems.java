import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeProblems {
    
    public static int countFridayThirteens(LocalDate startDate, LocalDate endDate){
       int friThirteens = 0;
       
       for(LocalDate ldt = startDate; !ldt.isAfter(endDate) ;ldt = ldt.plusDays(1)){           
           if( (ldt.getDayOfWeek() == DayOfWeek.FRIDAY) && ( ldt.getDayOfMonth() == 13)){               
               friThirteens++;
           }  
       }
       return friThirteens;
   }
   
   public static String dayAfterSeconds(LocalDateTime timeHere, long seconds){
       LocalDateTime futureDateTime = timeHere.plusSeconds(seconds);  
       String getDayOfWeek = futureDateTime.getDayOfWeek().name();       
       return getDayOfWeek ;
   }
   
   public static int whatHourIsItThere(LocalDateTime timeHere, String here, String there){       
       ZonedDateTime zdtHere = timeHere.atZone(ZoneId.of(here));       
       ZonedDateTime zdtThere = zdtHere.withZoneSameInstant(ZoneId.of(there));       
       return zdtThere.getHour();
   }
   
}