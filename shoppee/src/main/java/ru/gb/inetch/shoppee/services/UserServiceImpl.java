package ru.gb.inetch.shoppee.services;

import ru.gb.inetch.shoppee.repositories.RoleRepository;
import ru.gb.inetch.shoppee.repositories.UserRepositoryImpl;
import ru.gb.inetch.shoppee.entities.SystemUser;
import ru.gb.inetch.shoppee.entities.Role;
import ru.gb.inetch.shoppee.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
	private UserRepositoryImpl userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public void setUserRepository(UserRepositoryImpl userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Autowired
	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public User findByUserName(String userName) {
		return userRepository.getUser(userName);
	}

	@Override
	public User findById(Long id){
		return userRepository.getUser(id);
	}

	@Override
	@Transactional
	public void save(SystemUser systemUser) {
		System.out.println("user service, save systemUser");

		User user = new User();
		user.setUserName(systemUser.getUserName());
		user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
		user.setFirstName(systemUser.getFirstName());
		user.setLastName(systemUser.getLastName());
		user.setEmail(systemUser.getEmail());
		user.setRoles(Arrays.asList(roleRepository.getDefaultRole()));

		save(user);
	}

	@Override
	@Transactional
	public void save(User user) {
		System.out.println("user service, save user");

		if(user.getRoles().isEmpty()){
			user.setRoles(Arrays.asList(roleRepository.getDefaultRole()));
		}

		userRepository.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.getUser(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	@Override
	public List<User> getAllUsers(){
		return (List<User>) userRepository.getAll();
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public boolean isGranted(User user, Collection<String> roles){
		StringBuilder sb = new StringBuilder();
		for(String s: roles){
			if(sb.length() > 0){
				sb.append(",");
			}else{
				sb.append("(");
			}
			sb.append("'").append(s).append("'");
		}
		if(sb.length() > 0){
			sb.append(")");
		}else{
			return false;
		}

		return userRepository.isOnUser(user, sb.toString());
	}
}
