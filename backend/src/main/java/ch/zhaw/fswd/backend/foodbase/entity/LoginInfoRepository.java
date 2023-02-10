package ch.zhaw.fswd.backend.foodbase.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginInfoRepository extends JpaRepository<LoginInfo, Long> {

@Query(value="SELECT l.id from LoginInfo as l WHERE l.loginName=?1")
Optional< Long> getLoginInfoIdByUserName(String username);

@Query(value="SELECT l FROM LoginInfo as l WHERE l.loginName=?1")
Optional<LoginInfo> getLoginInfoByUserName(String username);
}