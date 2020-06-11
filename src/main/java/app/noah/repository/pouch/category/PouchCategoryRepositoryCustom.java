package app.noah.repository.pouch.category;

import java.util.Map;

public interface PouchCategoryRepositoryCustom
{
    Map<String,Object> simpleSearchPouchCategory();
    Map<String,Object> complexSearchPouchCategory();
    Map<String,Object> getSummary();
}
