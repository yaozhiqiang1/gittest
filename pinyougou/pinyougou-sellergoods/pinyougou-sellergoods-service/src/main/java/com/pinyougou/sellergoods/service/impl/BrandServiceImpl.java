package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Date:2018/5/24
 * Author: yaozhiqiang
 * Desc:service实现层
 */
//将该服务注册到本地ioc容器，注册到dubbo的注册中心
@Service(interfaceClass = BrandService.class)
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询所有商品
     * @return  商品集合
     */
    @Override
    public List<TbBrand> queryAll() {
        List<TbBrand> tbBrands = brandMapper.queryAll();
        System.out.println("进了service---"+tbBrands);
        return tbBrands;
    }
}
