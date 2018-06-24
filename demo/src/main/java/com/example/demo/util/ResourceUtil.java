package com.example.demo.util;

import com.example.demo.domain.Permission;
import com.example.demo.domain.SysRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceUtil {

   /* @Autowired
    private static PermissionService roleAndPermissionService;

    public static Map<String, Integer> createNew(HttpServletRequest request){

        Map<String, Integer> map = new HashMap<>();

        String url = request.getServletPath();
        String method = request.getMethod();

        Boolean resAlreadyExists = roleAndPermissionService.checkResExists(url, method);

        if (resAlreadyExists){
            Permission permission = roleAndPermissionService.selectPermissionOfCodePos(url, method);
            map.put("resCode",permission.getResCode());
            map.put("resPos", permission.getResPos());
            return map;
        }else{
            //①计算系统允许的最大权限码数值
            Integer systemAllowedMaxCode = 1 << 30;

            //②声明两个变量，并指定初始值，作为最终使用的权限码和权限位
            Integer resCode = 1;
            Integer resPos = 0;

            //③查询系统中最大的权限位数值
            //这里需要注意的是maxPos不存在时会是null值，不能使用基本数据类型接收
            Integer maxPos = roleAndPermissionService.selectSystemMaxPos();

            //如果maxPos为null，那么对应权限码的值应该也是null
            //如果maxPos不为null，那么查询最大权限码时一定要在maxPos范围内查询
            Integer maxCode = (maxPos == null) ? null : roleAndPermissionService.selectSystemMaxCode(maxPos);

            //④检查maxCode是否达到了系统允许的最大值
            if(maxPos != null && maxCode != null) {

                //※注意：将所有数据类型都统一为包装类型，用equals()方法比较其数值是最保险的做法
                if(maxCode.equals(systemAllowedMaxCode)) {

                    //权限位+1
                    resPos = maxPos + 1;

                    //权限码重新从1开始
                    resCode = 1;
                }else{

                    //权限位仍然使用最大值
                    resPos = maxPos;

                    //权限码在原来基础上左移一位
                    resCode = maxCode << 1;

                }

            }

            Permission permission = new Permission(null,null,null,url,resCode, resPos, method, null);
            roleAndPermissionService.insertPermission(permission);

            return null;
        }
    }*/

    /**
     * 根据深度加载的角色集合计算用户权限码数组
     * @param roleList
     * @param maxPos 系统中最大的权限位的值，加1后就是权限码数组的长度
     * @return
     */
    public static String calculateCodeArr(List<SysRole> roleList, Integer maxPos) {

        //1.计算权限码数组的长度
        int length = maxPos + 1;

        //2.声明一个数组，用来保存计算得到的权限码数值
        int [] codeArr = new int[length];

        //3.逐层遍历
        for (SysRole role : roleList){
            List<Permission> permissionList = role.getPermissionList();

            for (Permission permission : permissionList){

                //4.获取permission对象的权限码
                Integer resCode = permission.getResCode();

                //5.获取permission对象的权限位
                Integer resPos = permission.getResPos();

                //6.以权限位为下标从codeArr取出旧值
                int oldValue = codeArr[resPos];

                //7.将旧值和resCode做或运算，得到新值
                int newValue = oldValue | resCode;

                //8.将新值放回codeArr中原来的位置
                codeArr[resPos] = newValue;

            }
        }

        //9.将codeArr转换为字符串
        return convertCodeArr2Str(codeArr);

    }

    /**
     * 将权限码数组转换为逗号分隔的字符串
     * @param codeArr
     * @return
     */
    public static String convertCodeArr2Str(int [] codeArr) {

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < codeArr.length; i++) {
            builder.append(",").append(codeArr[i]);
        }

        return builder.substring(1);
    }

    /**
     * 权限验证工具方法
     * @param codeArrStr
     * @param resCode
     * @param resPos
     * @return
     */
    public static boolean checkAuthority(String codeArrStr, Integer resCode, Integer resPos) {

        //1.将codeArrStr转换为数组
        String[] split = codeArrStr.split(",");

        //2.以resPos为下标从数组中取出元素
        String codeStr = split[resPos];

        //3.将codeStr转换为整型
        Integer code = Integer.parseInt(codeStr);

        //4.进行与运算
        int result = resCode & code;

        //5.结果非零时表示其有权限
        return result != 0;
    }

}
