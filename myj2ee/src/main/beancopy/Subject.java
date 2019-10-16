package beancopy;

import java.io.Serializable;

/**
 * create by caichengcheng
 * date:2019-10-16
 */
public class Subject implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
