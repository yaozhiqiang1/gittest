package com.fongwell.satchi.crm.core.homePage.pcHomePage.service;

import com.fongwell.satchi.crm.core.common.AbstractWriteService;
import com.fongwell.satchi.crm.core.common.error.DataNotFoundException;
import com.fongwell.satchi.crm.core.homePage.pcHomePage.domain.aggregate.PcHomePage;
import com.fongwell.satchi.crm.core.homePage.pcHomePage.domain.dto.PcHomePageData;
import com.fongwell.satchi.crm.core.homePage.pcHomePage.repository.PcHomePageRepository;
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
@Service("homePageService")
public class PcHomePageServiceImpl extends AbstractWriteService<PcHomePage,Long,PcHomePageData> implements PcHomePageService {


    public PcHomePageServiceImpl() {
        super(PcHomePage.class);
    }

    @Autowired
    private PcHomePageRepository repository;


    @Override
    public void onCreate(PcHomePageData data) {
        create(data);
    }

    @Override
    public void onUpdate(Long id, PcHomePageData data) {
        update(id,data);
    }

    @Override
    public void onDelete(Collection<Long> ids) {
        if(!CollectionUtils.isEmpty(ids)){
            List<PcHomePage> all = repository.findAll(ids);
            for(PcHomePage next : all){
                next.onDelete();
                repository.save(next);
            }
        }
    }

    @Override
    public void onDisable(Collection<Long> ids) {
        if(!CollectionUtils.isEmpty(ids)){
            List<PcHomePage> all = repository.findAll(ids);
            for(PcHomePage next : all){
                next.onDisable();
                repository.save(next);
            }
        }
    }

    @Override
    public void onEnable(Collection<Long> ids) {
        if(!CollectionUtils.isEmpty(ids)){
            List<PcHomePage> all = repository.findAll(ids);
            for(PcHomePage next : all){
                next.onEnable();
                repository.save(next);
            }
        }
    }

    @Override
    public void onSort(Long id, int orderNumber) {
        PcHomePage one = findOne(id, true);
        one.onSort(orderNumber);
        repository.save(one);
    }

    public PcHomePage findOne(long id, boolean check) {
        PcHomePage one = repository.findOne(id);
        if (one == null && check) {
            throw new DataNotFoundException("HomePage not found :" + id);
        }
        return one;
    }


    @Override
    protected JpaRepository<PcHomePage, Long> getRepository() {
        return repository;
    }
}


