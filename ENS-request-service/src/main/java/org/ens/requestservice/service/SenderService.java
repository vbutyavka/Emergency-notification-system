package org.ens.requestservice.service;

import org.ens.requestservice.entity.Sender;
import org.ens.requestservice.repository.SenderRepository;
import org.ens.requestservice.service.crud.CrudService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SenderService extends CrudService<Sender, SenderRepository>  implements UserDetailsService {
    public SenderService(SenderRepository repository) {
        super(repository);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        log.info("Trying to authenticate sender");
        Sender sender = repository.findByLogin(login);
        if (sender == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(sender.getLogin(), sender.getPword(), Collections.emptyList());
    }

    public Long getIdByLogin(String login) {
        log.info("Trying to getIdByLogin({})", login);
        return repository.getIdByLogin(login);
    }
}
