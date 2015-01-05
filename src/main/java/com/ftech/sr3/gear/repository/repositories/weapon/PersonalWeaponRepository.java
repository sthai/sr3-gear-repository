package com.ftech.sr3.gear.repository.repositories.weapon;

import com.ftech.sr3.gear.repository.domain.weapon.PersonalWeapon;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by John_Archer on 27/12/2014.
 */
@RepositoryRestResource(collectionResourceRel = "personalWeapon", path="personalWeapon")
public interface PersonalWeaponRepository extends PagingAndSortingRepository<PersonalWeapon, Long> {
}
