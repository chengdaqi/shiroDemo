import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.xml.transform.Source;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    Map userMap = new HashMap();

    {
        userMap.put("FlightingYan","9999cc413e010ab8b3e72d436580f29b");
        super.setName("customRealm");
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //从数据库或者缓存中获取用户名
        String username = (String)principals.getPrimaryPrincipal();
        Set roles = getRolesByUserName(username);
        Set permissions = getPermissionsByUsername(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    private Set getPermissionsByUsername(String username) {
        Set sets = new HashSet();
        sets.add("add");
        sets.add("del");
        return sets;
    }

    private Set getRolesByUserName(String username) {
        Set sets = new HashSet();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //从传来的主体中获得用户名
        String username = (String)token.getPrincipal();
        //从数据库获取用户
        String password = getUserPasswordByUserName(username);
        System.out.println("CustomRealm:"+password);
        if(password==null){
            return null;
        }
        //SimpleAuthorizationInfo 授权
        //SimpleAuthenticationInfo 认证
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("FlightingYan",password,"customRealm");
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("FlightingYan"));
        return simpleAuthenticationInfo;
    }

    private String getUserPasswordByUserName(String username) {

        return (String) userMap.get(username);
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456","FlightingYan");
        System.out.println(md5Hash.toString());
    }
}
