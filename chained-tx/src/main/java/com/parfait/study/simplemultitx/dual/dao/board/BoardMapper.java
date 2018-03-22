package com.parfait.study.simplemultitx.dual.dao.board;

import com.parfait.study.simplemultitx.dual.model.board.Board;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

public interface BoardMapper {

    @Select("SELECT * FROM member")
    List<Board> findAll();

    @Select("SELECT * FROM board WHERE id = #{id}")
    Board findOne(@Param("id") Long id);

    @Insert("INSERT INTO board(title, content) VALUES(#{title}, #{content})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyColumn = "id", keyProperty = "id", before = false, resultType = Long.class)
    int insert(Board board);

    @Delete("TRUNCATE TABLE board")
    void truncate();
}
