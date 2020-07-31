package ankang.mybatis.learn.session;

import lombok.Getter;

import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-29
 */
public class BoundSql {
    @Getter
    private final String tokenSql;
    @Getter
    private final List<Token> tokenList;
    @Getter
    private final String prepareSql;

    public BoundSql(String tokenSql , List<Token> tokenList) {
        this.tokenSql = tokenSql;
        this.tokenList = tokenList;

        String prepareSql = tokenSql;
        for (Token token : this.tokenList) {
            prepareSql = prepareSql.replace(token.getTokenStr() , "?");
        }

        this.prepareSql = prepareSql;
    }

    public static class Token {
        @Getter
        private final String paramter;
        @Getter
        private final String tokenStr;

        public Token(String paramter , String tokenStr) {
            this.paramter = paramter;
            this.tokenStr = tokenStr;
        }
    }

}
