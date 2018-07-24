package com.fongwell.satchi.crm.core.category.service;

import com.fongwell.satchi.crm.core.category.domain.aggregate.TopCategory;
import com.fongwell.satchi.crm.core.category.dto.TopCategoryData;
import com.fongwell.satchi.crm.core.category.error.CategoryActiveException;
import com.fongwell.satchi.crm.core.category.repository.TopCategoryRepository;
import com.fongwell.satchi.crm.core.common.AbstractWriteService;
import com.fongwell.satchi.crm.core.common.State;
import com.fongwell.satchi.crm.core.common.error.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * Created by roman on 18-4-16.
 */
@Service("topCategoryService")
public class TopCategoryServiceImpl extends AbstractWriteService<TopCategory, Long, TopCategoryData> implements TopCategoryService {

    @Value("${topCategory.limit}")
    private Integer limit;

    @Autowired
    private TopCategoryRepository repository;

    public TopCategoryServiceImpl() {
        super(TopCategory.class);
    }

    @Override
    public void onCreate(TopCategoryData data) {
//        TopCategory category = repository.findByName(data.getName().trim());
//        if(category != null){
//            throw new DuplicateParameterException("TopCategoryData.name already exists :"+data.getName());
//        }
        create(data);
    }

    @Override
    public void onUpdate(Long id, TopCategoryData data) {
//        TopCategory category = repository.findByName(data.getName().trim());
//        if(category != null){
//            throw new DuplicateParameterException("TopCategoryData.name already exists :"+data.getName());
//        }
        update(id, data);
    }

    @Override
    public synchronized String onEnable(final Collection<Long> ids) {
        List<TopCategory> all = repository.findByState(State.enable);
        Set exists = new HashSet(all.size(), 2f);
        for (TopCategory next : all) {
            exists.add(next.getId());
        }
        exists.addAll(ids);
        if (exists.size() > limit) {
        	return "201";
 //           throw new CategoryActiveException("actives category More than the limit :" + limit);
        }

        List<TopCategory> enables = repository.findAll(new Specification<TopCategory>() {
            @Override
            public Predicate toPredicate(Root<TopCategory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList();
                predicates.add(root.<Long>get("id").in(ids));
                predicates.add(cb.equal(root.<Boolean>get("deleted"), false));
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        });
        for (TopCategory next : enables) {
            next.onEnable();
            repository.save(next);
        }
        return "200";
    }

    @Override
    public void onDisable(Collection<Long> ids) {
        List<TopCategory> all = repository.findAll(ids);
        for (TopCategory next : all) {
            next.onDisable();
            repository.save(next);
        }
    }

    @Override
    public void onDelete(Collection<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {

            List<TopCategory> all = repository.findAll(ids);
            for (TopCategory next : all) {
                repository.delete(next);
            }

        }
    }

    public void onSort(Long id, int orderNumber) {
        TopCategory one = findOne(id, true);
        one.onSort(orderNumber);
        repository.save(one);
    }

    public boolean deleteValidate(Collection<Long> parents) {
        if (!CollectionUtils.isEmpty(parents)) {
            return false;
        }
        List<TopCategory> all = repository.findByCategoryIdIn(parents);
        boolean validate = true;
        for (TopCategory next : all) {
            if (State.enable.equals(next.getState())) {
                validate = false;
                break;
            }
        }
        return validate;
    }

    public void deleteByParent(Collection<Long> parents) {
        if (!CollectionUtils.isEmpty(parents)) {
            List<TopCategory> all = repository.findByCategoryIdIn(parents);
            for (TopCategory next : all) {
                next.onDelete();
                repository.save(next);
            }
        }
    }

    private TopCategory findOne(Long id, boolean check) {
        TopCategory category = repository.findOne(id);
        if (category == null && check) {
            throw new DataNotFoundException("TopCategory not found :" + id);
        }
        return category;
    }

    @Override
    protected JpaRepository<TopCategory, Long> getRepository() {
        return repository;
    }
}
