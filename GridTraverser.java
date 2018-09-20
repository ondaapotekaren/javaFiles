import java.util.function.Consumer;

public class GridTraverser<E> {

    Direction dir;
    Integer x;
    Integer y;

    GridTraverser (Integer x,Integer y,Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public Direction getDir() {
        return dir;
    }

    public Integer getX() {
        return x;
    }
    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }
    public void setY(Integer y) {
        this.y = y;
    }

    Grid<E> grid;

    public void connect(Grid grid) {
        this.grid = grid;
    }
    public E checkGrid(){
        return grid.getValue(x,y);
    }

    public void setGrid(E v) {
        grid.setValue(v,x,y);
    }
    public void directionCond(Consumer func1,
                               Consumer func2,
                               Consumer func3,
                               Consumer func4){
        if (dir.equals(Direction.NORTH)) {
            func1.accept(this);
        } else if (dir.equals(Direction.EAST)) {
            func2.accept(this);
        } else if (dir.equals(Direction.SOUTH)) {
            func3.accept(this);
        } else if (dir.equals(Direction.WEST)) {
            func4.accept(this);
        }
    }
    public void turnRight() {
        directionCond(p -> setDir(Direction.EAST)
                ,p -> setDir(Direction.SOUTH)
                ,p -> setDir(Direction.WEST)
                ,p -> setDir(Direction.NORTH));
    }
    public void turnLeft() {
        directionCond(p -> setDir(Direction.WEST)
                ,p -> setDir(Direction.NORTH)
                ,p -> setDir(Direction.EAST)
                ,p -> setDir(Direction.SOUTH));
    }

}
