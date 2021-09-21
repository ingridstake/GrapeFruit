package main.java.edu.chalmers.tda367.project.Model.Position;

public class NormalPosition implements IPosition{

    private final int id;

    protected NormalPosition(int id){
        this.id = id;

    }

    @Override
    public int getId() {
        return id;
    }
}
