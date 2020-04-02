package model;

public class Player {
    private String name;
    private Field ownField = new Field();
    private Field enemyField = new Field();

    public Field getOwnField() {
        return ownField;
    }

    public Field getEnemyField() {
        return enemyField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
