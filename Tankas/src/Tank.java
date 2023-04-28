public class Tank {
    private int positionX = 0;
    private int positionY = 0;
    private int shotNorth = 0;
    private int shotEast = 0;
    private int shotSouth = 0;
    private int shotWest = 0;
    String lookingSide = TankConstants.NORTH.getDirection();

    public void goNorth(){
        positionY++;
        pritnAction(TankConstants.NORTH);
    }

    public void goEast(){
        positionX++;
        pritnAction(TankConstants.EAST);
    }

    public void goSouth(){
        positionY--;
        pritnAction(TankConstants.SOUTH);

    }

    public void goWest(){
        positionX--;
        pritnAction(TankConstants.WEST);
    }

    public void makeShot(){
        if(lookingSide.equals(TankConstants.NORTH.getDirection())){
            shotNorth++;
        } else if (lookingSide.equals(TankConstants.EAST.getDirection())) {
            shotEast++;
        } else if (lookingSide.equals(TankConstants.SOUTH.getDirection())) {
            shotSouth++;
        } else if (lookingSide.equals(TankConstants.WEST.getDirection())){
            shotWest++;
        }
        System.out.println("Šūvis " + lookingSide);
    }

    public void getInfo(){
        System.out.println("INFO: Tanko koordinatė: " + " (" + positionX + ":" + positionY + "). Kryptis " + lookingSide);
        System.out.println("INFO: Tanko šūviai: "
                + shotNorth + " " + TankConstants.NORTH.getDirection() + ", "
                + shotEast + " " + TankConstants.EAST.getDirection() + ", "
                + shotSouth + " " + TankConstants.SOUTH.getDirection() + ", "
                + shotWest + " " + TankConstants.WEST.getDirection() + ". "
                + "Viso šūvių: " + (shotNorth + shotEast + shotSouth + shotWest));
    }

    private void pritnAction(TankConstants side){
        lookingSide = side.getDirection();
        System.out.println("Tankas pajuda " + lookingSide + " (" + positionX + ":" + positionY + ")");
    }


}
