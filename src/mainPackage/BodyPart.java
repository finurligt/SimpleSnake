package mainPackage;

public abstract class BodyPart implements GameObject {
    int x,y;
    int gridSize;
    int offsetX,offsetY;

    public BodyPart(int x, int y, int offsetX, int offsetY, int gridSize) {
        this.x = x;
        this.y = y;
        this.gridSize =gridSize;
        this.offsetX=offsetX;
        this.offsetY=offsetY;
    }


}
