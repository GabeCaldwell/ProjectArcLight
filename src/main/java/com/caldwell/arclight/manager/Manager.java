package com.caldwell.arclight.manager;
import com.caldwell.arclight.bodies.Star;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

// ********************************************************************************** //
// Title: Arclight                                                                    //
// Author: Gabriel Caldwell                                                           //
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022                                    //
// File: Manager.java                                                                 //
// Description: Main class for manager feature                                        //
// ********************************************************************************** //

public class Manager implements Serializable {

    // fields
    ArrayList<Star> stars;
    Stack<Star> starStack;

    // constructors
    public Manager() {
        this.stars = new ArrayList<>();
        this.starStack = new Stack<>();
    }

    public Manager(ArrayList<Star> stars) {
        this.stars = stars;
    }

    //=================================================================================================================

    public ArrayList<Star> getStars() {
        return this.stars;
    }

    public void setStars(ArrayList<Star> stars) {
        this.stars = stars;
    }

    //=================================================================================================================

    // output array to file
    public void writeStars() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stars.dat"))) {
            oos.writeObject(this.stars);
            System.out.println("save success");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // retrieve array from file
    public void readStars() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stars.dat"))) {
            this.stars = (ArrayList<Star>) ois.readObject();
            System.out.println("read success");
        }
        catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }



    //=================================================================================================================



    // quicksort function for sorting stars by distance,
    // typical quicksort algorithm meaning it operates at O(Nlog(N)) complexity
    public void quickSort(ArrayList<Star> array) {
        quickSort(array, 0, array.size()-1);
    }

    public ArrayList<Star> returnQuickSort(ArrayList<Star> array) {
        quickSort(array, 0, array.size()-1);
        return(array);
    }

    // helper method for quickSort()
    public void quickSort(ArrayList<Star> array, int lowIndex, int highIndex) {

        // checks if there's only one element in the sub array, so it knows when to stop
        if (lowIndex >= highIndex) {
            return;
        }

        // picks the pivot at random and puts it at the end of the array
        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        double pivot = array.get(pivotIndex).getDistance();
        swap(array, pivotIndex, highIndex);

        // sorts the array and returns the place where the array is split
        int leftPointer = partition(array, lowIndex, highIndex, pivot);

        // recursive calls to sort the sub arrays
        quickSort(array, lowIndex, leftPointer - 1);
        quickSort(array,leftPointer + 1, highIndex);
    }

    // sorts then partitions array into 2 sub arrays
    public int partition(ArrayList<Star> array, int lowIndex, int highIndex, double pivot) {

        // pointers receive starting values
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        // walks through the list and swaps elements that are out of order
        while (leftPointer < rightPointer) {
            while (array.get(leftPointer).getDistance() <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (array.get(rightPointer).getDistance() >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(array, leftPointer, rightPointer);
        }

        // somehow makes the code work, will break if removed
        if(array.get(leftPointer).getDistance() > array.get(highIndex).getDistance()) {
            swap(array, leftPointer, highIndex);
        }
        else {
            leftPointer = highIndex;
        }
        return leftPointer;
    }

    // swaps elements at designated indices
    public void swap(ArrayList<Star> array, int leftPointer, int rightPointer) {
        Star temp = array.get(leftPointer);
        array.set(leftPointer, array.get(rightPointer));
        array.set(rightPointer, temp);
    }



    //=================================================================================================================



    // quicksort function for sorting stars by name,
    // typical quicksort algorithm meaning it operates at O(Nlog(N)) complexity
    public void quickSortString(ArrayList<Star> array) {
        quickSortString(array, 0, array.size()-1);
    }

    public ArrayList<Star> returnQuickSortString(ArrayList<Star> array) {
        quickSortString(array, 0, array.size()-1);
        return(array);
    }

    // helper method for quickSort()
    public void quickSortString(ArrayList<Star> array, int lowIndex, int highIndex) {

        // checks if there's only one element in the sub array, so it knows when to stop
        if (lowIndex >= highIndex) {
            return;
        }

        // picks the pivot at random and puts it at the end of the array
        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        String pivot = array.get(pivotIndex).getName();
        swapString(array, pivotIndex, highIndex);

        // sorts the array and returns the place where the array is split
        int leftPointer = partitionString(array, lowIndex, highIndex, pivot);

        // recursive calls to sort the sub arrays
        quickSortString(array, lowIndex, leftPointer - 1);
        quickSortString(array,leftPointer + 1, highIndex);
    }

    // sorts then partitions array into 2 sub arrays
    public int partitionString(ArrayList<Star> array, int lowIndex, int highIndex, String pivot) {

        // pointers receive starting values
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        // walks through the list and swaps elements that are out of order
        while (leftPointer < rightPointer) {
            while (array.get(leftPointer).getName().compareToIgnoreCase(pivot) <= 0  && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (array.get(rightPointer).getName().compareToIgnoreCase(pivot) >= 0 && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(array, leftPointer, rightPointer);
        }

        // somehow makes the code work, will break if removed
        if(array.get(leftPointer).getName().compareToIgnoreCase(array.get(highIndex).getName()) > 0) {
            swap(array, leftPointer, highIndex);
        }
        else {
            leftPointer = highIndex;
        }
        return leftPointer;
    }

    // swaps elements at designated indices
    public void swapString(ArrayList<Star> array, int leftPointer, int rightPointer) {
        Star temp = array.get(leftPointer);
        array.set(leftPointer, array.get(rightPointer));
        array.set(rightPointer, temp);
    }




    //=================================================================================================================




    // quicksort function for sorting stars by name,
    // typical quicksort algorithm meaning it operates at O(Nlog(N)) complexity
    public void quickSortColor(ArrayList<Star> array) {
        quickSortColor(array, 0, array.size()-1);
    }

    public ArrayList<Star> returnQuickSortColor(ArrayList<Star> array) {
        quickSortColor(array, 0, array.size()-1);
        return(array);
    }

    // helper method for quickSort()
    public void quickSortColor(ArrayList<Star> array, int lowIndex, int highIndex) {

        // checks if there's only one element in the sub array, so it knows when to stop
        if (lowIndex >= highIndex) {
            return;
        }

        // picks the pivot at random and puts it at the end of the array
        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex;
        String pivot = array.get(pivotIndex).getColor();
        swapColor(array, pivotIndex, highIndex);

        // sorts the array and returns the place where the array is split
        int leftPointer = partitionColor(array, lowIndex, highIndex, pivot);

        // recursive calls to sort the sub arrays
        quickSortColor(array, lowIndex, leftPointer - 1);
        quickSortColor(array,leftPointer + 1, highIndex);
    }

    // sorts then partitions array into 2 sub arrays
    public int partitionColor(ArrayList<Star> array, int lowIndex, int highIndex, String pivot) {

        // pointers receive starting values
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        // walks through the list and swaps elements that are out of order
        while (leftPointer < rightPointer) {
            while (array.get(leftPointer).getColor().compareToIgnoreCase(pivot) <= 0  && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (array.get(rightPointer).getColor().compareToIgnoreCase(pivot) >= 0 && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(array, leftPointer, rightPointer);
        }

        // somehow makes the code work, will break if removed
        if(array.get(leftPointer).getColor().compareToIgnoreCase(array.get(highIndex).getColor()) > 0) {
            swap(array, leftPointer, highIndex);
        }
        else {
            leftPointer = highIndex;
        }
        return leftPointer;
    }

    // swaps elements at designated indices
    public void swapColor(ArrayList<Star> array, int leftPointer, int rightPointer) {
        Star temp = array.get(leftPointer);
        array.set(leftPointer, array.get(rightPointer));
        array.set(rightPointer, temp);
    }

}
