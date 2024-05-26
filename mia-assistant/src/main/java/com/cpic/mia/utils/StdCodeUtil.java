package com.cpic.mia.utils;



import java.util.concurrent.ThreadLocalRandom;
/**
 * @author pengqian-012
 * @package com.cpic.mia.utils
 * @describe
 * @date 2024/5/26
 */
public class StdCodeUtil {

    public static String generateCode(){
        ThreadLocalRandom current = ThreadLocalRandom.current();
        long currentTms = System.currentTimeMillis();
        long randoms= current.nextLong(currentTms);
        String stdRandom = Long.toString(randoms, 36);
        //String format = String.format("%08s", stdRandom);
        return stdRandom;
    }
}
