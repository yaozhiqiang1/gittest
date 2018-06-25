package com.fongwell.satchi.crm.core.support.jpa.hibernate.converter;

import org.modelmapper.internal.typetools.TypeResolver;
import org.modelmapper.internal.util.Types;
import org.modelmapper.spi.*;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by docker on 11/27/17.
 */
public class HibernateCollectionConverter implements ConditionalConverter<Collection, Collection> {


    private final ConcurrentHashMap<CacheKey, CollectionConverter> converterCache = new ConcurrentHashMap<>();


    public Collection convert(MappingContext<Collection, Collection> context) {
        Collection source = context.getSource();
        if (source == null) {
            return null;
        }

//        if (source.isEmpty()) {
//            return new LinkedList();
//        }


        PropertyInfo destinationProperty = context.getMapping().getDestinationProperties().iterator().next();
        Object parent = context.getParent().getDestination();


        CacheKey cacheKey = new CacheKey(parent.getClass(), destinationProperty.getName());
        CollectionConverter cachedConverter = converterCache.get(cacheKey);

        if (cachedConverter == null) {
            Class elementType = this.getElementType(context);
            OneToMany oneToMany = destinationProperty.getAnnotation(OneToMany.class);
            ManyToMany manyToMany = destinationProperty.getAnnotation(ManyToMany.class);
            if (oneToMany != null || manyToMany != null) {


                String mappedBy = oneToMany != null ? oneToMany.mappedBy() : manyToMany.mappedBy();

                try {

                    Field parentIdField = null;

                    if (mappedBy.length() > 0) {

                        parentIdField = findAnnotatedIdField(parent.getClass());
                        if (parentIdField != null) {
                            parentIdField.setAccessible(true);
                        }
                    }


                    Field field = parent.getClass().getDeclaredField(destinationProperty.getName());
                    field.setAccessible(true);

                    cachedConverter = new HibernateCollectionConverterImpl(elementType, field, parentIdField, mappedBy);
                    converterCache.put(cacheKey, cachedConverter);

                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            } else {
                cachedConverter = new DefaultCollectionConverter(elementType);
                converterCache.put(cacheKey, cachedConverter);
            }
        }

        return cachedConverter.convert(context);


    }

    private Field findAnnotatedIdField(Class clazz) {

        Class temp = clazz;

        Field field = null;

        while (temp != null) {
            Field[] fields = temp.getDeclaredFields();
            for (Field f : fields) {
                if (f.getAnnotation(Id.class) != null) {
                    if (field != null) {
                        if (!f.getName().equals(field.getName())) {
                            throw new IllegalStateException("More than one field has @Id annotation, does not support yet!");
                        }
                    } else {
                        field = f;
                    }


                }
            }

            temp = temp.getSuperclass();
        }


        return field;

    }


    protected Class<?> getElementType(MappingContext<Collection, Collection> context) {
        Mapping mapping = context.getMapping();
        if (mapping instanceof PropertyMapping) {
            PropertyInfo destInfo = mapping.getLastDestinationProperty();
            Class elementType = TypeResolver.resolveRawArgument(destInfo.getGenericType(), destInfo.getInitialType());
            return elementType == TypeResolver.Unknown.class ? Object.class : elementType;
        } else {
            return context.getGenericDestinationType() instanceof ParameterizedType ? Types.rawTypeFor(((ParameterizedType) context.getGenericDestinationType()).getActualTypeArguments()[0]) : Object.class;
        }
    }

    @Override
    public MatchResult match(Class<?> class1, Class<?> class2) {
        return Collection.class.isAssignableFrom(class1) && Collection.class.isAssignableFrom(class2) ? MatchResult.FULL : MatchResult.NONE;
    }


    private static interface CollectionConverter {

        Collection convert(MappingContext<Collection, Collection> context);
    }

    private static class CacheKey implements Serializable {

        private Class parentType;
        private String fieldName;

        public CacheKey(Class parentType, String fieldName) {
            this.parentType = parentType;
            this.fieldName = fieldName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CacheKey cacheKey = (CacheKey) o;

            if (parentType != null ? !parentType.equals(cacheKey.parentType) : cacheKey.parentType != null)
                return false;
            return fieldName != null ? fieldName.equals(cacheKey.fieldName) : cacheKey.fieldName == null;

        }

        @Override
        public int hashCode() {
            int result = parentType != null ? parentType.hashCode() : 0;
            result = 31 * result + (fieldName != null ? fieldName.hashCode() : 0);
            return result;
        }
    }

    private static final class DefaultCollectionConverter implements CollectionConverter {

        private Class elementType;

        public DefaultCollectionConverter(Class elementType) {
            this.elementType = elementType;
        }


        @Override
        public Collection convert(MappingContext<Collection, Collection> context) {
            Collection source = context.getSource();
            Collection result = new ArrayList(source.size());
            for (Object sourceElement : source) {
                if (sourceElement != null) {
                    MappingContext elementContext = context.create(sourceElement, elementType);
                    Object element = context.getMappingEngine().map(elementContext);
                    result.add(element);
                }
            }


            return result;

        }
    }

    private static final class HibernateCollectionConverterImpl implements CollectionConverter {
        private final Class elementType;
        private final Field field;
        private final Field parentIdField;
        private final Field mappedByField;

        public HibernateCollectionConverterImpl(Class elementType, Field field, Field parentIdField, String mappedBy) {
            this.field = field;
            this.elementType = elementType;
            this.parentIdField = parentIdField;

            if (mappedBy.length() > 0) {
                try {
                    mappedByField = elementType.getDeclaredField(mappedBy);
                    mappedByField.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            } else {
                mappedByField = null;
            }
        }

        @Override
        public Collection convert(MappingContext<Collection, Collection> context) {
            Collection source = context.getSource();
            Object parent = context.getParent().getDestination();


            try {
                Collection currentCollection = (Collection) field.get(parent);

                if (currentCollection == null) {
                    currentCollection = new LinkedList();
                }
                Object parentId = null;
                if (parentIdField != null) {
                    parentId = parentIdField.get(parent);
                }

                Map<Integer, Object> currentMap = new HashMap<>(currentCollection.size(), 2f);
                for (Object item : currentCollection) {
                    currentMap.put(item.hashCode(), item);
                }
                currentCollection.clear();

                for (Object item : source) {
                    Object current = currentMap.get(item.hashCode());
                    if (current == null) {
                        MappingContext elementContext = context.create(item, elementType);

                        Object mappedItem = context.getMappingEngine().map(elementContext);
                        if (mappedByField != null && parentId != null) {
                            mappedByField.set(mappedItem, parentId);
                        }
                        currentCollection.add(mappedItem);
                    } else {
                        MappingContext elementContext = context.create(item, current);
                        context.getMappingEngine().map(elementContext);
                        currentCollection.add(current);

                    }
                }


                return currentCollection;

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
