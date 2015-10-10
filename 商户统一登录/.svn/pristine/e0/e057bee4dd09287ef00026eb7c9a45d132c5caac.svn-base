package com.cplatform.mall.storeuc.cas.persondir;

import com.cplatform.mall.storeuc.service.DataAccessService;
import org.apache.commons.lang.Validate;
import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.BasePersonAttributeDao;
import org.jasig.services.persondir.support.CaseInsensitiveNamedPersonImpl;
import org.jasig.services.persondir.support.NamedPersonImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.*;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-28 上午9:53
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class ExtendedPersonAttributeDao extends BasePersonAttributeDao {

    @Autowired
    DataAccessService dataAccessService;

    @Override
    public IPersonAttributes getPerson(String uid) {

       Validate.notNull(uid, "uid may not be null.");

        try {
            Map<String, String> map = dataAccessService.findByCode(uid);

            final Map<String, List<Object>> multivaluedQueryResult = new LinkedHashMap<String, List<Object>>(map.size());
            for (final Map.Entry<String, String> seedEntry : map.entrySet()) {
                final String seedName = seedEntry.getKey();
                final Object seedValue = seedEntry.getValue();
                multivaluedQueryResult.put(seedName, Collections.singletonList(seedValue));
            }
            multivaluedQueryResult.remove("PWD");
            IPersonAttributes person = new CaseInsensitiveNamedPersonImpl(uid, multivaluedQueryResult);

            if (person == null) {
                return null;
            }

            //Force set the name of the returned IPersonAttributes if it isn't provided in the return object
            if (person.getName() == null) {
                person = new NamedPersonImpl(uid, person.getAttributes());
            }

            return person;

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    @Override
    public Set<IPersonAttributes> getPeople(Map<String, Object> query) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<IPersonAttributes> getPeopleWithMultivaluedAttributes(Map<String, List<Object>> query) {
        return null;
    }

    @Override
    public Set<String> getPossibleUserAttributeNames() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<String> getAvailableQueryAttributes() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
