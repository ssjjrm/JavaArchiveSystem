package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 确保你的 mybatis-config.xml 放在 src/main/resources 下
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            throw new RuntimeException("MyBatis配置文件加载失败", e);
        }
    }

    // 必须是 public static，这样才能直接调用
    public static SqlSession getSqlSession() {
        // true 表示自动提交事务，适合大部分场景
        return sqlSessionFactory.openSession(true);
    }
}