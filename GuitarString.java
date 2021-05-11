//************************************************************************
// File: GuitarString.java         Assignment 8
// 
// Author: Hanah Leventhal	       Email: hanah.leventhal@yale.edu
//
// Class: GuitarString
// Dependencies: RingBuffer 
//
//   Time spent on this problem: 3 hrs
//   --------------------
//   
//      This is a class to create the Guitar String. It fills the 
//      constructors and methods needed in order to enqueue and dequeue
//      the buffer appropriately to simulate the sound of a plucked
//      guitar string.
//
//************************************************************************

public class GuitarString {

    private RingBuffer          buffer; // ring buffer
    private static final double DECAY = 0.996;
    private static final int SAMPLING_RATE = 44100;
    private int N;
    private int tics;

    // create a guitar string of the given frequency
    public GuitarString(double frequency) {
        tics = 0;
        N = (int)Math.round(SAMPLING_RATE / frequency);
        buffer = new RingBuffer(N);
        for (int i = 0; i < N; i++) {
            buffer.enqueue(0);
        }
    }

    // create a guitar string with size & initial values given by the array
    public GuitarString(double[] init) {
        tics = 0;
        N = init.length;
        buffer = new RingBuffer(N);
        for (int i = 0; i < N; i++) {
            buffer.enqueue(init[i]);
        }
    }

    // pluck the guitar string by replacing the buffer with white noise
    public void pluck() {
        while (!buffer.isEmpty()){
            buffer.dequeue();
        }
        for (int i = 1; i <= N; i++) {
            buffer.enqueue(Math.random() - 0.5);
        }
    }

    // advance the simulation one time step
    public void tic() {
        // YOUR CODE HERE formula thing for decay
        double x = buffer.peek();
        buffer.dequeue();
        double y = buffer.peek();
        double avg = (x + y) / 2;
        buffer.enqueue(avg * DECAY);
        tics++;
    }

    // return the current sample
    public double sample() {
        if (!buffer.isEmpty()) {
            return buffer.peek();
        }
        return 0;
    }

    // return number of times tic was called
    public int time() {
        return tics;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < N; i++) {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }

}
