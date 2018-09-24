import java.util.ArrayList;
import java.util.function.*;
public class VirusCarrier<E> extends GridTraverser{
    E defaultSign;
    VirusCarrier(Integer x,Integer y,Direction dir) {
        super(x,y,dir);
    }

    public void setDefaultSign(E defaultSign) {
        this.defaultSign = defaultSign;
    }

    /*
    *  If grid is too small it needs to scale up
    * */

    public void moveForward() {
        if (getX()-1 < 0
                || getY()-1 <0
                || getX()+1 > getGridSize()-1
                || getY()+1 > getGridSize()-1) {
            Grid grid1 = new Grid(getGridSize()+2,defaultSign);
            for (int i = 0;i<getGridSize()+2;i++) {
                grid1.setValue(defaultSign,i,0);
                grid1.setValue(defaultSign,i,getGridSize()+1);
                grid1.setValue(defaultSign,0,i);
                grid1.setValue(defaultSign,getGridSize()+1,i);
            }
            for (int x=0;x<getGridSize();x++) {
                for (int y=0;y<getGridSize();y++) {
                    grid1.setValue(grid.getValue(x,y),x+1,y+1);
                }
            }
            connect(grid1);
            //update position.
            setX(getX()+1);
            setY(getY()+1);
        }
        directionCond(p->setY(getY()-1)
                ,p->setX(getX()+1)
                ,p->setY(getY()+1)
                ,p->setX(getX()-1));
    }
}
