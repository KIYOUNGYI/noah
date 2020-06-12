package app.noah.dto;

import lombok.Data;

@Data
public class PouchCategorySearchCondition
{
    private Long offset;
    private Long limit;
    private Integer sort;
}
