package com.bonc.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bonc.reggie.common.R;
import com.bonc.reggie.entity.User;
import com.bonc.reggie.service.UserService;
import com.bonc.reggie.utils.SMSUtils;
import com.bonc.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 发送手机短信验证码
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession httpSession){

        // 获取手机号
        String phone = user.getPhone();
        if(StringUtils.isNotEmpty(phone)){
            //  生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code: {}",code);

            // 使用阿里云提供的短信API完成发送短信
            // SMSUtils.sendMessage("短信签名","模板code",phone,code);

            // 需要将生成的验证码保存到Session
            httpSession.setAttribute(phone,code); // 手机号作为键，验证码作为值

            return R.success("手机验证码短信发送成功");

        }


        return R.error("短信发送失败");
    }


//    /**
//     * 移动端用户登录
//     * @param map
//     * @param httpSession
//     * @return
//     */
//    @PostMapping("/login")
//    public R<User> login(@RequestBody Map map,HttpSession httpSession){
//        log.info(map.toString());
//
//        // 获取手机号
//        String phone = map.get("phone").toString();
//
//        // 获取验证码
//        String sendCode = map.get("code").toString();
//
//        // 从Session中获取保存的验证码
//        Object sessionCode = httpSession.getAttribute(phone);
//
//        // 进行验证码的比对 (页面提交的验证码和Session中保存的验证码比对)
//        if(sessionCode!=null && sessionCode.equals(sendCode)){
//            // 如果能够比对成功，说明登录成功
//
//            // 判断当前手机号对应的用户是否为新用户，如果是新用户就自动完成注册
//            LambdaQueryWrapper<User> userLambdaQueryWrapper =  new LambdaQueryWrapper<>();
//            userLambdaQueryWrapper.eq(User::getPhone,phone);
//            User user = userService.getOne(userLambdaQueryWrapper);
//            if(user == null){
//                user = new User();
//                user.setPhone(phone);
//                user.setStatus(1);
//                userService.save(user);
//            }
//
//            httpSession.setAttribute("user",user.getId());
//            return R.success(user);
//
//        }
//
//
//
//
//        return R.error("登录失败");
//    }


    /**
     * 移动端用户登录测试方法，完成方法在上面
     * @param map
     * @param httpSession
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map,HttpSession httpSession){
        log.info(map.toString());

        // 获取手机号
        String phone = map.get("phone").toString();

        // 判断当前手机号对应的用户是否为新用户，如果是新用户就自动完成注册
        LambdaQueryWrapper<User> userLambdaQueryWrapper =  new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getPhone,phone);
        User user = userService.getOne(userLambdaQueryWrapper);
        if(user == null){
            user = new User();
            user.setPhone(phone);
            user.setStatus(1);
            userService.save(user);
        }

        httpSession.setAttribute("user",user.getId());
        return R.success(user);

    }

}
