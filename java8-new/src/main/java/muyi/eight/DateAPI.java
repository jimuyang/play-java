package muyi.eight;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.*;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/6 下午11:13
 * @description:
 */
public class DateAPI {

    void test() {
        long l = System.currentTimeMillis();

        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.toString()); //SystemClock[Asia/Shanghai]

        long millis = clock.millis();

        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);

        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());

        // ZoneRules[currentStandardOffset=+01:00]
        // ZoneRules[currentStandardOffset=-03:00]

        /* LocalTime */
        LocalTime now1 = LocalTime.now();
        LocalTime now2 = LocalTime.now(zone2);

        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);       // 23:59:59

//        DateTimeFormatter germanFormatter =
//                DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.CHINA);
//
//        System.out.println(LocalTime.parse("3:30pm"));

        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedTime(FormatStyle.SHORT)
                        .withLocale(Locale.GERMAN);

        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime);   // 13:37

        /* LocalDate */
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);

        System.out.println(yesterday.toString());
        System.out.println(yesterday.toEpochDay());
        System.out.println(yesterday.getMonth());
        System.out.println(yesterday.getMonthValue());
        System.out.println(yesterday.atStartOfDay().toString());
        System.out.println(yesterday.getEra().toString());
        System.out.println(yesterday.get(ChronoField.DAY_OF_MONTH));

        LocalDate nextMonthToday = today.plusMonths(1);
        System.out.println(nextMonthToday);

        /* 格式化日期和时间 */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd, yyyy - HH:mm");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mm");

        LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter1);
        String string = formatter.format(parsed);
        System.out.println(string);

//        LocalDateTime localDateTime = (LocalDateTime)formatter1.parse("Nov 03, 2014 - 07:13");
//        System.out.println(localDateTime);
    }

    void someExamples() {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        LocalDateTime nowDateTime = LocalDateTime.now();

        print(nowDate);
        print(nowTime);
        print(nowDateTime);

        LocalDate localDate = LocalDate.of(2019, 1, 6);
        print(localDate);
        localDate = LocalDate.parse("2016-02-14");
        print(localDate);

        print(nowDate.with(TemporalAdjusters.firstDayOfMonth()));
        print(nowDate.withDayOfMonth(2));
        print(nowDate.with(TemporalAdjusters.lastDayOfMonth()));

        print(LocalDate.parse("2017-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY))); // 2017-01-02

    }

    void convertDate() {
        LocalDateTime now = LocalDateTime.now();
        print(now);
        Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        print(date);

        Date date1 = new Date();
        print(date1);
        LocalDateTime localDateTime = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        print(localDateTime);
    }

    void print(Object o) {
        System.out.println(o.toString());
    }

}
