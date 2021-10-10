package edu.chalmers.grapefruit.Utils;

/**
 * @author ingrid.stake
 * @author tove.nilsson
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