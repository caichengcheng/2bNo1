package hwp.ZerenList;

/**
 * create by caichengcheng
 * date:2019-04-07
 */
public class IsLocationUser extends RulerHandler{
    @Override
    public void apply(Context context) {
        if(context.isLocationUser()){
            if(this.successor!=null){
                this.successor.apply(context);
            }
        }else{
            throw new RuntimeException("您不处于可以领取奖品的地区");
        }
    }
}
