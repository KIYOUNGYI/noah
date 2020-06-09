package app.noah.adminapi;

import app.noah.domain.Pouch;
import app.noah.dto.PouchDto;
import app.noah.dto.PouchSearchCondition;
import app.noah.handler.ResultHandler;
import app.noah.repository.Pouch.PouchRepository;
import app.noah.repository.Pouch.PouchRepository_Old;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Api(value="캐스트 API", tags="PouchSimpleApiController")
@RestController
@RequiredArgsConstructor
public class PouchSimpleApiController
{
    private final PouchRepository pouchRepository;

    @ApiOperation(value="캐스트 목록 검색", response = PouchDto.class)
    @GetMapping("/api/v2/pouch")
    public ResponseEntity<?> getPouchList(PouchSearchCondition condition)
    {
        if(condition.getOffset()==null) condition.setOffset(0l);
        if(condition.getLimit()==null) condition.setLimit(10l);
        System.out.println(">> condition:"+condition.toString());
        Map<String,Object> result = pouchRepository.searchPageSimple(condition);
        return new ResultHandler().handle(result);
    }

    @ApiOperation(value="캐스트 상세", response = Object.class)
    @GetMapping("/api/v2/pouch/{id}")
    public ResponseEntity<?> updateCast(@PathVariable Long id)
    {
        Map<String,Object> result = pouchRepository.getPouchDetail(id);
        return new ResultHandler().handle(result);
    }

    

}
