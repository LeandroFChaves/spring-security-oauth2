package br.com.authorization.server.dao;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.authorization.server.entity.User;

@Repository
@Transactional
public interface UserDAO extends PagingAndSortingRepository<User, String> {

	public Page<User> findByUserName(String username, Pageable pageable);
	
	public User findByUserName(String username);

}
