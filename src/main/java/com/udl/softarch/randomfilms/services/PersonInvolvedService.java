package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.PersonInvolved;

/**
 * Created by adrian on 28/4/15.
 */
public interface PersonInvolvedService {

    PersonInvolved savedPersonInvoved(PersonInvolved personInvolved);
    void removePersonInvoved(PersonInvolved personInvolved);
}
