package app.noah.repository.pouch;

import app.noah.dto.PouchDto;
import app.noah.dto.PouchRequestDto;
import app.noah.dto.PouchSearchCondition;

import java.util.List;
import java.util.Map;

public interface PouchRepositoryCustom
{
    List<PouchDto> search(PouchSearchCondition condition);
    Map<String,Object> searchPageSimple(PouchSearchCondition condition);
    Map<String,Object> searchPageComplex(PouchSearchCondition condition);
    Map<String,Object> getPouchDetail(Long idPouch);
    Map<String,Object> insertOrUpdatePouch(PouchRequestDto pouchRequestDto);
}
