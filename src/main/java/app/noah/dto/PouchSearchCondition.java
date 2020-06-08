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

//<li role="presentation"><a role="menuitem" tabindex="-1" href="/pick/list_editer_pick?sort=11<?=$where?>">정렬순</a></li>
//<li role="presentation"><a role="menuitem" tabindex="-1" href="/pick/list_editer_pick?sort=1<?=$where?>">최신등록순</a></li>
//<li role="presentation"><a role="menuitem" tabindex="-1" href="/pick/list_editer_pick?sort=2<?=$where?>">이전등록순</a></li>
//<li role="presentation"><a role="menuitem" tabindex="-1" href="/pick/list_editer_pick?sort=3<?=$where?>">전시순</a></li>
//<li role="presentation"><a role="menuitem" tabindex="-1" href="/pick/list_editer_pick?sort=4<?=$where?>">비전시순</a></li>
//<li role="presentation"><a role="menuitem" tabindex="-1" href="/pick/list_editer_pick?sort=5<?=$where?>">카테고리순</a></li>
//<li role="presentation"><a role="menuitem" tabindex="-1" href="/pick/list_editer_pick?sort=6<?=$where?>">카테고리역순</a></li>
//<li role="presentation"><a role="menuitem" tabindex="-1" href="/pick/list_editer_pick?sort=7<?=$where?>">추천많은순</a></li>
//<li role="presentation"><a role="menuitem" tabindex="-1" href="/pick/list_editer_pick?sort=8<?=$where?>">추천적은순</a></li>
//<li role="presentation"><a role="menuitem" tabindex="-1" href="/pick/list_editer_pick?sort=9<?=$where?>">댓글많은순</a></li>
//<li role="presentation"><a role="menuitem" tabindex="-1" href="/pick/list_editer_pick?sort=10<?=$where?>">댓글적은순</a></li>
