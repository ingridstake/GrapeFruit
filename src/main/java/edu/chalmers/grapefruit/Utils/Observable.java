package edu.chalmers.grapefruit.Utils;

/**
 * @author Ingrid Stake
 * @author Tove Nilsson
 */

public interface Observable {

    /**
     * Adds an Observer to the Observable's collection of Observers
     * @param observer the Observer that is added
     */
    void addObserver(Observer observer);

    /**
     * Notifies all Observers in the observer collection of the observable.
     */
    void notifyObservers();
}