public enum TankConstants {
    NORTH("į Šiaurę"),
    SOUTH("į Pietus"),
    EAST("į Rytus"),
    WEST("į Vakarus");
    private String direction;

    TankConstants(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
