package app.noah.repository.pouch.category;

import app.noah.dto.PouchCategoryRequestDto;
import app.noah.dto.PouchCategorySearchCondition;

import java.util.Map;

public interface PouchCategoryRepositoryCustom
{
    Map<String,Object> simpleSearchPouchCategory();
    Map<String,Object> complexSearchPouchCategory(PouchCategorySearchCondition condition);
    Map<String,Object> getSummary();
//    Map<String,Object> insertPouchCategory(PouchCategoryRequestDto dto);
}
