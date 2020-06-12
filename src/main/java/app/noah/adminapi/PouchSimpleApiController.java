package app.noah.adminapi;

import app.noah.dto.PouchCategorySearchCondition;
import app.noah.dto.PouchDto;
import app.noah.dto.PouchRequestDto;
import app.noah.dto.PouchSearchCondition;
import app.noah.handler.ResultHandler;
import app.noah.repository.pouch.PouchRepository;
import app.noah.repository.pouch.category.PouchCategoryRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value="캐스트 API", tags="PouchSimpleApiController")
@RestController
@RequiredArgsConstructor
public class PouchSimpleApiController
{
    private final PouchRepository pouchRepository;

    private final PouchCategoryRepository pouchCategoryRepository;

    @ApiOperation(value="캐스트 목록 검색", response = PouchDto.class)
    @GetMapping("/admin-api/v2/pouch")
    public ResponseEntity<?> getPouchList(PouchSearchCondition condition)
    {
        if(condition.getOffset()==null) condition.setOffset(0l);
        if(condition.getLimit()==null) condition.setLimit(10l);
        System.out.println(">> condition:"+condition.toString());
        Map<String,Object> result = pouchRepository.searchPageSimple(condition);
        return new ResultHandler().handle(result);
    }

    @ApiOperation(value="캐스트 상세정보", response = Object.class)
    @GetMapping("/admin-api/v2/pouch/{id}")
    public ResponseEntity<?> getPouchDetail(@PathVariable Long id)
    {
        Map<String,Object> result = pouchRepository.getPouchDetail(id);
        return new ResultHandler().handle(result);
    }

    @ApiOperation(value="캐스트 등록 or 수정", response = Object.class)
    @PostMapping("/admin-api/v2/pouch")
    public ResponseEntity<?> insertPouch(@RequestBody PouchRequestDto pouchRequestDto)
    {
        Map<String,Object> result = pouchRepository.insertOrUpdatePouch(pouchRequestDto);
        return new ResultHandler().handle(result);
    }

    @ApiOperation(value="캐스트 목록 요약",response = Object.class)
    @GetMapping("/admin-api/v2/pouch/status")
    public ResponseEntity<?> pouchStatus()
    {
        Map<String, Object> result = pouchRepository.getSummary();
        return new ResultHandler().handle(result);
    }

    /********************** 캐스트 카테고리 목록 ***************************/

    @ApiOperation(value="캐스트 카테고리 목록 상태 요약",response = Object.class)
    @GetMapping("/admin-api/v2/pouch/category/status")
    public ResponseEntity<?> categoryStatus()
    {
        Map<String,Object> result = pouchCategoryRepository.getSummary();
        return new ResultHandler().handle(result);
    }

    @ApiOperation(value="캐스트 카테고리 목록 검색",response = Object.class)
    @GetMapping("/admin-api/v2/pouch/category")
    public ResponseEntity<?> getCategoryList(PouchCategorySearchCondition condition)
    {
        if(condition.getOffset()==null) condition.setOffset(0l);
        if(condition.getLimit()==null) condition.setLimit(10l);
        Map<String,Object> result = pouchCategoryRepository.complexSearchPouchCategory(condition);
        return new ResultHandler().handle(result);
    }

    @ApiOperation(value="캐스트 카테고리 목록", response = Object.class)
    @GetMapping("/admin-api/v2/pouch/category/simple")
    public ResponseEntity<?> getSimplePouchCategoryList()
    {
        Map<String, Object> result = pouchCategoryRepository.simpleSearchPouchCategory();
        return new ResultHandler().handle(result);
    }


}
