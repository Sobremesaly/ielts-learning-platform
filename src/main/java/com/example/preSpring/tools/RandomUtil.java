package com.example.preSpring.tools;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author 小叶子
 */
public class RandomUtil {

    private static final Random random = new Random();
    /**
     * SIXDF 生成一个6位的验证码
     */
    private static final DecimalFormat SIXDF = new DecimalFormat("000000");

    public static String getSixBitRandom() {
        return SIXDF.format(random.nextInt(1000000));
    }
}


