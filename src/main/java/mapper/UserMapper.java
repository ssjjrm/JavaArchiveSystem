package mapper;

import org.apache.ibatis.annotations.*;
import pojo.*;
import java.util.List;


@Mapper
public interface UserMapper {

    @Select("select * from users")
    List<User> list();


    @Select("select * from users where name = #{name} and password = #{password}")
    User getByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Delete("delete from users where name = #{name}")
    void deleteByName(String name);

    @Insert("insert into users(name,password,role) values(#{name},#{password},#{role})")
    void insert(User user);

    @Update("update users set password=#{password} where id=#{id}")
    boolean updatePassword(AbstractUser user);


    @Update("UPDATE users SET password = #{password}, role = #{role} WHERE id = #{id}")
    int update(AbstractUser user);

}
