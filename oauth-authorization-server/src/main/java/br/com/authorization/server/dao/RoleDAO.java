package br.com.authorization.server.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.authorization.server.entity.Role;

@Repository
@Transactional
public interface RoleDAO extends PagingAndSortingRepository<Role, Integer> {

}
