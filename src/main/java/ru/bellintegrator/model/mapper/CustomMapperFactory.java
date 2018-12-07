package ru.bellintegrator.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;
import ru.bellintegrator.model.Office;
import ru.bellintegrator.model.Organization;
import ru.bellintegrator.model.User;
import ru.bellintegrator.view.OfficeView;
import ru.bellintegrator.view.OrganizationView;
import ru.bellintegrator.view.UserView;

/**
 * Фабрика для создания MapperFactory.
 * При необходимости можно добавить кастомные мапперы
 */
@Service
public class CustomMapperFactory implements FactoryBean<MapperFactory> {
    @Override
    public MapperFactory getObject() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .constructorResolverStrategy(null)
                .build();
        mapperFactory.classMap(Organization.class, OrganizationView.class)
                .field("active", "isActive")
                .byDefault()
                .register();
        mapperFactory.classMap(Office.class, OfficeView.class)
                .field("organization.id", "orgId")
                .field("active", "isActive")
                .byDefault()
                .register();
        mapperFactory.classMap(User.class, UserView.class)
                .field("office.id", "officeId")
                .field("doc.docDate", "docDate")
                .field("doc.docNumber", "docNumber")
                .field("doc.docType.name", "docName")
                .field("doc.docType.code", "docCode")
                .field("country.name", "citizenshipName")
                .field("country.code", "citizenshipCode")
                .field("identified", "isIdentified")
                .byDefault()
                .register();

        return mapperFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
