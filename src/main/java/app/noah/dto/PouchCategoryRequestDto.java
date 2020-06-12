package app.noah.dto;

import app.noah.domain.PouchCategory;
import app.noah.utils.LocalDateTimeUtil;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PouchCategoryRequestDto
{
    private String pouchCategoryTitle;
    private Long sortKey;
    private Boolean isDisplay;

    //생성
    public PouchCategoryRequestDto(String pouchCategoryTitle,Long sortKey)
    {
        this.pouchCategoryTitle = pouchCategoryTitle;
        this.sortKey = sortKey;
    }

    public PouchCategoryRequestDto(String pouchCategoryTitle,Long sortKey,Boolean isDisplay)
    {
        this.pouchCategoryTitle = pouchCategoryTitle;
        this.sortKey = sortKey;
        this.isDisplay = isDisplay;
    }

}
