package app.noah.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PouchCategoryDto
{
    private Long pouchCategoryId;
    private String pouchCategoryText;

    @QueryProjection
    public PouchCategoryDto(Long pouchCategoryId, String pouchCategoryText)
    {
        this.pouchCategoryId = pouchCategoryId;
        this.pouchCategoryText = pouchCategoryText;
    }
}
