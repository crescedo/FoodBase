package ch.zhaw.fswd.backend.foodbase.entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    
}
