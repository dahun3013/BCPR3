package STT.Mapper;


import STT.domain.Document_Trans;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SttMapper{
    @Select("select count(*) from document_trans")
    int getCountDocument_Trans();

    @Select("select * from document_trans")
    List<Document_Trans> getDocument_TransList();

    @Select("select * from document_Trans where email = #{email}")
    List<Document_Trans> getDocument_Trans(@Param("email") String email);

    @Insert("insert into document_Trans(email,trans_date,kind,input,output) "
            +"values(#{email},#{trans_date},#{kind},#{input,jdbcType=VARCHAR},#{output})")
    int insertMedia_TransContent(
            @Param("email") String email,
            @Param("trans_date") LocalDateTime trans_date,
            @Param("kind") String kind,
            @Param("input") String input,
            @Param("output") String output);

    @Delete("delete from document_Trans where email = #{email} and document_no = #{document_no}")
    int deleteMedia_TransContent(
            @Param("email") String email,
            @Param("media_no") int document_no);
}
