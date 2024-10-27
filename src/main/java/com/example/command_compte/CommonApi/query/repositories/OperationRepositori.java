package com.example.command_compte.CommonApi.query.repositories;
import com.example.command_compte.CommonApi.query.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional

public interface OperationRepositori extends JpaRepository<Operation,String> {
}
