package app.noah.dto;

import lombok.Data;

@Data
public class PouchSearchCondition
{
    //제목, 내용, 작성날짜
    private String title;
    private String content;
    private String startDate;
    private String endDate;

    private Long offset;
    private Long limit;
    private Integer sort;

}

