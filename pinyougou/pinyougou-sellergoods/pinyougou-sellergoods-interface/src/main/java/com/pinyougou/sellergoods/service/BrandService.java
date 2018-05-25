package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;

import java.util.List;

/**
 * Date:2018/5/24
 * Author: yaozhiqiang
 * Desc:service接口层
 */
public interface BrandService {

    /**
     * 查询所有商品
     * @return 商品集合
     */
    public List<TbBrand> queryAll();

}
