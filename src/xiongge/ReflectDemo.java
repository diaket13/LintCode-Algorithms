package xiongge;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author wangw
 * @version $$Id: reflectDemo, v 0.1 2018/5/4 0004 15:17 wangw Exp $$
 */
public class ReflectDemo {
    public static void main(String[] args) {
        ReflectBean<String> bean = new ReflectBean<>();
        bean.setB(true);
        bean.setC('x');
        bean.setD(3.14);
        bean.setI(666);
        bean.setS("true x 3.14 666");
        List<String> list = new ArrayList<>(5);
        list.add(bean.getB().toString());
        list.add(bean.getC().toString());
        list.add(bean.getD().toString());
        list.add(bean.getI().toString());
        list.add(bean.getS());
        bean.setL(list);
        Class clazz = bean.getClass();
        Field[] fields = clazz.getFields();
        for(Field field : fields){
            System.out.println("fields      " + field.getName() +" | " + field.getType());
        }
        fields = clazz.getDeclaredFields();
        for(Field field : fields){
            System.out.println("declaredFields      " + field.getName() + " | " + field.getType() + " | " + Modifier.toString(field.getModifiers()));
        }
        try {
            Field field = clazz.getDeclaredField("pss");
            System.out.println(field.getName() +"|" + field.getType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        for (Field field : clazz.getDeclaredFields()){
            if((field.getModifiers() & 2) == 0){
                continue;
            }
            String fieldName = field.getName();
            String getMethodName = "set"
                                   + fieldName.substring(0, 1).toUpperCase()
                                   + fieldName.substring(1);
            try {
                Method method = clazz.getMethod(getMethodName,field.getType());
                System.out.println("method       " + Modifier.toString(method.getModifiers()) + " " + method.getReturnType() + " " +  method.getName() + "(" + method.getParameterTypes()[0].getName() + ")");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
        try {
            Object newBean = clazz.newInstance();
            Field[] newFields = clazz.getDeclaredFields();
            for(Field field :  newFields){
                if ((field.getModifiers() & 2)==0){
                    continue;
                }
                String fieldName = field.getName();
                String set = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                String get = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method setM = clazz.getMethod(set, field.getType());
                Method getM = clazz.getMethod(get);
                System.out.println(fieldName + " " +getM.invoke(newBean));
                Object value = getM.invoke(bean);
                setM.invoke(newBean,value);
                System.out.println(fieldName + " " + getM.invoke(newBean));

            }
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }
}
class ReflectBean<T>{
    private Integer i;
    private Double d;
    private String s;
    private Character c;
    private Boolean b;
    private List<T> l;

    public static final Short S=Short.MAX_VALUE;
    public static Byte B=Byte.MAX_VALUE;
    public Float f=3.14f;
    protected String ps="protected";
    String ds ="default";
    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Character getC() {
        return c;
    }

    public void setC(Character c) {
        this.c = c;
    }

    public Boolean getB() {
        return b;
    }

    public void setB(Boolean b) {
        this.b = b;
    }

    public List<T> getL() {
        return l;
    }

    public void setL(List<T> l) {
        this.l = l;
    }
}
