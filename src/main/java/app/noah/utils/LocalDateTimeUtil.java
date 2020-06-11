package app.noah.utils;


import java.time.*;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {


    static final String DEFAULT_YMD_PATTERN = "yyyy-MM-dd";

    static final String DEFAULT_YMD_PATTERN2 = "MM-dd";

    static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    static final String DEFAULT_TIME_PATTERN2 = "HH:mm";

    static final String TIME_START = "00:00:00";

    static final String TIME_END = "23:59:59";


    static final String JOINING_DATE_TIME_PATTERN = "yyyyMMddHHmmss";

    static final String JOINING_DATE_TIME_MILLIS_PATTERN = "yyyyMMddHHmmss.SSS";

    public static LocalDateTime getYMDtoLocalDateTime(String ymdStr) {

        return getYMDtoLocalDateTime(ymdStr, DEFAULT_YMD_PATTERN);
    }

    public static LocalDateTime getYMDtoLocalDateTime(String ymdStr, String ymdPattern) {

        return getYMDtoLocalDateTime(ymdStr, ymdPattern, true);
    }

    public static LocalDateTime getYMDtoLocalDateTime(String ymdStr, String ymdPattern, boolean isStart) {

        if (ymdStr == null || ymdStr.length() == 0) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ymdPattern + DEFAULT_TIME_PATTERN);
        String timeStr = ymdStr + (isStart ? TIME_START : TIME_END);
        return LocalDateTime.parse(timeStr, formatter);
    }

    public static LocalDateTime getYMDtoLocalDateTimeStart(String ymdStr) {

        return getYMDtoLocalDateTime(ymdStr, DEFAULT_YMD_PATTERN, true);
    }

    public static LocalDateTime getYMDtoLocalDateTimeEnd(String ymdStr) {

        return getYMDtoLocalDateTime(ymdStr, DEFAULT_YMD_PATTERN, false);
    }

    public static LocalDateTime getYMDTimeToLocalDateTime(String dateTimeStr, String dateTimePattern) {

        if (dateTimeStr == null || dateTimeStr.length() == 0) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    public static LocalDateTime getYMDTimeToLocalDateTime(String dateTimeStr) {

        if (dateTimeStr == null || dateTimeStr.length() == 0) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_YMD_PATTERN + " " + DEFAULT_TIME_PATTERN);
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    public static LocalDateTime getYMDtoLocalDateTimeWithCurrentTime(String ymdStr) {

        LocalDate localDate = LocalDate.parse(ymdStr, DateTimeFormatter.ofPattern(DEFAULT_YMD_PATTERN));

        LocalTime localTime = LocalTime.now();

        return LocalDateTime.of(localDate, localTime);
    }

    public static String getLocalDateTimeToYmd(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_YMD_PATTERN);
        return dateTime.format(formatter);
    }

    /**
     * yyyy-MM-dd HH:mm:ss 패턴 출력
     */
    public static String getLocalDateTimeToYmdTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_YMD_PATTERN + " " + DEFAULT_TIME_PATTERN);
        return dateTime.format(formatter);
    }

    /**
     * yyyy-MM-dd HH:mm 패턴 출력
     */
    public static String getLocalDateTimeToYmdTime2(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_YMD_PATTERN + " " + DEFAULT_TIME_PATTERN2);
        return dateTime.format(formatter);
    }

    /**
     * MM-dd HH:mm 패턴 출력
     */
    public static String getLocalDateTimeToYmdTime3(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_YMD_PATTERN2 + " " + DEFAULT_TIME_PATTERN2);
        return dateTime.format(formatter);
    }


    /**
     * 파일명노출용 패턴 출력
     */
    public static String getLocalDateTimeForFileName(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(JOINING_DATE_TIME_PATTERN);
        return dateTime.format(formatter);
    }

    /**
     * 변경 체크용 시간 문자열 생성 millis
     */
    public static String getLocalDateTimeForCheckKey(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(JOINING_DATE_TIME_MILLIS_PATTERN);
        return dateTime.format(formatter);
    }


    public static LocalDateTime unixToLocalDateTime(long unixTimestamp) {
        return LocalDateTime.ofInstant(
                Instant.ofEpochSecond(unixTimestamp)
                , ZoneId.systemDefault()
        );
    }
}