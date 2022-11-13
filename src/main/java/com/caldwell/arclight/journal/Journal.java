package com.caldwell.arclight.journal;
import com.caldwell.arclight.bodies.Star;
import java.io.Serializable;
import java.util.ArrayList;

public class Journal implements Serializable {

    // fields
    private ArrayList<Star> starList;

    // constructors
    public Journal() {
        this.starList = new ArrayList<>();
    }

    // adding stars to star list
    public void addStar(Star star) {
        starList.add(star);
    }

    // remove stars from star list
    public void removeStar(Star star) {
        starList.remove(star);
    }

/*    // sorting implementations for stars using different variables
    public void sortStarsByName(ArrayList<Star> starList) {

    }

    public void sortStarsBySize(ArrayList<Star> starList) {

    }

    public void sortStarsByAge(ArrayList<Star> starList) {

    }

    public void sortStarsByTemperature(ArrayList<Star> starList) {

    }

    public static void quickSort(ArrayList<Star> starList) {
        quickSort(starList,0,starList.size()-1);
    }

    public static void quickSort(ArrayList<Star> starList, int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) {
            return;
        }


    }

    *//*public static int partition() {

    }*//*

    public static void swap(ArrayList<Star> starList, int index1, int index2) {

    }*/

}
