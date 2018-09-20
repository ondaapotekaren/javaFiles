import java.util.function.*;
public class VirusCarrier<E> extends GridTraverser{

    VirusCarrier(Integer x,Integer y,Direction dir) {
        super(x,y,dir);
    }
    public void moveForward() {

        /*
        *   Check size of x, y coordinates
        *   and extend grid if to small
        *
        *   Don't forget to correct current coordinates
        * */


        directionCond(p->setY(getY()-1)
                ,p->set);
    }

}
