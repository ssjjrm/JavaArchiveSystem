package mapper;

import org.apache.ibatis.annotations.*;
import pojo.Archive;
import pojo.User;

import java.util.List;

public interface ArchiveMapper {

    @Select("select archiveId, creator, timestamp, description, fileName from archives")
    List<Archive> list();

    @Select("select archiveId, creator, timestamp, description, fileName from archives where archiveId = #{archiveId}")
    Archive getArchiveById(@Param("archiveId") Integer archiveId);

    @Select("select count(*) from archives")
    Integer getArchiveCount();


    // 自增主键：插入时不写archiveId，数据库自动生成
    @Insert("insert into archives (creator, timestamp, description, filename) " +
            "values (#{creator}, #{timestamp}, #{description}, #{fileName})")
    void insertArchive(Archive archive);

}
