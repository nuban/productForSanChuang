package com.blockchain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blockchain.config.security.JwtTokenUtil;
import com.blockchain.mapper.UserMapper;
import com.blockchain.pojo.ResBean;
import com.blockchain.pojo.User;
import com.blockchain.service.IUserService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xbh
 * @since 2022-03-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public ResBean login(String username, String password, String code, HttpServletRequest request) {

//        System.out.println("1");
        String  captcha = (String) request.getSession().getAttribute("captcha");
        if(StringUtils.isBlank(code) || !captcha.equalsIgnoreCase(code) ){
            return ResBean.error("验证码错误");
        }
//        System.out.println("2");
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null==userDetails|| !passwordEncoder.matches(password,userDetails.getPassword())){
            return ResBean.error("用户名或密码不正确");
        }
//        System.out.println("3");
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);

        return ResBean.success("登录成功",tokenMap);

    }

    @Override
    public User getUserByUsername(String username) {
        return  userMapper.selectOne(new QueryWrapper<User>().eq("user_name",username));
    }

    @Override
    public String getUsernameByid(Integer id) {

       return userMapper.selectOne(new QueryWrapper<User>().eq("id",id)).getUsername();
    }

    @Override
    public ResBean findall() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>());
        for(User user : users){
            user.setPassword(null);
        }
        System.out.println(users);
        if(!users.isEmpty())
            return ResBean.success("查询成功",users);
        else
            return ResBean.error("查询失败");
    }

    @Override
    public ResBean deleteByid(Integer id) {

        if(userMapper.deleteById(id) == 0){
            return ResBean.error("删除失败");
        }else{
            return ResBean.success("删除成功");
        }

    }
}
