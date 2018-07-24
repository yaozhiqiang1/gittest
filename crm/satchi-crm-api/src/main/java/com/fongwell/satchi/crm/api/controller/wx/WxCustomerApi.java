package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.api.authentication.wx.WxCustomerContext;
import com.fongwell.satchi.crm.api.controller.common.SmsUtils;
import com.fongwell.satchi.crm.core.common.error.DataNotFoundException;
import com.fongwell.satchi.crm.core.customer.domain.aggregate.Customer;
import com.fongwell.satchi.crm.core.customer.dto.CustomerAuthenticationDetails;
import com.fongwell.satchi.crm.core.customer.dto.CustomerRegisterRequest;
import com.fongwell.satchi.crm.core.customer.query.dto.CustomerDetails;
import com.fongwell.satchi.crm.core.customer.query.mapper.WxCustomerQueryMapper;
import com.fongwell.satchi.crm.core.customer.query.mapper.WxUserMapper;
import com.fongwell.satchi.crm.core.customer.query.repository.CustomerQueryRepository;
import com.fongwell.satchi.crm.core.customer.service.CustomerRegistrationService;
import com.fongwell.satchi.crm.core.store.query.AdminStoreQueryMapper;
import com.fongwell.satchi.crm.wx.account.vo.Result;
import com.fongwell.satchi.crm.wx.user.binding.WxUserBindingService;
import com.foxinmy.weixin4j.util.StringUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private WxUserMapper wxUserMapper;

    private HttpSession session;

    /**
     * 获取微信用户的基本信息
     *
     * @return
     */
    @GetMapping("/profile")
    public Payload profile() {
        return new Payload(wxCustomerQueryMapper.queryProfile(WxCustomerContext.getUser().getCustomerId()));
    }

    @GetMapping("/verificationCode")
    public Payload verificationCode(@RequestParam String codePassword, @RequestParam String mobile) {
        try {
            String code = (String) session.getAttribute("code");
            String mobile1 = (String) session.getAttribute("mobile");
            if (code == null || mobile == null) {
                return new Payload(false);
            }

            if (mobile1.equals(mobile) && code.equals(codePassword)) {
                Map map = new HashMap(1, 2f);
                CustomerDetails customer = customerQueryRepository.queryCustomerDetails(mobile);
                if (customer != null) {
                    return new Payload("login");
                }
                map.put("verificationCode", true);
                return new Payload(map);
            }
            return new Payload(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Payload(false);

    }


    @GetMapping("/info")
    public Payload info() {

        Map map = new HashMap(1, 2f);
        CustomerAuthenticationDetails user = WxCustomerContext.getUser();


        map.put("loggedin", user != null);
        return new Payload(map);

    }

    /**
     * 登录并绑定用户
     *
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
     *
     * @param request 注册信息
     */
/*  @PostMapping("/register")
  @ResponseStatus(HttpStatus.OK)
  @Validate
  public void register(@Valid @RequestBody CustomerRegisterRequest request, BindingResult result) {
    System.out.println("走了register");
    //注册保存
    Customer newCustomer = customerRegistrationService.register(request);
    if (WxCustomerContext.getWxId() != null) {
      //微信和会员绑定
      wxUserBindingService.bind(WxCustomerContext.getWxId(), newCustomer.getId());
    }
  }*/
    @PostMapping("/register")
    public Payload register(@RequestBody CustomerRegisterRequest request) {
        if (request != null) {
            //注册保存
            Customer newCustomer = customerRegistrationService.register(request);
            if (WxCustomerContext.getWxId() != null) {
                //微信和会员绑定
                wxUserBindingService.bind(WxCustomerContext.getWxId(), newCustomer.getId());
            }
            return new Payload(true);
        }
        return new Payload(false);
    }


    /**
     * 短信验证
     */
    @PostMapping("/sendSms")
    public Result sendSms(@RequestBody CustomerRegisterRequest customerRegisterRequest, HttpServletRequest request) {

        session = request.getSession();
        session.setMaxInactiveInterval(20);
        String mobile = customerRegisterRequest.getMobile();

        if (!StringUtil.isBlank(mobile)) {
            String code = RandomStringUtils.randomNumeric(4);
            System.out.println("code +++" + code);
            //session = request.getSession();
            session.setAttribute("mobile", mobile);
            session.setAttribute("code", code);
            //调用短信工具发送短信
            SmsUtils.sendMsg(mobile, "yaozhiqiang", "SMS_137830227", "{\"code\":\"" + code + "\"}");
            return Result.ok("验证成功");
        }
        return Result.fail("验证失败");
    }

    /**
     * 重新绑定
     *
     * @param storeId
     */
    @GetMapping("/againBindingStore")
    public void againBindingStore(@RequestParam Long storeId) {
        long customerId = WxCustomerContext.getUser().getCustomerId();
//        long customerId = 3905997475673344L;
        customerQueryRepository.updateCustomer(customerId, storeId);
    }

    /**
     * 扫码绑定萌门店
     *
     * @return
     */
    @GetMapping("/bindingStore")
    public Payload bindingStore() {
        //检查用户登录信息是不是用户会员id还是微信id
        long customerId = WxCustomerContext.getUser().getCustomerId();
//      long customerId = 3905997475673344L;
        long storeId = 0;
        try {
            storeId = customerQueryRepository.queryCustomerStore(customerId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Payload(false);
        }
        if (storeId != 0) {
            Map map = adminStoreQueryMapper.get(storeId);
            String name = (String) map.get("name");
            return new Payload(name);
        }
        return new Payload(false);
    }

    //  @PostMapping("/test")
//  public void test(@RequestParam String mobile ,@RequestParam Long storeId){ Customer customer = customerQueryRepository.queryCustomerStoreId(mobile,storeId);
//    System.out.println("测试"+customer.getMobile()+"    "+customer.getStoreId());
//      Map map = adminStoreQueryMapper.get(storeId);
//      String name = (String) map.get("name");
//     customerQueryRepository.updateCustomer(storeId,mobile);
//     System.out.println("name===" + name);
//  }
    @GetMapping("/getWxPortrait")
    public Payload getWxPortrait() {

//        String wxId = WxCustomerContext.getWxId();
        String wxId = "ooBmhjrhtQX449mCdSivjC45NOag";
        if (StringUtils.isNotBlank(wxId)) {
            String portraitUrl = wxUserMapper.getWxPortrait(wxId);
            return new Payload(portraitUrl);
        }
        return new Payload(false);
    }

}
