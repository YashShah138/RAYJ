package com.nighthawk.spring_portfolio.mvc.calendar;

// Prototype Implementation

public class APCalendar {

    /** Returns true if year is a leap year and false otherwise.
     * isLeapYear(2019) returns False
     * isLeapYear(2016) returns True
     */          
    public static boolean isLeapYear(int year) {
        // implementation not shown
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            return true;
        }
        return false;
        }
        
    /** Returns the value representing the day of the week 
     * 0 denotes Sunday, 
     * 1 denotes Monday, ..., 
     * 6 denotes Saturday. 
     * firstDayOfYear(2019) returns 2 for Tuesday.
    */
    public static int firstDayOfYear(int year) {
        // implementation not shown
        if (year > 1752) {
            return (year + numberOfLeapYears(1, year - 1))%7;
        }
        return (year + (year-1)/4 + 5)%7;
        }


    /** Returns n, where month, day, and year specify the nth day of the year.
     * This method accounts for whether year is a leap year. 
     * dayOfYear(1, 1, 2019) return 1
     * dayOfYear(3, 1, 2017) returns 60, since 2017 is not a leap year
     * dayOfYear(3, 1, 2016) returns 61, since 2016 is a leap year. 
    */ 
    public static int dayOfYear(int month, int day, int year) {
        // implementation not shown
        int count = 0;
        if (month < 8) {
            count = 31*((month)/2) + 30*((month-1)/2) + day;
        } else {
            count = 214 + 31*((month-7)/2) + 30*((month-8)/2) + day;
        }
        if (month > 2) {
            if (!isLeapYear(year)) {
                count = count - 2;
            } else {
                count--;
            }
        }
        return count;
        }

    /** Returns the number of leap years between year1 and year2, inclusive.
     * Precondition: 0 <= year1 <= year2
    */ 
    public static int numberOfLeapYears(int year1, int year2) {
         // to be implemented in part (a)
        int count = 0;
        for (int i = year1; i <= year2; i++) {
            if (isLeapYear(i)) {
                count++;
            }
        }
        return count;
        }

    /** Returns the value representing the day of the week for the given date
     * Precondition: The date represented by month, day, year is a valid date.
    */
    public static int dayOfWeek(int month, int day, int year) { 
        // to be implemented in part (b)
        return (firstDayOfYear(year) + dayOfYear(month, day, year) - 1) % 7;
        }

    /** Tester method */
    public static void main(String[] args) {
        // Private access modifiers
        System.out.println("numberOfLeapYears 2000-2022: " + APCalendar.numberOfLeapYears(2000, 2022));
        System.out.println("firstDayOfYear 1700 (1): " + APCalendar.firstDayOfYear(1700));
        System.out.println("firstDayOfYear 1701 (3): " + APCalendar.firstDayOfYear(1701));
        System.out.println("firstDayOfYear 1752 (3): " + APCalendar.firstDayOfYear(1752));
        System.out.println("firstDayOfYear 1753 (1): " + APCalendar.firstDayOfYear(1753));
        System.out.println("firstDayOfYear 1800 (3): " + APCalendar.firstDayOfYear(1800));
        System.out.println("firstDayOfYear 1801 (4): " + APCalendar.firstDayOfYear(1801));
        System.out.println("firstDayOfYear 2019 (2): " + APCalendar.firstDayOfYear(2019));
        System.out.println("dayOfYear 3/1/2017: " + APCalendar.dayOfYear(3, 1, 2017));
        System.out.println("dayOfYear 3/1/2016: " + APCalendar.dayOfYear(3, 1, 2016));

        // Public access modifiers
        System.out.println("isLeapYear 1600: " + APCalendar.isLeapYear(1600));
        System.out.println("isLeapYear 1800: " + APCalendar.isLeapYear(1800));
        System.out.println("isLeapYear 2019: " + APCalendar.isLeapYear(2019));
        System.out.println("isLeapYear 2024: " + APCalendar.isLeapYear(2024));
        System.out.println("numberOfLeapYears 2000-2022: " + APCalendar.numberOfLeapYears(2000, 2022));
        System.out.println("dayOfWeek 7/5/2022: " + APCalendar.dayOfWeek(7, 5, 2022));
        System.out.println("dayOfWeek 8/5/2022: " + APCalendar.dayOfWeek(8, 5, 2022));
        System.out.println("dayOfWeek 11/18/2022: " + APCalendar.dayOfWeek(11, 18, 2022));
    }

}