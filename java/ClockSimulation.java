/*
    Problem:

    Write a program that will display every second in a 24 hour clock time cycle. Make the output in the format:

    00:00:01
    00:00:02
    00:00:03

     The clock must show hours, minutes. and seconds.

*/

public class ClockSimulation {
    final static int MAX_SEC_MIN = 60; // seconds per min, mins per hour

    public static void main(String[] args) {
        countUp(3600); // total seconds in 1 hour
        countUp(86400); // total seconds in 24 hours

        System.out.println("End of processing.");
    }

    private static void countUp(int maxSeconds) {
        if (maxSeconds <= 0)
            return;
                
        int hours, mins, secs;

        for (int i = 1; i <= maxSeconds; i++) {

            secs = i % MAX_SEC_MIN;

            mins = i / MAX_SEC_MIN;
            hours = mins / MAX_SEC_MIN;
            mins = mins % MAX_SEC_MIN;

            System.out.format("%02d:%02d:%02d\n", hours, mins, secs);
        }
    }
}
