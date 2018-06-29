package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.base.validate.Validate;
import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.api.authentication.wx.WxCustomerContext;
import com.fongwell.satchi.crm.core.common.error.DataNotFoundException;
import com.fongwell.satchi.crm.core.customer.domain.aggregate.Customer;
import com.fongwell.satchi.crm.core.customer.dto.CustomerAuthenticationDetails;
import com.fongwell.satchi.crm.core.customer.dto.CustomerRegisterRequest;
import com.fongwell.satchi.crm.core.customer.query.dto.CustomerDetails;
import com.fongwell.satchi.crm.core.customer.query.mapper.WxCustomerQueryMapper;
import com.fongwell.satchi.crm.core.customer.query.repository.CustomerQueryRepository;
import com.fongwell.satchi.crm.core.customer.service.CustomerRegistrationService;

import com.fongwell.satchi.crm.core.store.query.AdminStoreQueryMapper;
import com.fongwell.satchi.crm.wx.user.binding.WxUserBindingService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.execchain.TunnelRefusedException;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by docker on 2/25/18.
 */
@RestController
@RequestMapping("/api/wx")
public class WxCustomerApi {

   @Autowired
  private CustomerRegistrationService customerRegistrationService;

  @Autowired
  private WxCustomerQueryMapper wxCustomerQueryMapper;

  @Autowired
  private AdminStoreQueryMapper adminStoreQueryMapper;

  @Resource(name = "wxUserBindingService")
  private WxUserBindingService wxUserBindingService;


  @Resource(name = "customerQueryRepository")
  private CustomerQueryRepository customerQueryRepository;

  @Resource
  private HttpSession session;

    /**
     * 获取微信用户的基本信息
     * @return
     */
  @GetMapping("/profile")
  public Payload profile() {
    return new Payload(wxCustomerQueryMapper.queryProfile(WxCustomerContext.getUser().getCustomerId()));
  }


  @GetMapping("/verificationCode")
  public Payload verificationCode(@RequestParam String codePassword){
    String code = (String) session.getAttribute("code");
    if (code == null){
      System.out.println("验证码是否已经存入session" + code);
      return new Payload(false);
    }

    Map map = new HashMap(1, 2f);
    map.put("verificationCode", code.equals(codePassword));
    //System.out.println(user.toString());
    return new Payload(map);

  }

  @GetMapping("/info")
  public Payload info() {

    Map map = new HashMap(1, 2f);
    CustomerAuthenticationDetails user = WxCustomerContext.getUser();
    map.put("loggedin", user != null);
    //System.out.println(user.toString());
    return new Payload(map);

  }

  /**
   * 登录并绑定用户
   * @param mobile 手机号
   */
  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public Payload login(@RequestParam String mobile) {


    CustomerDetails customer = customerQueryRepository.queryCustomerDetails(mobile);
    if (customer == null) {
      throw new DataNotFoundException("customer not found: " + mobile);
    } else {
      //绑定用户
      wxUserBindingService.bind(WxCustomerContext.getWxId(), customer.getId());
    }
    return new Payload("true");
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public void unbind() {
    if (WxCustomerContext.getWxId() != null) {
      wxUserBindingService.unbind(WxCustomerContext.getWxId());
    }

  }

  /**
   * 注册并绑定用户
   * @param request 注册信息
   * @param result  结果集
   */
  @PostMapping("/register")
  @ResponseStatus(HttpStatus.OK)
  @Validate
  public void register(@Valid @RequestBody CustomerRegisterRequest request, BindingResult result) {
    //注册保存
    Customer newCustomer = customerRegistrationService.register(request);
    if (WxCustomerContext.getWxId() != null) {
      //微信和会员绑定
      wxUserBindingService.bind(WxCustomerContext.getWxId(), newCustomer.getId());
    }
  }

  /**
   * 短信验证
   */
  @PostMapping("/sendSms")
  public void sendSms(@RequestBody String mobile,HttpServletRequest request){

    if(mobile != null) try {
      String code = RandomStringUtils.randomNumeric(4);
        System.out.println("code +++" + code);
        session = request.getSession();
        session.setAttribute("code",code);
        //调用短信工具发送短信
     // SmsUtils.sendMsg(mobile, "yaozhiqiang", "SMS_137830227", "{\"code\":\"" + code + "\"}");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @PostMapping("/bindingStore")
  public Payload bindingStore(@RequestParam Long storeId){
      //检查用户登录信息是不是用户会员id还是微信id
    String mobile = WxCustomerContext.getUser().getUsername();
    System.out.println("用户登录信息id" + mobile);
    if (StringUtils.isNotBlank(mobile)){
      Customer customer = customerQueryRepository.queryCustomerStoreId(mobile,storeId);
      if (customer != null){
        Map map = adminStoreQueryMapper.get(storeId);
        String name = (String) map.get("name");
          return new Payload(name);
      }
      customerQueryRepository.updateCustomer(storeId,mobile);
      return new Payload("绑定成功");
    }
    return new Payload("null");
  }

//  @PostMapping("/test")
//  public void test(@RequestParam String mobile ,@RequestParam Long storeId){ Customer customer = customerQueryRepository.queryCustomerStoreId(mobile,storeId);
//    System.out.println("测试"+customer.getMobile()+"    "+customer.getStoreId());
//      Map map = adminStoreQueryMapper.get(storeId);
//      String name = (String) map.get("name");
//     customerQueryRepository.updateCustomer(storeId,mobile);
//     System.out.println("name===" + name);*/
//  }

}
