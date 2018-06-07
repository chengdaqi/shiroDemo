import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class Jdbc_realm {

    DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }

    @Test
    public void aboutJdbcRealm(){
        //
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);

        /**
         * 自定义查询语句
         * **/
        String sql = "select password from test_users where user_name=?";
        jdbcRealm.setAuthenticationQuery(sql);

        String role_permission = "select role_name from role_user where user_name = ?";
        jdbcRealm.setUserRolesQuery(role_permission);

        defaultSecurityManager.setRealm(jdbcRealm);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("xiaoRan","123456");

        subject.login(token);

        System.out.println(subject.isAuthenticated());
         subject.checkRoles("admin");
        /*subject.checkPermission("add");*/


    }
}
