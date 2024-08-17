package bondio.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface loadUserByUsername {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
