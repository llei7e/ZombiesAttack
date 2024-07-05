package clueless.zombiesattack;

public class Winner {
    private String name;
    private int points;

    public Winner(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
}
