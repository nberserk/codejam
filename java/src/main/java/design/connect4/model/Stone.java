package design.connect4.model;

public enum Stone {
    Empty(" "),
    Black("B"),
    White("W");

    private final String mark;
    Stone(String mark){
        this.mark = mark;
    }

    @Override
    public String toString() {
        return this.mark;
    }
}
