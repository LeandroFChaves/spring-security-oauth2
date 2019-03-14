package br.com.authorization.server.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.authorization.server.entity.Permission;

@Repository
@Transactional
public interface PermissionDAO extends PagingAndSortingRepository<Permission, String> {

}
