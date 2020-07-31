package ankang.mybatis.learn.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-29
 */
public class StringUtils {

    /**
     * 获取token中的字符串
     *
     * @param string     含有token的字符串
     * @param openToken  token的起始标识
     * @param closeToken token的结束标识
     * @return 返回token字符串列表，token字符串是包含openToken和closeToken的
     */
    public static List<String> getTokenList(String string , String openToken , String closeToken) {
        final List<String> tokenList = new ArrayList<>();

        // 定义两个开始坐标
        int i0 = 0;
        int j0 = 0;
        while (true) {
            final int i = string.indexOf(openToken , i0);
            final int j = string.indexOf(closeToken , j0);
            if (i == -1 && j == -1) {
                break;
            }
            tokenList.add(string.substring(i , j + closeToken.length()));
            i0 = i+openToken.length();
            j0 = j+closeToken.length();
        }

        return tokenList;
    }
}