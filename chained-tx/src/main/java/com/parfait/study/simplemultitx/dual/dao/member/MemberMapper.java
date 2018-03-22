package com.parfait.study.simplemultitx.dual.dao.member;

import com.parfait.study.simplemultitx.dual.model.member.Member;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

public interface MemberMapper {

    @Select("SELECT * FROM member")
    List<Member> findAll();

    @Select("SELECT * FROM member WHERE id = #{id}")
    Member findOne(@Param("id") Long id);

    @Insert("INSERT INTO member(name, age) VALUES(#{name}, #{age})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyColumn = "id", keyProperty = "id", before = false, resultType = Long.class)
    int insert(Member member);

    @Delete("TRUNCATE TABLE member")
    void truncate();
}
