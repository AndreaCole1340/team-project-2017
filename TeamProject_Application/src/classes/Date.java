package classes;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Date {
    // Properties

    private int day;
    private int month;
    private int year;

    // Methods
    // Constructors
    public Date() {
        day = month = year = 1;
    }

    public Date(int day, int month, int year) throws IllegalArgumentException {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    // Getters and Setters
    public int getDay() {
        return day;
    }

    // Set day with error checking included
    public void setDay(int day) throws IllegalArgumentException {

        IllegalArgumentException error = new IllegalArgumentException("Invalid Day");

        if (day < 1 || day > 31) {
            throw error;
        }
        // Check leap year
        boolean leapYear = isLeapYear();
        // Check month to set suitable day
        switch (this.month) {
            case 2:
                if (leapYear && day < 30) {
                    this.day = day;
                } else if (!leapYear && day < 29) {
                    this.day = day;
                } else {
                    throw error;
                }
                break;

            case 4:
            case 6:
            case 9:
            case 11:
                if (day < 31) {
                    this.day = day;
                } else {
                    throw error;
                }
                break;

            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (day < 32) {
                    this.day = day;
                } else {
                    throw error;
                }
                break;

            default:
                this.day = 1;
        }

    }

    public int getMonth() {
        return month;
    }

    // Set month with error checking
    public void setMonth(int month) throws IllegalArgumentException {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid Month, Please enter a value between 1 and 12");
        }

        this.month = month;
    }

    public int getYear() {
        return year;
    }

    // Set year with error checking
    public void setYear(int year) throws IllegalArgumentException {
        if (year < 1900) {
            throw new IllegalArgumentException("Invalid Year, Please enter a year greater than 1900");
        }

        this.year = year;
    }

    // toString and equals
    @Override
    public String toString() {
        return day + " / " + month + " / " + year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && o instanceof Date) {
            Date d = (Date) o;
            return this.day == d.getDay() && this.month == d.getMonth()
                    && this.year == d.getYear();
        }

        return false;
    }

    // Take user input
    public void read() {
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        boolean valid = false;
        do {
            try {
                System.out.println("\tENTER DATE");
                System.out.print("ENTER YEAR: ");
                setYear(Integer.parseInt(in.nextLine()));
                System.out.print("ENTER MONTH: ");
                setMonth(Integer.parseInt(in.nextLine()));
                System.out.print("ENTER DAY: ");
                setDay(Integer.parseInt(in.nextLine()));
                valid = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter an integer value!!!");
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void addDay() {
        day++;
        if (day > 29 && month == 2 && isLeapYear()) {
            day = day - 29;
            addMonth();
        } else if (day > 28 && month == 2 && !isLeapYear()) {
            day = day - 28;
            addMonth();
        } else if (day > 30 && !is31dayMonth()) {
            day = day - 30;
            addMonth();
        } else if (day > 31 && is31dayMonth()) {
            day = day - 31;
            addMonth();
        }
    }

    // Private helper methods
    private boolean isLeapYear() {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }

    private void addMonth() {
        month++;
        if (month >= 13) {
            addYear();
            month = month - 12;
        }
    }

    private void addYear() {
        year++;
    }

    private boolean is31dayMonth() {
        List<Integer> month31 = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
        return month31.contains(month);
    }

    public static Date valueOf(String data) throws IllegalArgumentException{
        if(data.length() != 10)
            throw new IllegalArgumentException("Please enter date string in format \"YYYY-MM-DD\"");
        else{
            String[] arr = {data.substring(0, 4), data.substring(5, 7), data.substring(8, 10)};
            int year = Integer.parseInt(arr[0]);
            int month = Integer.parseInt(arr[1]);
            int day = Integer.parseInt(arr[2]);
            return new Date(day, month, year);
        }
    }
}
