package alexiil.utils;
public class StringUtils {
    public static boolean contains(Object object, Object[] array) {
        for (Object obj : array)
            if (obj.equals(object))
                return true;
        return false;
    }
}
