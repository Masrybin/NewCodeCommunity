package com.bin.controller;

import com.bin.bean.ActivationConsequence;
import com.bin.bean.User;
import com.bin.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String getRegisterHtml() {
        return "site/register";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "site/login";
    }

    @PostMapping("/register")
    public String register(User user, Model model) {
        Map<String, String> mapInfo = userService.judgeUserRegisterInfo(user);
        if (mapInfo == null | mapInfo.isEmpty()) {
            model.addAttribute("successInfo", "已经向您邮箱发送了一封邮件，请您确认注册账号信息！");
            model.addAttribute("target", "/index");
            return "/site/operate-result";
        } else {
            model.addAttribute("accountInfo", mapInfo.get("accountInfo"));
            model.addAttribute("passwordInfo", mapInfo.get("passwordInfo"));
            model.addAttribute("emailInfo", mapInfo.get("emailInfo"));
            model.addAttribute("user", user);
            return "site/register";
        }
    }

    @GetMapping("/activation/{id}/{activationCode}")
    public String activateAccount(@PathVariable("id") Integer id, @PathVariable("activationCode") String activationCode, Model model) {
        Enum<ActivationConsequence> activationConsequence = userService.activation(id, activationCode);
        if (ActivationConsequence.ACTIVATION_SUCCESS.equals(activationConsequence)) {
            model.addAttribute("successInfo", "您的账号已经激活成功,可以正常使用了！");
            model.addAttribute("target", "/login");
        } else if (ActivationConsequence.ACTIVATION_REPEAT.equals(activationConsequence)) {
            model.addAttribute("successInfo", "您的账号已经激活过了,无需重复激活！");
            model.addAttribute("target", "/login");
        } else {
            model.addAttribute("successInfo", "您的账号信息未激活成功，请您查询是否注册成功！");
            model.addAttribute("target", "/register");
        }
        System.out.println(userService.selectUserById(id));
        return "/site/operate-result";
    }
}
