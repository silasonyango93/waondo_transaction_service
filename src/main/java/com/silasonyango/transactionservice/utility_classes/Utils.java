package com.silasonyango.transactionservice.utility_classes;

import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static String toCamelCase(String str) {

        if (str == null) {
            return null;
        }

        boolean space = true;
        StringBuilder builder = new StringBuilder(str);
        final int len = builder.length();

        for (int i = 0; i < len; ++i) {
            char c = builder.charAt(i);
            if (space) {
                if (!Character.isWhitespace(c)) {
                    builder.setCharAt(i, Character.toTitleCase(c));
                    space = false;
                }
            } else if (Character.isWhitespace(c)) {
                space = true;
            } else {
                builder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }

    public static String formatToCommaSeperatedValue(double amount) {
        if(amount == 0){
            return "00.00";
        }else {
            DecimalFormat formatter = new DecimalFormat("#,###.00");
            return formatter.format(amount);
        }


    }

    public static String formatMonetaryValue(Number value, String currency) {
        if (currency != null && !currency.trim().isEmpty()) {
            return String.format("%s %,.2f", currency, value);
        }

        return String.format("%,.2f", value);
    }

    public static String convertToUserFriendlyDate(String yourDateString,String yourDateFormat) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(yourDateFormat);
        DateFormat stringDateFormat = new SimpleDateFormat("E, MMM dd yyyy");
        try {
            date = formatter.parse(yourDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return stringDateFormat.format(date);
    }
    public static String convertToUserFriendlyDateWithTime(String yourDateString,String yourDateFormat) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(yourDateFormat);
        DateFormat stringDateFormat = new SimpleDateFormat("E, MMM dd yyyy HH:mm");
        try {
            date = formatter.parse(yourDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return stringDateFormat.format(date);
    }

    public static String formatAccountNumber(String accountNumber) {
        return accountNumber != null && !accountNumber.equals("") ? accountNumber.replaceAll("....(?!$)", "$0 ") : "";
    }




    public static String addCommaSeperatorsToAmountAttachedToCurrency(String amountAttachedToCurrency) {
        String processedAmount = "";

        if(amountAttachedToCurrency != null && !amountAttachedToCurrency.equals("") && !amountAttachedToCurrency.matches("[0-9]{3,}")) {
            String[] splitParts = amountAttachedToCurrency.split("(?<=\\D)(?=\\d)");
            processedAmount = splitParts[0] +" "+ formatToCommaSeperatedValue(Double.parseDouble(splitParts[1]));
        }
        return processedAmount;
    }

    public static boolean checkTwoDatesAreSimilar(Date firstDate, Date secondDate){
        return DateUtils.isSameDay(firstDate, secondDate);
    }



    public static boolean isToday(Date date) {
        Date dateToday = null;
        Calendar calendar = Calendar.getInstance();
        dateToday = calendar.getTime();
        return checkTwoDatesAreSimilar(dateToday, date);
    }


    public static boolean isYesterday(Date comparisonDate) {
        Date yesterday = null;
        Calendar today = Calendar.getInstance();
        today.add(Calendar.HOUR_OF_DAY, -24);
        yesterday = today.getTime();
        if (checkTwoDatesAreSimilar(yesterday,comparisonDate)) {
            return true;
        }
        return false;
    }

    public static String getToday() {
        DateFormat stringDateFormat = new SimpleDateFormat("E, MMM dd yyyy");
        Date currentTime = Calendar.getInstance().getTime();
        return stringDateFormat.format(currentTime);
    }

    public static String getStringRelativeTimeSpan(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return extractStringRelativeTimeSpan(String.valueOf(calendar.getTimeInMillis()));
    }

    public static String extractStringRelativeTimeSpan(String timeAtMiliseconds) {
        if (timeAtMiliseconds.equalsIgnoreCase("")) {
            return "";
        }
        //API.log("Day Ago "+dayago);
        String result = "now";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String todayDate = formatter.format(new Date());
        Calendar calendar = Calendar.getInstance();

        long dayagolong = Long.valueOf(timeAtMiliseconds) * 1000;
        calendar.setTimeInMillis(dayagolong);
        String agoformater = formatter.format(calendar.getTime());

        Date CurrentDate = null;
        Date CreateDate = null;

        try {
            CurrentDate = formatter.parse(todayDate);
            CreateDate = formatter.parse(agoformater);

            long different = Math.abs(CurrentDate.getTime() - CreateDate.getTime());

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;

            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;

            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;

            different = different % secondsInMilli;
            if (elapsedDays == 0) {
                if (elapsedHours == 0) {
                    if (elapsedMinutes == 0) {
                        if (elapsedSeconds < 0) {
                            return "0" + " s";
                        } else {
                            if (elapsedDays > 0 && elapsedSeconds < 59) {
                                return "now";
                            }
                        }
                    } else {
                        return String.valueOf(elapsedMinutes) + "m ago";
                    }
                } else {
                    return String.valueOf(elapsedHours) + "h ago";
                }

            } else {
                if (elapsedDays <= 29) {
                    return String.valueOf(elapsedDays) + "d ago";
                }
                if (elapsedDays > 29 && elapsedDays <= 58) {
                    return "1Mth ago";
                }
                if (elapsedDays > 58 && elapsedDays <= 87) {
                    return "2Mth ago";
                }
                if (elapsedDays > 87 && elapsedDays <= 116) {
                    return "3Mth ago";
                }
                if (elapsedDays > 116 && elapsedDays <= 145) {
                    return "4Mth ago";
                }
                if (elapsedDays > 145 && elapsedDays <= 174) {
                    return "5Mth ago";
                }
                if (elapsedDays > 174 && elapsedDays <= 203) {
                    return "6Mth ago";
                }
                if (elapsedDays > 203 && elapsedDays <= 232) {
                    return "7Mth ago";
                }
                if (elapsedDays > 232 && elapsedDays <= 261) {
                    return "8Mth ago";
                }
                if (elapsedDays > 261 && elapsedDays <= 290) {
                    return "9Mth ago";
                }
                if (elapsedDays > 290 && elapsedDays <= 319) {
                    return "10Mth ago";
                }
                if (elapsedDays > 319 && elapsedDays <= 348) {
                    return "11Mth ago";
                }
                if (elapsedDays > 348 && elapsedDays <= 360) {
                    return "12Mth ago";
                }

                if (elapsedDays > 360 && elapsedDays <= 720) {
                    return "1 year ago";
                }

                if (elapsedDays > 720) {
                    SimpleDateFormat formatterYear = new SimpleDateFormat("MM/dd/yyyy");
                    Calendar calendarYear = Calendar.getInstance();
                    calendarYear.setTimeInMillis(dayagolong);
                    return formatterYear.format(calendarYear.getTime()) + "";
                }

            }

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String formatToUsableDateFormat(String dateString) {
        return dateString.replace("T", " ").substring(0, 10);
    }
}
