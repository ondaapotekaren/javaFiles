import java.util.ArrayList;
import java.util.HashMap;

class Program {
    int i = 0;
    HashMap<String,Long> regs;
    ArrayList<Long> queue;
    boolean wait;
    int sent = 0;

    Program () {
        this.regs = new HashMap<String,Long>();
        this.queue = new ArrayList<Long>();
        wait = false;
    }

    Program (Integer i ) {
        this.i = i;
    }

    class Inst {
        String inst;
        String reg;
        Long x;
        Long y;

        Inst(String inst, String reg, Long x,Long y) {
            this.inst = inst;
            this.reg = reg;
            this.x = x;
            this.y = y;
        }
    }

    Inst fetch(ArrayList<String> insts) {


        String[] sl = insts.get(i).split(" ");
        String name = sl[0];
        String reg = sl[1];
        Long x = null;
        Long y = null;

        if (Character.isDigit(sl[1].charAt(sl[1].length()-1))) {
            x = new Long(Integer.parseInt(sl[1]));
        } else {
            x = regs.get(sl[1]);
            if (x == null) {
                x = new Long(0);
                regs.put(sl[1],x);
            }
        }
        if(sl.length > 2) {
            if (Character.isDigit(sl[2].charAt(sl[2].length()-1) )) {
                y = new Long(Integer.parseInt(sl[2]));
            }
            else {
                y=regs.get(sl[2]);
                if(y == null) {
                    y = new Long(0);
                    regs.put(sl[2],y);
                }
            }
        }
        Inst inst = new Inst(name,reg,x,y);
        return inst;
    }

    void exec(Inst inst,Program peer) {
        Long v;
        switch (inst.inst) {
            case "snd":
                peer.queue.add(inst.x);
                sent++;
                break;
            case "set":
                regs.put(inst.reg,inst.y);
                break;
            case "add":
                regs.put(inst.reg,inst.x+inst.y);
                break;
            case "mul":
                regs.put(inst.reg,inst.x*inst.y);
                break;
            case "mod":
                v = inst.x % inst.y;
                regs.put(inst.reg, inst.x % inst.y);
                break;
            case "rcv":
                if (queue.isEmpty()) {
                    i--;
                    wait = true;
                } else {
                    regs.put(inst.reg,queue.remove(0));
                    wait = false;
                }
                break;
            case "jgz":
                if (inst.x > 0) {
                    i--;
                    long yy = (long) inst.y;
                    i = i + (int)yy;
                }
                break;
            default:
                break;
        }
        i++;
    }
}