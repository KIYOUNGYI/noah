package app.noah.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PouchCategoryDetailDto
{
    private Long sortKey;
    private String categoryName;
    private Integer pouchCount;
    private Boolean isDisplay;

    @QueryProjection
    public PouchCategoryDetailDto(Long sortKey, String categoryName,
                                  Integer pouchCount, Boolean isDisplay)
    {
        this.sortKey = sortKey;
        this.categoryName = categoryName;
        this.pouchCount = pouchCount;
        this.isDisplay = isDisplay;
    }
}
