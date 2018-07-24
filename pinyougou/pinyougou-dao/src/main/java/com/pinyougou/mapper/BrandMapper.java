package com.pinyougou.mapper;

import com.pinyougou.pojo.TbBrand;

import java.util.List;

/**
 * Date:2018/5/24
 * Author: yaozhiqiang
 * Desc:品牌dao层
 */
public interface BrandMapper {

    /**
     * 查询所有品牌
     * @return 品牌集合
     */
    List<TbBrand> queryAll();

}
