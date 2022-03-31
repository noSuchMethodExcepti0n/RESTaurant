package js.RESTaurant.server.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import js.RESTaurant.server.model.User;
import js.RESTaurant.server.repository.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(username);
		if(!user.isPresent()) {
			user = userRepository.findByUsername(username);
		} 
		
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username ));
		return user.map(MyUserDetails :: new).get();
	}

}
