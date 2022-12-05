package com.nighthawk.spring_portfolio.mvc.calendar;

/** Simple POJO 
 * Used to Interface with APCalendar
 * The toString method(s) prepares object for JSON serialization
 * Note... this is NOT an entity, just an abstraction
 */
class Year {
   private int year;
   private boolean isLeapYear;

   private int year4;
   private int firstDayOfYear;

   private int year1;
   private int year2;
   private int numberOfLeapYears;

   private int month;
   private int day;
   private int year3;
   private int dayOfWeek;

   private int month1;
   private int day1;
   private int year5;
   private int dayOfYear;

   // zero argument constructor
   public Year() {} 

   /* year getter/setters */
   public int getYear() {
      return year;
   }
   public void setYear(int year) {
      this.year = year;
      this.setIsLeapYear(year);
   }


   /* year getter/setters */
   public int getYear4() {
      return year4;
   }
   public void setFirst(int year) {
      this.year4 = year;
      this.setFirstDayOfYear(year);
   }

   
   /* year getter/setters */
   public int getYear1() {
      return year1;
   }
   public int getYear2() {
      return year2;
   }
   public void setNum(int year1, int year2) {
      this.year1 = year1;
      this.year2 = year2;
      this.setNumberOfLeapYears(year1, year2);
   }

   /* year getter/setters */
   public int getMonth() {
      return month;
   }
   public int getDay() {
      return day;
   }
   public int getYear3() {
      return year3;
   }
   public void setWeek(int month, int day, int year) {
      this.month = month;
      this.day = day;
      this.year3 = year;
      this.setDayOfWeek(month, day, year);
   }

   public int getMonth1() {
      return month1;
   }
   public int getDay1() {
      return day1;
   }
   public int getYear5() {
      return year5;
   }
   public void setDay(int month, int day, int year) {
      this.month1 = month;
      this.day1 = day;
      this.year5 = year;
      this.setDayOfYear(month, day, year);
   }   

   /* isLeapYear getter/setters */
   public boolean getIsLeapYear(int year) {
      return APCalendar.isLeapYear(year);
   }
   private void setIsLeapYear(int year) {  // this is private to avoid tampering
      this.isLeapYear = APCalendar.isLeapYear(year);
   }

   /* isLeapYearToString formatted to be mapped to JSON */
   public String isLeapYearToString(){
      return ( "{ \"year\": "  +this.year+  ", " + "\"isLeapYear\": "  +this.isLeapYear+ " }" );
   }	

   /* isLeapYear getter/setters */
   public int getFirstDayOfYear(int year) {
      return APCalendar.firstDayOfYear(year);
   }
   private void setFirstDayOfYear(int year) {  // this is private to avoid tampering
      this.firstDayOfYear = APCalendar.firstDayOfYear(year);
   }

   /* isLeapYearToString formatted to be mapped to JSON */
   public String firstDayOfYearToString(){
      return ( "{ \"year\": "  +this.year4+  ", " + "\"firstDayOfYear\": "  +this.firstDayOfYear+ " }" );
   }	

   /* isLeapYear getter/setters */
   public int getNumberOfLeapYears(int year1, int year2) {
      return APCalendar.numberOfLeapYears(year1, year2);
   }
   private void setNumberOfLeapYears(int year1, int year2) {  // this is private to avoid tampering
      this.numberOfLeapYears = APCalendar.numberOfLeapYears(year1, year2);
   }

   /* isLeapYearToString formatted to be mapped to JSON */
   public String numberOfLeapYearsToString(){
      return ( "{ \"year1\": "  +this.year1+  ", " + "\"year2\": "  +this.year2+  ", " + "\"numberOfLeapYears\": "  +this.numberOfLeapYears+ " }" );
      }	

   /* isLeapYear getter/setters */
   public int getDayOfWeek(int month, int day, int year) {
      return APCalendar.dayOfWeek(month, day, year);
   }
   private void setDayOfWeek(int month, int day, int year) {  // this is private to avoid tampering
      this.dayOfWeek = APCalendar.dayOfWeek(month, day, year);
   }

   /* isLeapYearToString formatted to be mapped to JSON */
   public String dayOfWeekToString(){
      return ( "{ \"month\": "  +this.month+  ", " + "\"day\": "  +this.day+  ", " + "\"year\": "  +this.year3+  ", " + "\"dayOfWeek\": "  +this.dayOfWeek+ " }" );
   }

   public int getDayOfYear(int month, int day, int year) {
      return APCalendar.dayOfYear(month, day, year);
   }
   private void setDayOfYear(int month, int day, int year) {  // this is private to avoid tampering
      this.dayOfYear = APCalendar.dayOfYear(month, day, year);
   }

   /* isLeapYearToString formatted to be mapped to JSON */
   public String dayOfYearToString(){
      return ( "{ \"month\": "  +this.month1+  ", " + "\"day\": "  +this.day1+  ", " + "\"year\": "  +this.year5+  ", " + "\"dayOfYear\": "  +this.dayOfYear+ " }" );
   }	

   /* standard toString placeholder until class is extended */
   public String toString() { 
      return isLeapYearToString(); 
   }

   public static void main(String[] args) {
      Year year = new Year();
      year.setYear(2020);
      System.out.println(year);
   }
}