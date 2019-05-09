package hwp.ZerenList;

/**
 * create by caichengcheng
 * date:2019-04-07
 */
public abstract class RulerHandler {
    protected RulerHandler successor;

    public abstract void apply(Context context);

    public   void setSuccessor(RulerHandler successor){
        this.successor = successor;
    }

    public RulerHandler getSuccessor() {
        return successor;
    }
}
