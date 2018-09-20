import java.util.ArrayList;
import java.util.List;

public class Grid<E> {
    ArrayList<ArrayList<E>> grid;
    int size;
    Grid (int size) {
        this.size = size;
        ArrayList<ArrayList<E>> arrayList = new ArrayList<>();
        for (int i = 0;i<size;i++) {
            ArrayList<E> arrayList1 = new ArrayList<>();
            for (int ii = 0;ii<size;ii++) {
                arrayList1.add(null);
            }
            arrayList.add(arrayList1);
        }
    }
    public void setValue(E value,int x, int y) {

        ArrayList<E> row = grid.get(y);
        row.set(x,value);
        grid.set(y,row);
    }
    public E getValue(int x, int y) {
            return grid.get(y).get(x);
    }


}
