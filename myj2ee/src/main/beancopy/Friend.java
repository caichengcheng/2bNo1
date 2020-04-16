package beancopy;

import java.io.Serializable;

/**
 * create by caichengcheng
 * date:2019-10-16
 */
public class Friend implements Cloneable, Serializable {
    private String bestFriendName;

    public String getBestFriendName() {
        return bestFriendName;
    }

    public void setBestFriendName(String bestFriendName) {
        this.bestFriendName = bestFriendName;
    }
    @Override
    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void say(){
        System.out.println("my friend is  "+ bestFriendName);
    }

    public Friend(String bestFriendName) {
        this.bestFriendName = bestFriendName;
    }

    public Friend() {
    }
}
