package hwp.ZerenList;

/**
 * create by caichengcheng
 * date:2019-04-07
 */
public class NewUser extends RulerHandler{


    @Override
    public void apply(Context context) {
        if(context.isNewUser()){
            if(this.successor!=null){
                this.successor.apply(context);
            }
        }else{
            throw new RuntimeException("您不是新用户");
        }
    }
}
