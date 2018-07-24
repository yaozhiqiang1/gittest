package com.fongwell.satchi.crm.core.support.ddd;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by docker on 11/21/17.
 */
@Component("aggregateFactory")
public class ModelMapperAggregateFactory implements AggregateFactory {

    @Resource(name = "modelMapper")
    private ModelMapper mapper;


    @Override
    public Object createAggregate(Object source, Class clazz) {

        Object target = mapper.map(source, clazz);

        return target;
    }

    @Override
    public void mergeAggregate(Object source, Object destination) {
        mapper.map(source, destination);
    }
}
