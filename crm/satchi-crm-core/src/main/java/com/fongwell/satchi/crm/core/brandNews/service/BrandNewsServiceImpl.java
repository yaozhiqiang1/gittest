package com.fongwell.satchi.crm.core.brandNews.service;

import com.fongwell.satchi.crm.core.brandNews.domain.aggregate.BrandNews;
import com.fongwell.satchi.crm.core.brandNews.dto.BrandNewsData;
import com.fongwell.satchi.crm.core.brandNews.repository.BrandNewsRepository;
import com.fongwell.satchi.crm.core.common.AbstractWriteService;
import com.fongwell.satchi.crm.core.common.error.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * Created by roman on 18-3-24.
 */
@Service("brandNewsService")
public class BrandNewsServiceImpl extends AbstractWriteService<BrandNews,Long,BrandNewsData> implements BrandNewsService {

    public BrandNewsServiceImpl(){
        super(BrandNews.class);
    }

    @Autowired
    private BrandNewsRepository brandNewsRepository;

    @Override
    public void onCreate(BrandNewsData data) {
        create(data);
    }

    @Override
    public void onSort(Long id,int orderNumber) {
        BrandNews one = findOne(id, true);
        one.onSort(orderNumber);
        brandNewsRepository.save(one);
    }



    @Override
    public void onUpdate(long id,BrandNewsData data) {
        update(id,data);
    }

    @Override
    public void onDelete(long id) {
        BrandNews one = findOne(id,true);
        one.onDelete();
        brandNewsRepository.save(one);
    }

    @Override
    public void onEnable(Collection<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<BrandNews> all = brandNewsRepository.findAll(ids);
            for(BrandNews next : all){
                next.onEnable();
            }
            brandNewsRepository.save(all);
        }
    }

    @Override
    public void onDisable(Collection<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<BrandNews> all = brandNewsRepository.findAll(ids);
            for(BrandNews next : all){
                next.onDisable();
            }
            brandNewsRepository.save(all);
        }
    }


    public BrandNews findOne(long id,boolean check) {
        BrandNews news = brandNewsRepository.findOne(id);
        if(check && news == null){
            throw new DataNotFoundException("brandNews not found :"+id);
        }
        return news;
    }


    @Override
    protected JpaRepository<BrandNews, Long> getRepository() {
        return brandNewsRepository;
    }
}
