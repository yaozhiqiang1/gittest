package com.fongwell.satchi.crm.core.common;

import com.fongwell.satchi.crm.core.brandNews.domain.aggregate.BrandNews;
import com.fongwell.satchi.crm.core.support.ddd.Sorter;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by roman on 18-3-24.
 */
public class Splitter {
    private List<? extends Sorter> sorters;

    private LinkedHashMap<Long,Sorter> active;

    private LinkedHashMap<Long,Sorter> inactive;

    Splitter(List<? extends Sorter> sorters) {
        this.sorters = sorters;
    }

    public static Splitter build(List<? extends Sorter> sorters){
        Splitter splitter = new Splitter(sorters);
        splitter.split();
        return splitter;
    }

    private void split(){
        if(!CollectionUtils.isEmpty(sorters)){
            active = new LinkedHashMap<>();
            inactive = new LinkedHashMap<>();
            for(Sorter next : sorters){
                if(next.isActive()){
                    active.put(next.getId(),next);
                }else{
                    inactive.put(next.getId(),next);
                }
            }
        }
    }

    public LinkedHashMap<Long, ? extends Sorter> getActive() {
        return active;
    }

    public LinkedHashMap<Long, ? extends Sorter> getInactive() {
        return inactive;
    }
}
