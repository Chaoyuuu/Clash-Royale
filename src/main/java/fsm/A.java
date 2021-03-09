package fsm;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class A implements Cloneable {
    private B[] bs;
    private C c;


        public A clone() {
        try {
            A clone_A = (A) super.clone();
            clone_A.bs = bs.clone();
            return clone_A;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}


class B implements Cloneable {
    public B clone() {
        try {
            B clone = (B) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

class C implements Cloneable {

}