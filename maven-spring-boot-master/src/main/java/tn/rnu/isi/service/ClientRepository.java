package tn.rnu.isi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import tn.rnu.isi.model.Client;



public interface ClientRepository extends CrudRepository <Client, Long>{


    Client findByIdClient(Long idClient);


    List<Client> findAll();

    Client save (Client client);

    @Modifying
    @Query("update Client u set u.idClient = ?1")
    int updateIdClient(Long idClient);

    @Transactional
    @Modifying
    @Query("delete from Client u where u.idClient = ?1")
    void delete(Long idClient);

}