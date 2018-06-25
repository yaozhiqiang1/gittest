package com.fongwell.satchi.crm.core.homePage.mobileHomePage.service;

import com.fongwell.satchi.crm.core.common.AbstractWriteService;
import com.fongwell.satchi.crm.core.common.State;
import com.fongwell.satchi.crm.core.common.error.DataNotFoundException;
import com.fongwell.satchi.crm.core.homePage.mobileHomePage.domain.aggregate.MobileHomePage;
import com.fongwell.satchi.crm.core.homePage.mobileHomePage.domain.dto.MobileHomePageData;
import com.fongwell.satchi.crm.core.homePage.mobileHomePage.repository.MobileHomePageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * Created by roman on 18-4-13.
 */
@Service("mobileHomePageService")
public class MobileHomePageServiceImpl extends AbstractWriteService<MobileHomePage,Long,MobileHomePageData> implements MobileHomePageService {

    @Autowired
    private MobileHomePageRepository repository;


    public MobileHomePageServiceImpl() {
        super(MobileHomePage.class);
    }

    @Override
    protected JpaRepository<MobileHomePage, Long> getRepository() {
        return repository;
    }

    @Override
    public void onCreate(MobileHomePageData data) {
        create(data);
    }

    @Override
    public void onUpdate(Long id, MobileHomePageData data) {
        update(id,data);
    }

    @Override
    public void onDisable(Collection<Long> ids) {
        if(!CollectionUtils.isEmpty(ids)){
            List<MobileHomePage> all = repository.findAll(ids);
            for(MobileHomePage next : all){
                if(State.enable.equals(next.getState())){
                    next.onDisable();
                    repository.save(next);
                }
            }
        }
    }

    @Override
    public void onEnable(Collection<Long> ids) {
        if(!CollectionUtils.isEmpty(ids)){
            List<MobileHomePage> all = repository.findAll(ids);
            for(MobileHomePage next : all){
                if(State.disable.equals(next.getState())){
                    next.onEnable();
                    repository.save(next);
                }
            }
        }
    }

    @Override
    public void onDelete(Collection<Long> ids) {
        if(!CollectionUtils.isEmpty(ids)){
            List<MobileHomePage> all = repository.findAll(ids);
            for(MobileHomePage next : all){
                next.onDelete();
                repository.save(next);
            }
        }

    }

    @Override
    public void onSort(Long id, int orderNumber) {
        MobileHomePage one = findOne(id, true);
        one.onSort(orderNumber);
        repository.save(one);
    }

    private MobileHomePage findOne(Long id,boolean check){
        MobileHomePage one = repository.findOne(id);
        if(one == null && check){
            throw new DataNotFoundException("MobileHomePage not found :"+id);
        }
        return one;
    }
}
