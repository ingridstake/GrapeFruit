package edu.chalmers.grapefruit.Interfaces;

public interface Observable {
    void addObserver(Observer observer);
    void notifyObservers();
}