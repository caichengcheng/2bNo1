package proxy.reflect;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author caichengcheng
 *         time: 2019/2/20.
 */
public class ParamBean {
    private String name;
    private String[] father;
    private String[] mother;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getFather() {
        return father;
    }

    public void setFather(String[] father) {
        this.father = father;
    }

    public String[] getMother() {
        return mother;
    }

    public void setMother(String[] mother) {
        this.mother = mother;
    }

    public static void main(String[] args) {

        for(int i=0;i<6;i++){
            System.out.print("6");
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParamBean paramBean = (ParamBean) o;
        return Objects.equals(name, paramBean.name) &&
                Arrays.equals(father, paramBean.father) &&
                Arrays.equals(mother, paramBean.mother);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(father);
        result = 31 * result + Arrays.hashCode(mother);
        return result;
    }
}
