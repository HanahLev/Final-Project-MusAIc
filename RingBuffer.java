//************************************************************************
// File: RingBuffer.java         Assignment 8
// 
// Author: Hanah Leventhal	     Email: hanah.leventhal@yale.edu
//
// Class: RingBuffer 
//
//   Time spent on this problem: 3 hrs
//   --------------------
//   
//      This is the RingBuffer. It lists the constructors and methods 
//      associated with adding and deleting the values in the buffer.
//
//************************************************************************

public class RingBuffer {
    private double[] rb;          // items in the buffer
    private int first;            // rb[first]  = first item / index for the next dequeue or peek
    private int last;             // rb[last-1] = last  item / index for the next enqueue
    private int size;             // number of items in the buffer

    // create an empty buffer, with given max capacity
    public RingBuffer(int capacity) {
        rb = new double[capacity];
        size = 0;
        first = 0;
        last = 0;
    }

    // return number of items currently in the buffer
    public int size() {
        return size;
    }

    // is the buffer empty (size equals zero)?
    public boolean isEmpty() {
        boolean isEmpty = false;
        if (size() == 0) {
            isEmpty = true;
        }
        return isEmpty;
    }

    // is the buffer full (size equals array capacity)?
    public boolean isFull() {
        boolean isFull = false;
        if (size() == rb.length) {
            isFull = true;
        }
        return isFull;
    }

    // add item x to the end
    public void enqueue(double x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last++;
        if (last == rb.length) {
            last = 0;
        }
        size++;
    }

    // delete and return item from the front
    public double dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        double deletedFirst = rb[first];
        rb[first] = 0;
        first++;
        if (first == rb.length) {
            first = 0;
        }
        size--;
        return deletedFirst;
    }

    // return (but do not delete) item from the front
    public double peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(N);
        for (int i = 1; i <= N; i++) {
            buffer.enqueue(i);
        }
        double t = buffer.dequeue();
        buffer.enqueue(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        System.out.println(buffer.peek());
    }

}
