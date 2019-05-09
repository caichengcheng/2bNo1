package hwp.ZerenList;

import hwp.Condiment;

/**
 * create by caichengcheng
 * date:2019-04-07
 */
public class Context {
    private boolean   isNewUser=true;
    private boolean isLocationUser = true;

    public boolean isNewUser() {
        return isNewUser;
    }

    public boolean isLocationUser() {
        return isLocationUser;
    }


    public void setNewUser(boolean newUser) {
        isNewUser = newUser;
    }

    public void setLocationUser(boolean locationUser) {
        isLocationUser = locationUser;
    }
}
