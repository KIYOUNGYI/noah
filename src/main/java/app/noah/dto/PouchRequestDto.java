package app.noah.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * insert 시 사용
 */
@Data
public class PouchRequestDto
{
    private Long idPouch;
    private Long idRegister;
    private Boolean isDisplay;
    private String title;
    private Long idPouchCategory;
    private String content;
    private String openDate;
    private ImageContentDto imageContentDto;
    private List<Long> products = new ArrayList<>();
}
